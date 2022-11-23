package Application.Model;

import java.util.ArrayList;

public class Deltager extends Person {

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private boolean foredragsholder;
    private boolean privatPerson;
    private String firma;

    public Deltager(String navn, int alder, String adresse, String tlfNummer, String email) {
        super(navn, alder, adresse, tlfNummer, email);

    }
}
