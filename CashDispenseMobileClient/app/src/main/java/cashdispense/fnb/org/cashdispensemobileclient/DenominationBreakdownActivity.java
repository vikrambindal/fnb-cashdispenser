package cashdispense.fnb.org.cashdispensemobileclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DenominationBreakdownActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denomination_breakdown);

        Intent intent = getIntent();
        String breakdowns = intent.getStringExtra("breakdown");
        Double changeAmount = intent.getDoubleExtra("changeAmount", 0d);

        EditText breakdownMultiTextField = findViewById(R.id.breakdownMultiText);
        TextView totalDispensed = findViewById(R.id.totalDispensed);

        breakdownMultiTextField.setEnabled(false);
        totalDispensed.setText("Total " +  (changeAmount >= 1d ? ("R" + String.format("%.2f", changeAmount))
                : (String.format("%.2f", changeAmount) + "cents")));

        breakdowns = breakdowns.substring(1, breakdowns.length()-1);
        for(String breakdown: breakdowns.split(",")){
            breakdownMultiTextField.append(breakdown);
            breakdownMultiTextField.append(System.getProperty("line.separator"));
        }

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DenominationCaptureActivity.class);
                startActivity(intent);
            }
        });
    }
}
