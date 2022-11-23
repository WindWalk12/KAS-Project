package Application.Model;

import java.util.ArrayList;

public class Hotel {

    private double pris;
    private double prisDobbeltVaerelse;
    private String navn;
    private String adresse;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Service> services = new ArrayList<>();
    private ArrayList<Konference> konferencer = new ArrayList<>();


    public Hotel(double pris, double prisDobbeltVaerelse, String navn, String adresse) {
        this.prisDobbeltVaerelse = prisDobbeltVaerelse;
        this.pris = pris;
        this.navn = navn;
        this.adresse = adresse;
    }
    //---------------------------------------------------------------------------------------------------------------------

    /*
    composition 1 --- 0..* Services
     */
    public ArrayList<Service> getServices() {
        return new ArrayList<>(services);
    }

    public Service createService(String navn, double pris) {
        Service service = new Service(navn, pris);
        services.add(service);
        return service;
    }

    public void removeService(Service service) {
        if (services.contains(service)) {
            services.remove(service);
        }
    }
    //---------------------------------------------------------------------------------------------------------------------
    // association --> 0..* Tilmeldinger

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            tilmelding.setHotel(this);
        }
    }

    public void removeTilmelding(Tilmelding tilmelding) {
        if (tilmeldinger.contains(tilmelding)) {
            tilmeldinger.remove(tilmelding);
            tilmelding.setHotel(null);
        }
    }
}
