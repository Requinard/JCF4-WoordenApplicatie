package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringJoiner;

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

    @FXML
    private void aantalAction(ActionEvent event) {
        int totalWords = teller.GetTotalWordCount();
        int uniqueWords = teller.GetUniqueWordCount();

        taOutput.setText(String.format("Totaal aantal woorden: %d\nTotaal aantal unieke woorden: %d", totalWords, uniqueWords));
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        taOutput.setText(teller.GetReversedWordOrder().stream().reduce((x, y) -> x + "\n" + y).get());
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        taOutput.setText(teller.GetWordCount().keySet().stream().reduce((x, y) -> x + "\n" + String.format("%s: %d", y, teller.GetWordCount().get(y))).get());
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        Map<String, Set<Integer>> stringSetMap = teller.GetConcondances();
        StringBuilder stringBuilder = new StringBuilder();

        for(String word: stringSetMap.keySet()){
            stringBuilder.append(String.format("%s: %s\n", word, stringSetMap.get(word)));
        }

        taOutput.setText(stringBuilder.toString());
    }

}
