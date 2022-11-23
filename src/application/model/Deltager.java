package application.model;

import java.util.ArrayList;

public class Deltager extends Person {

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private boolean foredragsholder;
    private boolean privatPerson;
    private String firma;

    public Deltager(String navn, String adresse) {
        super(navn, adresse);
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            tilmelding.setDeltager(this);
        }
    }

    public void removeTilmelding(Tilmelding tilmelding) {
        if (tilmeldinger.contains(tilmelding)) {
            tilmeldinger.remove(tilmelding);
            tilmelding.setDeltager(null);
        }
    }


}
