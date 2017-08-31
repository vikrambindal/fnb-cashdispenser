package cashdispense.fnb.org.tasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import cashdispense.fnb.org.cashdispensemobileclient.CredentialAuthenticationListener;

public class UserLoginAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private final String username;
    private final String password;
    private CredentialAuthenticationListener credentialAuthenticationListener;
    private Socket clientSocket;

    public UserLoginAsyncTask(String username, String password,
                              CredentialAuthenticationListener credentialAuthenticationListener,
                              Socket clientSocket) {
        this.username = username;
        this.password = password;
        this.credentialAuthenticationListener = credentialAuthenticationListener;
        this.clientSocket = clientSocket;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        processAuthenticationOnServer(username, password);
        return isUserAuthenticated();
    }

    @Override
    protected void onPostExecute(final Boolean success) {

        if (success) {
            credentialAuthenticationListener.onValidCredentials();
        } else {
            credentialAuthenticationListener.onInvalidCredentials();
        }
    }

    private boolean isUserAuthenticated() {
        boolean authenticated = false;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String input = in.readLine();
            authenticated = Boolean.valueOf(input);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticated;
    }

    private void processAuthenticationOnServer(String username, String password) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream())),
                    true);
            out.println("AUTH:" + username + ":" + password);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}