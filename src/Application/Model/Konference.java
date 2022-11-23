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
}
