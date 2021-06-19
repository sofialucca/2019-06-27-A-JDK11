package it.polito.tdp.crimes.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		Model m = new Model();
		m.creaGrafo("drug-alcohol", 2014);
		List<Adiacenti> result = m.getArchiMassimi();
		m.getPercorso(result.get(0));
	}

}
