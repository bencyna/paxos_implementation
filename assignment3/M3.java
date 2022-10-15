public class M3 extends Member {
    public M3() {
        super(true, 75);
    }

    public void fromWoods() {
        chancesOfResponse = 100;
    }

    public void toWoods() {
        chancesOfResponse = 0;
    }
}
