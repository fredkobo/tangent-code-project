package fredkobo.co.za.codeproject.presentation.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import fredkobo.co.za.codeproject.R;
import fredkobo.co.za.codeproject.domain.interactors.project.dto.Project;
import fredkobo.co.za.codeproject.presentation.shared.FlowType;
import fredkobo.co.za.codeproject.presentation.shared.GenericContextualDialog;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class HomeActivity extends AppCompatActivity implements HomeView {

    private HomePresenterInterface homePresenter;
    private int deletePosition;
    private ManageProjectsFragment manageProjectsFragment;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpComponents();
        homePresenter = new HomePresenter(this);
        progressDialog.show();
        homePresenter.retrieveProjectList();
    }

    private void setUpComponents() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    @Override
    public void retrieveProjectListSuccess(ArrayList<Project> projects) {
        manageProjectsFragment = ManageProjectsFragment.newInstance(this, projects);
        startFragment(manageProjectsFragment);
        progressDialog.dismiss();
    }

    @Override
    public void retrieveProjectListFailure(String failureMessage) {
        showDialog(failureMessage);
        progressDialog.dismiss();
    }

    @Override
    public void editProjectSuccess(Project project) {
        progressDialog.dismiss();
        showDialog("Edit SUCCESS");
        progressDialog.dismiss();
    }

    @Override
    public void editProjectFailure(String response) {
        progressDialog.dismiss();
        showDialog("Edit FAILED");
        progressDialog.dismiss();
    }

    @Override
    public void deleteSuccess() {
        manageProjectsFragment.deleteSuccess(deletePosition);
        showDialog("Delete SUCCESS");
        progressDialog.dismiss();
    }

    @Override
    public void deleteFailed() {
        showDialog("Delete FAILED");
        progressDialog.dismiss();
    }

    @Override
    public void addProjectSuccess() {
        progressDialog.dismiss();
        showDialog("Project ADDED");
    }

    @Override
    public void addProjectFailure(String response) {
        showDialog(response);
        progressDialog.dismiss();
    }

    @Override
    public void deleteProjectInvoked(int pk, int position) {
        deletePosition = position;
        homePresenter.deleteProject(pk);
    }

    @Override
    public void addProject(String title, String description, String start_date, String end_date, boolean isBillable, boolean isActive) {
        homePresenter.addProject(title, description, start_date, end_date, isBillable, isActive);
        progressDialog.show();
    }

    @Override
    public void editProject(int pk, String title, String description, String start_date, String end_date, boolean isBillable, boolean isActive) {
        homePresenter.editProject(pk, title, description, start_date, end_date, isBillable, isActive);
        progressDialog.show();
    }

    @Override
    public void startAddFragment(FlowType type, Project project) {
        AddEditFragment addEditFragment = AddEditFragment.newInstance(type, project, this);
        startFragment(addEditFragment);
    }

    private void startFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void showDialog(String content) {
        GenericContextualDialog dialog = GenericContextualDialog.newInstance(content);
        dialog.show(getSupportFragmentManager(), "Error dialog");
    }

}
