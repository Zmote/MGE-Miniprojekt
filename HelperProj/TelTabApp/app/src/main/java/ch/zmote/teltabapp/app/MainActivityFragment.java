package ch.zmote.teltabapp.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivityFragment extends android.app.Fragment {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter adapter;
    private ItemClickListener mListener;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        adapter = new MyAdapter(mListener);

        MyApplication.notes.add(new Note("Sport", "Krafttraining um 12.00, vergiss es nicht du Idiot! Hahahaha OMG alles geht den Bach runter!"));
        MyApplication.notes.add(new Note("Schule", "MGE fertig machen"));
        MyApplication.notes.add(new Note("C++", "Testatabgabe"));
        MyApplication.notes.add(new Note("Sport", "Krafttraining um 12.00"));
        MyApplication.notes.add(new Note("Schule", "MGE fertig machen"));
        MyApplication.notes.add(new Note("C++", "Testatabgabe"));
        MyApplication.notes.add(new Note("Sport", "Krafttraining um 12.00"));
        MyApplication.notes.add(new Note("Schule", "MGE fertig machen"));
        MyApplication.notes.add(new Note("C++", "Testatabgabe"));
        MyApplication.notes.add(new Note("Sport", "Krafttraining um 12.00"));
        MyApplication.notes.add(new Note("Schule", "MGE fertig machen"));
        MyApplication.notes.add(new Note("C++", "Testatabgabe"));
        MyApplication.notes.add(new Note("Sport", "Krafttraining um 12.00"));
        MyApplication.notes.add(new Note("Schule", "MGE fertig machen"));
        MyApplication.notes.add(new Note("C++", "Testatabgabe"));
        MyApplication.notes.add(new Note("Sport", "Krafttraining um 12.00"));
        MyApplication.notes.add(new Note("Schule", "MGE fertig machen"));
        MyApplication.notes.add(new Note("C++", "Testatabgabe"));
        MyApplication.notes.add(new Note("Sport", "Krafttraining um 12.00"));
        MyApplication.notes.add(new Note("Schule", "MGE fertig machen"));
        MyApplication.notes.add(new Note("C++", "Testatabgabe"));
        MyApplication.notes.add(new Note("Sport", "Krafttraining um 12.00"));
        MyApplication.notes.add(new Note("Schule", "MGE fertig machen"));
        MyApplication.notes.add(new Note("C++", "Testatabgabe"));

        recyclerView = (RecyclerView) root.findViewById(R.id.view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(),null));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.startObservingNotes();

        return root;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mListener = (ItemClickListener) activity;
    }

    @Override
    public void onAttach(Context activity){
        super.onAttach(activity);
        mListener = (ItemClickListener) activity;
    }

    @Override
    public void onResume(){
        super.onResume();
       // adapter.startObservingNotes();
    }

    @Override
    public void onPause(){
        super.onPause();
        //adapter.stopObservingNotes();
    }
}
