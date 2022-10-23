public class M2 extends Member {

    public M2(int majority, Boolean instantRes) {
        super(true, 50, 2000, "Member M2", majority, instantRes);
    }

    public void toCafe() {
        responseDelay = 0;
        chancesOfResponse = 50;
        System.out.println("m2 to cafe");

    }
    public void fromCafe() {
        responseDelay = 2000;
        chancesOfResponse = 100;
        System.out.println("m2 from cafe");

    }
}
