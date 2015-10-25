package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cemil.dogan.activities.ItemClickListener;
import com.cemil.dogan.activities.R;
import com.cemil.dogan.activities.ViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import domain.Reservation;

public class ReservationAdapter extends RecyclerView.Adapter<ViewHolder>{
    private Context context;
    private ItemClickListener mListener;
    private List<Reservation> rList;

    public ReservationAdapter(ItemClickListener context){
        mListener = context;
    }
    public ReservationAdapter(ItemClickListener context, List<Reservation> rList){
        mListener = context;
        this.rList = rList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.column_row_reservations, viewGroup, false);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView availability = (TextView) v.findViewById(R.id.date);
        ViewHolder vh = new ViewHolder(context,v, name,null, availability);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        viewHolder.setPos(i);
        viewHolder.getNameView().setText(rList.get(i).getGadget().getName());
        viewHolder.getAvailabilityView().setText(format.format(rList.get(i).getReservationDate()));
        viewHolder.getView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClicked(rList.get(i).getGadget());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public void update(Object data) {
        rList = (List<Reservation>) data;
        notifyDataSetChanged();
    }
}
