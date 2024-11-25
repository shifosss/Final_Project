package interface_adapter.preference;

/**
 * State for the preference view.
 */
public class PreferenceState {
    private String username = "";

    public PreferenceState(PreferenceState copy) {
        this.username = copy.username;
    }

    public PreferenceState() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
