package ch.zmote.teltabapp.app;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment {
    Bundle args;
    Note currentNote;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.content,container,false);
        TextView title  = (TextView) root.findViewById(R.id.title);
        TextView content = (TextView) root.findViewById(R.id.textContent);
        args = getArguments();
        currentNote = (Note) args.getSerializable("currentNote");
        title.setText(currentNote.getTitle());
        content.setText(currentNote.getContent());
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentNote.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return root;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
