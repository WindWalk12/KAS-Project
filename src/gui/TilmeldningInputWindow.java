package gui;

import application.controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import storage.Storage;

import javax.swing.*;

public class TilmeldningInputWindow extends Stage {

    public TilmeldningInputWindow(String title, Stage owner) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setHeight(600);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private TextField txfName, txfAddress;
    private TextField txfLName = new TextField();
    private TextField txfLAddress = new TextField();
    Label lblLName = new Label("Ledsagers navn:");
    Label lblLAddress = new Label("Ledsagers Adresse:");
    Label lblServ = new Label("Services");
    Label lblUdflugter = new Label("Udflugter?");
    private CheckBox chkFord, chkPriv, chkLeds, chkHotel;
    private CheckBox[] chkServ = new CheckBox[3];
    private CheckBox[] chkUdflugter = new CheckBox[3];
    private VBox vBoxServ, vBoxUdflugter;
    private ListView<Hotel> lvwHotels;
    private ComboBox<Konference> cboKonf = new ComboBox<>();
    private Deltager actualDeltager = null;
    private Ledsager actualLedsager = null;
    private Tilmelding actualTilmeldning = null;

    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblName = new Label("Name:");
        pane.add(lblName, 0, 0);

        txfName = new TextField();
        pane.add(txfName, 1, 0);

        Label lblAddress = new Label("Adresse:");
        pane.add(lblAddress, 0, 1);

        txfAddress = new TextField();
        pane.add(txfAddress, 1, 1);

        chkFord = new CheckBox("Fordragsholder?");
        pane.add(chkFord, 0, 2);

        chkPriv = new CheckBox("Privat person?");
        pane.add(chkPriv, 1, 2);

        Label lblkonf = new Label("Vælg en konference");
        pane.add(lblkonf, 0, 3);

        cboKonf.getItems().addAll(Controller.getKonferencerer());
        pane.add(cboKonf, 1, 3);
        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged();
        cboKonf.valueProperty().addListener(listener);

        chkLeds = new CheckBox("ledsager?");
        pane.add(chkLeds, 0, 4);
        chkLeds.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                txfLName.setDisable(aBoolean);
                txfLName.setVisible(t1);

                lblLName.setDisable(aBoolean);
                lblLName.setVisible(t1);

                txfLAddress.setDisable(aBoolean);
                txfLAddress.setVisible(t1);

                lblLAddress.setDisable(aBoolean);
                lblLAddress.setVisible(t1);

                vBoxUdflugter.setVisible(t1);
                lblUdflugter.setVisible(t1);

                if (!cboKonf.getSelectionModel().isEmpty()) {
                    vBoxUdflugter.getChildren().clear();
                    int i = 0;
                    for (Udflugt u :cboKonf.getValue().getUdflugter()) {
                        chkUdflugter[i] = new CheckBox();
                        chkUdflugter[i].setText(u.getNavn());
                        chkUdflugter[i].setUserData(u);
                        vBoxUdflugter.getChildren().add(chkUdflugter[i]);
                        i++;
                    }
                }
            }
        });

        lblLName.setDisable(true);
        lblLName.setVisible(false);
        pane.add(lblLName, 0, 5);

        txfLName.setDisable(true);
        txfLName.setVisible(false);
        pane.add(txfLName, 1, 5);

        lblLAddress.setDisable(true);
        lblLAddress.setVisible(false);
        pane.add(lblLAddress, 0, 6);

        txfLAddress.setDisable(true);
        txfLAddress.setVisible(false);
        pane.add(txfLAddress, 1, 6);

        pane.add(lblUdflugter, 0, 7);
        lblUdflugter.setVisible(false);

        vBoxUdflugter = new VBox();
        pane.add(vBoxUdflugter, 0, 8);

        chkHotel = new CheckBox("Hotel?");
        pane.add(chkHotel, 0, 9);

        chkHotel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                lvwHotels.setDisable(aBoolean);
                lvwHotels.setVisible(t1);

                lblServ.setVisible(t1);
                vBoxServ.setVisible(t1);
            }
        });

        lvwHotels = new ListView<>();
        pane.add(lvwHotels, 0, 10);
        lvwHotels.setDisable(true);
        lvwHotels.setVisible(false);
        lvwHotels.setPrefWidth(100);
        lvwHotels.setPrefHeight(100);
        ChangeListener<Hotel> listenerHotels = (ov, oldKonference, newKonference) -> this.selectedHotelChanged();
        lvwHotels.getSelectionModel().selectedItemProperty().addListener(listenerHotels);

        pane.add(lblServ, 1, 9);
        lblServ.setVisible(false);

        vBoxServ = new VBox();
        pane.add(vBoxServ, 1, 10);
        vBoxServ.setSpacing(10);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 11);
        buttonBox.setPadding(new Insets(10, 10, 0, 10));
        buttonBox.setAlignment(Pos.TOP_RIGHT);

        Button btnCancel = new Button("Cancel");
        buttonBox.getChildren().add(btnCancel);
        btnCancel.setOnAction(event -> this.cancelAction());

        Button btnOK = new Button("OK");
        buttonBox.getChildren().add(btnOK);
        btnOK.setOnAction(event -> this.okAction());
    }

    // -------------------------------------------------------------------------
    // Button actions

    private void cancelAction() {
        txfName.clear();
        txfAddress.clear();
        txfLName.clear();
        txfLAddress.clear();
        chkFord.setSelected(false);
        chkPriv.setSelected(false);
        lblUdflugter.setVisible(false);
        vBoxServ.setVisible(false);
        vBoxUdflugter.setVisible(false);
        chkLeds.setSelected(false);
        chkHotel.setSelected(false);
        TilmeldningInputWindow.this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        String address = txfAddress.getText().trim();

        String LName = txfLName.getText().trim();
        String LAddress = txfLAddress.getText().trim();

        if (name.length() > 0 && address.length() > 0) {
            txfName.clear();
            txfAddress.clear();
            actualDeltager = new Deltager(name, address, chkFord.isSelected(), chkPriv.isSelected());
            Storage.addDeltager(actualDeltager);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tilmeldning af deltager");
            alert.setHeaderText("Der mangler information");
            alert.setContentText("Indtast et navn og adresse");
            alert.show();
        }

        if (LName.length() > 0 && LAddress.length() > 0 && chkLeds.isSelected()) {
            txfLName.clear();
            txfLAddress.clear();
            actualLedsager = new Ledsager(LName, LAddress);
            Storage.addLedsager(actualLedsager);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tilmeldning af deltager");
            alert.setHeaderText("Ledsager mangler information");
            alert.setContentText("Indtast et navn og adresse");
            alert.show();
        }
    }

    // -------------------------------------------------------------------------

    public Tilmelding getActualTilmeldning() {
        return actualTilmeldning;
    }

    private void selectedKonferenceChanged() {
        lvwHotels.getItems().setAll(cboKonf.getValue().getHoteller());
        if (chkLeds.isSelected()) {
            int i = 0;
            for (Udflugt u :cboKonf.getValue().getUdflugter()) {
                chkUdflugter[i] = new CheckBox();
                chkUdflugter[i].setText(u.getNavn());
                chkUdflugter[i].setUserData(u);
                vBoxUdflugter.getChildren().add(chkUdflugter[i]);
                i++;
            }
        }
    }

    private void selectedHotelChanged() {
        int i = 0;
        vBoxServ.getChildren().clear();
        lblServ.setVisible(true);
        vBoxServ.setVisible(true);
        for (Service s :lvwHotels.getSelectionModel().getSelectedItem().getServices()) {
            chkServ[i] = new CheckBox();
            chkServ[i].setText(s.getNavn());
            chkServ[i].setUserData(s);
            vBoxServ.getChildren().add(chkServ[i]);
            i++;
        }
    }
}
