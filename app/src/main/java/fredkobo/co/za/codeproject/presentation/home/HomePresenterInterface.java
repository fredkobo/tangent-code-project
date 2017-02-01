package fredkobo.co.za.codeproject.presentation.home;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public interface HomePresenterInterface {
    void retrieveProjectList();
    void onRetrieveProjectListSuccess(String response);
    void onRetrieveProjectListFailure(String response);
}
