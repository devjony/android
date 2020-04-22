package br.com.devjony.geojson.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.devjony.geojson.domain.Features;
import br.com.devjony.geojson.domain.Json;

public class GeoJsonService extends Service {

    private final IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        public GeoJsonService getService() {
            return GeoJsonService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String findJson() throws IOException {
        File internalStorageDir = getFilesDir();
        File jsonFile = new File(internalStorageDir, "map.json");

        String jsonString = new String(Files.readAllBytes(Paths.get(String.valueOf(jsonFile))));
        return jsonString;
    }

    public void jsonData(String jsonString) throws IOException {
        JsonReaderWtriter jsonReaderWtriter = new JsonReaderWtriter(jsonString);
        Json json = jsonReaderWtriter.readJson();
        printJson(json);
    }

    public void printJson(Json json) {

        for (Features feature : json.getFeatures()) {
            String latitude = feature.getGeometry().getCoordinates().get(0);
            String longitude = feature.getGeometry().getCoordinates().get(1);
            Toast.makeText(this, "Latitude: " + latitude + "\nLongitude: " + longitude, Toast.LENGTH_LONG).show();
        }


    }
}
