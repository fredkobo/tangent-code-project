package fredkobo.co.za.codeproject.domain.interactors.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class AuthenticationSuccessResponse {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
