package ch.zmote.gadgeothekapp.app.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ch.zmote.gadgeothekapp.app.R;
import ch.zmote.gadgeothekapp.app.service.Callback;
import ch.zmote.gadgeothekapp.app.service.LibraryService;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
    Button loginButton;
    Button registrierenButton;
    AutoCompleteTextView username;
    EditText password;
    AutoCompleteTextView email;
    AutoCompleteTextView matrikelnummer;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_signup, container, false);
        final View.OnClickListener activityListener = (View.OnClickListener) getActivity();
        loginButton = (Button) root.findViewById(R.id.login_button);
        registrierenButton = (Button) root.findViewById(R.id.registrieren_button);
        username = (AutoCompleteTextView) root.findViewById(R.id.name);
        password = (EditText) root.findViewById(R.id.password);
        email = (AutoCompleteTextView) root.findViewById(R.id.email);
        matrikelnummer = (AutoCompleteTextView) root.findViewById(R.id.matrikelnummer);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.onClick(v);
            }
        });
        registrierenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final View button = v;
                LibraryService.register(email.getText().toString(), password.getText().toString(), username.getText().toString(), matrikelnummer.getText().toString(), new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if (input) {
                            activityListener.onClick(button);
                        } else {
                            Toast toast = Toast.makeText(getActivity(),"Something went wrong!",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onError(String message) {

                    }
                });

            }
        });
        return root;
    }


}
