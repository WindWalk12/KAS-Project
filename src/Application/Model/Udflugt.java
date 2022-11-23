package Application.Model;

import java.time.LocalDate;

public class Udflugt {

    private LocalDate dato;
    private double pris;
    private String navn;
    private Konference konference;

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
}
