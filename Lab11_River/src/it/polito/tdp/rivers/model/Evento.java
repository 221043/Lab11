package it.polito.tdp.rivers.model;

public class Evento implements Comparable<Evento> {

	public enum TipoEvento { FLOW_IN };
	
	private Flow flow;
	private TipoEvento type;	
	
	public Evento(Flow flow, TipoEvento type) {
		this.flow = flow;
		this.type = type;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public TipoEvento getType() {
		return type;
	}

	public void setType(TipoEvento type) {
		this.type = type;
	}

	@Override
	public int compareTo(Evento e) {
		return this.getFlow().getDay().compareTo(e.getFlow().getDay());
	}

	@Override
	public String toString() {
		return "Evento [flow=" + flow + ", type=" + type + "]";
	}
	
}
