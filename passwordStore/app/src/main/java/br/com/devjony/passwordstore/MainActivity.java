package br.com.devjony.passwordstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String login = "admin";
    private final String password = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getData(View v) {
        String login = ((EditText)findViewById(R.id.login)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        validateLogin(login, password);
    }

    private void validateLogin(String login, String password) {
        if(this.login.equalsIgnoreCase(login) && this.password.equalsIgnoreCase(password)) {
            Intent intent = new Intent(this, InputActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid login", Toast.LENGTH_LONG).show();
        }
    }
}
