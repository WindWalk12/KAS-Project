package Application.Model;

import java.util.ArrayList;

public class Hotel {
    private int vaerelsesNr;
    private double pris;
    private String navn;
    private String adresse;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Service> services = new ArrayList<>();

    public Hotel(int vaerelsesNr, String navn, String adresse) {
        this.vaerelsesNr = vaerelsesNr;
        this.navn = navn;
        this.adresse = adresse;
    }
}
