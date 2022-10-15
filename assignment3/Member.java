public class Member {
    Boolean wantsPresidency;
    int chancesOfResponse;
    int responseDelay;

    Member(Boolean wantsPresidency, int chancesOfResponse) {
        this.wantsPresidency = wantsPresidency;
        this.chancesOfResponse = chancesOfResponse;
        responseDelay = 0;
    }
}
