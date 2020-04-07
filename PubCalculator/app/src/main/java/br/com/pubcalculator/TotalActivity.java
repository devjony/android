package br.com.pubcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class TotalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        Bundle extras = getIntent().getExtras();
        double totalValue = extras.getDouble("totalValue");
        double personValue = extras.getDouble("personValue");

        TextView totalText = ((TextView)findViewById(R.id.finishTotal));
        TextView personTotalText = ((TextView)findViewById(R.id.personTotal));

        totalText.setText(String.format("Total: $%.2f", totalValue));
        personTotalText.setText(String.format("Per Person: $%.2f", personValue));
    }

    public void returning(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
