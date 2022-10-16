import java.io.DataOutputStream;
import java.net.Socket;

public class Member {
    Boolean wantsPresidency;
    int chancesOfResponse;
    int responseDelay;
    String name;
    int majority;
    int maxIDAccepted;

    Member(Boolean wantsPresidency, int chancesOfResponse, String name, int majority) {
        this.wantsPresidency = wantsPresidency;
        this.chancesOfResponse = chancesOfResponse;
        responseDelay = 0;
        this.name = name;
        this.majority = majority;
        maxIDAccepted = 0;
    }

    public void Propose() throws Exception {
        Socket s2 = new Socket("localhost", 5432);
        DataOutputStream dout2=new DataOutputStream(s2.getOutputStream());  
        dout2.writeUTF(name);  
    }

    public String Accept(String value, int ID) {
        System.out.println("accept: " + value + " ID: " + ID);
        return "Accept";
    }



}