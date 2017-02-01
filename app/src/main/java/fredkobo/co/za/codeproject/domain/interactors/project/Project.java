package fredkobo.co.za.codeproject.domain.interactors.project;

/**
 * Created by frederickkobo on 2017/02/01.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project {

    @SerializedName("pk")
    private int pk;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("is_billable")
    private boolean isBillable;
    @SerializedName("is_active")
    private boolean isActive;
    @SerializedName("task_set")
    private List<TaskSet> taskSet = null;
    @SerializedName("resource_set")
    private List<ResourceSet> resourceSet = null;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean getIsBillable() {
        return isBillable;
    }

    public void setIsBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<TaskSet> getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(List<TaskSet> taskSet) {
        this.taskSet = taskSet;
    }

    public List<ResourceSet> getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(List<ResourceSet> resourceSet) {
        this.resourceSet = resourceSet;
    }

}
