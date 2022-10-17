import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketHandler extends Thread {
    Socket socket;
    PaxosImplementation paxos;
    ServerSocket ss;
    
    public SocketHandler(Socket socket, PaxosImplementation paxos, ServerSocket ss) {
        this.socket = socket;
        this.paxos = paxos;
        this.ss = ss;
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
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
