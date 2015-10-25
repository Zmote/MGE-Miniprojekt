package ch.zmotions.phonetabapp.app;

import java.util.ArrayList;
import java.util.List;

public class Notes {
    private List<Note> notes = new ArrayList<Note>();
    public Notes(){
        notes.add(new Note("Einkaufen", "Milch"));
        notes.add(new Note("TODOs", "MGE Ubungen"));
        notes.add(new Note("Selbstudium", ""));
    }
    public Note get(int position){
        return notes.get(position);
    }

    public int getSize(){
        return notes.size();
    }
}
