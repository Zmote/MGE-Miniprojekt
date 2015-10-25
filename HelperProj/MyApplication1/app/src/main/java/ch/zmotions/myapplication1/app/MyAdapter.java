package ch.zmotions.myapplication1.app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ViewHolder viewHolder = new ViewHolder(v, textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(MyApplication.notes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return MyApplication.notes.size();
    }

    public void add(int position, Note module) {
        MyApplication.notes.add(position, module);
        notifyItemInserted(position);
    }

    public void remove(Note module) {
        int position = MyApplication.notes.indexOf(module);
        MyApplication.notes.remove(position);
        notifyItemRemoved(position);
    }
}