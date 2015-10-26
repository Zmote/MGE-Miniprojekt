package com.cemil.dogan.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Stack;

import domain.Gadget;
import domain.Loan;
import fragments.BibliothekFragment;
import fragments.GadgetFragment;
import fragments.AusleihenDetailFragment;
import fragments.AusleihenFragment;
import fragments.ReservationFragment;
import fragments.SettingsFragment;
import fragments.DetailsFragment;
import service.Callback;
import service.LibraryService;


public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ItemClickListener,IToolbarSetter  {

    private DrawerLayout drawer;
    private View content;
    private String header_email;
    private String header_name;
    private Toolbar toolbar;
    enum Pages{Gadgets, Ausleihen, Reservationen, Bibliothek,DETAIL, AUSLEIHDETAIL}
    private Gadget thisGadget;
    public static Stack<Pages> pages = new Stack();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget);

        //drawer_layout --> activity_main.xml
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        content = findViewById(R.id.content);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            header_email = extras.getString("email");
            header_name =  extras.getString("name");
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Gadgets");
        //populates hamburger menu
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //gets the menu element, menu element has header and menu parts...
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);


        //getFragmentManager().beginTransaction().replace(R.id.content, new GadgetFragment()).commit();

        getFragmentManager().beginTransaction().replace(R.id.content,new GadgetFragment()).addToBackStack(null).commit();
        pages.push(Pages.Gadgets);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu ){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //opens main menu and setting menu
    @Override
    public boolean  onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                TextView name = (TextView) findViewById(R.id.header_name);
                TextView email = (TextView) findViewById(R.id.header_email);
                name.setText(header_name);
                email.setText(header_email);
                break;
            case R.id.action_settings:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new SettingsFragment())
                        .commit();
                break;
            case R.id.action_logout:
                LibraryService.logout(new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean result) {
                        Toast.makeText(getBaseContext(), "Logged out", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getBaseContext(), "Could not be logged out", Toast.LENGTH_LONG).show();
                    }
                });


        }
        return super.onOptionsItemSelected(item);
    }




    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.drawerHome :
                pages.push(Pages.Gadgets);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new GadgetFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.loans :
                pages.push(Pages.Ausleihen);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new AusleihenFragment())
                        .addToBackStack(null)
                        .commit();
                    break;
            case R.id.reservationen:
                pages.push(Pages.Reservationen);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new ReservationFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case R.id.bibliothek:
                pages.push(Pages.Bibliothek);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, new BibliothekFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
        menuItem.setChecked(true);
        drawer.closeDrawers();
        return true;
    }

    @Override
    public void onItemClicked(Loan currentLoan) {
        thisGadget = currentLoan.getGadget();
        toolbar.setTitle(thisGadget.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle args = new Bundle();
        args.putSerializable("ausleihen", currentLoan);
        AusleihenDetailFragment detailsFragment = new AusleihenDetailFragment();
        detailsFragment.setArguments(args);
        pages.push(Pages.AUSLEIHDETAIL);
        getFragmentManager().beginTransaction().replace(R.id.content, detailsFragment)
                .addToBackStack(null).commit();
    }



    @Override
    public void onItemClicked(Gadget currentGadget) {
        thisGadget = currentGadget;
        toolbar.setTitle(thisGadget.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle args = new Bundle();
        args.putSerializable("gadget", currentGadget);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(args);
        pages.push(Pages.DETAIL);
        getFragmentManager().beginTransaction().replace(R.id.content, detailsFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount() <= 1){
            finish();
        }else{
            getFragmentManager().popBackStack();
        }

    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void initiateActivity(Class a) {
        final Class currentClass = a;
        final AppCompatActivity thisActivity = this;
        startActivity(new Intent(thisActivity, currentClass));

    }

}
