import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class PaxosImplementation extends Thread {
    private Member[] members;

    PaxosImplementation(Member[] members) throws Exception {
        this.members = members;

    }

    // lock
    public void newProposal(String value) throws Exception {
        // send message through method to all members
        int id;
        if (value.contains("id")) {
            id = Integer.parseInt(value.split("id: ")[1]);
            value = value.split("id: ")[0];

            for (Member member : members) {
                String acceptorRes = member.AcceptProposal(value, id);
                if (acceptorRes.equals("fail")) {
                    continue;
                }

                int acceptedId = Integer.parseInt(acceptorRes.replaceAll("[^\\d.]", ""));
                for (Member member2 : members) {
                    if (!member2.getName().equals(member.getName())) {
                        if (member2.majorityAccepted(acceptedId)) {
                            System.out
                                    .println("Consensus Reached Woohoo!!! ID: " + acceptedId + " and value: " + value);
                            return;
                        }
                    }
                }
            }
        } else {
            synchronized (this) {
                BufferedReader currentID = new BufferedReader(new FileReader("currentID.txt"));
                id = Integer.parseInt(currentID.readLine()) + 1;
                FileWriter f2 = new FileWriter("currentID.txt", false);
                f2.write(Integer.toString(id));
                f2.close();
                currentID.close();
            }
            Member proposer = null;

            for (Member member : members) {
                if (member.getName().equals(value)) {
                    proposer = member;
                    break;
                }
            }

            for (Member member : members) {
                if (!member.getName().equals(proposer.getName())) {
                    String acceptorRes = member.Accept(value, id);
                    // need to send acceptorRes back to proposer!
                    if (proposer != null && !acceptorRes.equals("fail")) {
                        // System.out.println(member.getName() + " accepted prep: " + acceptorRes);
                        proposer.AcceptedPrep(acceptorRes);
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("running paxos...");
            // will be while consensus not reached
            for (Member member : members) {
                member.Prepare();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
