public class M3 extends Member {
    public M3(int majority, Boolean instantRes) {
        super(true, 100, 500, "Member M3", majority, instantRes);
    }

    public void fromWoods() {
        chancesOfResponse = 100;
    }

    public void toWoods() {
        chancesOfResponse = 0;
    }
}
