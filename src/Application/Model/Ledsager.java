package Application.Model;

import java.util.ArrayList;

public class Ledsager extends Person {

    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private Tilmelding tilmelding;

    public Ledsager(String navn, String adresse) {
        super(navn, adresse);



    }
}
