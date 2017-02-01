package fredkobo.co.za.codeproject.framework;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class ApplicationCache {
    private static String authenticationToken;

    public static void setAuthenticationToken(String token) {
        authenticationToken = token;
    }

    public static String getAuthenticationToken() {
        return authenticationToken;
    }
}
