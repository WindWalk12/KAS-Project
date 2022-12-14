package application.model;

import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {

    private String navn;
    private String adresse;
    private LocalDate startDato;
    private int antalDage;
    private double prisPrDag;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private ArrayList<Hotel> hoteller = new ArrayList<>();

    public Konference(String navn, String adresse, LocalDate startDato, int antalDage, double prisPrDag) {
        this.navn = navn;
        this.adresse = adresse;
        this.startDato = startDato;
        this.antalDage = antalDage;
        this.prisPrDag = prisPrDag;
    }
    //------------------------------------------------------------------------------------------------------------------
    // association --> 0..* Hoteller

    public ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }
    public void addHotel(Hotel hotel) {
        if (!hoteller.contains(hotel)) {
            hoteller.add(hotel);
            hotel.addKonference(this);
        }
    }
    public void removeHotel(Hotel hotel) {
        if (hoteller.contains(hotel)) {
            hoteller.remove(hotel);
            hotel.removeKonference(this);
        }
    }
    //----------------------------------------------------------------------------------------------------------------
    // association --> 0..* Tilmeldinger

    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }
    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            tilmelding.setKonference(this);
        }
    }


    //--------------------------------------------------------------------------------------------------------------
    // composition 1 --- 0..* Udflugter

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }
    public Udflugt createUdflugt(LocalDate dato, double pris, String navn) {
        Udflugt udflugt = new Udflugt(dato,pris,navn,this);
        udflugter.add(udflugt);
        Storage.addUdflugt(udflugt);
        return udflugt;
    }

    public double getPrisPrDag() {
        return prisPrDag;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public int getAntalDage() {
        return antalDage;
    }

    public void setAntalDage(int antalDage) {
        this.antalDage = antalDage;
    }

    public void setPrisPrDag (int prisPrDag){
        this.prisPrDag = prisPrDag;
    }

    public double getFortjeneste() {
        double fortjenste = 0;
        for (Tilmelding t : tilmeldinger) {
            fortjenste += t.getSamletPris();
        }
        return fortjenste;
    }

    @Override
    public String toString() {
        return navn;
    }
}
