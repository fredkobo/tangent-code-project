package fredkobo.co.za.codeproject.presentation.login;

/**
 * Created by frederickkobo on 2017/01/31.
 */
public interface AuthenticationResponseListener {
    void onAuthenticationSuccess(String response);
    void onAuthenticationFailure(String response);
    void onServiceCallFailed(String message);
}
