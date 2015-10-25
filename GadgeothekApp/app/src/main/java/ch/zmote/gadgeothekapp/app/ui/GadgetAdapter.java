package ch.zmote.gadgeothekapp.app.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ch.zmote.gadgeothekapp.app.R;
import ch.zmote.gadgeothekapp.app.domain.Gadget;
import ch.zmote.gadgeothekapp.app.domain.Reservation;

public class GadgetAdapter extends RecyclerView.Adapter<ViewHolder>{
    private Context context;
    private ItemClickListener mListener;
    private List<Gadget> gadgetsList;

    public GadgetAdapter(ItemClickListener context){
        mListener = context;
    }
    public GadgetAdapter(ItemClickListener context, List<Gadget> gadgetsList){
        mListener = context;
        this.gadgetsList = gadgetsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.column_row, viewGroup, false);
        TextView name = (TextView) v.findViewById(R.id.textView);
        TextView price = (TextView) v.findViewById(R.id.price);
        ViewHolder vh = new ViewHolder(context,v, name, price, null);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.setPos(i);
        viewHolder.getNameView().setText(gadgetsList.get(i).getName());
        viewHolder.getPriceView().setText(gadgetsList.get(i).getPrice() + " CHF");
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(gadgetsList.get(i));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gadgetsList.size();
    }

    public void update(Object data) {
        gadgetsList = (List<Gadget>) data;
        notifyDataSetChanged();
    }
}
