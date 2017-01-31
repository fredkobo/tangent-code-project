package fredkobo.co.za.codeproject.presentation.login;

/**
 * Created by frederickkobo on 2017/01/31.
 */

public interface LoginView {
   void  showLoginFailureMessage(String failureMessage);
   void  authenticationSuccessful(String token);
}
