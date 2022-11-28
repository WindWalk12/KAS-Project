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
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TilmeldningInputWindow extends Stage {

    public TilmeldningInputWindow(String title, Stage owner) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setHeight(580);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private TextField txfName, txfAddress, txfDays;
    private TextField txfLName = new TextField();
    private TextField txfLAddress = new TextField();
    Label lblLName = new Label("Ledsagers navn:");
    Label lblLAddress = new Label("Ledsagers Adresse:");
    Label lblDays = new Label();
    Label lblServ = new Label("Services");
    Label lblUdflugter = new Label("Udflugter?");
    private CheckBox chkFord, chkPriv, chkLeds, chkHotel;
    private CheckBox[] chkServ;
    private CheckBox[] chkUdflugter;
    private VBox vBoxServ, vBoxUdflugter;
    private ListView<Hotel> lvwHotels;
    private ComboBox<Konference> cboKonf = new ComboBox<>();

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
        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged(pane);
        cboKonf.valueProperty().addListener(listener);

        pane.add(lblDays, 0, 4);
        lblDays.setVisible(false);

        txfDays = new TextField();
        pane.add(txfDays, 1, 4);
        txfDays.setVisible(false);

        chkLeds = new CheckBox("ledsager?");
        pane.add(chkLeds, 0, 5);
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
        pane.add(lblLName, 0, 6);

        txfLName.setDisable(true);
        txfLName.setVisible(false);
        pane.add(txfLName, 1, 6);

        lblLAddress.setDisable(true);
        lblLAddress.setVisible(false);
        pane.add(lblLAddress, 0, 7);

        txfLAddress.setDisable(true);
        txfLAddress.setVisible(false);
        pane.add(txfLAddress, 1, 7);

        pane.add(lblUdflugter, 0, 8);
        lblUdflugter.setVisible(false);

        vBoxUdflugter = new VBox();
        pane.add(vBoxUdflugter, 0, 9);

        chkHotel = new CheckBox("Hotel?");
        pane.add(chkHotel, 0, 10);

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
        pane.add(lvwHotels, 0, 11);
        lvwHotels.setDisable(true);
        lvwHotels.setVisible(false);
        lvwHotels.setPrefWidth(100);
        lvwHotels.setPrefHeight(100);
        ChangeListener<Hotel> listenerHotels = (ov, oldKonference, newKonference) -> this.selectedHotelChanged();
        lvwHotels.getSelectionModel().selectedItemProperty().addListener(listenerHotels);

        pane.add(lblServ, 1, 10);
        lblServ.setVisible(false);

        vBoxServ = new VBox();
        pane.add(vBoxServ, 1, 11);
        vBoxServ.setSpacing(10);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 12);
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
        txfDays.clear();
        txfLName.clear();
        txfLAddress.clear();
        chkFord.setSelected(false);
        chkPriv.setSelected(false);
        lblUdflugter.setVisible(false);
        vBoxServ.setVisible(false);
        vBoxUdflugter.setVisible(false);
        chkLeds.setSelected(false);
        if (chkHotel.isSelected()) {
            for (CheckBox checkBox : chkServ) {
                checkBox.setSelected(false);
            }
        }
        chkHotel.setSelected(false);
        TilmeldningInputWindow.this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        String address = txfAddress.getText().trim();
        String sDays = txfDays.getText().trim();
        int iDays = 0;
        boolean isNumber = Pattern.matches("[0-9]+", sDays);
        if (isNumber) {
            iDays = Integer.parseInt(sDays);
        }

        if (chkHotel.isSelected() && name.length() > 0 && address.length() > 0 && iDays > 0 && iDays <= cboKonf.getValue().getAntalDage() && chkLeds.isSelected()) {
            if (!lvwHotels.getSelectionModel().isEmpty()) {
                ArrayList<Service> checkedServices = new ArrayList<>();
                for (CheckBox checkBox : chkServ) {
                    if (checkBox.isSelected()) {
                        checkedServices.add((Service) checkBox.getUserData());
                    }
                }
                String lName = txfLName.getText().trim();
                String lAddress = txfLAddress.getText().trim();

                if (lName.length() > 0 && lAddress.length() > 0) {
                    Ledsager actualLedsager = Controller.createLedsager(lName, lAddress);
                    for (CheckBox checkBox : chkUdflugter) {
                        if (checkBox.isSelected()) {
                            actualLedsager.addUdflugt((Udflugt) checkBox.getUserData());
                        }
                    }
                    Deltager actualDeltager = Controller.createDeltager(name, address, chkFord.isSelected(), chkPriv.isSelected());
                    Tilmelding actualTilmeldning = Controller.createTilmeldning(actualDeltager, cboKonf.getValue(), lvwHotels.getSelectionModel().getSelectedItem(), actualLedsager, checkedServices, iDays);
                    cboKonf.getValue().addTilmelding(actualTilmeldning);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pris for tilmeldning");
                    alert.setHeaderText("Tak for tilmeldningen");
                    alert.setContentText("Det kommer til at koste " + actualTilmeldning.udregnSamletPris() + " dkk");
                    alert.show();
                    cancelAction();
                    TilmeldningInputWindow.this.hide();
                }  else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Tilmeldning af deltager");
                    alert.setHeaderText("Ledsager mangler information");
                    alert.setContentText("Indtast venligst ledsagers manglende informationer");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tilmeldning af deltager");
                alert.setHeaderText("Der mangler information");
                alert.setContentText("Vælg venligst et hotel");
                alert.show();
            }
        } else if (name.length() > 0 && address.length() > 0 && iDays > 0 && iDays <= cboKonf.getValue().getAntalDage() && chkLeds.isSelected()) {
            String lName = txfLName.getText().trim();
            String lAddress = txfLAddress.getText().trim();

            if (lName.length() > 0 && lAddress.length() > 0) {
                Ledsager actualLedsager = Controller.createLedsager(lName, lAddress);
                for (CheckBox checkBox : chkUdflugter) {
                    if (checkBox.isSelected()) {
                        actualLedsager.addUdflugt((Udflugt) checkBox.getUserData());
                    }
                }
                Deltager actualDeltager = Controller.createDeltager(name, address, chkFord.isSelected(), chkPriv.isSelected());
                Tilmelding actualTilmeldning = Controller.createTilmeldning(actualDeltager, cboKonf.getValue(), null, actualLedsager, null, iDays);
                cboKonf.getValue().addTilmelding(actualTilmeldning);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pris for tilmeldning");
                alert.setHeaderText("Tak for tilmeldningen");
                alert.setContentText("Det kommer til at koste " + actualTilmeldning.udregnSamletPris() + " dkk");
                alert.show();
                cancelAction();
                TilmeldningInputWindow.this.hide();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tilmeldning af deltager");
                alert.setHeaderText("Ledsager mangler information");
                alert.setContentText("Indtast venligst ledsagers manglende informationer");
                alert.show();
            }
        } else if (name.length() > 0 && address.length() > 0 && iDays > 0 && iDays <= cboKonf.getValue().getAntalDage() && !cboKonf.getSelectionModel().isEmpty()) {
            txfName.clear();
            txfAddress.clear();
            txfDays.clear();
            if (chkHotel.isSelected()) {
                for (CheckBox checkBox : chkServ) {
                    checkBox.setSelected(false);
                }
            }
            chkHotel.setSelected(false);
            Deltager actualDeltager = Controller.createDeltager(name, address, chkFord.isSelected(), chkPriv.isSelected());
            Tilmelding actualTilmeldning = Controller.createTilmeldning(actualDeltager, cboKonf.getValue(), null, null, null, iDays);
            cboKonf.getValue().addTilmelding(actualTilmeldning);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pris for tilmeldning");
            alert.setHeaderText("Tak for tilmeldningen");
            alert.setContentText("Det kommer til at koste " + actualTilmeldning.udregnSamletPris() + " dkk");
            alert.show();
            cancelAction();
            TilmeldningInputWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tilmeldning af deltager");
            alert.setHeaderText("Der mangler information");
            alert.setContentText("Indtast venligst de manglende informationer \n" + "Antal dage kan maks være " + cboKonf.getValue().getAntalDage() + " og ned til 1");
            alert.show();
        }
    }

    // -------------------------------------------------------------------------

    private void selectedKonferenceChanged(GridPane pane) {
        lvwHotels.getItems().setAll(cboKonf.getValue().getHoteller());
        chkUdflugter = new CheckBox[cboKonf.getValue().getUdflugter().size()];
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
        lblDays.setText("Antal dage:");
        lblDays.setVisible(true);
        txfDays.setPromptText("Maks " + cboKonf.getValue().getAntalDage() + " dage");
        txfDays.setVisible(true);
    }

    private void selectedHotelChanged() {
        chkServ = new CheckBox[lvwHotels.getSelectionModel().getSelectedItem().getServices().size()];
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
