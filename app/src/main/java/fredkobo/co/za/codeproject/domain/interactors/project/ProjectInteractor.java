package fredkobo.co.za.codeproject.domain.interactors.project;

import java.util.Date;

import fredkobo.co.za.codeproject.domain.interactors.project.dto.AddProjectRequest;
import fredkobo.co.za.codeproject.domain.interactors.project.dto.DeleteProjectRequest;
import fredkobo.co.za.codeproject.domain.interactors.project.dto.EditProjectRequest;
import fredkobo.co.za.codeproject.domain.interactors.project.dto.GetProjectListRequest;
import fredkobo.co.za.codeproject.presentation.home.HomePresenterInterface;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class ProjectInteractor implements ProjectInteractorInterface {

    @Override
    public void GetProjectList(HomePresenterInterface homePresenterInterface) {
        GetProjectListRequest getProjectListRequest = new GetProjectListRequest(homePresenterInterface);
        getProjectListRequest.execute();
    }

    @Override
    public void DeleteProject(int pk, HomePresenterInterface homePresenterInterface) {
        DeleteProjectRequest deleteProjectRequest = new DeleteProjectRequest(pk, homePresenterInterface);
        deleteProjectRequest.execute();
    }

    @Override
    public void EditProject(int pk, String title, String description, Date start_date, HomePresenterInterface homePresenterInterface) {
        EditProjectRequest editProjectRequest = new EditProjectRequest(pk, title, description, start_date, homePresenterInterface);
        editProjectRequest.execute();
    }

    @Override
    public void AddProject(String title, String description, Date start_date, Date end_date, boolean is_billable, boolean is_active, HomePresenterInterface homePresenter) {
        AddProjectRequest addProjectRequest = new AddProjectRequest(title, description, start_date, end_date, is_billable, is_active, homePresenter);
        addProjectRequest.execute();
    }


}
