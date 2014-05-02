package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Connection.Client;
import Connection.Information;
import Connection.Query;

import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade & Beaud Guillaume
 * 
 */
public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private Client client;

	private Query currentQuery;
	private JTable table;
	private SearchWindow searchWindow;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public GUI() throws SQLException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (client != null) {
					int answer = JOptionPane.showConfirmDialog(null,
							"Do you want to commit?", "Information",
							JOptionPane.YES_NO_OPTION);
					if (answer == 0) {
						if (client.commit()) {
							JOptionPane.showMessageDialog(null,
									"Commit successful!", "Information",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"Commit failled!", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}

					client.close();
				}
			}
		});
		setTitle("Introduction to Database Systems - Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 471);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnManager = new JMenu("Manager");
		menuBar.add(mnManager);

		JMenuItem mntmCommit = new JMenuItem("Commit");
		mntmCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (client.commit()) {
					JOptionPane.showMessageDialog(null, "Commit successful!",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Commit failled!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnManager.add(mntmCommit);

		JMenuItem mntmRollback = new JMenuItem("Rollback");
		mntmRollback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (client.rollback()) {
					JOptionPane.showMessageDialog(null, "Rollback successful!",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Rollback failled!",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnManager.add(mntmRollback);

		JMenu mnRequest = new JMenu("Request");
		menuBar.add(mnRequest);

		JMenuItem mntmCustomizedRequest = new JMenuItem("Customized request");
		mntmCustomizedRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String request = JOptionPane.showInputDialog(null,
						"What is your request?", "Information",
						JOptionPane.INFORMATION_MESSAGE);

				if (request != null) {
					sendQuery(request);
				}
			}
		});

		JMenuItem mntmSearch = new JMenuItem("Search");
		mntmSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!searchWindow.isVisible()) {
					if (isConnected()) {
						searchWindow.beVisible();
						GUI.this.setEnabled(false);
					}
				}
			}
		});
		mnRequest.add(mntmSearch);
		mnRequest.add(mntmCustomizedRequest);

		JMenu mnDeliverable = new JMenu("Deliverable 2");
		mnRequest.add(mnDeliverable);

		JMenuItem mntmNewMenuItem = new JMenuItem(
				"Print the names of artists from Switzerland");
		mnDeliverable.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem(
				"Print the names of areas with the highest number male artists, female artists and groups, as well as the number of artists of each type in each of the three areas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.NAME_AREA_HIGHEST_MALE_FEMALE_GROUPS;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmNewMenuItem_1);

		JMenuItem mntmListTheNames = new JMenuItem(
				"List the names of 10 groups with the most recorded tracks");
		mntmListTheNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.NAME_10_GROUPS_MOST_TRACKS;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmListTheNames);

		JMenuItem mntmListTheNames_1 = new JMenuItem(
				"List the names of 10 groups with the most releases");
		mntmListTheNames_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.NAME_10_GROUPS_MOST_RELEASES;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmListTheNames_1);

		JMenuItem mntmPrintTheName = new JMenuItem(
				"Print the name of a female artist associated with the most genres");
		mntmPrintTheName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.NAME_FEMALE_ARTIST_MOST_GENRES;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmPrintTheName);

		JMenuItem mntmListAllCities = new JMenuItem(
				"List all cities which have more female than male artists");
		mntmListAllCities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.ALL_CITIES_MORE_FEMALE_THAN_MALE_ARTISTS;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmListAllCities);

		JMenuItem mntmListTheReleases = new JMenuItem(
				"List the releases with the highest number of tracks");
		mntmListTheReleases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.RELEASES_HIGHEST_NUMBER_TRACKS;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmListTheReleases);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.NAMES_OF_ARTISTS_FROM_SWITZERLAND;
				sendQuery(query);
			}
		});

		JMenu mnData = new JMenu("Data");
		menuBar.add(mnData);

		JMenuItem mntmMore = new JMenuItem("More");
		mntmMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				more();
			}
		});
		mnData.add(mntmMore);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				JScrollBar bar = scrollPane.getVerticalScrollBar();
				if (isCurrentQuery()
						&& (bar.getValue() + bar.getHeight() > bar.getMaximum() * 0.75)) {
					more();
				}
			}
		});
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);

		// Client code
		client = new Client();
		if (!client.connect()) {
			JOptionPane.showMessageDialog(null,
					"Unable to connect to the database", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Unable to connect!");
			System.exit(0);
		}

		searchWindow = new SearchWindow(this);
	}

	/**
	 * Send a query to the database
	 * 
	 * @param query
	 */
	public void sendQuery(String query) {

		if (isCurrentQuery()) {
			currentQuery.close();
			currentQuery = null;
		}

		tableModel.setColumnCount(0);
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();

		currentQuery = client.query(query);
		if (isCurrentQuery()) {
			currentQuery.printResult(tableModel);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid query : " + query,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Print more data from the answer of the query
	 */
	private void more() {
		if (isCurrentQuery()) {
			currentQuery.printResult(tableModel);
		} else {
			JOptionPane.showMessageDialog(null, "No more data to show!",
					"Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Test if the query and the result are close
	 * 
	 * @return true, if the query isn't close
	 */
	private boolean isCurrentQuery() {
		return (currentQuery != null) && (!currentQuery.isClosed());
	}

	/**
	 * Test if we are connected to the database
	 * 
	 * @return true, if we are connected to the database
	 */
	private boolean isConnected() {
		return (client != null) && client.isConnected();
	}
}
