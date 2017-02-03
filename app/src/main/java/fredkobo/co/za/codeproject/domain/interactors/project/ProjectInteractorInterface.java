package fredkobo.co.za.codeproject.domain.interactors.project;

import fredkobo.co.za.codeproject.presentation.home.HomePresenterInterface;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public interface ProjectInteractorInterface {
    void GetProjectList(HomePresenterInterface homePresenterInterface);
    void DeleteProject(int pk, HomePresenterInterface homePresenterInterface);
    void EditProject(int pk, String title, String description, String start_date, String end_date, boolean is_billable, boolean is_active, HomePresenterInterface homePresenterInterface);
    void AddProject(String title, String description, String start_date, String end_date, boolean is_billable, boolean is_active, HomePresenterInterface homePresenter);
}
