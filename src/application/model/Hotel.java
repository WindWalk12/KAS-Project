package application.model;

import storage.Storage;

import java.util.ArrayList;

public class Hotel {

    private double pris;
    private double prisDobbeltVaerelse;
    private String navn;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Service> services = new ArrayList<>();
    private ArrayList<Konference> konferencer = new ArrayList<>();


    public Hotel(double pris, double prisDobbeltVaerelse, String navn) {
        this.prisDobbeltVaerelse = prisDobbeltVaerelse;
        this.pris = pris;
        this.navn = navn;
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
        Storage.addService(service);
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
    //---------------------------------------------------------------------------------------------------------------------
    // association --> 0..* Konferencer

    public ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }
    public void addKonference(Konference konference) {
        if (!konferencer.contains(konference)) {
            konferencer.add(konference);
            konference.addHotel(this);
        }
    }
    public void removeKonference(Konference konference) {
        if (konferencer.contains(konference)) {
            konferencer.remove(konference);
            konference.removeHotel(this);
        }
    }

    public Double getPris() {
        return  pris;
    }

    public Double getPrisDobbeltVaerelse() {
        return prisDobbeltVaerelse;
    }

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}








