package fredkobo.co.za.codeproject.domain.interactors.project;

import com.google.gson.annotations.SerializedName;

/**
 * Created by frederickkobo on 2017/02/01.
 */

public class ResourceSet {

    @SerializedName("id")
    private int id;
    @SerializedName("user")
    private String user;
    @SerializedName("start_date")
    private String start_date;
    @SerializedName("end_date")
    private String end_date;
    @SerializedName("rate")
    private float rate;
    @SerializedName("agreed_hours_per_month")
    private double agreed_hours_per_month;
    @SerializedName("created")
    private String created;
    @SerializedName("updated")
    private String updated;
    @SerializedName("project")
    private String project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public double getAgreed_hours_per_month() {
        return agreed_hours_per_month;
    }

    public void setAgreed_hours_per_month(double agreed_hours_per_month) {
        this.agreed_hours_per_month = agreed_hours_per_month;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
