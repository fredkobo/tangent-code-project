package fredkobo.co.za.codeproject.domain;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import fredkobo.co.za.codeproject.framework.ServiceConfigConstants;
import fredkobo.co.za.codeproject.presentation.login.AuthenticationResponseListener;

/**
 * Created by frederickkobo on 2017/01/31.
 */

public class AuthenticationRequestTask extends AsyncTask<Void, Void, String> {

    private String TAG = AuthenticationRequestTask.class.getSimpleName();
    private String username;
    private String password;
    private AuthenticationResponseListener authenticationResponseListener;

    public AuthenticationRequestTask(String username, String password, AuthenticationResponseListener authenticationResponseListener){
        this.username = username;
        this.password = password;
        this.authenticationResponseListener = authenticationResponseListener;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response;
        HttpURLConnection connection = null;
        try {
            URL authenticationUrl = new URL("http://userservice.staging.tangentmicroservices.com:80/api-token-auth/");
            connection = (HttpURLConnection) authenticationUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setConnectTimeout(ServiceConfigConstants.CONNECTION_TIMEOUT);
            connection.setReadTimeout(ServiceConfigConstants.READ_TIMEOUT);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.connect();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", username);
            jsonObject.put("password", password);

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(jsonObject.toString());
            wr.flush();
            wr.close();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            response = stringBuilder.toString();
            return response;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    protected void onPostExecute(String response) {
        if(response != null){
            Log.d(TAG, "RESPONSE:" + response);
        }
    }
}
