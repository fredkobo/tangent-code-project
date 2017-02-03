package fredkobo.co.za.codeproject.presentation.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import fredkobo.co.za.codeproject.R;
import fredkobo.co.za.codeproject.domain.interactors.project.dto.Project;
import fredkobo.co.za.codeproject.presentation.shared.FlowType;

/**
 * Created by frederickkobo on 2017/02/02.
 */

public class AddEditFragment extends Fragment {

    private View rootView;
    private HomeView homeView;
    private EditText edTitle;
    private EditText edDescription;
    private EditText edStartDate;
    private EditText edEndDate;
    private CheckBox cbIsBillable;
    private CheckBox cbIsActive;
    private Button btnSubmit;
    private FlowType flowType;
    private Project project;

    public static AddEditFragment newInstance(FlowType flowType, Project project, HomeView homeView) {
        AddEditFragment fragment = new AddEditFragment();
        fragment.homeView = homeView;
        fragment.flowType = flowType;
        fragment.project = project;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_edit, container, false);
        setUpComponents();
        setUpComponentListeners();
        return rootView;
    }

    private void setUpComponents() {
        edTitle = (EditText) rootView.findViewById(R.id.ed_title);
        edDescription = (EditText) rootView.findViewById(R.id.ed_description);
        edStartDate = (EditText) rootView.findViewById(R.id.ed_start_date);
        edEndDate = (EditText) rootView.findViewById(R.id.ed_end_date);
        cbIsBillable = (CheckBox) rootView.findViewById(R.id.cb_is_billable);
        cbIsActive = (CheckBox) rootView.findViewById(R.id.cb_is_active);
        btnSubmit = (Button) rootView.findViewById(R.id.btn_submit);

        if(flowType == FlowType.EDIT  && project != null) {
            edTitle.setText(project.getTitle());
            edDescription.setText(project.getDescription());
            edStartDate.setText(project.getStartDate());
            edEndDate.setText(project.getEndDate());
            cbIsBillable.setChecked(project.getIsBillable());
            cbIsActive.setChecked(project.getIsActive());
        }
    }

    private void setUpComponentListeners() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidCredentials()) {
                    if(flowType == FlowType.EDIT) {
                        homeView.editProject(project.getPk(), edTitle.getText().toString(), edDescription.getText().toString(), edStartDate.getText().toString(), edEndDate.getText().toString(), cbIsBillable.isChecked(), cbIsActive.isChecked());
                    } else {
                        homeView.addProject(edTitle.getText().toString(), edDescription.getText().toString(), edStartDate.getText().toString(), edEndDate.getText().toString(), cbIsBillable.isChecked(), cbIsActive.isChecked());
                    }
                }
            }
        });
    }

    private boolean isValidCredentials() {
        boolean isValid = true;
        if (edTitle.getText().toString().isEmpty()){
            isValid = false;
            edTitle.setError("This field cannot to empty");
        }

        if(edDescription.getText().toString().isEmpty()) {
            isValid = false;
            edDescription.setError("This field cannot to empty");
        }

        if (edStartDate.getText().toString().isEmpty()) {
            isValid = false;
            edStartDate.setError("This field cannot to empty");
        }
        return isValid;
    }
}
