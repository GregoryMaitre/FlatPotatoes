package Test;

import java.sql.SQLException;

import Connection.Client;
import Connection.InsertDelete;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade
 * 
 */
public class ConnectAndInsert {
	public static void main(String[] args) throws SQLException {
		Client client = new Client();
		if (client.connect()) {
			InsertDelete insert = client.insertDelete("INSERT INTO Area(ID, NAME, TYPE) VALUES(1234567, 'Chez moi', 'lard')");
			insert.printResult();
			insert.close();
			InsertDelete delete = client.insertDelete("DELETE FROM Area a WHERE a.ID = 1234567");
			delete.printResult();
			delete.close();
			client.close();
		}
	}
}