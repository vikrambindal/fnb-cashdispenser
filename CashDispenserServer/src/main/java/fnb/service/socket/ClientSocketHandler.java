package fnb.service.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.List;
import java.util.logging.Logger;

import fnb.service.constants.AppConstants;
import fnb.service.denomination.DenominationBreakdownService;
import fnb.service.denomination.DenominationBreakdownServiceImpl;

public class ClientSocketHandler extends Thread {

	private static final Logger logger = Logger.getLogger(ClientSocketHandler.class.getName());
	
    private Socket clientSocket;
    private DenominationBreakdownService denominationBreakdownService;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public ClientSocketHandler(Socket socket) {
        this.clientSocket = socket;
        this.denominationBreakdownService = new DenominationBreakdownServiceImpl();
    }

    @Override
    public void run() {
        try {
        	bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        	printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        	
        	String inputLine = null;
        	List<String> denominationBreakdowns = null;
        	
        	while((inputLine = bufferedReader.readLine()) != null) {
        		if (inputLine.startsWith("AUTH")) {
        			String[] authHeader = inputLine.split(":");
        			String username = authHeader[1];
        			String password = authHeader[2];

        			logger.info("Received username= " + username + " , password= " + password);

        			printWriter.println(username.equals(AppConstants.USERNAME) && password.equals(AppConstants.PASSWORD) ? "true" : "false");
        		} else {
        			
        			logger.info("Received change amount= " + inputLine);

        			denominationBreakdowns = denominationBreakdownService.computeBreakdown(new BigDecimal(inputLine));
                    printWriter.println(denominationBreakdowns);
        		}                
    		}
        	bufferedReader.close();
        	printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}