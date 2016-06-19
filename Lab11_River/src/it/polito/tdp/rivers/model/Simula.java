package it.polito.tdp.rivers.model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Simula {

	private River fiume;
	private double Q;
	private List<Double> C;
	private double K;
	private double flowMin;
	private Model model;
	
	private PriorityQueue<Evento> listaEventi;
	
	private int ggNoErogazioneMinima;
	private int ggTot;
	
	public Simula(Model model, double K, River fiume){
		listaEventi = new PriorityQueue<>();
		this.model = model;
		this.K = K;
		this.fiume = fiume;
		Q = K * 30 * fiume.getAvgFlows() * 3600 * 24;
		C = new LinkedList<>();
		C.add(Q/2);
		flowMin = 0.8 * fiume.getAvgFlows() * 3600 * 24;
		
		this.ggNoErogazioneMinima = 0;
		this.ggTot = 0;
	}
	
	public void loadAllFlows(List<Flow> flows){
		for(Flow f:flows)
			listaEventi.add(new Evento(f, Evento.TipoEvento.FLOW_IN));
	}
	
	public void run(){
		while(!listaEventi.isEmpty()){
			ggTot++;
			Evento e = listaEventi.remove();
			Random rnd = new Random();
			float p = (float) (rnd.nextFloat()-0.1);
			double temp;
			if(p<=0.05)
				temp = C.get(C.size()-1)+e.getFlow().getFlow()-10*flowMin;
			else
				temp = C.get(C.size()-1)+e.getFlow().getFlow()*3600*24-flowMin;
			if(temp<0){
				ggNoErogazioneMinima++;
				temp = 0;
			}
			if(temp>Q) //tracimazione
				temp = Q;
			C.add(temp);
		}	
	}

	public int getGgNoErogazioneMinima() {
		return ggNoErogazioneMinima;
	}
	
	public double getAvgC(){
		double avg=0;
		for(double d:C)
			avg += d;
		return avg/C.size();
	}
	
	public int getGgTot(){
		return this.ggTot;
	}
	
}
