package ch.zmote.gadgeothekapp.app.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import ch.zmote.gadgeothekapp.app.R;
import ch.zmote.gadgeothekapp.app.domain.Gadget;
import ch.zmote.gadgeothekapp.app.service.LibraryService;
import ch.zmote.gadgeothekapp.app.ui.fragments.DetailsFragment;
import ch.zmote.gadgeothekapp.app.ui.fragments.GadgetFragment;

public class GadgetActivity extends AppCompatActivity implements ItemClickListener{
    private Toolbar toolbar;
    private GadgetFragment gadgetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Gadgets");
        setSupportActionBar(toolbar);
        gadgetFragment = new GadgetFragment();
        getFragmentManager().beginTransaction().replace(R.id.content,gadgetFragment).addToBackStack(null).commit();
    }

    @Override
    public void onItemClicked(Gadget currentGadget) {
        toolbar.setTitle(currentGadget.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle args = new Bundle();
        args.putSerializable("gadget", currentGadget);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.content, detailsFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            getFragmentManager().beginTransaction().replace(R.id.content, gadgetFragment).addToBackStack(null).commit();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar.setTitle("Gadgets");
        }

        return super.onOptionsItemSelected(item);
    }

}
