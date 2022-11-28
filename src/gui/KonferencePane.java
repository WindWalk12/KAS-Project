package gui;

import application.controller.Controller;
import application.model.Konference;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class KonferencePane extends GridPane {
    private ListView<Konference> lvwCompanies;
    private Button btnCreate, btnUpdate, btnTilm;
    private HBox btnBox;

    public KonferencePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

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

        btnUpdate = new Button("Opdater");
        btnBox.getChildren().add(btnUpdate);

        btnTilm = new Button("Tilmeld");
        btnBox.getChildren().add(btnTilm);

    }

    // -------------------------------------------------------------------------

    private void createAction() {

    }

}
