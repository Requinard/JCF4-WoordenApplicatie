package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import woordenapplicatie.WoordenTeller;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Heb je dan geen hoedje meer\n" +
            "Maak er één van bordpapier\n" +
            "Eén, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van, hoedje van\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier\n" +
            "\n" +
            "En als het hoedje dan niet past\n" +
            "Zetten we 't in de glazenkas\n" +
            "Een, twee, drie, vier\n" +
            "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    private WoordenTeller teller;

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
        teller = new WoordenTeller(taInput.getText());
    }

    private void resetTeller() {
        if (!teller.getInput().equals(taInput.getText()))
            teller = new WoordenTeller(taInput.getText());
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        resetTeller();
        int totalWords = teller.GetTotalWordCount();
        int uniqueWords = teller.GetUniqueWordCount();

        taOutput.setText(String.format("Totaal aantal woorden: %d\nTotaal aantal unieke woorden: %d", totalWords, uniqueWords));
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        resetTeller();
        taOutput.setText(teller.GetReversedWordOrder()
                .stream()
                .reduce((x, y) -> x + "\n" + y)
                .get()
        );
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        resetTeller();

        Map<String, Integer> stringIntegerHashMap = teller.GetOrderedWordCount();

        taOutput.setText(stringIntegerHashMap
                .keySet()
                .stream()
                .map(x -> String.format("%s: %d", x, stringIntegerHashMap.get(x)))
                .reduce((x, y) -> x + "\n" + y)
                .get()
        );

    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        resetTeller();
        Map<String, Set<Integer>> stringSetMap = teller.GetConcondances();

        taOutput.setText(
                stringSetMap
                        .keySet()
                        .stream()
                        .map(x -> String.format("%s: %s", x, stringSetMap.get(x)))
                        .reduce((x, y) -> x + "\n" + y)
                        .get()
        );
    }

}
