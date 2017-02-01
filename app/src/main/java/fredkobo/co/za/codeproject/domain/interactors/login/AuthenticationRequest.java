package fredkobo.co.za.codeproject.domain.interactors.login;

import android.os.AsyncTask;

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

public class AuthenticationRequest extends AsyncTask<Void, Void, String> {

    private String TAG = AuthenticationRequest.class.getSimpleName();
    private String username;
    private String password;
    private AuthenticationResponseListener authenticationResponseListener;
    private int responseCode;


    public AuthenticationRequest(String username, String password, AuthenticationResponseListener authenticationResponseListener){
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

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonObject.toString());
            outputStream.flush();
            outputStream.close();

            responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStreamReader responseStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedResponseReader = new BufferedReader(responseStreamReader);
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while((line = bufferedResponseReader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                response = responseBuilder.toString();
            } else if(responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                InputStreamReader responseStreamReader = new InputStreamReader(connection.getErrorStream());
                BufferedReader bufferedResponseReader = new BufferedReader(responseStreamReader);
                StringBuilder responseBuilder = new StringBuilder();
                String line;
                while((line = bufferedResponseReader.readLine()) != null) {
                    responseBuilder.append(line);
                }
                response = responseBuilder.toString();
            } else {
                response = responseCode + " Service failure";
            }

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

        if(response != null) {
            switch (responseCode) {
                case HttpURLConnection.HTTP_OK:
                    authenticationResponseListener.onAuthenticationSuccess(response);
                    break;
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    authenticationResponseListener.onAuthenticationFailure(response);
                    break;
                default:
                    authenticationResponseListener.onServiceCallFailed(responseCode + " Service call failed");
                    break;
            }
        } else {
            authenticationResponseListener.onServiceCallFailed(responseCode + " Service call failed");
        }

    }
}
