public class MemberThread extends Thread {
    public Member member;

    Boolean wantsPresidency;
    int chancesOfResponse;
    int responseDelay;
    int majority;
    Boolean instantRes;
    String type;
    int citizenCount;

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
    }

    @Override
    public void run() {
        if (type.equals("M1")) {
            member = new M1(majority, instantRes);
        } else if (type.equals("M2")) {
            member = new M2(majority, instantRes);
        } else if (type.equals("M3")) {
            member = new M3(majority, instantRes);
        } else if (type.equals("M4") || type.equals("citizen")) {
            member = new M4(chancesOfResponse, responseDelay, majority, citizenCount, instantRes);
        } else {
            System.out.println("error couldn't create " + type + " member");
        }

    }
}
