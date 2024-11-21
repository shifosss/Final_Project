package interface_adapter.account;

import interface_adapter.ViewModel;

/**
 * The viewmodel for the account view usecase.
 */
public class AccountViewModel extends ViewModel<AccountState> {
    public AccountViewModel() {
        super("account");
        setState(new AccountState());
    }
}
