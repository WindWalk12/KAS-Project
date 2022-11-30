package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;

public class KonferenceWindow extends Stage {

    public KonferenceWindow(String title, Stage owner) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private Konference konference;
    private final TextField txfName = new TextField();
    private final TextField txfAddress = new TextField();
    private final TextField txfPrisPrDag = new TextField();
    private final TextField txfAntalDage = new TextField();
    private DatePicker datePickerStartDato = new DatePicker();


    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblName = new Label("Name:");
        pane.add(lblName, 0, 0);
        pane.add(txfName, 1, 0, 2, 1);

        Label lblAddress = new Label("Adresse:");
        pane.add(lblAddress, 0, 1);
        pane.add(txfAddress, 1,1,2,1);

        Label lblPrisPrDag = new Label("Pris Per Dag:");
        pane.add(lblPrisPrDag, 0, 2);
        pane.add(txfPrisPrDag, 1,2,2,1);

        Label lblAntalDage = new Label("Antal Dage:");
        pane.add(lblAntalDage, 0, 3);
        pane.add(txfAntalDage, 1,3,2,1);

        Label lblDatePicker = new Label("Vælg Startdato");
        pane.add(lblDatePicker, 0, 4);
        pane.add(datePickerStartDato, 0, 5);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 6);
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
        txfPrisPrDag.clear();
        txfAntalDage.clear();
        KonferenceWindow.this.hide();
    }

    private void okAction() {


        String name = txfName.getText().trim();
        String address = txfAddress.getText().trim();
        String prisPrDag = txfPrisPrDag.getText();
        String dage = txfAntalDage.getText().trim();

        if (name.length() > 0) {
            txfName.clear();
            KonferenceWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create konference");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type information");
            alert.show();
        }


        if (address.length() > 0) {
            txfAddress.clear();
            KonferenceWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create konference");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type address");
            alert.show();
        }



        if (prisPrDag.length() > 0) {
            txfPrisPrDag.clear();
            KonferenceWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create konference");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type price per day");
            alert.show();
        }

        if (dage.length() > 0) {
            txfAntalDage.clear();
            KonferenceWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create konference");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type amount of days");
            alert.show();
        }

        LocalDate startDato;
        startDato = datePickerStartDato.getValue();
        double PrisPrDag = Double.parseDouble(prisPrDag);
        int antalDage = Integer.parseInt(dage);
        Konference konference = Controller.createKonference(name, address, startDato, antalDage, PrisPrDag);

    }

    // -------------------------------------------------------------------------

}
