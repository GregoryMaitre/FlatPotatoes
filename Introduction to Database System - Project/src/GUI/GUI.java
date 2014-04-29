package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connection.Client;
import Connection.Information;
import Connection.Query;

import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTable;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade
 * 
 */
public class GUI extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel tableModel;
	private Client client;

	private Query currentQuery;
	private JTable table;

	/**
	 * Launch the application.
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
				sendQuery(request);
			}
		});
		mnRequest.add(mntmCustomizedRequest);

		JMenu mnDeliverable = new JMenu("Deliverable 2");
		mnRequest.add(mnDeliverable);

		JMenuItem mntmNewMenuItem = new JMenuItem(
				"Print the names of artists from Switzerland");
		mnDeliverable.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem(
				"Print the names of areas with the highest number male artists, female artists and groups, as well as the number of artists of each type in each of the three areas");
		mnDeliverable.add(mntmNewMenuItem_1);

		JMenuItem mntmListTheNames = new JMenuItem(
				"List the names of 10 groups with the most recorded tracks");
		mnDeliverable.add(mntmListTheNames);

		JMenuItem mntmListTheNames_1 = new JMenuItem(
				"List the names of 10 groups with the most releases");
		mnDeliverable.add(mntmListTheNames_1);

		JMenuItem mntmPrintTheName = new JMenuItem(
				"Print the name of a female artist associated with the most genres");
		mnDeliverable.add(mntmPrintTheName);

		JMenuItem mntmListAllCities = new JMenuItem(
				"List all cities which have more female than male artists");
		mnDeliverable.add(mntmListAllCities);

		JMenuItem mntmListTheReleases = new JMenuItem(
				"List the releases with the highest number of tracks");
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
				if ((currentQuery != null) && (!currentQuery.isClosed())) {
					currentQuery.printResult(tableModel);
				} else {
					JOptionPane.showMessageDialog(null,
							"No more data to show!", "Information",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		mnData.add(mntmMore);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
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
	}

	public void sendQuery(String query) {
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
		
		currentQuery = client.query(query);
		if (currentQuery != null) {
			currentQuery.printResult(tableModel);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid query : " + query,
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
