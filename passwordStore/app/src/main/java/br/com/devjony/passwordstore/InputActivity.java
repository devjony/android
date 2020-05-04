package br.com.devjony.passwordstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void getData(View v) {
        String server = ((EditText)findViewById(R.id.server)).getText().toString();
        String serverPassword = ((EditText)findViewById(R.id.serverPassword)).getText().toString();

        if(!serverPassword.equalsIgnoreCase("")) {
            saveData(server, serverPassword);
        } else {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show();
        }
    }

    private void saveData(String server, String serverPassword) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(InputActivity.this);
        SharedPreferences.Editor spEditor = sp.edit();

        spEditor.putString(server, serverPassword);
        spEditor.commit();

        Toast.makeText(this, server + " password saved", Toast.LENGTH_LONG).show();
        cleanInputs();
    }

    private void cleanInputs() {
        ((EditText)findViewById(R.id.server)).setText("");
        ((EditText)findViewById(R.id.serverPassword)).setText("");
        ((EditText)findViewById(R.id.recoveredPassword)).setText("");
    }

    public void callRecoveryData(View v) {
        String server = ((EditText)findViewById(R.id.server)).getText().toString();

        recoveryData(server);
    }

    private void recoveryData(String server) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(InputActivity.this);

        String passwordRecovered = sp.getString(server, "unknow");
        ((EditText)findViewById(R.id.recoveredPassword)).setText(passwordRecovered);
    }
}
