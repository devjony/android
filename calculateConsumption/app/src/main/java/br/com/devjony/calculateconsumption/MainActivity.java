package br.com.devjony.calculateconsumption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private CalculateService calculateService;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = new Intent(this, CalculateService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder iBinder) {
        CalculateService.MyBinder b = (CalculateService.MyBinder) iBinder;
        calculateService = b.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        calculateService = null;
    }

    public void calculate(View v) {
        EditText kmEditText = (EditText)findViewById(R.id.kmValue);
        EditText litersEditText = (EditText)findViewById(R.id.litersValue);

        Double km = calculateService.convertToDouble(kmEditText);
        Double liters = calculateService.convertToDouble(litersEditText);

        double consumption = calculateService.calculateResult(km, liters);
        double formatedConsumption = calculateService.doubleFormat(consumption);

        Toast.makeText(this, "Consumption: " + formatedConsumption + " km/l", Toast.LENGTH_LONG).show();
    }
}
