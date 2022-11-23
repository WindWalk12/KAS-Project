package Application.Model;

import java.util.ArrayList;

public class Hotel {

    private double pris;
    private double prisDobbeltVaerelse;
    private String navn;
    private String adresse;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Service> services = new ArrayList<>();

    public Hotel(double pris, double prisDobbeltVaerelse, String navn, String adresse) {
        this.prisDobbeltVaerelse = prisDobbeltVaerelse;
        this.pris = pris;
        this.navn = navn;
        this.adresse = adresse;
    }
}
