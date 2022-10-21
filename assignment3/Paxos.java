import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Paxos {

    private static void defaultRun(Boolean instantRes) throws Exception {
        // these values are not neccessarily having an effect

        MemberThread M1 = new MemberThread("M1", true, 100, 0, 5, instantRes, 0);
        M1.start();
        MemberThread M2 = new MemberThread("M2", true, 100, 0, 5, instantRes, 0);
        M2.start();
        MemberThread M3 = new MemberThread("M2", true, 100, 0, 5, instantRes, 0);
        M3.start();
        Random random = new Random();

        MemberThread M4 = new MemberThread("citizen", false, 100, random.nextInt(300), 5, instantRes, 1);
        M4.start();
        MemberThread M5 = new MemberThread("citizen", false, 100, random.nextInt(300), 5, instantRes, 2);
        M5.start();
        MemberThread M6 = new MemberThread("citizen", false, 100, random.nextInt(300), 5, instantRes, 3);
        M6.start();
        MemberThread M7 = new MemberThread("citizen", false, 100, random.nextInt(300), 5, instantRes, 4);
        M7.start();
        MemberThread M8 = new MemberThread("citizen", false, 100, random.nextInt(300), 5, instantRes, 5);
        M8.start();

        MemberThread M9 = new MemberThread("citizen", false, 100, random.nextInt(300), 5, instantRes, 6);
        M9.start();
        MemberThread[] members = { M1, M2, M3, M4, M5, M6, M7, M8, M9 };

        Thread.sleep(500);

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
            System.out.println(
                    "How many members do you want? Type \"default\" for the 9 classic members stipulated in assignment 3");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            String input = reader.readLine();
            System.out.println("input: " + input);
            if (!input.matches("\\d+")) {
                if (input.equals("default")) {
                    System.out.println(
                            "Do you want all responses to be instant (y)? Alternativley, type N for the members to match the assignment 3 description");
                    String instantResInput = reader.readLine();
                    if (instantResInput.equals("Y") || instantResInput.equals("y") || instantResInput.equals("yes")
                            || instantResInput.equals("Yes")) {
                        defaultRun(true);
                    } else {
                        defaultRun(false);
                    }
                } else {
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
                } else if (memberInput.equals("medium")) {
                    // create an medium response time member
                } else if (memberInput.equals("late")) {
                    // create an late response time member
                } else if (memberInput.equals("never")) {
                    // create an never response time member
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}