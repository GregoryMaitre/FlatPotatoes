package Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * This class contains method to manipulate the query and the answer from the database
 * 
 * @author Gregory Maitre & Patrick Andrade & Beaud Guillaume
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
	
	private Vector<Vector<String>> dataList;
	private Vector<String> header;

	/**
	 * Constructor
	 * 
	 * @param query the query to be send
	 */
	public Query(String query) {
		this.query = query;
	}

	/**
	 * Send the query to the database and get the result
	 * 
	 * @param connection the connection between the database and the client
	 * @throws SQLException
	 */
	public void send(Connection connection) throws SQLException {
		// Create the query and send it
		statement = connection.createStatement();
		result = statement.executeQuery(query);

		// Usefull data from the result
		resultMetaData = result.getMetaData();
		
		header = new Vector<String>();
		dataList = new Vector<Vector<String>>();
		
		for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
			header.add(resultMetaData.getColumnName(i));
		}
	}
	
	/**
	 * Test if there are any data in the answer
	 * 
	 * @return true if there are no data in the answer
	 */
	public boolean isClosed() {
		return closed;
	}
	
	/**
	 * Print the result in the table
	 * 
	 * @param table the table where we want to print
	 */
	public void printResult(DefaultTableModel table) {
		
		count = 100;
		try {
			while ((count > 0) && result.next()) {
				count--;
				Vector<String> data = new Vector<String>();
				for (int i = 1; i <= resultMetaData.getColumnCount(); i++) {
					data.add(result.getObject(i).toString());
				}
				dataList.add(data);
			}
			
			table.setDataVector(dataList, header);
			
			if (count > 0) {
				close();
			}
		} catch (SQLException e) {
			System.err.println("Error when trying to print the result of " + query);
		}
	}

	/**
	 * Close the query, that is close the result and the statement
	 */
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
