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

public class AddProjectRequest extends AsyncTask<Void, Void, String> {

    private String title;
    private String description;
    private Date start_date;
    private Date end_date;
    private boolean is_billable;
    private boolean is_active;
    private HomePresenterInterface homePresenter;
    private int responseCode;

    public AddProjectRequest(String title, String description, Date start_date, Date end_date, boolean is_billable, boolean is_active, HomePresenterInterface homePresenter) {
        this.title = title;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.is_billable = is_billable;
        this.is_active = is_active;
        this.homePresenter = homePresenter;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String response;
        HttpURLConnection connection = null;
        try {
            URL authenticationUrl = new URL(ServiceConfigConstants.ROOT_PROJECT_URL);
            connection = (HttpURLConnection) authenticationUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(ServiceConfigConstants.CONNECTION_TIMEOUT);
            connection.setReadTimeout(ServiceConfigConstants.READ_TIMEOUT);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", "Token " + ApplicationCache.getAuthenticationToken());
            connection.connect();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", title);
            jsonObject.put("description", description);
            jsonObject.put("start_date", start_date);
            jsonObject.put("end_date", end_date);
            jsonObject.put("is_billable", is_billable);
            jsonObject.put("is_active", is_active);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonObject.toString());
            outputStream.flush();
            outputStream.close();

            responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
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
        if (responseCode == HttpURLConnection.HTTP_CREATED) {
            homePresenter.onAddProjectRequestSuccess(response);
        }  else {
            homePresenter.onAddProjectRequestFailure(response);
        }
    }
}
