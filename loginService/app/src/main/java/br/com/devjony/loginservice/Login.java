package br.com.devjony.loginservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class Login extends Service {

    private final IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder {
        Login getService() {
            return Login.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public boolean validate(String login, String password) {
        return (login.equals("devjony") && password.equals("22091992"));
    }
}
