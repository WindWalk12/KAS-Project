package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KonferencePane extends GridPane {
    private ListView<Konference> lvwKonferencer;
    private Button btnCreate, btnUpdate, btnTilm;
    private HBox btnBox;
    private TilmeldningInputWindow tilmeldWindow;
    private KonferenceWindow konferenceWindow;
    private UpdateWindow updateWindow;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        tilmeldWindow = new TilmeldningInputWindow("Tilmeld en deltager", new Stage());
        konferenceWindow = new KonferenceWindow("Opret en konference", new Stage());


        Label lblKonf = new Label("Konferencer:");
        this.add(lblKonf, 0, 0);

        lvwKonferencer = new ListView<>();
        this.add(lvwKonferencer, 0, 1, 1, 3);
        lvwKonferencer.setPrefWidth(200);
        lvwKonferencer.setPrefHeight(150);
        lvwKonferencer.getItems().setAll(Controller.getKonferencerer());

        btnBox = new HBox();
        this.add(btnBox, 0, 4);
        btnBox.setSpacing(20);

        btnCreate = new Button("Opret");
        btnBox.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> this.createAction());

        btnUpdate = new Button("Opdater");
        btnBox.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> this.updateAction());

        btnTilm = new Button("Tilmeld");
        btnBox.getChildren().add(btnTilm);
        btnTilm.setOnAction(event -> this.tilmeldAction());

    }

    // -------------------------------------------------------------------------

    private void createAction() {
        konferenceWindow.showAndWait();
        lvwKonferencer.getItems().setAll(Controller.getKonferencerer());


    }

    private void updateAction() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            updateWindow = new UpdateWindow("Updater konference", new Stage(),konference);
            updateWindow.showAndWait();

            // Wait for the modal dialog to close

            int selectIndex = lvwKonferencer.getSelectionModel().getSelectedIndex();
            lvwKonferencer.getItems().setAll(Controller.getKonferencerer());
            lvwKonferencer.getSelectionModel().select(selectIndex);
        }

    }

    private void tilmeldAction() {
        tilmeldWindow.showAndWait();
    }

}

