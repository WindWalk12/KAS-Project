package gui;

import application.controller.Controller;
import application.model.Konference;
import application.model.Udflugt;
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

public class UdflugtUpdateWindow extends Stage {

    public UdflugtUpdateWindow(String title, Stage owner, Udflugt udflugt) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.udflugt = udflugt;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    // -------------------------------------------------------------------------

    private final TextField txfName = new TextField();
    private final TextField txfPris = new TextField();

    private DatePicker datePickerStartDato = new DatePicker();

    private Udflugt udflugt;


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
        pane.add(txfPris, 1, 1, 2, 1);

        Label lblDatePicker = new Label("Vælg Dato");
        pane.add(lblDatePicker, 0, 3);
        pane.add(datePickerStartDato, 1, 3, 2, 1);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 4, 2, 1);
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
        txfPris.clear();
        UdflugtUpdateWindow.this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        Double pris = Double.parseDouble(txfPris.getText().trim());
        LocalDate startDato;
        startDato = datePickerStartDato.getValue();


        if (name.length() > 0 && pris >= 0 && startDato != null) {
            txfName.clear();
            txfPris.clear();
            Controller.updateUdflugt(udflugt, name, pris, startDato);
            UdflugtUpdateWindow.this.hide();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oprete udflugt");
            alert.setHeaderText("Information mangler");
            alert.setContentText("Indtast alle de nødvendige informationer ");
            alert.show();
        }
    }

    // ----------------------------------------------------------------------
}
