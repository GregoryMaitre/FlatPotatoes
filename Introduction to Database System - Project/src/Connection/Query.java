package Connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * TODO: Comment this class
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

	public Query(String query) {
		this.query = query;
	}

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
	
	public boolean isClosed() {
		return closed;
	}
	
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
