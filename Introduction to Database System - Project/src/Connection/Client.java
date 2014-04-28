package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade
 * 
 */
public class Client {

	private boolean connected = false;
	private Connection connection;

	public Client() throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
	}

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
	 * Commit change to the database
	 */
	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			System.err.println("Error when commit");
		}
	}

	/**
	 * Undo all change until last commit
	 */
	public void rollback() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			System.err.println("Error when commit");
		}
	}

	/**
	 * Close the connection
	 * 
	 * @return
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
		return connected;
	}

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
}
