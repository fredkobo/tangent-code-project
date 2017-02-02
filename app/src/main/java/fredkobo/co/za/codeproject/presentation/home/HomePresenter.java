package fredkobo.co.za.codeproject.presentation.home;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import fredkobo.co.za.codeproject.domain.interactors.project.dto.Project;
import fredkobo.co.za.codeproject.domain.interactors.project.ProjectInteractor;
import fredkobo.co.za.codeproject.domain.interactors.project.ProjectInteractorInterface;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class HomePresenter implements HomePresenterInterface {

    private HomeView homeView;
    private ProjectInteractorInterface projectInteractor;
    private Gson gson;

    public HomePresenter(HomeView homeView){
        this.homeView = homeView;
        projectInteractor = new ProjectInteractor();
        gson = new Gson();
    }

    @Override
    public void retrieveProjectList() {
        projectInteractor.GetProjectList(this);
    }

    @Override
    public void deleteProject(int pk) {
        projectInteractor.DeleteProject(pk, this);
    }

    @Override
    public void editProject(int pk, String title, String description, Date start_date) {
        projectInteractor.EditProject(pk, title, description, start_date, this);
    }

    @Override
    public void addProject(String title, String description, Date start_date, Date end_date, boolean is_billable, boolean is_active) {
        projectInteractor.AddProject(title, description, start_date, end_date, is_billable, is_active, this);
    }

    @Override
    public void onRetrieveProjectListSuccess(String response) {
        Project[] projectArray = gson.fromJson(response, Project[].class);
        ArrayList<Project> projectArrayList = new ArrayList<Project>(Arrays.asList(projectArray));
        homeView.retrieveProjectListSuccess(projectArrayList);
    }

    @Override
    public void onRetrieveProjectListFailure(String response) {
        homeView.retrieveProjectListFailure(response);
    }

    @Override
    public void onEditProjectRequestSuccess(String response) {
        Project project = gson.fromJson(response, Project.class);
        homeView.editProjectSuccess(project);
    }

    @Override
    public void onEditProjectRequestFailure(String response) {
        homeView.editProjectFailure(response);
    }

    @Override
    public void onDeleteProjectSuccess(String response) {
        homeView.deleteSuccess();
    }

    @Override
    public void onDeleteProjectListFailure(String response) {
        homeView.deleteFailed();
    }

    @Override
    public void onAddProjectRequestSuccess(String response) {
        Project project = gson.fromJson(response, Project.class);
        homeView.addProjectSuccess();
    }

    @Override
    public void onAddProjectRequestFailure(String response) {
        homeView.addProjectFailure(response);
    }
}
