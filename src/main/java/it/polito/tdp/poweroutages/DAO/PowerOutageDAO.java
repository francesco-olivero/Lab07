package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutage> getPowerOutageList() {

		String sql = "SELECT `id`, `nerc_id`, `customers_affected`, `date_event_began`, `date_event_finished` "
				+ "FROM `PowerOutages`";
		List<PowerOutage> powerOutageList = new ArrayList<PowerOutage>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage n = new PowerOutage(res.getInt("id"), res.getInt("nerc_id"), 
				res.getInt("customers_affected"), res.getTimestamp("date_event_began").toLocalDateTime(), 
				res.getTimestamp("date_event_finished").toLocalDateTime());
				
				powerOutageList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powerOutageList;
	}
	
	// NOTA: come prendere le date da database sql
	// dal database prendo sempre una Date o Timestamp (se mi serve anche il tempo in ore/minuti), 
	// nella classe PowerOutages ho dei LocalDateTime e nel dao
	// faccio rs.getTimestamp().toLocalDate() nel costruttore di PowerOutage

}
