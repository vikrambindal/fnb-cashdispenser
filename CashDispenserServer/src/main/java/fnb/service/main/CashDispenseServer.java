package fnb.service.main;

import java.io.IOException;

import fnb.service.constants.AppConstants;
import fnb.service.socket.ServerSocketService;
import fnb.service.socket.ServerSocketServiceImpl;

public class CashDispenseServer {

	public static void main(String[] args) throws IOException {
		ServerSocketService serverSocketService = new ServerSocketServiceImpl();
		serverSocketService.startServer(AppConstants.SOCKET_PORT);
	}
}
