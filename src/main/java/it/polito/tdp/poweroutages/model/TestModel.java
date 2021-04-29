package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		//System.out.println(model.getNercList());
		List <PowerOutage> list = model.trovaCombinazioneOttima(2, 150, 3);
		System.out.print(list+" "+"ore: "+model.sommaOre(list)+" "+"anni: "+model.sommaAnni(list));

	}

}
