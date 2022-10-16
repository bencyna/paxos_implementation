import java.io.DataInputStream;
import java.net.Socket;
import java.nio.channels.MembershipKey;

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

            paxos.newProposal(value);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
