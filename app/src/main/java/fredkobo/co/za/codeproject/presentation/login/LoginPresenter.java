package fredkobo.co.za.codeproject.presentation.login;

import com.google.gson.Gson;

import fredkobo.co.za.codeproject.domain.interactors.login.AuthenticationFailureResponse;
import fredkobo.co.za.codeproject.domain.interactors.login.AuthenticationSuccessResponse;
import fredkobo.co.za.codeproject.domain.interactors.login.LoginInteractor;
import fredkobo.co.za.codeproject.domain.interactors.login.LoginInteractorInterface;
import fredkobo.co.za.codeproject.framework.ApplicationCache;

/**
 * Created by frederickkobo on 2017/01/31.
 */

public class LoginPresenter implements LoginPresenterInterface, AuthenticationResponseListener {

    private LoginView loginView;
    private Gson gson;
    private LoginInteractorInterface loginInteractor;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        gson = new Gson();
        loginInteractor = new LoginInteractor();
    }

    @Override
    public void login(String username, String password) {
        validateFields(username, password);
    }

    private void validateFields(String username, String password) {
        if(!username.isEmpty() && !password.isEmpty()) {
            loginInteractor.performAuthentication(username, password, this);
        } else {
            if(username.isEmpty()) {
                loginView.showUsernameFieldError("This field cannot be empty");
            }

            if(password.isEmpty()) {
                loginView.showPasswordFieldError("This field cannot be empty");
            }
        }
    }

    @Override
    public void onAuthenticationSuccess(String response) {
        AuthenticationSuccessResponse authenticationSuccessResponse = gson.fromJson(response, AuthenticationSuccessResponse.class);
        String token = authenticationSuccessResponse.getToken();
        ApplicationCache.setAuthenticationToken(token);
        loginView.authenticationSuccessful();
    }

    @Override
    public void onAuthenticationFailure(String response) {
        AuthenticationFailureResponse authenticationFailureResponse = gson.fromJson(response, AuthenticationFailureResponse.class);
        String[] messages = authenticationFailureResponse.getNon_field_errors();
        loginView.showLoginFailureMessage(messages[0]);
    }

    @Override
    public void onServiceCallFailed(String message) {
        loginView.showLoginFailureMessage(message);
    }
}
