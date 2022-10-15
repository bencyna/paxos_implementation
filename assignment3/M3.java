public class M3 extends Member {
    public M3(int majority) {
        super(true, 75, "Member M3", majority);
    }

    public void fromWoods() {
        chancesOfResponse = 100;
    }

    public void toWoods() {
        chancesOfResponse = 0;
    }
}
