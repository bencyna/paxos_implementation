public class M3 extends Member {
    public M3(int majority, Boolean instantRes) {
        super(true, 100, 100, "Member M3", majority, instantRes);
    }

    public void fromWoods() {
        chancesOfResponse = 100;
        // System.out.println("m3 from woods");
    }

    public void toWoods() {
        // System.out.println("m3 to woods");
        chancesOfResponse = 0;
    }
}
