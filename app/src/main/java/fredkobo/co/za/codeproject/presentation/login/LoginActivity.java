package fredkobo.co.za.codeproject.presentation.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fredkobo.co.za.codeproject.R;


public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter loginPresenter;
    private EditText edUsername;
    private EditText edPassword;
    private TextView tvErrorText;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);

        edUsername = (EditText) findViewById(R.id.ed_username);
        edPassword = (EditText) findViewById(R.id.ed_password);
        tvErrorText = (TextView) findViewById(R.id.tv_error_text);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.login(edUsername.getText().toString(), edPassword.getText().toString());
            }
        });
    }

    @Override
    public void showLoginFailureMessage(String failureMessage) {
        tvErrorText.setText(failureMessage);
        tvErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void authenticationSuccessful(String token) {
        // TODO proceed to next screen
    }
}
