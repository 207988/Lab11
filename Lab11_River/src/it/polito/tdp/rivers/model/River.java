package it.polito.tdp.rivers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class River {

	private List<Flow> flows;
	private String name;
	private int id;
	

	public River(int id) {
		this.id = id;
	}

	public River(int id, String name) {
		this.id = id;
		this.name = name;
		flows=new ArrayList<Flow>();
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	public List<Flow> getFlows() {
		if (flows == null)
			flows = new ArrayList<Flow>();
		return flows;
	}
	
	public void addFlow(Flow f){
		flows.add(f);
	}
	public float fmed(){
		float temp=0;
		for(Flow f:flows)
			temp+=f.getFlow();
		return temp/flows.size();
	}
	
	public void ordinaFlows(){
		List<Flow> temp=new ArrayList<Flow>(flows);
		Collections.sort(temp);
		flows.clear();
		flows.addAll(temp);
	}
	
	

	@Override
	public String toString() {
		return name;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		River other = (River) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
