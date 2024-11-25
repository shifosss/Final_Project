package use_case.change_preference;

/**
 * OutputData for the change preference.
 */
public class ChangePreferenceOutputData {
    private final boolean useCaseFailed;

    public ChangePreferenceOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
