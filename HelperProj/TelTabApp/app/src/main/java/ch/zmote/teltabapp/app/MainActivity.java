package ch.zmote.teltabapp.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
    boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        int fragmentHolder = R.id.fragment;

        if(findViewById(R.id.fragment2) != null) {
            twoPane = true;
            fragmentHolder = R.id.fragment2;
            toolbar = (Toolbar) findViewById(R.id.view2);
        }

        setSupportActionBar(toolbar);
        getFragmentManager()
                .beginTransaction()
                .replace(fragmentHolder, new MainActivityFragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int position) {
        Bundle args = new Bundle();
        args.putSerializable("position", position);
        args.putSerializable("twopane", twoPane);

        if(twoPane){
            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setArguments(args);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.tabContent, contentFragment)
                    .commit();
        }else{
            Intent in = new Intent(this,ContentActivity.class);
            in.putExtras(args);
            startActivity(in);
        }

    }
}
