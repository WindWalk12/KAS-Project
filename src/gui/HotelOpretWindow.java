package gui;

import application.controller.Controller;
import application.model.Hotel;
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

public class HotelOpretWindow extends Stage {

    public HotelOpretWindow(String title, Stage owner) {
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

    private Hotel hotel;
    private final TextField txfName = new TextField();
    private final TextField txfPris = new TextField();
    private final TextField txfPrisDobbeltVærelse = new TextField();

    private Hotel actualHotel = null;

    private ComboBox<Konference> cboKonf = new ComboBox<>();



    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblName = new Label("Name:");
        pane.add(lblName, 0, 0);
        pane.add(txfName, 1, 0, 2, 1);

        Label lblPris = new Label("Pris:");
        pane.add(lblPris, 0, 1);
        pane.add(txfPris, 1,1,2,1);

        Label lblDobbelt = new Label("Pris Dobbelt Værelse:");
        pane.add(lblDobbelt, 0, 2);
        pane.add(txfPrisDobbeltVærelse, 1,2,2,1);

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

        Label lblKonf = new Label("Konferencer:");
        pane.add(lblKonf, 0, 3);

        cboKonf.getItems().setAll(Controller.getKonferencerer());
        pane.add(cboKonf, 1, 3);

    }

    // -------------------------------------------------------------------------
    // Button actions

    private void cancelAction() {
        txfName.clear();
        txfPris.clear();
        txfPrisDobbeltVærelse.clear();
        actualHotel = null;
        HotelOpretWindow.this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        String pris = txfPris.getText().trim();
        String prisDobbelt = txfPrisDobbeltVærelse.getText();

        if (name.length() > 0) {
            txfName.clear();
            HotelOpretWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Hotel");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type information");
            alert.show();
        }


        if (pris.length() > 0) {
            txfPris.clear();
            HotelOpretWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Hotel");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type address");
            alert.show();
        }

        if (prisDobbelt.length() > 0) {
            txfPrisDobbeltVærelse.clear();
            HotelOpretWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create Hotel");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type price per day");
            alert.show();
        }

        double PrisDouble = Double.parseDouble(prisDobbelt);
        double price = Double.parseDouble(pris);
        Hotel hotel1 = Controller.createHotel(price,PrisDouble,name,cboKonf.getValue());

    }

    // -------------------------------------------------------------------------

    public Hotel getActualHotel() {
        return actualHotel;
    }
}
