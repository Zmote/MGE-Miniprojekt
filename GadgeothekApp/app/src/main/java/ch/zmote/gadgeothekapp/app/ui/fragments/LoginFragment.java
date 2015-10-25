package ch.zmote.gadgeothekapp.app.ui.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import ch.zmote.gadgeothekapp.app.R;
import ch.zmote.gadgeothekapp.app.service.Callback;
import ch.zmote.gadgeothekapp.app.service.LibraryService;

public class LoginFragment extends Fragment{
    Button loginButton;
    Button registrierenButton;
    AutoCompleteTextView username;
    EditText password;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login,container, false);

        final View.OnClickListener activityListener = (View.OnClickListener) getActivity();
        loginButton = (Button) root.findViewById(R.id.login_button);
        registrierenButton = (Button) root.findViewById(R.id.login_registrieren_button);
        username = (AutoCompleteTextView) root.findViewById(R.id.username);
        password = (EditText) root.findViewById(R.id.password);
        registrierenButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                activityListener.onClick(v);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final View button = v;
                LibraryService.login(username.getText().toString(), password.getText().toString(), new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        //TODO: Decide what to do after login.
                    activityListener.onClick(button);
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
