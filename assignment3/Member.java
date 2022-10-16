import java.io.DataOutputStream;
import java.net.Socket;

public class Member {
    Boolean wantsPresidency;
    int chancesOfResponse;
    int responseDelay;
    String name;
    int majority;
    int maxIDAccepted;
    Boolean acceptedPrevious;
    int acceptedID;

    Member(Boolean wantsPresidency, int chancesOfResponse, String name, int majority) {
        this.wantsPresidency = wantsPresidency;
        this.chancesOfResponse = chancesOfResponse;
        responseDelay = 0;
        this.name = name;
        this.majority = majority;
        maxIDAccepted = 0;
        acceptedPrevious = false;
    }

    public void Propose() throws Exception {
        System.out.println("propose executed");

        if (!name.contains("M1")) {
            System.out.println("not m1: " + name);
            return;
        }
        Socket s2 = new Socket("localhost", 5432);
        DataOutputStream dout2=new DataOutputStream(s2.getOutputStream());  
        dout2.writeUTF(name);  
        s2.close();
    }

    public String Accept(String value, int ID) {
        if (maxIDAccepted > ID) {
            return "fail";
        }
        if (acceptedPrevious) {
            this.maxIDAccepted = ID;
            return "Accept " + ID + " accepted id = " + acceptedID;
        }
        System.out.println("accept: " + value + " ID: " + ID);
        this.maxIDAccepted = ID;
        acceptedPrevious = true;
        return "Accept " + ID +", " + value;
    }



}