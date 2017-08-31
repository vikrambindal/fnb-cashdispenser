package fnb.service.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class ServerSocketServiceImpl implements ServerSocketService {

	private static final Logger logger = Logger.getLogger(ServerSocketServiceImpl.class.getName());

	private ServerSocket serverSocket;
	
	@Override
    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        
        logger.info("Awaiting connections....");
        
        while(true) {
        	ClientSocketHandler csh = new ClientSocketHandler(serverSocket.accept());
        	csh.start();
            
        	logger.info("Connection obtained...");
        }
        
    }
 
	@Override
    public void stopServer() throws IOException {
        serverSocket.close();
    }
}