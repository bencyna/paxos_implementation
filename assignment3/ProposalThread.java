public class ProposalThread extends Thread {
    Boolean consensus;
    Member member;

    ProposalThread(Member member) {
        this.member = member;
        consensus = false;
    }

    public void setConsensus() {
        this.consensus = true;
    }

    @Override
    public void run() {
        try {
            while (!consensus) {
                Thread.sleep(5000);
                if (!consensus) {
                    member.Prepare();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
