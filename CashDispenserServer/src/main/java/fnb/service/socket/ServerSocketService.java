package fnb.service.socket;

import java.io.IOException;

public interface ServerSocketService {

	void startServer(int port) throws IOException;

	void stopServer() throws IOException;

}
