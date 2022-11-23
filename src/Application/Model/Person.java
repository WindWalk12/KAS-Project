package Application.Model;

public class Person {

private String navn;
private int alder;
private String adresse;
private String tlfNummer;
private String email;

    public Person(String navn, int alder, String adresse, String tlfNummer, String email) {
        this.navn = navn;
        this.alder = alder;
        this.adresse = adresse;
        this.tlfNummer = tlfNummer;
        this.email = email;
    }
}
