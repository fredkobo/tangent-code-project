package fredkobo.co.za.codeproject.presentation.home;

import java.util.Date;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public interface HomePresenterInterface {
    void retrieveProjectList();
    void deleteProject(int pk);
    void editProject(int pk, String title, String description, Date start_date);
    void addProject(String title, String description, Date start_date, Date end_date, boolean is_billable, boolean is_active);

    void onRetrieveProjectListSuccess(String response);
    void onRetrieveProjectListFailure(String response);

    void onEditProjectRequestSuccess(String response);
    void onEditProjectRequestFailure(String response);

    void onDeleteProjectSuccess(String response);
    void onDeleteProjectListFailure(String response);

    void onAddProjectRequestSuccess(String response);
    void onAddProjectRequestFailure(String response);
}
