package br.com.devjony.datapersistence;

public class Login {

    private String login, password;

    public Login(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Login() {
        this.login = null;
        this.password = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
