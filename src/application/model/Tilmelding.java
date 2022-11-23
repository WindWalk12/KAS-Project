package application.model;

import java.util.ArrayList;

public class Tilmelding {
    private double samletPris;
    private Deltager deltager;
    private Konference konference;
    private Hotel hotel;
    private Ledsager ledsager;
    private ArrayList<Service> services;
    private int antalDage;

    public Tilmelding( Deltager deltager, Konference konference, Hotel hotel, Ledsager ledsager, ArrayList<Service> services, int antalDage) {
        this.deltager = deltager;
        this.konference = konference;
        this.hotel = hotel;
        this.ledsager = ledsager;
        this.services = services;
        this.antalDage = antalDage;
    }

    public double udregnSamletPris() {

        if (ledsager == null) {
            return (konference.getPrisPrDag() * antalDage) + ((hotel.getPris() + udregnServicesPris()) * (antalDage-1));
        } else {
            return (konference.getPrisPrDag() * antalDage) + ((hotel.getPrisDobbeltVaerelse() + udregnServicesPris()) * (antalDage-1));
        }
    }

    public double udregnServicesPris() {
        double pris = 0;
        for (Service service : services) {
            pris += service.getPris();
        }
        return pris;
    }

    private void addService(Service s) {
        if (!services.contains(s)) {
            services.add(s);
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    // association --> 0..1 Hotel
    public Hotel getHotel() {
    return hotel;
}
    public void setHotel(Hotel hotel) {
        if (this.hotel != hotel) {
            Hotel oldHotel = this.hotel;
            if (oldHotel != null) {
                oldHotel.removeTilmelding(this);
            }
            this.hotel = hotel;
            if (hotel != null) {
                hotel.addTilmelding(this);
            }
        }
    }
//----------------------------------------------------------------------------------------------------------------------
// association --> 1..0..* Deltager
    public Deltager getDeltager() {
        return deltager;
    }

    public void setDeltager(Deltager deltager) {
        if (this.deltager != deltager) {
            Deltager oldDeltager = this.deltager;
            if (oldDeltager != null) {
                oldDeltager.removeTilmelding(this);
            }
            this.deltager = deltager;
            if (deltager != null) {
                deltager.addTilmelding(this);
            }
        }

    }
    //----------------------------------------------------------------------------------------------------------------------

    // association --> 0..* Konferencer
    public void setKonference(Konference konference) {
        if (this.konference != konference) {
            this.konference = konference;
            if (konference != null) {
                konference.addTilmelding(this);
            }
        }
    }
    //----------------------------------------------------------------------------------------------------------------------

}
