import java.util.Random;

public class AlternateMemberResponses extends Thread {
    Member M2;
    Member M3;
    Boolean m2AtCafe;
    Boolean m3InWoods;
    Boolean end;

    AlternateMemberResponses(Member M2, Member M3, Boolean instantRes) {
        this.M2 = M2;
        this.M3 = M3;
        m2AtCafe = false;
        m3InWoods = false;
        end = instantRes;
    }
    
    public void end() {
        end = true;
    }

    @Override
    public void run() {
        try {
        for (;;) {  
            if (end) {
                return;
            } 
            Thread.sleep(1000);
            Random random = new Random();
            int millis = (random.nextInt(10)+1) * 100;
            int millis2 = (random.nextInt(10)+1) * 100;

            Thread.sleep(millis);
            if (m2AtCafe) {
                M2.fromCafe();
                m2AtCafe = false;
            }
            else {
                M2.toCafe();
                m2AtCafe = true;
            }
            Thread.sleep(millis2);

            if (m3InWoods) {
                M3.fromWoods();
                m3InWoods = false;
            }
            else {
                M3.toWoods();
                m3InWoods = true;
            }
        }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

