package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.Simula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {

	Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxRiver;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtNumMeasurements;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtStartDate;

    @FXML
    private TextField txtFMed;

    @FXML
    private Button btnSimula;

    @FXML
    private TextField txtK;

    public void setModel(Model model){
    	this.model = model;
    	model.getAllRivers();
    	model.getAllFlows();
    	ObservableList<String> fiumi = FXCollections.observableArrayList(model.getRiversNames());
        boxRiver.setItems(fiumi);
    }
    
    @FXML
    void doSceltaFiume(ActionEvent event) {
    	String fiume = boxRiver.getValue();
    	River r = model.getRiverByName(fiume);
    	txtStartDate.setText(model.getFirstFlow(r));
    	txtEndDate.setText(model.getLastFlow(r));
    	txtFMed.setText(model.getAvgFlow(r)+"");
    	txtNumMeasurements.setText(""+model.getNumberFlows(r));
    }
    
    @FXML
    void doSimula(ActionEvent event) {
    	String fiume = boxRiver.getValue();
    	River r = model.getRiverByName(fiume);
    	if(r==null)
    		txtResult.setText("Scegliere un fiume");
    	else{ 
    		try{
    			double k = Double.parseDouble(txtK.getText());
        		Simula sim = model.simula(k, r);
            	txtResult.setText("Giorni in cui non è stata garantita l'erogazione minima: "+model.getGgNoErogazioneMinima(sim)+" su"+model.getGgTot(sim)+" giorni totali");
            	txtResult.appendText("\n\n"+model.getAvgC(sim));
        	} catch(NumberFormatException nfe){
        		txtResult.setText("Inserire un valore decimale di K");
        	}	
    	}
    } 

    @FXML
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Rivers.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Rivers.fxml'.";

        boxRiver.setValue("");
    }
    
}
