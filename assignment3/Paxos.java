import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Paxos {

    public static void main(String[] args) {
        try {
            int numOfMembers;
            System.out.println("How many members do you want?");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
     
            // Reading data using readLine
            String name = reader.readLine();
     
            // Printing the read line
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }
}