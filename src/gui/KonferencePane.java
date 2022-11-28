package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KonferencePane extends GridPane {
    private ListView<Konference> lvwCompanies;
    private Button btnCreate, btnUpdate, btnTilm;
    private HBox btnBox;
    private TilmeldningInputWindow tilmeldWindow;
    private KonferenceWindow konferenceWindow;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        tilmeldWindow = new TilmeldningInputWindow("Tilmeld en deltager", new Stage());
        konferenceWindow = new KonferenceWindow("Opret en konference", new Stage());

        Label lblKonf = new Label("Konferencer:");
        this.add(lblKonf, 0, 0);

        lvwCompanies = new ListView<>();
        this.add(lvwCompanies, 0, 1, 1, 3);
        lvwCompanies.setPrefWidth(200);
        lvwCompanies.setPrefHeight(150);
        lvwCompanies.getItems().setAll(Controller.getKonferencerer());

        btnBox = new HBox();
        this.add(btnBox, 0, 4);
        btnBox.setSpacing(20);

        btnCreate = new Button("Opret");
        btnBox.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> this.createAction());

        btnUpdate = new Button("Opdater");
        btnBox.getChildren().add(btnUpdate);

        btnTilm = new Button("Tilmeld");
        btnBox.getChildren().add(btnTilm);
        btnTilm.setOnAction(event -> this.tilmeldAction());

    }

    // -------------------------------------------------------------------------

    private void createAction() {
        konferenceWindow.showAndWait();
    }

    private void updateAction() {

    }

    private void  tilmeldAction() {
        tilmeldWindow.showAndWait();
    }

}
