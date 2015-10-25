package ch.zmote.gadgeothekapp.app.ui;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ch.zmote.gadgeothekapp.app.R;
import ch.zmote.gadgeothekapp.app.service.LibraryService;
import ch.zmote.gadgeothekapp.app.ui.fragments.LoginFragment;
import ch.zmote.gadgeothekapp.app.ui.fragments.SignupFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Gadge-o-App");
        toolbar.setSubtitle("Willkomen bei der Gadgeothek-App!");
        setSupportActionBar(toolbar);

        LibraryService.setServerAddress("http://mge2.dev.ifs.hsr.ch/public");
        getFragmentManager().beginTransaction().replace(R.id.fragment_placeholder,new LoginFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_in, R.anim.enter_out);
        fragmentTransaction.setTransition(200);
        String tag = v.getTag().toString();
        if(tag.equals("login_vonRegistrierung")){
            fragmentTransaction.replace(R.id.fragment_placeholder, new LoginFragment()).addToBackStack(null).commit();
        }
        if(tag.equals("registrierung_vonLogin")){
            fragmentTransaction.replace(R.id.fragment_placeholder, new SignupFragment()).addToBackStack(null).commit();
        }
        if(tag.equals("login_vonLogin")){
            startActivity(new Intent(this, GadgetActivity.class));
        }
        if(tag.equals("registrierung_vonRegistrierung")){
            fragmentTransaction.replace(R.id.fragment_placeholder, new LoginFragment()).addToBackStack(null).commit();
        }
    }
}
