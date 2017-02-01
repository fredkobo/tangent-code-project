package fredkobo.co.za.codeproject.domain.interactors.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class AuthenticationFailureResponse {

    @SerializedName("non_field_errors")
    private String[] non_field_errors;

    public String[] getNon_field_errors() {
        return non_field_errors;
    }

    public void setNon_field_errors(String[] non_field_errors) {
        this.non_field_errors = non_field_errors;
    }
}
