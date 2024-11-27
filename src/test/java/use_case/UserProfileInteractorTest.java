package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.create_recipe.CustomRecipeDataAccessInterface;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserProfileInteractorTest {
    private UserProfileInteractor userProfileInteractor;
    private UserProfileOutputBoundary userProfilePresenter;
    private CustomRecipeDataAccessInterface customRecipeDataAccessInterface;

    @BeforeEach
    public void setUp() {
        userProfilePresenter = mock(UserProfileOutputBoundary.class);
        customRecipeDataAccessInterface = mock(CustomRecipeDataAccessInterface.class);
        userProfileInteractor = new UserProfileInteractor(userProfilePresenter, customRecipeDataAccessInterface);
    }

    @Test
    public void switchToHomeView_Success() {
        userProfileInteractor.switchToHomePageView();

        verify(userProfilePresenter).switchToHomePageView();
    }
}
