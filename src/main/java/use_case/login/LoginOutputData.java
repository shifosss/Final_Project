package use_case.login;

import java.util.List;

/**
 * Output data for the login usecase.
 */
public class LoginOutputData {
    private String username;
    private List<String> ingredientsToAvoidId;
    private boolean useCaseFailed;

    public LoginOutputData(String username, List<String> ingredientsToAvoidId, boolean useCaseFailed) {
        this.username = username;
        this.ingredientsToAvoidId = ingredientsToAvoidId;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getIngredientsToAvoidId() {
        return ingredientsToAvoidId;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
