package cashdispense.fnb.org.socket;

import java.net.Socket;

public class ClientSocket {

    public static final int SERVERPORT = 3333;
    public static final String SERVER_IP = "10.0.2.2";
    private static Socket socket;

    public static void setSocket(Socket _socket){
        ClientSocket.socket=_socket;
    }

    public static Socket getSocket(){
        return ClientSocket.socket;
    }
}
