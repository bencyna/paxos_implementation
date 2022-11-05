public class Propose extends Thread {
    Member member;
    int timeout;
    Boolean done;

    Propose(Member member) {
        this.member = member;
        timeout = 5;
        done = false;
    }

    public void setResponseTrue() {
        done = true;
    }


    @Override
    public void run() {
        try {
            while (timeout > 0)
            {
                Thread.sleep(1000);
                timeout--;
            }
            if (!done) {
                member.Prepare();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
