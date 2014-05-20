package Connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade
 * 
 */
public class InsertDelete {
	private String insertDelete;

	private Statement statement;
	private int status;
	private boolean closed = false;

	/**
	 * Constructor
	 * 
	 * @param insertDelete the query to be send
	 */
	public InsertDelete(String insertDelete) {
		this.insertDelete = insertDelete;
	}

	/**
	 * Send the insert or delete to the database and get the result status
	 * 
	 * @param connection the connection between the database and the client
	 * @throws SQLException
	 */
	public void send(Connection connection) throws SQLException {
		// Create the query and send it
		statement = connection.createStatement();
		status = statement.executeUpdate(insertDelete);
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
	 * Print the status of the insertion or deletion
	 * 
	 * @param table the table where we want to print
	 */
	public void printResult() {
		System.out.println(getStatus());
	}
	
	public String getStatus() {
		return "Insertion/Deletion status: " + (status == 1 ? " success" : "failure");
	}

	/**
	 * Close the query, that is close the statement
	 */
	public void close() {
		try {
			statement.close();
		} catch (SQLException e) {
			System.err.println("Error when trying to close Statement");
		}
		closed = true;
	}
}
