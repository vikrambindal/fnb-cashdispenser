package cashdispense.fnb.org.cashdispensemobileclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Socket;

import cashdispense.fnb.org.socket.ClientSocket;
import cashdispense.fnb.org.tasks.ProcessAmountAsyncTask;

public class DenominationCaptureActivity extends Activity {

    private TextView amountDueField;
    private EditText captureDenomAmountField;
    private Socket clientSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denomination_capture);

        clientSocket = ClientSocket.getSocket();

        captureDenomAmountField = findViewById(R.id.captureDenomAmount);
        amountDueField = findViewById(R.id.amountDue);

        final Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isAmountInvalid()) {
                    new ProcessAmountAsyncTask(clientSocket,
                            getChangeAmount(),
                            new DenominationMessageListener())
                            .execute((Void) null);
                }
            }
        });
    }

    private double getChangeAmount() {
        double capturedAmount = Double.valueOf(captureDenomAmountField.getText().toString());
        double amountDue = Double.valueOf(amountDueField.getText().toString().substring(1));
        return amountDue - capturedAmount;
    }

    private boolean isAmountInvalid() {
        boolean invalid = false;

        if (captureDenomAmountField.getText() != null && !TextUtils.isEmpty(captureDenomAmountField.getText())) {
            double capturedAmount = Double.valueOf(captureDenomAmountField.getText().toString());
            double amountDue = Double.valueOf(amountDueField.getText().toString().substring(1));
            if (capturedAmount > amountDue) {
                captureDenomAmountField.setError(getString(R.string.amount_invalid));
                captureDenomAmountField.requestFocus();
                invalid = true;
            }
        } else {
            captureDenomAmountField.setError(getString(R.string.error_field_required));
            captureDenomAmountField.requestFocus();
            invalid = true;
        }
        return invalid;
    }

    private class DenominationMessageListener implements MessageListener {

        @Override
        public void onMessageReceived(String data) {
            Intent intent = new Intent(getApplicationContext(), DenominationBreakdownActivity.class);
            intent.putExtra("changeAmount", getChangeAmount());
            intent.putExtra("breakdown", data);
            startActivity(intent);
        }
    }
}
