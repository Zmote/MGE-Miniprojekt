package ch.zmotions.myapplication1.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public View parent;
    public TextView textView;

    public ViewHolder(View parent, TextView textView) {
        super(parent);
        this.parent = parent;
        this.textView = textView;
    }
}