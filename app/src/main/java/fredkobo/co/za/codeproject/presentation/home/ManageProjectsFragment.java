package fredkobo.co.za.codeproject.presentation.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fredkobo.co.za.codeproject.R;
import fredkobo.co.za.codeproject.domain.interactors.project.dto.Project;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class ManageProjectsFragment extends Fragment implements ManageProjectView {
    private View rootView;
    private RecyclerView projectRecyclerView;
    private ProjectAdapter projectAdapter;
    private ArrayList<Project> projectList;
    private HomeView homeView;

    public static ManageProjectsFragment newInstance(HomeView homeView, ArrayList<Project> projectList) {
        ManageProjectsFragment fragment = new ManageProjectsFragment();
        fragment.homeView = homeView;
        fragment.projectList = projectList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_manage_projects, container, false);
        setUpComponents();
        return rootView;
    }

    private void setUpComponents() {
        projectRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_projects);
        projectAdapter = new ProjectAdapter(projectList, homeView);
        projectRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        projectRecyclerView.setAdapter(projectAdapter);
    }

    @Override
    public void deleteSuccess(int deletePosition) {
        projectAdapter.removeAt(deletePosition);
    }

    @Override
    public void updateData(ArrayList<Project> projects) {
        projectList = projects;
        projectAdapter = new ProjectAdapter(projects, homeView);
        projectRecyclerView.swapAdapter(projectAdapter, false);
    }
}
