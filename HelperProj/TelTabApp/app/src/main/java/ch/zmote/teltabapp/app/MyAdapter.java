package ch.zmote.teltabapp.app;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> implements Observer{
    private Context context;
    private ItemClickListener mListener;

    MyAdapter(ItemClickListener context){
        mListener = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.column_row, viewGroup, false);
        TextView text = (TextView) v.findViewById(R.id.textView);
        ViewHolder vh = new ViewHolder(context,v, text);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.setPos(i);
        viewHolder.getTextView().setText(MyApplication.notes.get(i).getTitle());
        viewHolder.itemView.setBackgroundColor(Color.rgb(100,100,200));
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return MyApplication.notes.size();
    }

    @Override
    public void update(Observable observable, Object data) {
        Note currentNote = (Note) observable;
        int getCurrentPos = MyApplication.notes.getPos(currentNote);
        notifyItemChanged(getCurrentPos);
    }

    public void startObservingNotes(){
        for(int i = 0; i < MyApplication.notes.size();i++){
            MyApplication.notes.get(i).addObserver(this);
        }
    }

    public void stopObservingNotes(){
        for(int i = 0; i< MyApplication.notes.size();i++){
            MyApplication.notes.get(i).deleteObserver(this);
        }
    }
}
