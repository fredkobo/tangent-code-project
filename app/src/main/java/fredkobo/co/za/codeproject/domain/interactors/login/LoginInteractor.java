package fredkobo.co.za.codeproject.domain.interactors.login;

import fredkobo.co.za.codeproject.presentation.login.AuthenticationResponseListener;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class LoginInteractor implements LoginInteractorInterface{

    @Override
    public void performAuthentication(String username, String password, AuthenticationResponseListener authenticationResponseListener) {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password, authenticationResponseListener);
        authenticationRequest.execute();
    }
}
