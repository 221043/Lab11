package it.polito.tdp.rivers.model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private List<River> fiumi;
	private List<Flow> flussi;
	
	private RiversDAO dao; 
	
	public Model(){
		fiumi = null;
		flussi = new ArrayList<>();
		dao = new RiversDAO();
	}
	
	public List<River> getAllRivers(){
		if(fiumi==null)
			fiumi = dao.getAllRivers();
		return fiumi;
	}
	
	public List<Flow> getAllFlows(){
		if(flussi.isEmpty()){
			for(River r:fiumi){
				List<Flow> temp = dao.getFlowByRiver(r);
				r.setFlows(temp);
				flussi.addAll(temp);
			}
		}
		return flussi;
	}
	
	public List<String> getRiversNames(){
		List<String> temp = new ArrayList<>();
		for(River r:fiumi)
			temp.add(r.getName());
		return temp;
	}
	
	public River getRiverByName(String name){
		for(River r:fiumi){
			if(r.getName().compareTo(name)==0)
				return r;
		}
		return null;
	}
	
	public List<Flow> getFlowsByRiver(River r){
		return r.getFlows();
	}
	
	public String getFirstFlow(River r){
		if(r.getFlows().size()==0)
			return null;
		Flow f = r.getFlows().get(0);
		return f.getDay().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	public String getLastFlow(River r){
		Flow f = r.getFlows().get(r.getNumberFlows()-1);
		return f.getDay().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	public int getNumberFlows(River r){
		return r.getNumberFlows();
	}
	
	public double getAvgFlow(River r){
		return r.getAvgFlows();
	}
	
	public Simula simula(double k, River r){
		Simula sim = new Simula(this, k, r);
		sim.loadAllFlows(r.getFlows());
		sim.run();
		return sim;
	}
	
	public int getGgNoErogazioneMinima(Simula sim) {
		return sim.getGgNoErogazioneMinima();
	}
	
	public double getAvgC(Simula sim){
		return sim.getAvgC();
	}
	
	public int getGgTot(Simula sim){
		return sim.getGgTot();
	}
	
}
