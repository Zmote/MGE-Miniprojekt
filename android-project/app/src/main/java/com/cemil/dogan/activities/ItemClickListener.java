package com.cemil.dogan.activities;

/**
 * Created by Dogan on 25.10.15.
 */


 import android.support.v7.app.AppCompatActivity;
 import android.view.View;

 import domain.Gadget;
 import domain.Loan;
 import domain.Reservation;

public interface ItemClickListener {
    void onItemClicked(Gadget currentGadget);
    void initiateActivity (Class a);
    void onItemClicked(Loan currentReservation);
}