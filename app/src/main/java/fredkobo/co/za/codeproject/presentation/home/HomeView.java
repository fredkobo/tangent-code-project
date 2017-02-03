package fredkobo.co.za.codeproject.presentation.home;

import java.util.ArrayList;

import fredkobo.co.za.codeproject.domain.interactors.project.dto.Project;
import fredkobo.co.za.codeproject.presentation.shared.FlowType;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public interface HomeView {
    void retrieveProjectListSuccess(ArrayList<Project> projects);
    void retrieveProjectListFailure(String failureMessage);

    void editProjectSuccess(Project project);
    void editProjectFailure(String response);

    void deleteSuccess();
    void deleteFailed();

    void addProjectSuccess();
    void addProjectFailure(String response);

    void deleteProjectInvoked(int pk, int position);
    void addProject(String title, String description, String start_date, String end_date, boolean isBillable, boolean isActive);
    void editProject(int pk, String title, String description, String start_date, String end_date, boolean isBillable, boolean isActive);
    void startAddFragment(FlowType type, Project project);
    void setToolbarTitle(String title);
}
