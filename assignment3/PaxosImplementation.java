public class PaxosImplementation extends Thread{
    // Functions of paxos

// PUT a lock on proposer count changes 


    // proposers
    // accepters 
    // learners: can be queried for what they thin

    // nodes can take multiple roles, even all of them 

    // m,ust know how many acceptors a majority is 
    // (two majoriies will always overlap in at least one node
    // must be persistent: can't forget what they accepted)

    // Reaching a single consensus
    // once consensus is reached, no other consensus can occur

    //Functions:
    // Propose(prepare ID to all acceptors, timestamp) // no ID is used twice

    // Acceptor() {
        // check if promised to ignore requests with timestamp lower than x
        // if yes, will ignore
        // if no, return promise y

    // Acceptor recieves a ACCept-request message for IDp() {
        // DId it promise to ignore requests with ID lower than this? 
        // if yes: ignore
        // if no 
        // check have I ever accepted anything?
        // if yes, reply with promise IDp, accepted id = IDa and send it to proposer. 
        // else reply with ACCEPT IDp, Value, and send it to all learners
        //  
    // }

    // If a proposer gets a majority of PROMISE messages for a specific IDp, it sends ACCEPT-Request IDp, value, to a majority (all) acceptors
    // If the proposer has already accepted a value from promises
    // it picks the value with the highest IDa that it got
    // else proposer chooses value

    //IF a majority of acceptors accept IDp, value, consensus is reached, consensus is and will always be on value (not id)

    // properser and learners get ACCEPT messages for IDp, value:
    // If a proposer/learner gets majority of accept for a specific IDp, they know consensus has been reached on the value

    // 3 milestones that always happen
    // 1. Majority of accepts promise, no ID <IDp can make it through
    // 2. If a majority of acceptors accept IDp, value, consensus is reached. Consensus is and alwas will be on value
    // 3. if a proposer/learner gets majority of accept for a specific IDp, they know that consensus has been reached on value (not IDp)

    private Member[] members;   

    PaxosImplementation(Member[] members) throws Exception {
        this.members = members;

    }

    // lock
    public void newProposal(String value) {
        // send message through method to all members
        int id = 0;
        System.out.println("new proposal being sent to all");
        Member proposer = null; 

        for (Member member : members) {
            if (member.getName().equals(value)) {
                proposer = member;
                break;
            }
        }

        for (Member member : members) {
            String acceptorRes = member.Accept(value, id);
            // need to send acceptorRes back to proposer!
            if (proposer != null) {
                proposer.AcceptedProposal(acceptorRes);
            }
        }
    }

    @Override
    public void run() {
        try {
        System.out.println("running paxos...");
        // will be while consensus not reached
        while (true) {
            Thread.sleep(3000);
            for (Member member : members) {
                member.Propose();
            }
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
