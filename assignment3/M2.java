public class M2 extends Member {

    public M2() {
        super(true, 50);
    }

    public void toCafe() {
        responseDelay = 10;
    }
    public void fromCafe() {
        responseDelay = 0;
    }
}
