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
    private final TextField txfTitle = new TextField();
    private final CheckBox chkSenior = new CheckBox();


    private Tilmelding actualTilmeldning = null;

    private void initContent(GridPane pane) {
        // pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblName = new Label("Name:");
        pane.add(lblName, 0, 0);

        Label lblTitle = new Label("Title:");
        pane.add(lblTitle, 0,1);

        pane.add(txfName, 1, 0, 2, 1);
        pane.add(txfTitle, 1,1,2,1);

        pane.add(chkSenior, 1,2);
        Label lblSenior = new Label("Senior");
        pane.add(lblSenior, 2,2);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 3);
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
        txfTitle.clear();
        txfTitle.requestFocus();
        txfName.clear();
        actualTilmeldning = null;
        KonferenceWindow.this.hide();
    }

    private void okAction() {
        String name = txfName.getText().trim();
        String title = txfTitle.getText().trim();

        if (name.length() > 0 && title.length() > 0) {
            txfTitle.clear();
            txfName.clear();
            txfTitle.requestFocus();
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
