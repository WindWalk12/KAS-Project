package gui;

import application.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {


    @Override
    public void init() {
        Controller.initStorage();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Konference administrations system");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabOversigt = new Tab("Oversigt");
        tabPane.getTabs().add(tabOversigt);

        OversigtPane oversigtPane = new OversigtPane();
        tabOversigt.setContent(oversigtPane);
        tabOversigt.setOnSelectionChanged(event -> oversigtPane.updateList());

        Tab tabKonference = new Tab("Konference/Udflugter");
        tabPane.getTabs().add(tabKonference);

        KonferencePane konferencePane = new KonferencePane();
        tabKonference.setContent(konferencePane);
        tabKonference.setOnSelectionChanged(event -> konferencePane.updateList());

        Tab tabHotel = new Tab("Hotel/Services");
        tabPane.getTabs().add(tabHotel);

        HotelPane hotelPane = new HotelPane();
        tabHotel.setContent(hotelPane);
        tabHotel.setOnSelectionChanged(event -> hotelPane.updateList());

    }

}
