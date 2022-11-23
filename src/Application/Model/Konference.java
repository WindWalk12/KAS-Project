package Application.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {

    private String navn;
    private String adresse;
    private LocalDate startDato;
    private int antalDage;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private ArrayList<Hotel> hoteller = new ArrayList<>();

    public Konference(String navn, String adresse, LocalDate startDato, int antalDage) {
        this.navn = navn;
        this.adresse = adresse;
        this.startDato = startDato;
        this.antalDage = antalDage;
    }
    //------------------------------------------------------------------------------------------------------------------
    // association --> 0..* Konferencer

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
}
