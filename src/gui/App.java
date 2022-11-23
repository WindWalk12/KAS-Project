package gui;

import application.controller.Controller;

public class App {

    public static void main(String[] args) {
        System.out.println(Controller.getTilmeldninger().get(0).udregnServicesPris());
    }

}
