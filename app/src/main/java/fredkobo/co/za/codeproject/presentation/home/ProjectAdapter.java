package fredkobo.co.za.codeproject.presentation.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import fredkobo.co.za.codeproject.R;
import fredkobo.co.za.codeproject.domain.interactors.project.dto.Project;
import fredkobo.co.za.codeproject.presentation.shared.FlowType;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private ArrayList<Project> projectList;
    private HomeView homeView;

    public ProjectAdapter(ArrayList<Project> projectList, HomeView homeView) {
        this.projectList = projectList;
        this.homeView = homeView;
    }

    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_project, parent, false);
        return new ProjectAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvProjectName.setText(projectList.get(position).getTitle());
        holder.tvProjectKey.setText("PK: " + projectList.get(position).getPk());
        holder.tvStartDate.setText("Start Date: " + projectList.get(position).getStartDate());
        holder.btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO complete
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeView.startAddFragment(FlowType.EDIT, projectList.get(position));
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeView.deleteProjectInvoked(projectList.get(position).getPk(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (projectList != null) {
            return projectList.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView tvProjectName;
        final TextView tvProjectKey;
        final TextView tvStartDate;

        final Button btnViewDetails;
        final Button btnEdit;
        final Button btnDelete;

        ViewHolder(View view) {
            super(view);
            mView = view;
            tvProjectName = (TextView) view.findViewById(R.id.tv_project_name);
            tvProjectKey = (TextView) view.findViewById(R.id.tv_project_key);
            tvStartDate = (TextView) view.findViewById(R.id.tv_start_date);
            btnViewDetails = (Button) view.findViewById(R.id.btn_view_details);
            btnDelete = (Button) view.findViewById(R.id.btn_delete);
            btnEdit = (Button) view.findViewById(R.id.btn_edit);
        }
    }

    public void removeAt(int position) {
        projectList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, projectList.size());
        notifyItemRangeChanged(position, projectList.size());
    }
}
