package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {

    private LocalDate dato;
    private double pris;
    private String navn;
    private Konference konference;

    private ArrayList<Ledsager> ledsagere = new ArrayList<>();

    public Udflugt(LocalDate dato, double pris, String navn, Konference konference) {
        this.dato = dato;
        this.pris = pris;
        this.navn = navn;
        this.konference = konference;
    }

    //--------------------------------------------------------------------------------------------------------
    // composition 0..* --- 1 Konference
    public Konference getKonference() {
        return konference;
    }

    //--------------------------------------------------------------------------------------------------------
    // association --> 0..* Ledsager
    public ArrayList<Ledsager> getLedsager() {
        return new ArrayList<>(ledsagere);
    }
    public void addLedsager(Ledsager ledsager) {
        if (!ledsagere.contains(ledsager)) {
            ledsagere.add(ledsager);
            ledsager.addUdflugt(this);
        }
    }
    public void removeLedsager(Ledsager ledsager) {
        if (ledsagere.contains(ledsager)) {
            ledsagere.remove(ledsager);
            ledsager.removeUdflugt(this);
        }
    }

    public double getPris() {
        return pris;
    }

    public String getNavn() {
        return navn;
    }

    //--------------------------------------------------------------------------------------------------------
}
