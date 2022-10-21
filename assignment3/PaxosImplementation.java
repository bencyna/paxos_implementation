import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.Socket;

public class PaxosImplementation extends Thread {
    private MemberThread[] members;

    PaxosImplementation(MemberThread[] members) throws Exception {
        this.members = members;

    }

    public void consensusReached(int acceptedId, String value) throws Exception {
        System.out
        .println("Consensus Reached Woohoo!!! ID: " + acceptedId + " and value: " + value);
    }
      
    // send message through method to all members
    public void newProposal(String value) throws Exception {
        int id;
        // if value contains id, it means this proposal is coming from a initially accepted prepare message
        if (value.contains("id")) {
            id = Integer.parseInt(value.split("id: ")[1]);
            value = value.split("id: ")[0];

            System.out.println(value + " sending out voting proposal id: " + id);

            for (MemberThread memberThread : members) {
                String acceptorRes = memberThread.member.AcceptProposal(value, id);
                if (acceptorRes.equals("fail")) {
                    continue;
                }
                int acceptedId = Integer.parseInt(acceptorRes.replaceAll("[^\\d.]", ""));
                for (MemberThread memberThread2 : members) {
                    if (!memberThread2.getName().equals(memberThread.member.getName())) {

                        if (memberThread2.member.majorityAccepted(acceptedId)) {
                            consensusReached(acceptedId, value);
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
            MemberThread proposer = null;

            for (MemberThread memberThread : members) {
                if (memberThread.member.getName().equals(value)) {
                    proposer = memberThread;
                    break;
                }
            }
            System.out.println(proposer.getName() + " sending out initial prepare. id: " + id);
            for (MemberThread memberThread : members) {
                if (!memberThread.member.getName().equals(proposer.getName())) {
                    String acceptorRes = memberThread.member.Accept(value, id);
                    if (proposer != null && !acceptorRes.equals("fail")) {
                        // System.out.println(memberThread.member.getName() + " accepted prep: " + acceptorRes);
                        proposer.member.AcceptedPrep(acceptorRes);
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
            for (MemberThread memberThread : members) {
                memberThread.member.Prepare();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
