package it.polito.tdp.rivers.simulazione;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

public class Simulatore {
	private int giorniFail;
	private int nTracimazioni;
	private List<Float> occupazioneStorico=new ArrayList<Float>();
	private float capienzaTotale;
	private River r;
	private float occupazioneAttuale;
	private Queue<Flow> listaEventi= new PriorityQueue<Flow>();
	private float fmed;
	
	public Simulatore(float k, River r) {
		super();
		giorniFail=0;
		nTracimazioni=0;
		this.r = r;
		fmed=r.fmed()*86400;
		capienzaTotale=(k*30*fmed);
		occupazioneAttuale=capienzaTotale/2;
		listaEventi.addAll(r.getFlows());
		//System.out.println(listaEventi);
	}
	
	public String simula(){
		while(!listaEventi.isEmpty())
			passo();
		return this.risultati();
	}
	public void passo(){
		boolean debugUscita=false;
		Flow f=listaEventi.remove();
		//aggiungo al bacino il flusso in entrata
		occupazioneAttuale+=(f.getFlow()*86400);
		//calcolo il flusso in uscita contando la variabilita'
		float uscita=this.flussoUscita();
		
		if(occupazioneAttuale>=uscita){
			//caso acqua suff
			occupazioneAttuale-=uscita;			
		}
		else{
			//caso acqua insuff
			giorniFail++;
			debugUscita=true;
		}
		
		if(occupazioneAttuale>capienzaTotale){
			//tracimazione
			if(debugUscita)
				throw new RuntimeException("CASINO COLOSSALE");			
			occupazioneAttuale=capienzaTotale;
			nTracimazioni++;
		}
		
		occupazioneStorico.add(occupazioneAttuale);
	}
	
	public float flussoUscita(){
		float f=(float)0.8*fmed;
		Random random=new Random();
		if((random.nextInt(101))>=95)
			f=f*10;
		return f;
	}
	
	public String risultati(){
		float occupazioneMedia=0;
		for(float f:occupazioneStorico)
			occupazioneMedia+=f;
		return "Giorni non soddisfatti: "+giorniFail+"\nTracimazioni: "+nTracimazioni+"\nOccupazione Media: "+(occupazioneMedia/(occupazioneStorico.size()-1));
	}
	
	
	

}
