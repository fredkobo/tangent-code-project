package fredkobo.co.za.codeproject.domain.interactors.project.dto;

/**
 * Created by frederickkobo on 2017/02/01.
 */


import com.google.gson.annotations.SerializedName;

public class TaskSet {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("due_date")
    private String dueDate;
    @SerializedName("estimated_hours")
    private String estimatedHours;
    @SerializedName("project")
    private int project;
    @SerializedName("project_data")
    private ProjectData projectData;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(String estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public ProjectData getProjectData() {
        return projectData;
    }

    public void setProjectData(ProjectData projectData) {
        this.projectData = projectData;
    }

}
