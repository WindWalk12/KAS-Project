package gui;


import application.controller.Controller;
import application.model.Konference;
import application.model.Tilmelding;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class OversigtPane extends GridPane {
    private ListView<Konference> lvwKonferencer;
    private TextArea txaTilm;
    private TextField txfaddr, txfSDato, txfADage, txfTjen;

    public OversigtPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblKonf = new Label("Konferencer:");
        this.add(lblKonf, 0, 0);

        lvwKonferencer = new ListView<>();
        this.add(lvwKonferencer, 0, 1, 1, 6);
        lvwKonferencer.setPrefWidth(200);
        lvwKonferencer.setPrefHeight(300);
        lvwKonferencer.getItems().setAll(Controller.getKonferencerer());
        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> this.selectedKonferenceChanged();
        lvwKonferencer.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lbladdr = new Label("Adresse:");
        this.add(lbladdr, 2, 1);
        txfaddr = new TextField();
        this.add(txfaddr, 3, 1);
        txfaddr.setEditable(false);

        Label lblSDato = new Label("Start Dato:");
        this.add(lblSDato, 2, 2);
        txfSDato = new TextField();
        this.add(txfSDato, 3, 2);
        txfSDato.setEditable(false);

        Label lblADage = new Label("Antal dage:");
        this.add(lblADage, 2, 3);
        txfADage = new TextField();
        this.add(txfADage, 3, 3);
        txfADage.setEditable(false);

        Label lblTjen = new Label("Fortjenste:");
        this.add(lblTjen, 2, 4);
        txfTjen = new TextField();
        this.add(txfTjen, 3, 4);
        txfTjen.setEditable(false);

        Label lblDelt = new Label("Tilmeldte:");
        this.add(lblDelt, 2, 5);

        txaTilm = new TextArea();
        this.add(txaTilm, 2, 6, 2, 1);
        txaTilm.setPrefWidth(200);
        txaTilm.setPrefHeight(100);
        txaTilm.setEditable(false);


    }

    // -------------------------------------------------------------------------

    private void selectedKonferenceChanged() {
        this.updateView();
    }

    public void updateList() {
        lvwKonferencer.getItems().setAll(Controller.getKonferencerer());
    }

    public void updateView() {
        Konference konference = lvwKonferencer.getSelectionModel().getSelectedItem();
        if (konference != null) {
            txfaddr.setText(konference.getAdresse());
            txfSDato.setText(konference.getStartDato().toString());
            txfADage.setText(String.valueOf(konference.getAntalDage()));
            txfTjen.setText(konference.getFortjeneste() + " dkk");
            StringBuilder sb = new StringBuilder();
            for (Tilmelding t : konference.getTilmeldinger()) {
                sb.append(t.getDeltager().getNavn() + "\n");
            }
            txaTilm.setText(sb.toString());
        } else {
            txaTilm.clear();
            txfaddr.clear();
            txfSDato.clear();
            txfADage.clear();
            txfTjen.clear();
        }
    }
}
