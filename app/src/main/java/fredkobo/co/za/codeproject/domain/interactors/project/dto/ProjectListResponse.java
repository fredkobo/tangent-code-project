package fredkobo.co.za.codeproject.domain.interactors.project.dto;

import java.util.ArrayList;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class ProjectListResponse {

    ArrayList<Project> projectArrayList;

    public ArrayList<Project> getProjectArrayList() {
        return projectArrayList;
    }

    public void setProjectArrayList(ArrayList<Project> projectArrayList) {
        this.projectArrayList = projectArrayList;
    }
}
