package br.com.pubcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private double total = 0.0;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((EditText)findViewById(R.id.eb1)).setId(1);
        ((EditText)findViewById(R.id.eb2)).setId(2);
        ((EditText)findViewById(R.id.eb3)).setId(3);
        ((EditText)findViewById(R.id.eb4)).setId(4);

        ((FloatingActionButton)findViewById(R.id.b1)).setId(5);
        ((FloatingActionButton)findViewById(R.id.b2)).setId(6);
        ((FloatingActionButton)findViewById(R.id.b3)).setId(7);
        ((FloatingActionButton)findViewById(R.id.b4)).setId(8);
    }

    public void action(int edit) {
        EditText price = ((EditText)findViewById(edit));
        updateTotal(price);
    }

    public void addPrice(View v) {
        action((v.getId()) - 4);
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
            Toast.makeText(this, String.format("Item added: $%.2f", priceConverted), Toast.LENGTH_SHORT).show();
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
