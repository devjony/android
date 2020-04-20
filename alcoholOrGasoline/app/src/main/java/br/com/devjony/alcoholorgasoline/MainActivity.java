package br.com.devjony.alcoholorgasoline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View v) {
        Double alcoholPrice = convertToDouble((EditText)findViewById(R.id.alcoholPrice));
        Double gasolinePrice = convertToDouble((EditText)findViewById(R.id.gasolinePrice));

        sendResult((alcoholPrice / gasolinePrice) >= 0.74 ? "Gasoline" : "Alcohol");
    }

    public Double convertToDouble(EditText text) {
        return Double.parseDouble(text.getText().toString());
    }

    public void sendResult(String result) {
        Toast.makeText(this, "Use " + result, Toast.LENGTH_SHORT).show();
    }
}
