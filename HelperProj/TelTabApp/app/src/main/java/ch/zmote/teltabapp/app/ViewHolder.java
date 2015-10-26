package ch.zmote.teltabapp.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder{
    private View parent;
    private TextView text;
    private Context context;
    private int pos;

    public ViewHolder(Context context, View itemView, TextView text) {
        super(itemView);
        this.parent = itemView;
        this.text = text;
        this.context = context;
    }

    public void setPos(int pos){
        this.pos = pos;
    }

    public TextView getTextView(){
        return text;
    }

    public View getView(){
        return parent;
    }

}
