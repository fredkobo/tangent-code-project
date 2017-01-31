package fredkobo.co.za.codeproject.presentation.login;

import fredkobo.co.za.codeproject.domain.AuthenticationRequestTask;

/**
 * Created by frederickkobo on 2017/01/31.
 */

public class LoginPresenter implements LoginPresenterInterface, AuthenticationResponseListener {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String username, String password) {
        AuthenticationRequestTask authenticationRequestTask = new AuthenticationRequestTask(username, password, this);
        authenticationRequestTask.execute();
    }

    @Override
    public void onAuthenticationSuccess(String token) {

    }

    @Override
    public void onAuthenticationFailure(String failureMessage) {

    }
}
