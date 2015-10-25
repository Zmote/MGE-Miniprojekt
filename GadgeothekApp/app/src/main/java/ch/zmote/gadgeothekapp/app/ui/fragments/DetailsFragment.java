package ch.zmote.gadgeothekapp.app.ui.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ch.zmote.gadgeothekapp.app.R;
import ch.zmote.gadgeothekapp.app.domain.Gadget;
import ch.zmote.gadgeothekapp.app.domain.Reservation;
import ch.zmote.gadgeothekapp.app.service.Callback;
import ch.zmote.gadgeothekapp.app.service.LibraryService;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    private Reservation thisReservation;
    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_details,container,false);
        TextView priceContent = (TextView) root.findViewById(R.id.priceLarge);
        TextView manufacturerContent = (TextView) root.findViewById(R.id.manufacturerText);
        TextView stateContent = (TextView) root.findViewById(R.id.stateText);
        final TextView availabilityContent = (TextView) root.findViewById(R.id.availabilityText);
        Bundle args = getArguments();
        final Gadget thisGadget = (Gadget) args.getSerializable("gadget");
        final Button cancelResButton = (Button) root.findViewById(R.id.cancelResButton);
        final Button reservierenButton = (Button) root.findViewById(R.id.reservierenButton);
        reservierenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryService.reserveGadget(thisGadget, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        if(input){
                            getFragmentManager().beginTransaction().replace(R.id.content,new ReservationFragment())
                                    .addToBackStack(null).commit();
                        }else{
                            Toast toast = Toast.makeText(getActivity(),"Max. Reservationen erreicht!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        });
        cancelResButton.setVisibility(View.GONE);
        reservierenButton.setVisibility(View.GONE);
        priceContent.setText(thisGadget.getPrice() + ".-");
        manufacturerContent.setText(thisGadget.getManufacturer());
        stateContent.setText(thisGadget.getCondition().name());

        LibraryService.getReservationsForCustomer(new Callback<List<Reservation>>() {
            @Override
            public void onCompletion(List<Reservation> input) {
                reservierenButton.setVisibility(View.VISIBLE);
                cancelResButton.setVisibility(View.GONE);
                if (input.size() >= 1) {
                    for (int i = 0; i < input.size(); i++) {
                        if (thisGadget.equals(input.get(i).getGadget())) {
                            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                            availabilityContent.setText(format.format(input.get(i).getReservationDate()));
                            thisReservation = input.get(i);
                            reservierenButton.setVisibility(View.GONE);
                            cancelResButton.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });

        cancelResButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryService.deleteReservation(thisReservation, new Callback<Boolean>() {
                    @Override
                    public void onCompletion(Boolean input) {
                        reservierenButton.setVisibility(View.VISIBLE);
                        cancelResButton.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(String message) {
                        Toast toast = Toast.makeText(getActivity(), message,Toast.LENGTH_SHORT);
                    }
                });
            }
        });
        return root;
    }


}
