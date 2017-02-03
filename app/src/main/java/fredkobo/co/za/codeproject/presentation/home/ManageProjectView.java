package fredkobo.co.za.codeproject.presentation.home;

import java.util.ArrayList;

import fredkobo.co.za.codeproject.domain.interactors.project.dto.Project;

/**
 * Created by frederickkobo on 2017/02/02.
 */
public interface ManageProjectView {
    void deleteSuccess(int deletePosition);
    void updateData(ArrayList<Project> projects);
}
