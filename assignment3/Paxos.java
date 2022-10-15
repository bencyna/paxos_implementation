import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;


public class Paxos {


    private static void defaultRun() {
        Member M1 = new M1(5);
        Member M2 = new M2(5);
        Member M3 = new M3(5);

        Random random = new Random();
        Member M4 = new M4(random.nextInt(), 5);
        Member M5 = new M4(random.nextInt(), 5);
        Member M6 = new M4(random.nextInt(), 5);
        Member M7 = new M4(random.nextInt(), 5);
        Member M8 = new M4(random.nextInt(), 5);
        Member M9 = new M4(random.nextInt(), 5);
        

    }

    public static void main(String[] args) {
        try {
            int numOfMembers;
            System.out.println("How many members do you want? Type \"default\" for the 9 classic members stipulated in assignment 3");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
     
            // Reading data using readLine
            String input = reader.readLine();
     
            if (!input.matches("\\d+")) {
                if (input.equals("default")) {
                    defaultRun();
                }
                else {
                System.out.println("Please enter digits only e.g. 21");
                main(args);
                }
                return;
            }
            numOfMembers = Integer.parseInt(input);

            for (int i = 0; i < numOfMembers; i++) {
                System.out.println("What is the response time of the next member");
                BufferedReader member = new BufferedReader(
                    new InputStreamReader(System.in));
         
                // Reading data using readLine
                String memberInput = member.readLine();

                if (memberInput.equals("immediate")) {
                    // create an immediate response time member
                }
                else if (memberInput.equals("medium")) {
                    // create an medium response time member
                }
                else if (memberInput.equals("late")) {
                    // create an late response time member
                }
                else if (memberInput.equals("never")) {
                    // create an never response time member
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }
}