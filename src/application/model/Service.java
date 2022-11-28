package application.model;

public class Service {

    private String navn;
    private double pris;

    public Service (String navn, double pris){
        this.navn = navn;
        this.pris = pris;

    }

    public Double getPris() {
        return pris;
    }
    public String getNavn() { return navn; }

    @Override
    public String toString() {
        return navn + " (" + pris + " dkk)";
    }

}
