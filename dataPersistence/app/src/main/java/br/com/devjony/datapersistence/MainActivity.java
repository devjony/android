package br.com.devjony.datapersistence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SharedPreferences
        this.createSharedPreferencesAutentication();

        //File
        try {
            this.createFileAutentication();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
é sim
        //Database
        this.createDatabaseAutentication();
    }

    public void createFileAutentication() throws JsonParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        Login login = new Login("admin", "12345678");
        File internalStorageDir = getFilesDir();
        File file = new File(internalStorageDir, "login.json");
        try {
            objectMapper.writeValue(file, login);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDatabaseAutentication() {
        SQLiteDatabase myDB = openOrCreateDatabase("login.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (login VARCHAR(20), password VARCHAR(20))");
        ContentValues registry = new ContentValues();
        registry.put("login", "admin");
        registry.put("password", "12345678");

        myDB.insert("user", null, registry);
        myDB.close();
    }

    public void createSharedPreferencesAutentication() {
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putString("login", "admin");
        myEditor.putString("password", "12345678");
        myEditor.commit();
    }

    public void sharedPreferencesAutentication(View v) {
        String login = ((EditText)findViewById(R.id.login)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String loginString = myPreferences.getString("login", "unknow");
        String passwordString = myPreferences.getString("password", "unknow");

        if(loginString.equalsIgnoreCase(login) && passwordString.equalsIgnoreCase(password)) {
            Toast.makeText(this, "SP OK", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "SP ERROR", Toast.LENGTH_LONG).show();
        }
    }

    public void fileAutentication(View v) {
        try {
            String login = ((EditText)findViewById(R.id.login)).getText().toString();
            String password = ((EditText)findViewById(R.id.password)).getText().toString();

            final ObjectMapper mapper = new ObjectMapper();
            File internalStorageDir = getFilesDir();
            File jsonFile = new File(internalStorageDir, "login.json");

            Login loginObject = mapper.readValue(jsonFile, Login.class);

            String loginString = loginObject.getLogin();
            String passwordString = loginObject.getPassword();

            if(loginString.equalsIgnoreCase(login) && passwordString.equalsIgnoreCase(password)) {
                Toast.makeText(this, "FILE OK", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "FILE ERROR", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void databaseAutentication(View v) {
        String login = ((EditText)findViewById(R.id.login)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        SQLiteDatabase myDB = openOrCreateDatabase("login.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (login VARCHAR(20), password VARCHAR(20))");

        Cursor myCursor = myDB.rawQuery("select login, password from user", null);

        myCursor.moveToNext();
        String loginString = myCursor.getString(0);
        String passwordString = myCursor.getString(1);

        if(loginString.equalsIgnoreCase(login) && passwordString.equalsIgnoreCase(password)) {
            Toast.makeText(this, "SQL OK", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "SQL ERROR", Toast.LENGTH_LONG).show();
        }

        myDB.close();
    }
}