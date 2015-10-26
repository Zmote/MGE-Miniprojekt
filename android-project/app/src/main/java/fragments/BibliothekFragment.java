package fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cemil.dogan.activities.IToolbarSetter;
import com.cemil.dogan.activities.ItemClickListener;
import com.cemil.dogan.activities.LoginActivity;
import com.cemil.dogan.activities.R;

import service.Callback;
import service.LibraryService;

/**
 * A simple {@link Fragment} subclass.
 */
public class BibliothekFragment extends Fragment {


    public BibliothekFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        IToolbarSetter toolbar = (IToolbarSetter) getActivity();
        toolbar.setTitle("Bibliothek");
        final ItemClickListener mListener = (ItemClickListener) getActivity();
        View root = inflater.inflate(R.layout.fragment_bibliothek, container, false);
        final EditText serverAdress = (EditText) root.findViewById(R.id.input_serverAdres);
        Button serverButton = (Button) root.findViewById(R.id.serverButton);
        serverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryService.logout(new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        LibraryService.setServerAddress(serverAdress.getText().toString());
                        Toast toast = Toast.makeText(getActivity(), "Server-Adresse geändert!", Toast.LENGTH_SHORT);
                        toast.show();
                        mListener.initiateActivity(LoginActivity.class);
                    }

                    @Override
                    public void onError(String message) {
                        Toast toast = Toast.makeText(getActivity(), "Loggout on Server-Change failed", Toast.LENGTH_SHORT);
                        toast.show();
                        LibraryService.setServerAddress(LibraryService.getPerviousServerUrl());
                    }
                });

            }
        });
        return root;
    }


}
