package fredkobo.co.za.codeproject.presentation.home;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import fredkobo.co.za.codeproject.domain.interactors.project.Project;
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
    public void onRetrieveProjectListSuccess(String response) {
        Project[] projectArray = gson.fromJson(response, Project[].class);
        ArrayList<Project> projectArrayList = new ArrayList<Project>(Arrays.asList(projectArray));
        homeView.retrieveProjectListSuccess(projectArrayList);
    }

    @Override
    public void onRetrieveProjectListFailure(String response) {
        homeView.retrieveProjectListFailure(response);
    }
}
