public class Member {
    Boolean wantsPresidency;
    int chancesOfResponse;
    int responseDelay;
    String name;
    int majority;
    int maxIDAccepted;

    Member(Boolean wantsPresidency, int chancesOfResponse, String name, int majority) {
        this.wantsPresidency = wantsPresidency;
        this.chancesOfResponse = chancesOfResponse;
        responseDelay = 0;
        this.name = name;
        this.majority = majority;
        maxIDAccepted = 0;
    }

    public void Propose() {

    }

    public String Accept(String value, int ID) {
        return "";
    }



}