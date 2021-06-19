/**
 * Sample Skeleton for 'Crimes.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Adiacenti;
import it.polito.tdp.crimes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class CrimesController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxCategoria"
    private ComboBox<String> boxCategoria; // Value injected by FXMLLoader

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="boxArco"
    private ComboBox<Adiacenti> boxArco; // Value injected by FXMLLoader

    @FXML // fx:id="btnPercorso"
    private Button btnPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	this.boxArco.setValue(null);
    	if(!isValid()) {
    		return;
    	}
    	String categoria = this.boxCategoria.getValue();
    	int data = this.boxAnno.getValue();
    	
    	txtResult.appendText(model.creaGrafo(categoria, data));
    	
    	List<Adiacenti> result = model.getArchiMassimi();
    	txtResult.appendText("\n\n");
    	if(result.isEmpty()) {
    		txtResult.appendText("GRAFO ASSOCIATI ALLA CATEGORIA DI REATO " + categoria + "\nNELL'ANNO " + data + " NON HA ARCHI");
    		this.boxArco.setDisable(true);
    		this.btnPercorso.setDisable(true);   		
    	}else {
    		txtResult.appendText("ARCHI CON PESO PARI AL PESO MASSIMO:");
    		for(Adiacenti a : result) {
    			txtResult.appendText("\n" + a.toString() + " (" + a.getPeso() + ")");
    		}
    		this.boxArco.setDisable(false);
    		this.btnPercorso.setDisable(false);
    		this.boxArco.getItems().setAll(result);
    	}
    }

    private boolean isValid() {
		boolean check = true;
		if(this.boxCategoria.getValue() == null) {
			txtResult.appendText("ERRORE: scegliere una categoria\n");
			check = false;
		}
		if(this.boxAnno.getValue() == null) {
			txtResult.appendText("ERRORE: scegliere un giorno");
			check = false;
		}
		return check;
	}
    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	Adiacenti a = this.boxArco.getValue();
    	if(a == null) {
    		txtResult.appendText("ERRORE : selezionare un arco");
    		return;
    	}
    	
    	List<String> result = model.getPercorso(a);
    	if(result.isEmpty()) {
    		txtResult.appendText("ERRORE NON ESISTE UN PERCORSO CHE ATTRAVERSA TUTTI GLI ARCHI PER ANDARE DA " + a.getR1() + " A " + a.getR2());
    		return;
    	}
    	txtResult.appendText("PERCORSO TRA " + a.getR1() + " E " + a.getR2());
    	txtResult.appendText("\n\nCOSTO MASSIMO= " + model.getCostoOttimo() + "\n");
    	for(String s : result) {
    		txtResult.appendText("\n" + s);
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxCategoria != null : "fx:id=\"boxCategoria\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxArco != null : "fx:id=\"boxArco\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnPercorso != null : "fx:id=\"btnPercorso\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxAnno.getItems().setAll(model.getAnni());
    	this.boxCategoria.getItems().setAll(model.getCategorie());
    }
}
