package fredkobo.co.za.codeproject.domain.interactors.project;

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
}
