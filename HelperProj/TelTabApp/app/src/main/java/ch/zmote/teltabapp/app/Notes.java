package ch.zmote.teltabapp.app;

import java.util.ArrayList;
import java.util.List;

public class Notes {
    private List<Note> notes;

    Notes(){
        notes = new ArrayList<>();
    }

    public void add(Note note){
        notes.add(note);
    }

    public Note get(int position){
        return notes.get(position);
    }

    public void remove(int position){
        notes.remove(position);
    }

    public int getPos(Note note){
        for(int i = 0; i < notes.size();i++){
            if(notes.get(i).equals(note)){
                return i;
            }
        }
        return -1;
    };

    public int size(){
        return notes.size();
    }
}
