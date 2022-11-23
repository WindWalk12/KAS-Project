package application.model;

import java.util.ArrayList;

public class Ledsager extends Person {

    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Ledsager(String navn, String adresse) {
        super(navn, adresse);
    }

    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public void addUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
            udflugt.addLedsager(this);
        }
    }
    public void removeUdflugt(Udflugt udflugt) {
        if (udflugter.contains(udflugt)) {
            udflugter.remove(udflugt);
            udflugt.removeLedsager(this);
        }
    }

}
