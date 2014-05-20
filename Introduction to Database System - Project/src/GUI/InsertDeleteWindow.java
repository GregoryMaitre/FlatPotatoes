package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade
 * 
 */
public class InsertDeleteWindow extends JFrame {

	private JPanel contentPane;
	private JRadioButtonMenuItem rdbtnmntmInsert;
	private JMenuItem rdbtnmntmDelete;
	private JRadioButtonMenuItem rdbtnmntmArea;
	private JRadioButtonMenuItem rdbtnmntmArtist;
	private JRadioButtonMenuItem rdbtnmntmArtistgenre;
	private JRadioButtonMenuItem rdbtnmntmArtisttrack;
	private JRadioButtonMenuItem rdbtnmntmGenre;
	private JRadioButtonMenuItem rdbtnmntmMedium;
	private JRadioButtonMenuItem rdbtnmntmRecording;
	private JRadioButtonMenuItem rdbtnmntmRelease;
	private JRadioButtonMenuItem rdbtnmntmTrack;
	private JLabel lblLabel1;
	private JTextArea textArea1;
	private JLabel lblLabel2;
	private JTextArea textArea2;
	private JLabel lblLabel3;
	private JTextArea textArea3;
	private JLabel lblLabel4;
	private JTextArea textArea4;
	private JLabel lblLabel5;
	private JTextArea textArea5;
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane4;
	private JScrollPane scrollPane5;
	private GUI gui;

	/**
	 * Create the frame.
	 */
	public InsertDeleteWindow(GUI gui) {
		setTitle("Insert/Delete");
		setBounds(100, 100, 486, 461);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				InsertDeleteWindow.this.gui.setEnabled(true);
			}
		});

		setVisible(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (rdbtnmntmInsert.isSelected()) {
					send(getInsert());
				} else if (rdbtnmntmDelete.isSelected()) {
					send(getDelete());
				}
			}
		});
		menuBar.add(btnSend);

		JMenu mnAction = new JMenu("Action");
		menuBar.add(mnAction);

		rdbtnmntmInsert = new JRadioButtonMenuItem("Insert");
		rdbtnmntmInsert.setSelected(true);
		mnAction.add(rdbtnmntmInsert);

		rdbtnmntmDelete = new JRadioButtonMenuItem("Delete");
		mnAction.add(rdbtnmntmDelete);

		JMenu mnTable = new JMenu("Table");
		menuBar.add(mnTable);

		rdbtnmntmArea = new JRadioButtonMenuItem("Area");
		rdbtnmntmArea.setSelected(true);
		rdbtnmntmArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmArea);

		rdbtnmntmArtist = new JRadioButtonMenuItem("Artist");
		rdbtnmntmArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmArtist);

		rdbtnmntmArtistgenre = new JRadioButtonMenuItem("Artist_genre");
		rdbtnmntmArtistgenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmArtistgenre);

		rdbtnmntmArtisttrack = new JRadioButtonMenuItem("Artist_track");
		rdbtnmntmArtisttrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmArtisttrack);

		rdbtnmntmGenre = new JRadioButtonMenuItem("Genre");
		rdbtnmntmGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmGenre);

		rdbtnmntmMedium = new JRadioButtonMenuItem("Medium");
		rdbtnmntmMedium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmMedium);

		rdbtnmntmRecording = new JRadioButtonMenuItem("Recording");
		rdbtnmntmRecording.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmRecording);

		rdbtnmntmRelease = new JRadioButtonMenuItem("Release");
		rdbtnmntmRelease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmRelease);

		rdbtnmntmTrack = new JRadioButtonMenuItem("Track");
		rdbtnmntmTrack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshGUILabelText();
			}
		});
		mnTable.add(rdbtnmntmTrack);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblLabel1 = new JLabel("Label1");
		lblLabel1.setBounds(5, 5, 116, 28);
		contentPane.add(lblLabel1);

		lblLabel2 = new JLabel("Label2");
		lblLabel2.setBounds(5, 80, 116, 28);
		contentPane.add(lblLabel2);

		lblLabel3 = new JLabel("Label3");
		lblLabel3.setBounds(5, 155, 116, 28);
		contentPane.add(lblLabel3);

		lblLabel4 = new JLabel("Label4");
		lblLabel4.setBounds(5, 230, 116, 28);
		contentPane.add(lblLabel4);

		lblLabel5 = new JLabel("Label5");
		lblLabel5.setBounds(5, 303, 116, 28);
		contentPane.add(lblLabel5);

		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(133, 5, 332, 62);
		contentPane.add(scrollPane1);

		textArea1 = new JTextArea();
		scrollPane1.setViewportView(textArea1);

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(133, 80, 332, 62);
		contentPane.add(scrollPane2);

		textArea2 = new JTextArea();
		scrollPane2.setViewportView(textArea2);

		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(133, 155, 332, 62);
		contentPane.add(scrollPane3);

		textArea3 = new JTextArea();
		scrollPane3.setViewportView(textArea3);

		scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(133, 230, 332, 62);
		contentPane.add(scrollPane4);

		textArea4 = new JTextArea();
		scrollPane4.setViewportView(textArea4);

		scrollPane5 = new JScrollPane();
		scrollPane5.setBounds(133, 305, 332, 62);
		contentPane.add(scrollPane5);

		textArea5 = new JTextArea();
		scrollPane5.setViewportView(textArea5);

		ButtonGroup groupAction = new ButtonGroup();
		groupAction.add(rdbtnmntmInsert);
		groupAction.add(rdbtnmntmDelete);

		ButtonGroup groupTable = new ButtonGroup();
		groupTable.add(rdbtnmntmArea);
		groupTable.add(rdbtnmntmArtist);
		groupTable.add(rdbtnmntmArtistgenre);
		groupTable.add(rdbtnmntmArtisttrack);
		groupTable.add(rdbtnmntmGenre);
		groupTable.add(rdbtnmntmMedium);
		groupTable.add(rdbtnmntmRecording);
		groupTable.add(rdbtnmntmRelease);
		groupTable.add(rdbtnmntmTrack);

		this.gui = gui;
		refreshGUILabelText();
	}

	private void AllInvisible() {

		textArea1.setText("");
		textArea2.setText("");
		textArea3.setText("");
		textArea4.setText("");
		textArea5.setText("");

		showField(false, false, false, false, false);
	}

	private void showField(boolean first, boolean second, boolean third,
			boolean fourth, boolean fifth) {
		lblLabel1.setVisible(first);
		lblLabel2.setVisible(second);
		lblLabel3.setVisible(third);
		lblLabel4.setVisible(fourth);
		lblLabel5.setVisible(fifth);

		textArea1.setVisible(first);
		textArea2.setVisible(second);
		textArea3.setVisible(third);
		textArea4.setVisible(fourth);
		textArea5.setVisible(fifth);

		scrollPane1.setVisible(first);
		scrollPane2.setVisible(second);
		scrollPane3.setVisible(third);
		scrollPane4.setVisible(fourth);
		scrollPane5.setVisible(fifth);
	}

	private void refreshGUILabelText() {
		AllInvisible();

		if (rdbtnmntmArea.isSelected()) {
			lblLabel1.setText("ID");
			lblLabel2.setText("NAME");
			lblLabel3.setText("TYPE");

			showField(true, true, true, false, false);
		}

		if (rdbtnmntmArtist.isSelected()) {
			lblLabel1.setText("ID");
			lblLabel2.setText("AREA_ID");
			lblLabel3.setText("NAME");
			lblLabel4.setText("TYPE");
			lblLabel5.setText("GENDER");

			showField(true, true, true, true, true);
		}

		if (rdbtnmntmArtistgenre.isSelected()) {
			lblLabel1.setText("ARTIST_ID");
			lblLabel2.setText("GENRE_ID");

			showField(true, true, false, false, false);
		}

		if (rdbtnmntmArtisttrack.isSelected()) {
			lblLabel1.setText("ARTIST_ID");
			lblLabel2.setText("TRACK_ID");

			showField(true, true, false, false, false);
		}

		if (rdbtnmntmGenre.isSelected()) {
			lblLabel1.setText("ID");
			lblLabel2.setText("NAME");
			lblLabel3.setText("COUNT");
			;

			showField(true, true, true, false, false);
		}

		if (rdbtnmntmMedium.isSelected()) {
			lblLabel1.setText("ID");
			lblLabel2.setText("RELEASE_ID");
			lblLabel3.setText("FORMAT");

			showField(true, true, true, false, false);
		}

		if (rdbtnmntmRecording.isSelected()) {
			lblLabel1.setText("ID");
			lblLabel2.setText("NAME");
			lblLabel3.setText("LENGTH");

			showField(true, true, true, false, false);
		}

		if (rdbtnmntmRelease.isSelected()) {
			lblLabel1.setText("ID");
			lblLabel2.setText("NAME");

			showField(true, true, false, false, false);
		}

		if (rdbtnmntmTrack.isSelected()) {
			lblLabel1.setText("ID");
			lblLabel2.setText("MEDIUM_ID");
			lblLabel3.setText("RECORDING_ID");
			lblLabel4.setText("POSITION");

			showField(true, true, true, true, false);
		}
	}

	public void beVisible() {
		setVisible(true);
	}

	private void send(String request) {
		gui.sendInsertDelete(request);
		setVisible(false);
		gui.setEnabled(true);
	}

	private String getInsert() {
		if (rdbtnmntmArea.isSelected()) {
			return "INSERT INTO AREA(ID, NAME, TYPE) VALUES("
					+ textArea1.getText() + ", '" + textArea2.getText()
					+ "', '" + textArea3.getText() + "')";
		}

		if (rdbtnmntmArtist.isSelected()) {
			return "INSERT INTO ARTIST(ID, AREA_ID, NAME, TYPE, GENDER) VALUES("
					+ textArea1.getText()
					+ ", "
					+ textArea2.getText()
					+ ", '"
					+ textArea3.getText()
					+ "', '"
					+ textArea4.getText()
					+ "', '" + textArea5.getText() + "')";
		}

		if (rdbtnmntmArtistgenre.isSelected()) {
			return "INSERT INTO ARTIST_GENRE(ARTIST_ID, GENRE_ID) VALUES("
					+ textArea1.getText() + ", " + textArea2.getText() + ")";
		}

		if (rdbtnmntmArtisttrack.isSelected()) {
			return "INSERT INTO ARTIST_TRACK(ARTIST_ID, TRACK_ID) VALUES("
					+ textArea1.getText() + ", " + textArea2.getText() + ")";
		}

		if (rdbtnmntmGenre.isSelected()) {
			return "INSERT INTO GENRE(ID, NAME, COUNT) VALUES("
					+ textArea1.getText() + ", '" + textArea2.getText() + "', "
					+ textArea3.getText() + ")";
		}

		if (rdbtnmntmMedium.isSelected()) {
			return "INSERT INTO MEDIUM(ID, RELEASE_ID, FORMAT) VALUES("
					+ textArea1.getText() + ", " + textArea2.getText() + ", '"
					+ textArea3.getText() + "')";
		}

		if (rdbtnmntmRecording.isSelected()) {
			return "INSERT INTO RECORDING(ID, NAME, LENGTH) VALUES("
					+ textArea1.getText() + ", '" + textArea2.getText() + "', "
					+ textArea3.getText() + ")";
		}

		if (rdbtnmntmRelease.isSelected()) {
			return "INSERT INTO RELEASE(ID, NAME) VALUES("
					+ textArea1.getText() + ", '" + textArea2.getText() + "')";
		}

		if (rdbtnmntmTrack.isSelected()) {
			return "INSERT INTO TRACK(ID, MEDIUM_ID, RECORDING_ID, POSITION) VALUES("
					+ textArea1.getText()
					+ ", "
					+ textArea2.getText()
					+ ", "
					+ textArea3.getText() + ", " + textArea4.getText() + ")";
		}

		// Logically Impossible
		return null;
	}

	private String getDelete() {
		if (rdbtnmntmArea.isSelected()) {
			return "DELETE FROM AREA a WHERE a.ID = " + textArea1.getText()
					+ " AND a.NAME = '" + textArea2.getText()
					+ "' AND a.TYPE = '" + textArea3.getText() + "'";
		}

		if (rdbtnmntmArtist.isSelected()) {
			return "DELETE FROM ARTIST a WHERE a.ID = " + textArea1.getText()
					+ " AND a.AREA_ID = " + textArea2.getText()
					+ " AND a.NAME = '" + textArea3.getText()
					+ "' AND a.TYPE = '" + textArea4.getText()
					+ "' AND a.GENDER = '" + textArea5.getText() + "'";
		}

		if (rdbtnmntmArtistgenre.isSelected()) {
			return "DELETE FROM ARTIST_GENRE a WHERE a.ARTIST_ID = "
					+ textArea1.getText() + " AND a.GENRE_ID = "
					+ textArea2.getText();
		}

		if (rdbtnmntmArtisttrack.isSelected()) {
			return "DELETE FROM ARTIST_TRACK a WHERE a.ARTIST_ID = "
					+ textArea1.getText() + " AND a.TRACK_ID = "
					+ textArea2.getText();
		}

		if (rdbtnmntmGenre.isSelected()) {
			return "DELETE FROM GENRE g WHERE g.ID = " + textArea1.getText()
					+ " AND g.NAME = '" + textArea2.getText()
					+ "' AND g.COUNT = " + textArea3.getText();
		}

		if (rdbtnmntmMedium.isSelected()) {
			return "DELETE FROM MEDIUM m WHERE m.ID = " + textArea1.getText()
					+ " AND m.RELEASE_ID = " + textArea2.getText()
					+ " AND m.FORMAT = '" + textArea3.getText() + "'";
		}

		if (rdbtnmntmRecording.isSelected()) {
			return "DELETE FROM RECORDING r WHERE r.ID = "
					+ textArea1.getText() + "AND r.NAME = '"
					+ textArea2.getText() + "' AND r.LENGTH = "
					+ textArea3.getText();
		}

		if (rdbtnmntmRelease.isSelected()) {
			return "DELETE FROM RELEASE r WHERE r.ID = " + textArea1.getText()
					+ " AND r.NAME = '" + textArea2.getText() + "'";
		}

		if (rdbtnmntmTrack.isSelected()) {
			return "DELETE FROM TRACK t WHERE t.ID = " + textArea1.getText()
					+ " AND t.MEDIUM_ID = " + textArea2.getText()
					+ " AND t.RECORDING_ID = " + textArea3.getText()
					+ " AND t.POSITION = " + textArea4.getText();
		}
		
		// Logically Impossible
		return null;
	}
}
