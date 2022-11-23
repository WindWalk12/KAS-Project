package Application.Model;

import java.util.ArrayList;

public class Ledsager extends Person {

    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private Tilmelding tilmelding;

    public Ledsager(String navn, int alder, String adresse, String tlfNummer, String email) {
        super(navn, alder, adresse, tlfNummer, email);



    }
}
