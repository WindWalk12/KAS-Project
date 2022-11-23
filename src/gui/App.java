package gui;

import application.controller.Controller;

public class App {

    public static void main(String[] args) {

        Controller.initStorage();
        System.out.println("Samlet pris for Deltager 1: " + Controller.getTilmeldninger().get(0).udregnSamletPris());
        System.out.println("Samlet pris for Deltager 2: " + Controller.getTilmeldninger().get(1).udregnSamletPris());
        System.out.println("Samlet pris for Deltager 3: " + Controller.getTilmeldninger().get(2).udregnSamletPris());
        System.out.println("Samlet pris for Deltager 4: " + Controller.getTilmeldninger().get(3).udregnSamletPris());
    }

}
