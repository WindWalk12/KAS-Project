package application.controller;

import application.model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    public static void initStorage() {

        //opret konferencer
        //opret deltagere og ledsagere
        //opret hoteller
        //opret services

    }

    // -------------------------------------------------------------------------

    public static Konference createKonference(String navn, String adresse, LocalDate startDato, int antalDage) {
        Konference konference = new Konference(navn, adresse, startDato, antalDage);
        Storage.addKonference(konference);
        return konference;
    }

    public static void deleteKonference(Konference konference) {
        Storage.removeKonference(konference);
    }

    public static ArrayList<Konference> getKonferencerer() {
        return Storage.getKonferencer();
    }

    // -------------------------------------------------------------------------

    public static Tilmelding createTilmeldning(Deltager deltager, Konference konference, Hotel hotel, Ledsager ledsager, ArrayList<Service> services) {
        Tilmelding tilmelding = new Tilmelding(deltager, konference, hotel, ledsager, services);
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

    public static void deleteUdflugt(Udflugt udflugt) {
        Storage.removeUdflugt(udflugt);
    }

    public static ArrayList<Udflugt> getUdflugter() {
        return Storage.getUdflugter();
    }

    // -------------------------------------------------------------------------

    public static Hotel createHotel(double pris, double prisDobbeltVaerelse, String navn, String adresse) {
        Hotel hotel = new Hotel(pris, prisDobbeltVaerelse, navn, adresse);
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

    public static void deleteService(Service service) {
        Storage.removeService(service);
    }

    public static ArrayList<Service> getServices() {
        return Storage.getServices();
    }
}
