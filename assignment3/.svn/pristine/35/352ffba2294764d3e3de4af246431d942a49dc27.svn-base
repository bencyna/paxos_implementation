import java.util.Random;

public class M4 extends Member {
    public M4(int chanceOfResponse, int responseDelay, int majority, int citizenCount, Boolean instantRes) {
        super(false, chanceOfResponse, responseDelay, "Citizen" + citizenCount, majority, instantRes);
    }

    int generateResponseDelay() {
        Random rand = new Random(); 
        return rand.nextInt(10) * 100;
    }
}
