package fredkobo.co.za.codeproject.presentation.shared;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fredkobo.co.za.codeproject.R;

/**
 * Created by frederickkobo on 2017/02/02.
 */

public class GenericContextualDialog extends DialogFragment {

    private String content;

    public static GenericContextualDialog newInstance(String dialogContent) {
        GenericContextualDialog dialog = new GenericContextualDialog();
        dialog.setContent(dialogContent);
        dialog.setCancelable(true);
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.generic_contextual_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView tvContent = (TextView) view.findViewById(R.id.tv_dialog_content);
        tvContent.setText(content);
        Button positiveButton = (Button) view.findViewById(R.id.positive_button);
        positiveButton.setText("Okay");
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setContent(String content) {
        this.content = content;
    }
}
