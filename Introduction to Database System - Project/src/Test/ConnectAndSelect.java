package Test;

import java.sql.SQLException;

import Connection.Client;
import Connection.Query;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade & Beaud Guillaume
 * 
 */
public class ConnectAndSelect {
	public static void main(String[] args) throws SQLException {
		Client client = new Client();
		if (client.connect()) {
			Query query = client.query("select * from genre");
			//query.printResult();
			query.close();
			client.close();
		}
	}
}
