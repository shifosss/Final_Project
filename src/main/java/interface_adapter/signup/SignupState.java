package interface_adapter.signup;

public class SignupState {
    private String username = "";
    private String password = "";
    private String error = "";
    private boolean isCreated = false;

    public SignupState(SignupState copy) {
        username = copy.username;
        password = copy.password;
        error = copy.error;
        isCreated = copy.isCreated;
    }

    public SignupState() {}

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getError() { return error; }
    public boolean isCreated() { return isCreated; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setError(String error) { this.error = error; }
    public void setCreated(boolean created) { isCreated = created; }
}