package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;

import it.polito.tdp.poweroutages.model.Model;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			//Model m = new Model();
			//System.out.println(dao.getPowerOutageList()) ;
			//System.out.print("somma ore: "+m.sommaOre(dao.getPowerOutageList()));

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
	}

}
