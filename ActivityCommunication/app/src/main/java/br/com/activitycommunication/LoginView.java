package br.com.activitycommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        Bundle extras = getIntent().getExtras();
        String user = extras.getString("userLogin");
        String password = extras.getString("userPassword");

        String loginText = "User: " + user + "\nPassword: " + password;
        TextView login = findViewById(R.id.status);
        login.setText(loginText);
    }

    public void changeActivity(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
