import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Paxos {


    private static void defaultRun(Boolean instantRes) throws Exception {
        // these values are not neccessarily having an effect
        MemberThread M1 = new MemberThread("M1", true, 100, 0, "Member M1", 4, instantRes, 0);
        MemberThread M2 = new MemberThread("M2", true, 100, 0, "Member M2", 4, instantRes, 0);
        MemberThread M3 = new MemberThread("M2", true, 100, 0, "Member M2", 4, instantRes, 0);

        Random random = new Random();

        MemberThread M4 = new MemberThread("citizen", false, 100, random.nextInt(300), 4, instantRes, 1);
        MemberThread M5 = new MemberThread("citizen", false, 100, random.nextInt(300), 4, instantRes, 2);
        MemberThread M6 = new MemberThread("citizen", false, 100, random.nextInt(300), 4, instantRes, 3);
        MemberThread M7 = new MemberThread("citizen", false, 100, random.nextInt(300), 4, instantRes, 4);
        MemberThread M8 = new MemberThread("citizen", false, 100, random.nextInt(300), 4, instantRes, 5);

        MemberThread M9 = new MemberThread("citizen", false, 100, random.nextInt(300), 4, instantRes, 6);

        Member[] members = {M1, M2, M3, M4, M5, M6, M7, M8, M9};

        PaxosImplementation runPaxos = new PaxosImplementation(members);
        runPaxos.start();

        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(5432);

        for (;;) {
            Socket memberSocket = null;
            memberSocket = serverSocket.accept();
            Runnable socketHandler = new SocketHandler(memberSocket, runPaxos);
            new Thread(socketHandler).start();
        }
    }

    public static void main(String[] args) {
        try {
            int numOfMembers;
            System.out.println("How many members do you want? Type \"default\" for the 9 classic members stipulated in assignment 3");

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
     
            // Reading data using readLine
            String input = reader.readLine();
            System.out.println("input: " + input);
            if (!input.matches("\\d+")) {
                if (input.equals("default")) {
                    System.out.println("Do you want all responses to be instant (y)? Alternativley, type N for the members to match the assignment 3 description");
                    String instantResInput = reader.readLine();
                    if (instantResInput.equals("Y") || instantResInput.equals("y") || instantResInput.equals("yes") || instantResInput.equals("Yes")) {
                        defaultRun(true);
                    }
                    else {
                        defaultRun(false);
                    }
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