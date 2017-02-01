package fredkobo.co.za.codeproject.presentation.home;

import java.util.ArrayList;

import fredkobo.co.za.codeproject.domain.interactors.project.Project;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public interface HomeView {
    void retrieveProjectListSuccess(ArrayList<Project> projects);
    void retrieveProjectListFailure(String failureMessage);
}
