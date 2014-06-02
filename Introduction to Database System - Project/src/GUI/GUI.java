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
import Connection.InsertDelete;
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
	private InsertDeleteWindow insertDeleteWindow;

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

		JMenuItem mntmInsert = new JMenuItem("Insert/Delete");
		mntmInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				insertDeleteWindow.beVisible();
				GUI.this.setEnabled(false);
			}
		});
		mnRequest.add(mntmInsert);
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
				String query = Information.B;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmNewMenuItem_1);

		JMenuItem mntmListTheNames = new JMenuItem(
				"List the names of 10 groups with the most recorded tracks");
		mntmListTheNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.C;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmListTheNames);

		JMenuItem mntmListTheNames_1 = new JMenuItem(
				"List the names of 10 groups with the most releases");
		mntmListTheNames_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.D_V)) {
					sendQuery(Information.D);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable.add(mntmListTheNames_1);

		JMenuItem mntmPrintTheName = new JMenuItem(
				"Print the name of a female artist associated with the most genres");
		mntmPrintTheName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.E;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmPrintTheName);

		JMenuItem mntmListAllCities = new JMenuItem(
				"List all cities which have more female than male artists");
		mntmListAllCities.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.F;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmListAllCities);

		JMenuItem mntmListTheReleases = new JMenuItem(
				"List the releases with the highest number of tracks");
		mntmListTheReleases.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.G;
				sendQuery(query);
			}
		});
		mnDeliverable.add(mntmListTheReleases);

		JMenu mnDeliverable_1 = new JMenu("Deliverable 3");
		mnRequest.add(mnDeliverable_1);

		JMenuItem mntmForEachArea = new JMenuItem(
				"For each area that has more than 30 artists, list the male artist, the female artist and the group with the most tracks recorded");
		mntmForEachArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.H_V1)
						&& sendCreateView(Information.H_V2)
						&& sendCreateView(Information.H_V3)
						&& sendCreateView(Information.H_V4)
						&& sendCreateView(Information.H_V5)) {
					sendQuery(Information.H);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmForEachArea);

		JMenuItem mntmAmericanMetalGroup = new JMenuItem(
				"American metal group Metallica is asking its fans to choose the setlist for its upcoming concert in Switzerland. Assuming that the Metallica fans will choose the songs that have appeared on the highest number of mediums, list the top 25 songs.");
		mntmAmericanMetalGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.I_V1)
						&& sendCreateView(Information.I_V2)) {
					sendQuery(Information.I);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmAmericanMetalGroup);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem(
				"For each of the 10 genres with the most artists, list the female artist that has recorded the highest number of tracks");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.J_V1)
						&& sendCreateView(Information.J_V2)
						&& sendCreateView(Information.J_V3)
						&& sendCreateView(Information.J_V4)) {
					sendQuery(Information.J);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmNewMenuItem_2);

		JMenuItem mntmListAllGenres = new JMenuItem(
				"List all genres that have no female artists, all genres that have no male artists and all genres that have no groups");
		mntmListAllGenres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.K_V1)
						&& sendCreateView(Information.K_V2)
						&& sendCreateView(Information.K_V3)) {
					sendQuery(Information.K);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmListAllGenres);

		JMenuItem mntmForEachArea_1 = new JMenuItem(
				"For each area with more than 10 groups, list the 5 male artists that have recorded the highest number of tracks");
		mntmForEachArea_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.L_V1)
						&& sendCreateView(Information.L_V2)
						&& sendCreateView(Information.L_V3)) {
					sendQuery(Information.L);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmForEachArea_1);

		JMenuItem mntmListThe = new JMenuItem(
				"List the 10 groups with the highest number of tracks that appear on compilations. A compilation is a medium that contains tracks associated with more than one artist");
		mntmListThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.M_V1)
						&& sendCreateView(Information.M_V2)) {
					sendQuery(Information.M);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmListThe);

		JMenuItem mntmListTheTop = new JMenuItem(
				"List the top 10 releases with the most collaborations, i.e., releases where one artist is performing all songs and the highest number of different guest artists contribute to the album");
		mntmListTheTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, Information.N, "Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnDeliverable_1.add(mntmListTheTop);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem(
				"List the release which is associated with the most mediums. If there are more than one such release, list all such releases");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.O_V)) {
					sendQuery(Information.M);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmNewMenuItem_3);

		JMenuItem mntmNewMenuItem_4 = new JMenuItem(
				"List the most popular genre among the groups which are associated with at least 3 genres");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.P_V)) {
					sendQuery(Information.P);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmNewMenuItem_4);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem(
				"List the 5 titles that are associated with the most different songs (recordings) along with the number of songs that share such title");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendQuery(Information.Q);
			}
		});
		mnDeliverable_1.add(mntmNewMenuItem_5);

		JMenuItem mntmListTheTop_1 = new JMenuItem(
				"List the top 10 artists according to their track-to-release ratio. This ratio is computed by dividing the number of tracks an artist is associated with by the number of releases this artist has contributed a track to");
		mntmListTheTop_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.R_V1)
						&& sendCreateView(Information.R_V2)) {
					sendQuery(Information.R);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmListTheTop_1);

		JMenuItem mntmNewMenuItem_6 = new JMenuItem(
				"The concert hit index is a measure of probability that the artist can attract enough fans to fill a football stadium. We define the \u201Chit artist\u201D as one that has more than 10 songs that appear on more than 100 mediums and measure \"hit ability\" as the average number of mediums that a top 10 song appears on. List all \u201Chit artists\u201D according to their \"hit ability\"");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (sendCreateView(Information.S_V1)
						&& sendCreateView(Information.S_V2)) {
					sendQuery(Information.S);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error when trying to send request", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnDeliverable_1.add(mntmNewMenuItem_6);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = Information.A;
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
		insertDeleteWindow = new InsertDeleteWindow(this);
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

	public boolean sendCreateView(String createView) {
		return client.insertDelete(createView) != null;

	}

	public void sendInsertDelete(String insertDelete) {
		InsertDelete request = client.insertDelete(insertDelete);
		if (request != null) {
			JOptionPane.showMessageDialog(null, request.getStatus(),
					"Information", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,
					"Duplicate row with same primary keys", "Error",
					JOptionPane.ERROR_MESSAGE);
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
