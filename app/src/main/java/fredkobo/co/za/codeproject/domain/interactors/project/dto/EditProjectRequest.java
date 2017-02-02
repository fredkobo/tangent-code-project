package fredkobo.co.za.codeproject.domain.interactors.project.dto;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import fredkobo.co.za.codeproject.framework.ApplicationCache;
import fredkobo.co.za.codeproject.framework.ServiceConfigConstants;
import fredkobo.co.za.codeproject.presentation.home.HomePresenterInterface;

/**
 * Created by frederickkobo on 2017/02/02.
 */

public class EditProjectRequest extends AsyncTask<Void, Void, String> {

    private int responseCode;
    private int pk;
    private String title;
    private String description;
    private Date start_date;
    private HomePresenterInterface homePresenter;

    public EditProjectRequest(int pk, String title, String description, Date start_date, HomePresenterInterface homePresenterInterface) {
        this.pk = pk;
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.homePresenter = homePresenterInterface;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response;
        HttpURLConnection connection = null;
        try {
            URL authenticationUrl = new URL(ServiceConfigConstants.ROOT_PROJECT_URL + pk + "/");
            connection = (HttpURLConnection) authenticationUrl.openConnection();
            connection.setRequestMethod("PUT");
            connection.setConnectTimeout(ServiceConfigConstants.CONNECTION_TIMEOUT);
            connection.setReadTimeout(ServiceConfigConstants.READ_TIMEOUT);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", "Token " + ApplicationCache.getAuthenticationToken());
            connection.connect();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", title);
            jsonObject.put("description", description);
            jsonObject.put("start_date", start_date);


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
            homePresenter.onEditProjectRequestSuccess(response);
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND){
            homePresenter.onEditProjectRequestFailure("Not found.");
        } else {
            homePresenter.onEditProjectRequestFailure(response);
        }
    }
}
