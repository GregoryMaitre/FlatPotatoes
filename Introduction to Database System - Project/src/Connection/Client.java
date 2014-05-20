package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

/**
 * This class contains method to connect and send query to the database
 * 
 * @author Gregory Maitre & Patrick Andrade & Beaud Guillaume
 * 
 */
public class Client {

	private boolean connected = false;
	private Connection connection;

	/**
	 * Constructor: create the driver
	 * 
	 * @throws SQLException
	 */
	public Client() throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
	}

	/**
	 * Try to connect to the database
	 * 
	 * @return true, if we are connected
	 */
	public boolean connect() {
		if (!connected) {
			try {
				connection = DriverManager.getConnection(
						Information.URL_DATABASE, Information.USER,
						Information.PASSWORD);
				connection.setAutoCommit(false);
				connected = true;

				System.out.println("Connected to " + Information.URL_DATABASE);
			} catch (Exception e) {
				System.err.println("Error when trying to connect "
						+ Information.URL_DATABASE);
				connected = false;
			}
		}
		return connected;
	}
	
	/**
	 * Test if we are connected
	 * 
	 * @return true, if we are connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * Commit change to the database
	 */
	public boolean commit() {
		try {
			connection.commit();
			return true;
		} catch (SQLException e) {
			System.err.println("Error when commit");
		}
		return false;
	}

	/**
	 * Undo all change until last commit
	 */
	public boolean rollback() {
		try {
			connection.rollback();
			return true;
		} catch (SQLException e) {
			System.err.println("Error when commit");
			return false;
		}
	}

	/**
	 * Close the connection
	 * 
	 * @return true, if we are disconnected
	 */
	public boolean close() {
		if (connected) {
			try {
				connection.close();
				System.out.println("Disconnected from " + Information.URL_DATABASE);
			} catch (SQLException e) {
				System.err.println("Error when closing connection");
			}
			connected = false;
		}
		return !connected;
	}

	/**
	 * Create and send the query to the database
	 * 
	 * @param queryString the query
	 * @return the query object that contains the answer
	 */
	public Query query(String queryString) {
		Query query = null;
		try {
			query = new Query(queryString);
			query.send(connection);
		} catch (SQLException e) {
			System.err.println("Error when querying " + Information.URL_DATABASE);
			query = null;
		}
		return query;
	}
	
	public InsertDelete insertDelete(String insertDeleteString) {
		InsertDelete insertDelete = null;
		try {
			insertDelete = new InsertDelete(insertDeleteString);
			insertDelete.send(connection);
		} catch (SQLException e) {
			System.err.println("Error when inserting or deleting " + Information.URL_DATABASE);
			insertDelete = null;
		}
		return insertDelete;
	}
}
