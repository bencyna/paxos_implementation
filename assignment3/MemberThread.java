public class MemberThread extends Thread {
    public Member member;
    Boolean wantsPresidency;
    int chancesOfResponse;
    int responseDelay;
    int majority;
    Boolean instantRes;
    String type;
    int citizenCount;
    Boolean consensus;
    ProposalThread proposalThread;

    MemberThread(String type, Boolean wantsPresidency,
            int chancesOfResponse,
            int responseDelay,
            int majority,
            Boolean instantRes,
            int citizenCount) {
        this.wantsPresidency = wantsPresidency;
        this.chancesOfResponse = chancesOfResponse;
        this.responseDelay = responseDelay;
        this.majority = majority;
        this.instantRes = instantRes;
        this.type = type;
        this.citizenCount = citizenCount;
        consensus = false;
    }

    public void setConsensus() {
        if (this.proposalThread != null)
        {
            this.proposalThread.setConsensus();
        }
    }


    @Override
    public void run() {
        try {
        if (type.equals("M1")) {
            member = new M1(majority, instantRes);
            if (!instantRes) {
                ProposalThread proposalThread = new ProposalThread(member);
                this.proposalThread = proposalThread;
                proposalThread.start();
            }
        } else if (type.equals("M2")) {
            member = new M2(majority, instantRes);
            if (!instantRes) {
                ProposalThread proposalThread = new ProposalThread(member);
                this.proposalThread = proposalThread;
                proposalThread.start();
            }
        } else if (type.equals("M3")) {
            member = new M3(majority, instantRes);
            if (!instantRes) {
                ProposalThread proposalThread = new ProposalThread(member);
                this.proposalThread = proposalThread;
                proposalThread.start();
            }
        } else if (type.equals("M4") || type.equals("citizen")) {
            member = new M4(chancesOfResponse, responseDelay, majority, citizenCount, instantRes);
        } else {
            System.out.println("error couldn't create " + type + " member");
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    }
}
