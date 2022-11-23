package application.model;

import java.util.ArrayList;

public class Deltager extends Person {

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private boolean foredragsholder;
    private boolean privatPerson;

    public Deltager(String navn, String adresse, boolean foredragsholder, boolean privatPerson) {
        super(navn, adresse);
        this.foredragsholder = foredragsholder;
        this.privatPerson = privatPerson;
    }
    //--------------------------------------------------------------------------------------------------------
    // association --> 0..* Tilmeldinger
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
    //--------------------------------------------------------------------------------------------------------

}
