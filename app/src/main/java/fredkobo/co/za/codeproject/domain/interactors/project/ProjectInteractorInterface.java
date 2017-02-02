package fredkobo.co.za.codeproject.domain.interactors.project;

import java.util.Date;

import fredkobo.co.za.codeproject.presentation.home.HomePresenterInterface;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public interface ProjectInteractorInterface {
    void GetProjectList(HomePresenterInterface homePresenterInterface);
    void DeleteProject(int pk, HomePresenterInterface homePresenterInterface);
    void EditProject(int pk, String title, String description, Date start_date, HomePresenterInterface homePresenterInterface);
    void AddProject(String title, String description, Date start_date, Date end_date, boolean is_billable, boolean is_active, HomePresenterInterface homePresenter);
}
