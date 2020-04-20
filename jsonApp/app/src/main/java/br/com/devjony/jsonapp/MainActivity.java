package br.com.devjony.jsonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Save(View v) throws ClassNotFoundException {
        String name = ((EditText)findViewById(R.id.nameValue)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phoneValue)).getText().toString();
        EditText jsonFile = (EditText)findViewById(R.id.jsonView);

        try {
            String json = (new JsonReaderWriter(name, phone, name + " " + phone)).createJson();
            jsonFile.setText(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Write(View v) throws IOException {
        EditText name = (EditText)findViewById(R.id.nameJson);
        EditText phone = (EditText)findViewById(R.id.phoneJson);
        EditText jsonFile = (EditText)findViewById(R.id.jsonView);

        Schedule schedule = (new JsonReaderWriter(jsonFile.getText().toString())).readJson();
        name.setText(schedule.getName());
        phone.setText(schedule.getPhone());
    }
}
