package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    public static void initStorage() {

        //konferencer
        Konference k = Controller.createKonference("Hav og Himmel", "Odense Universitet", LocalDate.of(2022, 5, 18), 3, 1500);

        //udflugter
        Udflugt u1 = k.createUdflugt(LocalDate.of(2022,5,18), 125, "Byrundtur, Odense");
        Udflugt u2 = k.createUdflugt(LocalDate.of(2022, 5, 19), 75, "Egeskov");
        Udflugt u3 = k.createUdflugt(LocalDate.of(2022,5,20),200, "Trapholt Museum, Kolding");

        //hoteller
        Hotel h1 = Controller.createHotel(1050, 1250, "Den Hvide Svane");

        Service s1 = h1.createService("WIFI",50);
        ArrayList<Service> services1 = new ArrayList<>();
        services1.add(s1);

        Hotel h2 = Controller.createHotel(700, 800, "Høtel Phønix");
        Controller.createService("Bad", 200, h2);
        Controller.createService("WIFI", 75, h2);

        Hotel h3 = Controller.createHotel(500, 600, "Pension Tusindfryd");
        Controller.createService("Morgenmad", 100, h3);

        //deltagere
        Deltager d1 =  Controller.createDeltager("Finn Madsen", "Solsikkevej 3", false, true);
        Deltager d2 = Controller.createDeltager("Niels Petersen", "Lortevej 21", false, true);
        Deltager d3 = Controller.createDeltager("Peter Sommer", "Numsevej 1", false, true);
        Deltager d4 = Controller.createDeltager("Lone Jensen", "Kaninhulvej 35", true, false); //privatPerson to be decided

        //ledsagere
        Ledsager l1 = Controller.createLedsager("Mie Sommer", "bgdsfsdfhdsfhvej 2");
        l1.addUdflugt(u2);
        l1.addUdflugt(u3);
        Ledsager l2 = Controller.createLedsager("Jan Madsen", "Hundehulvej 53");
        l2.addUdflugt(u1);
        l2.addUdflugt(u2);

        //tilmeldinger
        Tilmelding t1 = Controller.createTilmeldning(d1, k, null, null, null, 3);
        Tilmelding t2 = Controller.createTilmeldning(d2, k, h1, null, null, 3);
        Tilmelding t3 = Controller.createTilmeldning(d3, k, h1, l1, services1, 3);
        Tilmelding t4 = Controller.createTilmeldning(d4, k, h1, l2, services1, 3);

        k.addTilmelding(t1);
        k.addTilmelding(t2);
        k.addTilmelding(t3);
        k.addTilmelding(t4);

        k.addHotel(h1);
        k.addHotel(h2);
        k.addHotel(h3);

        t1.udregnSamletPris();
        t2.udregnSamletPris();
        t3.udregnSamletPris();
        t4.udregnSamletPris();

    }

    // -------------------------------------------------------------------------

    public static Konference createKonference(String navn, String adresse, LocalDate startDato, int antalDage, double prisPrDag) {
        Konference konference = new Konference(navn, adresse, startDato, antalDage, prisPrDag);
        Storage.addKonference(konference);
        return konference;
    }

    public static void deleteKonference(Konference konference) {
        Storage.removeKonference(konference);
    }

    public static ArrayList<Konference> getKonferencerer() {
        return Storage.getKonferencer();
    }

    public static void updateKonference(Konference konference, String name, String adresse, int prisPerDag, int antalDage, LocalDate startDato) {
       konference.setNavn(name);
       konference.setAdresse(adresse);
       konference.setAntalDage(antalDage);
       konference.setPrisPrDag(prisPerDag);
       konference.setStartDato(startDato);

    }

    // -------------------------------------------------------------------------

    public static Tilmelding createTilmeldning(Deltager deltager, Konference konference, Hotel hotel, Ledsager ledsager, ArrayList<Service> services, int antalDage) {
        Tilmelding tilmelding = new Tilmelding(deltager, konference, hotel, ledsager, services, antalDage);
        konference.addTilmelding(tilmelding);
        Storage.addTilmelding(tilmelding);
        return tilmelding;
    }

    public static void deleteTilmeldning(Tilmelding tilmelding) {
        Storage.removeTilmelding(tilmelding);
    }

    public static ArrayList<Tilmelding> getTilmeldninger() {
        return Storage.getTilmeldinger();
    }

    // -------------------------------------------------------------------------

    public static Deltager createDeltager(String navn, String adresse, boolean foredragsholder, boolean privatPerson) {
        Deltager deltager = new Deltager(navn, adresse, foredragsholder, privatPerson);
        Storage.addDeltager(deltager);
        return deltager;
    }

    public static void deleteDeltager(Deltager deltager) {
        Storage.removeDeltager(deltager);
    }

    public static ArrayList<Deltager> getDeltagere() {
        return Storage.getDeltagere();
    }

    // -------------------------------------------------------------------------

    public static Ledsager createLedsager(String navn, String adresse) {
        Ledsager ledsager = new Ledsager(navn, adresse);
        Storage.addLedsager(ledsager);
        return ledsager;
    }

    public static void deleteLedsager(Ledsager ledsager) {
        Storage.removeLedsager(ledsager);
    }

    public static ArrayList<Ledsager> getLedsagere() {
        return Storage.getLedsagere();
    }

    // -------------------------------------------------------------------------

    public Udflugt createUdflugt(LocalDate dato, double pris, String navn, Konference konference) {
        Udflugt udflugt = konference.createUdflugt(dato, pris, navn);
        return udflugt;
    }

    public static void deleteUdflugt(Udflugt udflugt) {
        Storage.removeUdflugt(udflugt);
    }

    public static ArrayList<Udflugt> getUdflugter() {
        return Storage.getUdflugter();
    }

    // -------------------------------------------------------------------------

    public static Hotel createHotel(double pris, double prisDobbeltVaerelse, String navn) {
        Hotel hotel = new Hotel(pris, prisDobbeltVaerelse, navn);
        Storage.addHotel(hotel);
        return hotel;
    }

    public static void deleteHotel(Hotel hotel) {
        Storage.removeHotel(hotel);
    }

    public static ArrayList<Hotel> getHoteller() {
        return Storage.getHoteller();
    }

    // -------------------------------------------------------------------------

    public static Service createService(String navn, double pris, Hotel hotel) {
        Service service = hotel.createService(navn, pris);
        return service;
    }

    public static void deleteService(Service service) {
        Storage.removeService(service);
    }

    public static ArrayList<Service> getServices() {
        return Storage.getServices();
    }
}
