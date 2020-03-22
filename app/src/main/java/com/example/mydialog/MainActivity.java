package com.example.mydialog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //JSON
    private JSONSerializer serializer;
    private ArrayList<Note> noteArrayList;

    private boolean showDividers;
    private SharedPreferences prefs;


    RecyclerView recyclerView;
    NoteAdapter adapter;

    ArrayList<Note> listNote = new ArrayList<>();
    public void createNewNote(Note note) {
        listNote.add(note);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final Button button = (Button)findViewById(R.id.button);
        //button.setOnClickListener(new View.OnClickListener()){
            //@Override
              //      public void onClick(View v){
                //DialogShowNote dialog = new DialogShowNote();
                //dialog.sendNoteSelected(tempNote);
                //dialog.show(getSupportFragmentManager(), "123");
           // }

       // };

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogNewNote dialog = new DialogNewNote();
                dialog.show(getSupportFragmentManager(), "");
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new NoteAdapter(this, listNote);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        //JSON
        serializer = new JSONSerializer("Note to Self.json", getApplicationContext());
        try{
            noteArrayList = serializer.load();
        } catch (Exception e){
            noteArrayList = new ArrayList<>();
            Log.e("Error loading notes, lol.", "Klick and hold the power off button for 10 seconds and programme will work, lol", e);
        }

}



    @Override
    protected void onResume() {
        super.onResume();

        prefs = getSharedPreferences("Note to Self", MODE_PRIVATE);
        showDividers = prefs.getBoolean("dividers", true);

        if (showDividers){
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        } else {
            if (recyclerView.getItemDecorationCount() > 0) {
                recyclerView.removeItemDecorationAt(0);
            }
        }
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
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showNote(int index) {
        DialogShowNote dialog = new DialogShowNote();
        dialog.sendNoteSelected(listNote.get(index));
        dialog.show(getSupportFragmentManager(), "");
    }

    public void saveNotes(){
        try {
            serializer.save(noteArrayList);
        } catch (Exception e){
            Log.e("Error saving Notes, lol", "Idk what happening", e);
        }
    }

    @Override
    public void onPause(){
        super.onPause();

        saveNotes();
    }
}
