package gui;

import application.controller.Controller;

public class App {

    public static void main(String[] args) {

        Controller.initStorage();
        System.out.println(Controller.getTilmeldninger().get(0).udregnSamletPris());
        System.out.println(Controller.getTilmeldninger().get(1).udregnSamletPris());
        System.out.println(Controller.getTilmeldninger().get(2).udregnSamletPris());
        System.out.println(Controller.getTilmeldninger().get(3).udregnSamletPris());
    }

}
