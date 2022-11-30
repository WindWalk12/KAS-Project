package gui;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import application.model.Service;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HotelPane extends GridPane {
    private ListView<Hotel> lvwHoteller;
    private ListView<Service> lvwServices;
    private Button btnCreate, btnUpdate, btnServiceCreate, btnServiceUpdate;
    private HBox btnHoteBox, btnServBox;
    private ServiceWindow serviceWindow;

    private final TextField txfPris = new TextField();
    private final TextField txfDobbelt = new TextField();

    private HotelOpretWindow hotelWindow;

    public HotelPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        hotelWindow = new HotelOpretWindow("Opret hotel",new Stage());

        Label lblKonf = new Label("Hoteller:");
        this.add(lblKonf, 0, 0);

        Label lblPris = new Label("Pris:");
        this.add(txfPris, 0, 7, 1, 1);
        this.add(lblPris, 0, 6);

        Label lblPrisDobbelt = new Label("Pris Dobbeltværelse:");
        this.add(lblPrisDobbelt, 0, 8);
        this.add(txfDobbelt, 0, 9, 1, 1);



        lvwHoteller = new ListView<>();
        this.add(lvwHoteller, 0, 1, 1, 3);
        lvwHoteller.setPrefWidth(200);
        lvwHoteller.setPrefHeight(150);
        lvwHoteller.getItems().setAll(Controller.getHoteller());
        ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged();
        lvwHoteller.getSelectionModel().selectedItemProperty().addListener(listener);

        btnHoteBox = new HBox();
        this.add(btnHoteBox, 0, 4);
        btnHoteBox.setSpacing(20);

        btnCreate = new Button("Opret");
        btnHoteBox.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> this.createHotelAction());

        btnUpdate = new Button("Opdater");
        btnHoteBox.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> this.updateHotelAction());

        Label lblhote = new Label("Services:");
        this.add(lblhote, 1, 0);

        lvwServices = new ListView<>();
        this.add(lvwServices, 1, 1, 1, 3);
        lvwServices.setPrefWidth(200);
        lvwServices.setPrefHeight(150);

        btnServBox = new HBox();
        this.add(btnServBox, 1, 4);
        btnServBox.setSpacing(20);

        btnServiceCreate = new Button("Opret");
        btnServBox.getChildren().add(btnServiceCreate);
        btnServiceCreate.setOnAction(event -> this.createServAction());

        btnServiceUpdate = new Button("Opdater");
        btnServBox.getChildren().add(btnServiceUpdate);
        btnServiceUpdate.setOnAction(event -> this.updateServAction());

    }

    // -------------------------------------------------------------------------

    public void updateList() {
        lvwHoteller.getItems().setAll(Controller.getHoteller());
    }

    private void createHotelAction() {
       hotelWindow.showAndWait();
       lvwHoteller.getItems().setAll(Controller.getHoteller());
    }

    private void updateHotelAction() {
        Hotel hotels = lvwHoteller.getSelectionModel().getSelectedItem();
        if (hotels != null) {
            HotelUpdateWindow hotelUpdateWindow = new HotelUpdateWindow("Updater Hotel", new Stage(), hotels);
            hotelUpdateWindow.showAndWait();

            // Wait for the modal dialog to close

            int selectIndex = lvwHoteller.getSelectionModel().getSelectedIndex();
            lvwHoteller.getItems().setAll(Controller.getHoteller());
            lvwHoteller.getSelectionModel().select(selectIndex);
        }
    }
    private void selectedHotelChanged() {
        Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
        if (hotel != null) {
            txfPris.setText(String.format("%.1f dkk", hotel.getPris()));
            txfDobbelt.setText(String.format("%.1f dkk", hotel.getPrisDobbeltVaerelse()));
            lvwServices.getItems().setAll(hotel.getServices());
        } else {
            lvwServices.getItems().clear();
        }
    }

    private void createServAction() {

        Hotel selectedHotel = lvwHoteller.getSelectionModel().getSelectedItem();
        if (selectedHotel != null) {
            serviceWindow = new ServiceWindow("Opret Service", new Stage(), selectedHotel);
            serviceWindow.showAndWait();
            lvwServices.getItems().setAll(selectedHotel.getServices());
        }
    }
    private void updateServAction() {
        Service selectedService = lvwServices.getSelectionModel().getSelectedItem();
        if (selectedService != null) {
            ServiceUpdateWindow serviceUpdateWindow = new ServiceUpdateWindow("Opret Service", new Stage(), selectedService);
            serviceUpdateWindow.showAndWait();

            int selectIndex = lvwServices.getSelectionModel().getSelectedIndex();
            lvwServices.getItems().setAll(lvwHoteller.getSelectionModel().getSelectedItem().getServices());
            lvwServices.getSelectionModel().select(selectIndex);


        }


    }
}
