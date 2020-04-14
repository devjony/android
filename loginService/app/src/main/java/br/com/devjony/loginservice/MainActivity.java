package br.com.devjony.loginservice;

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

    private Login login;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = new Intent(this, Login.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Login.MyBinder b = (Login.MyBinder) iBinder;
        login = b.getService();
        Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        login = null;
    }

    public void button(View v) {
        EditText id1 = (EditText)findViewById(R.id.loginField);
        EditText id2 = (EditText)findViewById(R.id.passwordField);

        String login = id1.getText().toString();
        String password = id2.getText().toString();
        String status = this.login.validate(login, password) ? "Authorized access":"Login error";

        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }
}
