package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<PowerOutage> partenza; // lista di tutti i poweroutages
	List<PowerOutage> soluzioneMigliore;
	int customersMax; // numero max di customers
	
	public Model() {
		podao = new PowerOutageDAO();
		this.partenza=podao.getPowerOutageList();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	

	public List<PowerOutage> trovaCombinazioneOttima(int xAnni, int yOre) {
		List<PowerOutage> parziale = new ArrayList<PowerOutage>();
		soluzioneMigliore = new ArrayList<PowerOutage>();
		customersMax = 0;
		recursive(parziale, 0, xAnni, yOre);
		return soluzioneMigliore;
	}

	// TROVO TUTTE LE COMBINAZIONI E CERCO QUELLA MIGLIORE
	// scorro la lista partenza con livello L e provo la ricorsione o 
	// aggiungendo l'elemnto in posizione L o non mettendolo
	private void recursive(List<PowerOutage> parziale, int L, int xAnni, int yOre) {
		
		//casi terminali
		if(sommaAnni(parziale)>xAnni || sommaOre(parziale)>yOre) {
			return;
		}
				
		
		int customers = sommaCustomers(parziale);
		if(customers > this.customersMax) {
			soluzioneMigliore = new ArrayList<>(parziale);
			this.customersMax = customers;
		}
		
		
		// L = N -> non ci sono più esami da aggiungere
		if(L == partenza.size()) {
			return;
		}
		//generazione sottoproblemi
		//partenza[L] è da aggiungere oppure no? Provo entrambe le cose
		parziale.add(partenza.get(L));
		recursive(parziale, L+1, xAnni, yOre);
		
		parziale.remove(partenza.get(L));
		recursive(parziale, L+1, xAnni, yOre);
	}


}
