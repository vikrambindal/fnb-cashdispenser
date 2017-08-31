package cashdispense.fnb.org.tasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import cashdispense.fnb.org.cashdispensemobileclient.MessageListener;

public class ProcessAmountAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private double dispenseAmount;
    private Socket clientSocket;
    private MessageListener messageListener;

    public ProcessAmountAsyncTask(Socket clientSocket, double dispenseAmount, MessageListener messageListener) {
        this.dispenseAmount = dispenseAmount;
        this.clientSocket = clientSocket;
        this.messageListener = messageListener;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        processDispenseAmountOnServer(dispenseAmount);
        obtainBreakdownFromServer();
        return true;
    }

    private void obtainBreakdownFromServer() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String input = in.readLine();
            messageListener.onMessageReceived(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processDispenseAmountOnServer(double dispenseAmount) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream())),
                    true);
            out.println(dispenseAmount);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
