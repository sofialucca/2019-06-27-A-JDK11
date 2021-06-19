package it.polito.tdp.crimes.model;


import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private Graph<String,DefaultWeightedEdge> grafo;
	private EventsDao dao;
	private List<Adiacenti> archiMassimi;
	private List<String> percorsoOttimo;
	private double costoOttimo;
	private double costoMax;
	
	public Model() {
		dao = new EventsDao();
	}
	
	public String creaGrafo(String categoria, int anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		archiMassimi = new ArrayList<>();
		costoMax = 0;
		
		Graphs.addAllVertices(grafo, dao.getVertici(anno,categoria));
		List<Adiacenti> archi = dao.getArchi(anno, categoria, new ArrayList<>(grafo.vertexSet()));
		if(!archi.isEmpty()) {
			double mediana = archi.get(0).getPeso();
			for(Adiacenti a: archi) {
				double peso = a.getPeso();
				costoMax += peso;
				if(peso == mediana) {
					archiMassimi.add(a);
				}
				Graphs.addEdge(grafo, a.getR1(), a.getR2(), peso);
			}			
		}

		return "GRAFO CREATO:\n#VERTICI: "+ grafo.vertexSet().size() + "\n#ARCHI: " + archi.size();
	}
	
	public List<Adiacenti> getArchiMassimi(){
		return archiMassimi;
	}
	
	public List<Integer> getAnni(){
		return dao.getDate();
	}
	
	public List<String> getCategorie(){
		return dao.getCategorie();
	}
	
	public List<String> getPercorso(Adiacenti a){
		percorsoOttimo = new ArrayList<>();
		costoOttimo = costoMax;
		List<String> parziale = new ArrayList<>();
		parziale.add(a.getR1());
		cerca(parziale, a.getR2(), 0);
		return percorsoOttimo;
	}

	private void cerca(List<String> parziale, String arrivo, double costoParziale) {
		String ultimo = parziale.get(parziale.size() - 1);
		System.out.println(parziale.size()+ " "+parziale);
		if(costoParziale > costoOttimo) {
			return;
		}
		
		if(parziale.size() == grafo.vertexSet().size()) {
			if(ultimo.equals(arrivo)) {
				if(costoParziale < costoOttimo) {
					costoOttimo = costoParziale;
					percorsoOttimo = new ArrayList<>(parziale);
					
				}
			}
			return;
		}else {
			for(String s : Graphs.neighborListOf(grafo, ultimo)) {
				if(!parziale.contains(s)) {
					DefaultWeightedEdge e = grafo.getEdge(s, ultimo);
					parziale.add(s);
					cerca(parziale,arrivo, costoParziale + grafo.getEdgeWeight(e));
					parziale.remove(s);
				}
			}
		}
		
	}
	
	public double getCostoOttimo() {
		return this.costoOttimo;
	}

}
