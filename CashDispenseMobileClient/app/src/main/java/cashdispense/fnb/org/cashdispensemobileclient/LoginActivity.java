package cashdispense.fnb.org.cashdispensemobileclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cashdispense.fnb.org.socket.ClientSocket;
import cashdispense.fnb.org.socket.ClientSocketThread;
import cashdispense.fnb.org.tasks.UserLoginAsyncTask;
import cashdispense.fnb.org.validation.Validation;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameTextView;
    private EditText passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new Thread(new ClientSocketThread()).start();

        usernameTextView = (EditText) findViewById(R.id.username);
        passwordTextView = (EditText) findViewById(R.id.password);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUsernameAndPasswordInvalid()) {
                    UserLoginAsyncTask userLoginTask = new UserLoginAsyncTask(
                            usernameTextView.getText().toString(),
                            passwordTextView.getText().toString(),
                            new AuthorizationListener(),
                            ClientSocket.getSocket());

                    userLoginTask.execute((Void) null);
                }
            }
        });
    }

    private boolean isUsernameAndPasswordInvalid() {

        usernameTextView.setError(null);
        passwordTextView.setError(null);
        boolean invalid = false;
        View focusView = null;

        Validation validation = new Validation(this);
        String usernameErrorMessage = validation.validateUsername(usernameTextView.getText().toString());
        String passwordErrorMessage = validation.validatePassword(passwordTextView.getText().toString());

        if (passwordErrorMessage != null) {
            passwordTextView.setError(passwordErrorMessage);
            focusView = passwordTextView;
            invalid = true;
        }

        if (usernameErrorMessage != null) {
            usernameTextView.setError(usernameErrorMessage);
            focusView = usernameTextView;
            invalid = true;
        }

        if (invalid) {
            focusView.requestFocus();
        }

        return invalid;
    }

    private class AuthorizationListener implements CredentialAuthenticationListener {

        @Override
        public void onInvalidCredentials() {
            Toast.makeText(getApplicationContext(), R.string.invalid_credentials, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onValidCredentials() {
            Intent intent = new Intent(getApplicationContext(), DenominationCaptureActivity.class);
            startActivity(intent);
        }
    }
}