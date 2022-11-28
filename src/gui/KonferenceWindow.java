package gui;

import application.model.Tilmelding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private final TextField txfName = new TextField();
    private final TextField txfAddress = new TextField();
    private final TextField txfPrisPrDag = new TextField();
    private final TextField txfAntalDage = new TextField();
    private Tilmelding actualTilmeldning = null;
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
        pane.add(buttonBox, 0, 5);
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
        actualTilmeldning = null;
        KonferenceWindow.this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();

        if (name.length() > 0) {
            txfName.clear();
            KonferenceWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create person");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type name and title");
            alert.show();
        }
    }

    // -------------------------------------------------------------------------

    public Tilmelding getActualTilmeldning() {
        return actualTilmeldning;
    }
}
