package fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cemil.dogan.activities.DividerItemDecoration;
import com.cemil.dogan.activities.IToolbarSetter;
import com.cemil.dogan.activities.ItemClickListener;
import com.cemil.dogan.activities.R;

import java.util.ArrayList;
import java.util.List;

import adapters.ReservationAdapter;
import domain.Gadget;
import domain.Loan;
import domain.Reservation;
import service.Callback;
import service.LibraryService;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFragment extends Fragment {
    private ReservationAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ItemClickListener mListener;
    private List<Gadget> myGadgets = new ArrayList<>();


    public ReservationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        IToolbarSetter toolbar = (IToolbarSetter) getActivity();
        toolbar.setTitle("Reservationen");
        final View root = inflater.inflate(R.layout.fragment_reservation, container, false);
        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                adapter = new ReservationAdapter(mListener, input);
                adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                    }
                });
                recyclerView = (RecyclerView) root.findViewById(R.id.view);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addItemDecoration(
                        new DividerItemDecoration(getActivity(), null));
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
            }

            @Override
            public void onError(String message) {

            }
        });

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
}
