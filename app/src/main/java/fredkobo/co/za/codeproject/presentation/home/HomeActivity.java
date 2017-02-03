package fredkobo.co.za.codeproject.presentation.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import fredkobo.co.za.codeproject.R;
import fredkobo.co.za.codeproject.domain.interactors.project.dto.Project;
import fredkobo.co.za.codeproject.presentation.login.LoginActivity;
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
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;
    private ArrayList<Project> projectList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar_include);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            setTitle("Home");
        }

        setUpComponents();
        homePresenter = new HomePresenter(this);
        progressDialog.show();
        homePresenter.retrieveProjectList();
        setUpDrawerLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_contact:
                startAddFragment(FlowType.ADD, null);
                break;
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpComponents() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                popFragmentBackStack();
                ManageProjectsFragment manageProjectsFragment = ManageProjectsFragment.newInstance(this, projectList);
                startFragment(manageProjectsFragment);
                break;
            case R.id.nav_add:
                popFragmentBackStack();
                AddEditFragment addEditFragment = AddEditFragment.newInstance(FlowType.ADD, null, this);
                startFragment(addEditFragment);
                break;
            case R.id.nav_logout:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private void setUpDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        setupDrawerContent(navigationView);
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            navigationView.getMenu().getItem(0).setChecked(true);
        }
        drawerToggle.syncState();
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        if (!navigationView.getMenu().findItem(menuItem.getItemId()).isChecked()) {
                            selectDrawerItem(menuItem);
                            return true;
                        }
                        return true;
                    }

                });
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void popFragmentBackStack() {
        int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        while (backStackCount > 0) {
            getSupportFragmentManager().popBackStack();
            backStackCount--;
        }
    }

    @Override
    public void retrieveProjectListSuccess(ArrayList<Project> projects) {
        this.projectList = projects;
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
        navigationView.getMenu().getItem(1).setChecked(true);
        AddEditFragment addEditFragment = AddEditFragment.newInstance(type, project, this);
        startFragment(addEditFragment);
    }

    @Override
    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            setTitle(title);
        }
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
