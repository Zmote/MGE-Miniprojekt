package ch.zmote.teltabapp.app;

import java.io.Serializable;
import java.util.Observable;

public class Note extends Observable implements Serializable {

    private String title;
    private String content;

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public void setTitle(String title){
        this.title = title;
        setChanged();
        notifyObservers();
    }

    public void setContent(String content){
        this.content = content;
    }
}
