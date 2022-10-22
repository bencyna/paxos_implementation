public class M2 extends Member {

    public M2(int majority, Boolean instantRes) {
        super(true, 50, 50, "Member M2", majority, instantRes);
    }

    public void toCafe() {
        responseDelay = 0;
        chancesOfResponse = 50;

    }
    public void fromCafe() {
        responseDelay = 10000;
        chancesOfResponse = 100;
    }
}
