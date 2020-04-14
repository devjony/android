package br.com.activitycommunication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final String userLogin = "";
    public static final String userPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void accessing(View v) {
        String login = ((EditText)findViewById(R.id.login)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        Intent intent = new Intent(this, LoginView.class);
        Bundle extras = new Bundle();
        extras.putString("userLogin", login);
        extras.putString("userPassword", password);
        intent.putExtras(extras);

        startActivity(intent);
        finish();
    }
}
