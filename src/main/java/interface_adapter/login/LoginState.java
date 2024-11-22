package interface_adapter.login;

public class LoginState {
    private String username = "";
    private String password = "";
    private String error = "";
    private boolean isLoggedIn = false;

    public LoginState(LoginState copy) {
        username = copy.username;
        password = copy.password;
        error = copy.error;
        isLoggedIn = copy.isLoggedIn;
    }

    public LoginState() {}

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getError() { return error; }
    public boolean isLoggedIn() { return isLoggedIn; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setError(String error) { this.error = error; }
    public void setLoggedIn(boolean loggedIn) { isLoggedIn = loggedIn; }
}
