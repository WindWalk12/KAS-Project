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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HotelPane extends GridPane {
    private ListView<Hotel> lvwHoteller;
    private ListView<Service> lvwServices;
    private Button btnCreate, btnUpdate, btnServiceCreate, btnServiceUpdate;
    private HBox btnHoteBox, btnServBox;
    private ServiceWindow serviceWindow;

    public HotelPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblKonf = new Label("Hoteller:");
        this.add(lblKonf, 0, 0);

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
        btnCreate.setOnAction(event -> this.createHoteAction());

        btnUpdate = new Button("Opdater");
        btnHoteBox.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> this.updateHoteAction());

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

    private void createHoteAction() {
    }

    private void updateHoteAction() {
    }

    private void selectedHotelChanged() {
        Hotel hotel = lvwHoteller.getSelectionModel().getSelectedItem();
        if (hotel != null) {
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
