public class M2 extends Member {

    public M2(int majority) {
        super(true, 50, "Member M2", majority);
    }

    public void toCafe() {
        responseDelay = 10;
    }
    public void fromCafe() {
        responseDelay = 0;
    }
}
