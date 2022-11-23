package Application.Model;

import java.util.ArrayList;

public class Deltager extends Person {

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private boolean foredragsholder;
    private boolean privatPerson;
    private String firma;

    public Deltager(String navn, String adresse) {
        super(navn, adresse);

    }
}
