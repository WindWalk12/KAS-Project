package Application.Model;

import java.util.ArrayList;

public class Tilmelding {
    private double samletPris;
    private Deltager deltager;
    private Konference konference;
    private Hotel hotel;
    private ArrayList<Udflugt> udflugter;
    private Ledsager ledsager;

    public Tilmelding(double samletPris, Deltager deltager, Konference konference, Hotel hotel, ArrayList<Udflugt> udflugter, Ledsager ledsager) {
        this.samletPris = samletPris;
        this.deltager = deltager;
        this.konference = konference;
        this.hotel = hotel;
        this.udflugter = udflugter;
        this.ledsager = ledsager;
    }
}
