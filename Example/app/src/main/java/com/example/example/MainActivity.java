package com.example.example;

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

    public void BomDia(View v) {
        EditText entrada = (EditText)findViewById(R.id.editText);
        String nome = entrada.getText().toString();
        Toast.makeText(this, "Hello World, " + nome, Toast.LENGTH_LONG).show();
    }
}
