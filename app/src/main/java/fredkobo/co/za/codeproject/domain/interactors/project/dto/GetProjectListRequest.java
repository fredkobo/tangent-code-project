package fredkobo.co.za.codeproject.domain.interactors.project.dto;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import fredkobo.co.za.codeproject.framework.ApplicationCache;
import fredkobo.co.za.codeproject.framework.ServiceConfigConstants;
import fredkobo.co.za.codeproject.presentation.home.HomePresenterInterface;


/**
 * Created by frederickkobo on 2017/02/01.
 */

public class GetProjectListRequest extends AsyncTask<Void, Void, String> {

    private String TAG = GetProjectListRequest.class.getSimpleName();
    private int responseCode;
    private HomePresenterInterface homePresenter;

    public GetProjectListRequest(HomePresenterInterface homePresenterInterface) {
        this.homePresenter = homePresenterInterface;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response;
        HttpURLConnection connection = null;
        try {
            URL authenticationUrl = new URL(ServiceConfigConstants.ROOT_PROJECT_URL);
            connection = (HttpURLConnection) authenticationUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(ServiceConfigConstants.CONNECTION_TIMEOUT);
            connection.setReadTimeout(ServiceConfigConstants.READ_TIMEOUT);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", "Token " + ApplicationCache.getAuthenticationToken());
            connection.connect();

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
        if (responseCode == HttpURLConnection.HTTP_OK) {
            homePresenter.onRetrieveProjectListSuccess(response);
        } else if(responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
            homePresenter.onRetrieveProjectListFailure("500 Internal Server Error");
        } else {
            homePresenter.onRetrieveProjectListFailure(response);
        }
    }
}
