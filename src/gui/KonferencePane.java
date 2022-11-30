package gui;

import application.controller.Controller;
import application.model.Konference;
import application.model.Udflugt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class KonferencePane extends GridPane {
    private ListView<Konference> lvwKonferencer;
    private ListView<Udflugt> lvwUdflugter;
    private Button btnCreate, btnUpdate, btnTilm, btnUdfCreate, btnUdfUpdate;
    private HBox btnKonfBox, btnUdflugtBox;
    private TilmeldningInputWindow tilmeldWindow;
    private KonferenceWindow konferenceWindow;
    private KonferenceUpdateWindow konferenceUpdateWindow;
    private UdflugtWindow udflugtWindow;
    private UdflugtUpdateWindow udflugtUpdateWindow;

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
        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

        btnKonfBox = new HBox();
        this.add(btnKonfBox, 0, 4);
        btnKonfBox.setSpacing(20);

        btnCreate = new Button("Opret");
        btnKonfBox.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> this.createKonfAction());

        btnUpdate = new Button("Opdater");
        btnKonfBox.getChildren().add(btnUpdate);
        btnUpdate.setOnAction(event -> this.updateKonfAction());

        btnTilm = new Button("Tilmeld");
        btnKonfBox.getChildren().add(btnTilm);
        btnTilm.setOnAction(event -> this.tilmeldAction());

        Label lblhote = new Label("Udflugter:");
        this.add(lblhote, 1, 0);

        lvwUdflugter = new ListView<>();
        this.add(lvwUdflugter, 1, 1, 1, 3);
        lvwUdflugter.setPrefWidth(200);
        lvwUdflugter.setPrefHeight(150);

        btnUdflugtBox = new HBox();
        this.add(btnUdflugtBox, 1, 4);
        btnUdflugtBox.setSpacing(20);

        btnUdfCreate = new Button("Opret");
        btnUdflugtBox.getChildren().add(btnUdfCreate);
        btnUdfCreate.setOnAction(event -> this.createUdflugtAction());

        btnUdfUpdate = new Button("Opdater");
        btnUdflugtBox.getChildren().add(btnUdfUpdate);
        btnUdfUpdate.setOnAction(event -> this.updateUdflugtAction());

    }

    // -------------------------------------------------------------------------

    public void updateList() {
        lvwKonferencer.getItems().setAll(Controller.getKonferencerer());
    }

    private void createKonfAction() {
        konferenceWindow.showAndWait();
        lvwKonferencer.getItems().setAll(Controller.getKonferencerer());
    }

    private void updateKonfAction() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            konferenceUpdateWindow = new KonferenceUpdateWindow("Updater konference", new Stage(),konference);
            konferenceUpdateWindow.showAndWait();

            // Wait for the modal dialog to close

            int selectIndex = lvwKonferencer.getSelectionModel().getSelectedIndex();
            lvwKonferencer.getItems().setAll(Controller.getKonferencerer());
            lvwKonferencer.getSelectionModel().select(selectIndex);
        }

    }

    private void tilmeldAction() {
        tilmeldWindow.updateKonference();
        tilmeldWindow.showAndWait();
    }

    private void selectedKonferenceChanged() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            lvwUdflugter.getItems().setAll(konference.getUdflugter());
        } else {
            lvwUdflugter.getItems().clear();
        }
    }

    private void createUdflugtAction() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            udflugtWindow = new UdflugtWindow("Opret Udflugt", new Stage(), konference);
            udflugtWindow.showAndWait();

            // Wait for the modal dialog to close

            int selectIndex = lvwKonferencer.getSelectionModel().getSelectedIndex();
            lvwKonferencer.getItems().setAll(Controller.getKonferencerer());
            lvwKonferencer.getSelectionModel().select(selectIndex);
        }
    }

    private void updateUdflugtAction() {
        Udflugt udflugt = lvwUdflugter.getSelectionModel().getSelectedItem();
        if (udflugt != null) {
            udflugtUpdateWindow = new UdflugtUpdateWindow("Opdater Udflugt", new Stage(), udflugt);
            udflugtUpdateWindow.showAndWait();

            // Wait for the modal dialog to close

            int selectIndex = lvwUdflugter.getSelectionModel().getSelectedIndex();
            lvwUdflugter.getItems().setAll(lvwKonferencer.getSelectionModel().getSelectedItem().getUdflugter());
            lvwUdflugter.getSelectionModel().select(selectIndex);
        }
    }
}

