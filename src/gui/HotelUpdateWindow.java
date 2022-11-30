package gui;

import application.controller.Controller;
import application.model.Hotel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HotelUpdateWindow extends Stage {

    public HotelUpdateWindow(String title, Stage owner, Hotel hotel) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.hotel = hotel;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private final TextField txfName = new TextField();
    private final TextField txfPris = new TextField();
    private final TextField txfDobbeltPris = new TextField();

    private Hotel hotel;


    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblName = new Label("Navn:");
        pane.add(lblName, 0, 0);
        pane.add(txfName, 1, 0, 2, 1);

        Label lblPris = new Label("Pris:");
        pane.add(lblPris, 0, 1);
        pane.add(txfPris, 1,1,2,1);

        Label lblDobbelt = new Label("Pris Dobbeltværelse:");
        pane.add(lblDobbelt, 0, 2);
        pane.add(txfDobbeltPris, 1,2,2,1);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 6);
        buttonBox.setPadding(new Insets(10, 10, 0, 10));
        buttonBox.setAlignment(Pos.TOP_LEFT);

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
       HotelUpdateWindow.this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        double dobbeltpris = 0;
        double pris = 0;


        if(txfDobbeltPris.getText().trim().length() > 0){
            dobbeltpris = Integer.parseInt(txfDobbeltPris.getText().trim());
        }
        if (txfPris.getText().trim().length() > 0){
           pris = Double.parseDouble(txfPris.getText().trim());        }


        if (name.length() > 0 && txfDobbeltPris.getText().trim().length() > 0 && txfPris.getText().trim().length() > 0) {
            txfName.clear();
            txfPris.clear();
            txfDobbeltPris.clear();
            Controller.updateHotel(hotel,name,pris,dobbeltpris);
            HotelUpdateWindow.this.hide();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Opdater konference");
            alert.setHeaderText("Information missing");
            alert.setContentText("Indtast alle de nødvendige informationer ");
            alert.show();
        }
    }

    // ----------------------------------------------------------------------
}

