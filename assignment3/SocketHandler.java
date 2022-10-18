import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler extends Thread {
    Socket socket;
    PaxosImplementation paxos;
    
    public SocketHandler(Socket socket, PaxosImplementation paxos) {
        this.socket = socket;
        this.paxos = paxos;
    }

    @Override
    public void run() {
        try {
            DataInputStream din = new DataInputStream(socket.getInputStream());

            String value = "";
            value = din.readUTF();

            System.out.println("handler about to send a new proposal! with value: " + value);
            paxos.newProposal(value);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}