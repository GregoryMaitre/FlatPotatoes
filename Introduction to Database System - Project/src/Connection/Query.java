package Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade
 * 
 */
public class Query {

	private String query;

	private Statement statement;
	private ResultSet result;
	private ResultSetMetaData resultMetaData;
	
	// Use to display a part of the results
	private int count = 100;
	private boolean closed = false;

	public Query(String query) {
		this.query = query;
	}

	public void send(Connection connection) throws SQLException {
		// Create the query and send it
		statement = connection.createStatement();
		result = statement.executeQuery(query);

		// Usefull data from the result
		resultMetaData = result.getMetaData();
	}
	
	public boolean isClosed() {
		return closed;
	}
	
	public void printResult(DefaultListModel<String> list) {
		
		count = 100;
		try {
			
			String head = "";
			for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
				head += resultMetaData.getColumnName(i) + ", ";
			}

			list.addElement(head);

			String row = "";
			while ((count > 0) && result.next()) {
				count--;
				for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
					row += result.getObject(i).toString() + ", ";
				}
				list.addElement(row);
				row = "";
			}
			
			if (count > 0) {
				close();
			}
		} catch (SQLException e) {
			System.err.println("Error when trying to print the result of " + query);
		}
	}

	public void close() {
		try {
			result.close();
		} catch (SQLException e) {
			System.err.println("Error when trying to close ResultSet");
		}
		try {
			statement.close();
		} catch (SQLException e) {
			System.err.println("Error when trying to close Statement");
		}
		closed = true;
	}
}
