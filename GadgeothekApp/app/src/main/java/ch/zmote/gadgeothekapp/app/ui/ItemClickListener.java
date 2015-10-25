package ch.zmote.gadgeothekapp.app.ui;

import ch.zmote.gadgeothekapp.app.domain.Gadget;
import ch.zmote.gadgeothekapp.app.domain.Reservation;

public interface ItemClickListener {
    void onItemClicked(Gadget currentGadget);
}
