package br.com.devjony.geojson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import br.com.devjony.geojson.service.GeoJsonService;
import br.com.devjony.geojson.service.JsonReaderWtriter;

public class MainActivity extends AppCompatActivity implements ServiceConnection {

    private GeoJsonService geoJsonService;
    private JsonReaderWtriter jsonReaderWtriter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View v) {
        try {
            geoJsonService.jsonData(geoJsonService.findJson());
        } catch (IOException e) {
            System.out.println("Cannot find json file");
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        intent = new Intent(this, GeoJsonService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        GeoJsonService.MyBinder b = (GeoJsonService.MyBinder) service;
        geoJsonService = b.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        geoJsonService = null;
    }
}
