package fredkobo.co.za.codeproject.presentation.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import fredkobo.co.za.codeproject.R;
import fredkobo.co.za.codeproject.domain.interactors.project.Project;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class HomeActivity extends AppCompatActivity implements HomeView {

    private HomePresenterInterface homePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homePresenter = new HomePresenter(this);
        homePresenter.retrieveProjectList();
    }


    @Override
    public void retrieveProjectListSuccess(ArrayList<Project> projects) {
        ManageProjectsFragment manageProjectsFragment = new ManageProjectsFragment();
        manageProjectsFragment.setProjectList(projects);
        startFragment(manageProjectsFragment);

    }

    @Override
    public void retrieveProjectListFailure(String failureMessage) {

    }

    private void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
