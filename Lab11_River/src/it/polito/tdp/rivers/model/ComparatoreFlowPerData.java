package it.polito.tdp.rivers.model;

import java.util.Comparator;

public class ComparatoreFlowPerData implements Comparator<Flow>{

	@Override
	public int compare(Flow a, Flow b) {
		return a.getDay().compareTo(b.getDay());
	}

}
