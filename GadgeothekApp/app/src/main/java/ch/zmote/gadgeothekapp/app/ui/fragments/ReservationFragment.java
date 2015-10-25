package ch.zmote.gadgeothekapp.app.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ch.zmote.gadgeothekapp.app.R;
import ch.zmote.gadgeothekapp.app.domain.Gadget;
import ch.zmote.gadgeothekapp.app.domain.Reservation;
import ch.zmote.gadgeothekapp.app.service.Callback;
import ch.zmote.gadgeothekapp.app.service.LibraryService;
import ch.zmote.gadgeothekapp.app.ui.DividerItemDecoration;
import ch.zmote.gadgeothekapp.app.ui.ItemClickListener;
import ch.zmote.gadgeothekapp.app.ui.GadgetAdapter;
import ch.zmote.gadgeothekapp.app.ui.ReservationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReservationFragment extends Fragment implements ItemClickListener {
    private ReservationAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ItemClickListener mListener;
    private List<Gadget> myGadgets = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public ReservationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                        new DividerItemDecoration(getActivity(),null));
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
    public void onItemClicked(Gadget currentGadget) {

    }
}
