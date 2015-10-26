package ch.zmote.teltabapp.app;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ContentActivity extends AppCompatActivity {
    Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.view3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent in = getIntent();
        Bundle args = in.getExtras();
        int position = (Integer) args.getSerializable("position");
        //currentNote = (Note) args.getSerializable("currentNote") --> so nicht, da das Serializable-Objekt
        //ein sperates Note-Objekt erstellt mit der Datei, die es von deiner Application Objekt geholt hat.
        //Das heisst currentNote ist separat vom Application-Objekt, eigenständig, und wird auch nicht
        //observed, darum fand keine Aktualisierung statt.
        currentNote = MyApplication.notes.get(position);
        String text = currentNote.getTitle();
        String content = currentNote.getContent();

        EditText title = (EditText) findViewById(R.id.title);
        EditText activity_content = (EditText) findViewById(R.id.textContent);
        title.setText(text);
        activity_content.setText(content);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_content, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
