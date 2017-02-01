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
import fredkobo.co.za.codeproject.domain.interactors.project.Project;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class ManageProjectsFragment extends Fragment {
    private View rootView;
    private RecyclerView projectRecyclerView;
    private ProjectAdapter ProjectAdapter;
    private ArrayList<Project> projectList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_manage_projects, container, false);
        setUpComponents();
        return rootView;
    }

    private void setUpComponents() {
        ProjectAdapter = new ProjectAdapter(projectList);
        projectRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_projects);
        projectRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        projectRecyclerView.setAdapter(ProjectAdapter);
    }

    public void setProjectList(ArrayList<Project> projects) {
        projectList = projects;
    }
}
