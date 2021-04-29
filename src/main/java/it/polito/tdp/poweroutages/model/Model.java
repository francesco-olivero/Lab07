package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<PowerOutage> partenza; // lista di tutti i poweroutages
	List<PowerOutage> soluzioneMigliore;
	int customersMax;
	
	public Model() {
		this.podao = new PowerOutageDAO();
		this.customersMax=0;
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	

	public List<PowerOutage> trovaCombinazioneOttima(int xAnni, int yOre, int nercId) {
		this.partenza=podao.getPowerOutageList(nercId);
		List<PowerOutage> parziale = new ArrayList<PowerOutage>();
		soluzioneMigliore = new ArrayList<PowerOutage>();
		customersMax = 0;
		recursive(parziale, 0, xAnni, yOre);
		return soluzioneMigliore;
	}

	// CERCO TUTTI I SOTTOGRUPPI DI partenza E CERCO QUELLO MIGLIORE
	// scorro la lista partenza con livello L e provo la ricorsione o 
	// aggiungendo l'elemnto in posizione L o non mettendolo
	private void recursive(List<PowerOutage> parziale, int L, int xAnni, int yOre) {
		
		//CASI TERMINALI
		// CASO 1: L (livello) = N (numero totale di outages) -> non ci sono più oggetti da aggiungere
		// devo metterlo dopo aver controllato però se la soluzione rispetta è ottima, altrimenti non la salvo
		// se è l'ultima
		
		
		// CASO 2: somma anni o ore superiore a limiti
		if(sommaAnni(parziale)>xAnni || sommaOre(parziale)>yOre) {
			return;
		}
		
		
		//CONTROLLO SE LA SOLUZIONE È OTTIMA, NEL CASO LA SALVO
		int customers = sommaCustomers(parziale);
		if(customers > this.customersMax) {
			soluzioneMigliore = new ArrayList<>(parziale);
			this.customersMax = customers;
		}
		
		// CASO 1: L (livello) = N (numero totale di outages) -> non ci sono più oggetti da aggiungere
		if(L == partenza.size()) {
			return;
		}
		
		
		//GENERAZIONE DEI SOTTOPROBLEMI
		//normalmente si usa un ciclo for per valutare tutte le combinazini ad ogni passo
		//ma in questo caso cerco solo un sottoinsieme di tutti gli oggetti, quindi no ciclo for
		//partenza[L] è da aggiungere oppure no? Provo entrambe le cose
		parziale.add(partenza.get(L));
		recursive(parziale, L+1, xAnni, yOre); // RICORSIONE AGGIUNGENDO L'ELEMENTO
		parziale.remove(partenza.get(L));
		
		recursive(parziale, L+1, xAnni, yOre); // RICORSIONE SENZA L'ELEMENTO
	}

	public int sommaCustomers(List<PowerOutage> lista) {
		int somma = 0;
		for (PowerOutage po: lista) {
			somma += po.getCustomers();
		}
		return somma;
	}

	public int sommaAnni(List<PowerOutage> lista) {
		if(lista.isEmpty())
			return 0;
		int daAnno=lista.get(0).getAnno();
		int aAnno=lista.get(0).getAnno();
		for (PowerOutage po: lista) {
			if (po.getAnno()>aAnno)
				aAnno=po.getAnno();
			if (po.getAnno()<daAnno)
				daAnno=po.getAnno();
		}
		return aAnno-daAnno;
	}

	public double sommaOre(List<PowerOutage> lista) {
		double somma = 0;
		for (PowerOutage po: lista) {
			somma += po.getDurataOre();
		}
		return somma;
	}


}
