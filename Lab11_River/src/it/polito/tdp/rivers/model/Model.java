package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;
import it.polito.tdp.rivers.simulazione.Simulatore;

public class Model {
	
	private List<River>elencoRivers=new ArrayList<River>();
	private List<Flow>elencoFlow=new ArrayList<Flow>();
	
	public void load(){
		RiversDAO dao=new RiversDAO();
		elencoRivers.addAll(dao.getAllRivers());
		elencoFlow.addAll(dao.getAllFlows(elencoRivers));
		for(Flow f:elencoFlow)
			f.getRiver().addFlow(f);	
		for(River r:elencoRivers)
			r.ordinaFlows();
	}

	public List<River> getElencoRivers() {
		return elencoRivers;
	}
	
	public String simula(float k, River r){
		Simulatore sim =new Simulatore(k,r);
		return sim.simula();
	}
	
	

}
	