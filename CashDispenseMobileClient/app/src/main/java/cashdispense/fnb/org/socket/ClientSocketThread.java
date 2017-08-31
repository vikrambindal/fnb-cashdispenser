package cashdispense.fnb.org.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientSocketThread implements Runnable {

    @Override
    public void run() {
        try {
            InetAddress serverAddr = InetAddress.getByName(ClientSocket.SERVER_IP);
            Socket socket = new Socket(serverAddr, ClientSocket.SERVERPORT);
            ClientSocket.setSocket(socket);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
