package br.com.devjony.calculateconsumption;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.EditText;

import java.text.DecimalFormat;

public class CalculateService extends Service {

    private final IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        CalculateService getService() {
            return CalculateService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public Double convertToDouble(EditText editText) {
        Double editTextConverted = Double.parseDouble(editText.getText().toString());

        return editTextConverted;
    }

    public Double doubleFormat(Double number){
        DecimalFormat df = new DecimalFormat("#,###.00");
        return Double.valueOf(df.format(number));
    }

    public Double calculateResult(Double km, Double liters){
        return km / liters;
    }
}
