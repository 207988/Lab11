package it.polito.tdp.rivers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RiversController {
	
	Model model;
	
	public void setModel(Model m){
		this.model=m;
		m.load();
		boxRiver.getItems().addAll(m.getElencoRivers());
		
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<River> boxRiver;

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
    
    @FXML
    void doSelezionaFiume(ActionEvent event) {
    	txtResult.clear();
    	if(boxRiver.getValue()==null){
    		txtResult.setText("Seleziona un fiume valido");
    		return;
    	}
    	else{
    		River r= boxRiver.getValue();
    		txtNumMeasurements.setText(""+r.getFlows().size());
    		txtFMed.setText(""+r.fmed());
    		List<Flow> flows=r.getFlows();
    		txtStartDate.setText(""+flows.get(0).getDay());
    		txtEndDate.setText(""+flows.get(flows.size()-1).getDay());
    	}

    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	if(boxRiver.getValue()==null){
    		txtResult.setText("Seleziona un fiume valido");
    		return;
    	}
    	else if(!txtK.getText().matches("^[0]{1}[.]{1}[0-9]{1,2}$")){
    		txtResult.setText("Inserisci k nel formato 0.xx");
    		return;
    	}
    	
    	txtResult.setText(model.simula(Float.parseFloat(txtK.getText()), boxRiver.getValue()));
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

    }
}
