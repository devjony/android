package br.com.pubcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addPrice01(View v) {
        EditText price = ((EditText)findViewById(R.id.firstPrice));
        updateTotal(price);
    }

    public void addPrice02(View v) {
        EditText price = ((EditText)findViewById(R.id.secondPrice));
        updateTotal(price);
    }

    public void addPrice03(View v) {
        EditText price = ((EditText)findViewById(R.id.thirdPrice));
        updateTotal(price);
    }

    public void addPrice04(View v) {
        EditText price = ((EditText)findViewById(R.id.fourthPrice));
        updateTotal(price);
    }

    public EditText getPeopleNumber() {
        EditText people = ((EditText)findViewById(R.id.peopleNumber));
        return people;
    }

    public double convertToDouble(EditText editText) {
        try {
            double convertedValue = Double.parseDouble(editText.getText().toString());
            return convertedValue;
        } catch(NumberFormatException n) {
            return -1;
        }
    }

    public void updateTotal(EditText price) {
        double priceConverted = convertToDouble(price);
        if (1 <= priceConverted) {
            setTotal(getTotal() + priceConverted);
            Toast.makeText(this, "Item added: $" + priceConverted, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ivalid value", Toast.LENGTH_SHORT).show();
        }
    }

    public double closeTotal() {
        EditText peopleText = getPeopleNumber();
        double peopleConverted = convertToDouble(peopleText);

        if(1 <= peopleConverted) {
            double totalValue = getTotal() / peopleConverted;
            return totalValue;
        } else {
            Toast.makeText(this, "People number invalid", Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    public void callTotalActivity(View v) {

        double finish = closeTotal();

        if(finish >= 1) {
            Intent intent = new Intent(this, TotalActivity.class);
            Bundle extras = new Bundle();

            extras.putDouble("totalValue", getTotal());
            extras.putDouble("personValue", closeTotal());
            intent.putExtras(extras);

            startActivity(intent);
            finish();
        } else if(finish <= 0 && finish != -1) {
            Toast.makeText(this, "Nothing to calculate", Toast.LENGTH_SHORT).show();
        }
    }

    public double getTotal() {
        return total;
    }

    private void setTotal(double total) {
        this.total = total;
    }
}
