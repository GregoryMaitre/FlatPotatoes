package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade & Beaud Guillaume
 * 
 */
public class SearchWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea artistNameTextArea;
	private JTextArea genreTextArea;
	private JTextArea areaTextArea;
	private JTextArea recordingTextArea;
	private JTextArea releaseTextArea;
	private JTextArea mediumTextArea;
	private GUI gui;
	private JCheckBox chckbxArtist;
	private JCheckBox chckbxGenre;
	private JCheckBox chckbxArea;
	private JCheckBox chckbxRecording;
	private JCheckBox chckbxRelease;
	private JCheckBox chckbxMedium;

	/**
	 * Create the frame.
	 */
	public SearchWindow(GUI gui) {

		this.gui = gui;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				SearchWindow.this.gui.setEnabled(true);
			}
		});
		setType(Type.POPUP);
		setTitle("Search");
		setBounds(100, 100, 597, 413);
		setVisible(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				send();
			}
		});
		menuBar.add(btnSend);

		chckbxArtist = new JCheckBox("Artist");
		menuBar.add(chckbxArtist);

		chckbxGenre = new JCheckBox("Genre");
		menuBar.add(chckbxGenre);

		chckbxArea = new JCheckBox("Area");
		menuBar.add(chckbxArea);

		chckbxRecording = new JCheckBox("Recording");
		menuBar.add(chckbxRecording);

		chckbxRelease = new JCheckBox("Release");
		menuBar.add(chckbxRelease);

		chckbxMedium = new JCheckBox("Medium");
		menuBar.add(chckbxMedium);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 76, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 16, 16, 16, 16, 16, 16, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblArtistName = new JLabel("Artist name:");
		GridBagConstraints gbc_lblArtistName = new GridBagConstraints();
		gbc_lblArtistName.anchor = GridBagConstraints.NORTH;
		gbc_lblArtistName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblArtistName.insets = new Insets(0, 0, 5, 5);
		gbc_lblArtistName.gridx = 0;
		gbc_lblArtistName.gridy = 0;
		contentPane.add(lblArtistName, gbc_lblArtistName);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		artistNameTextArea = new JTextArea();
		artistNameTextArea.setWrapStyleWord(true);
		artistNameTextArea.setLineWrap(true);
		scrollPane.setViewportView(artistNameTextArea);

		JLabel lblGenre = new JLabel("Genre:");
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.anchor = GridBagConstraints.NORTH;
		gbc_lblGenre.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGenre.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenre.gridx = 0;
		gbc_lblGenre.gridy = 1;
		contentPane.add(lblGenre, gbc_lblGenre);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);

		genreTextArea = new JTextArea();
		genreTextArea.setWrapStyleWord(true);
		genreTextArea.setLineWrap(true);
		scrollPane_1.setViewportView(genreTextArea);

		JLabel lblArea = new JLabel("Area:");
		GridBagConstraints gbc_lblArea = new GridBagConstraints();
		gbc_lblArea.anchor = GridBagConstraints.NORTH;
		gbc_lblArea.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblArea.insets = new Insets(0, 0, 5, 5);
		gbc_lblArea.gridx = 0;
		gbc_lblArea.gridy = 2;
		contentPane.add(lblArea, gbc_lblArea);

		JScrollPane scrollPane_2 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
		gbc_scrollPane_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_2.gridx = 1;
		gbc_scrollPane_2.gridy = 2;
		contentPane.add(scrollPane_2, gbc_scrollPane_2);

		areaTextArea = new JTextArea();
		areaTextArea.setWrapStyleWord(true);
		areaTextArea.setLineWrap(true);
		scrollPane_2.setViewportView(areaTextArea);

		JLabel lblRecording = new JLabel("Recording:");
		GridBagConstraints gbc_lblRecording = new GridBagConstraints();
		gbc_lblRecording.anchor = GridBagConstraints.NORTH;
		gbc_lblRecording.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRecording.insets = new Insets(0, 0, 5, 5);
		gbc_lblRecording.gridx = 0;
		gbc_lblRecording.gridy = 3;
		contentPane.add(lblRecording, gbc_lblRecording);

		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 1;
		gbc_scrollPane_3.gridy = 3;
		contentPane.add(scrollPane_3, gbc_scrollPane_3);

		recordingTextArea = new JTextArea();
		recordingTextArea.setWrapStyleWord(true);
		recordingTextArea.setLineWrap(true);
		scrollPane_3.setViewportView(recordingTextArea);

		JLabel lblRelease = new JLabel("Release:");
		GridBagConstraints gbc_lblRelease = new GridBagConstraints();
		gbc_lblRelease.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblRelease.insets = new Insets(0, 0, 5, 5);
		gbc_lblRelease.gridx = 0;
		gbc_lblRelease.gridy = 4;
		contentPane.add(lblRelease, gbc_lblRelease);

		JScrollPane scrollPane_4 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_4 = new GridBagConstraints();
		gbc_scrollPane_4.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_4.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_4.gridx = 1;
		gbc_scrollPane_4.gridy = 4;
		contentPane.add(scrollPane_4, gbc_scrollPane_4);

		releaseTextArea = new JTextArea();
		releaseTextArea.setWrapStyleWord(true);
		releaseTextArea.setLineWrap(true);
		scrollPane_4.setViewportView(releaseTextArea);

		JLabel lblMedium = new JLabel("Medium:");
		GridBagConstraints gbc_lblMedium = new GridBagConstraints();
		gbc_lblMedium.insets = new Insets(0, 0, 0, 5);
		gbc_lblMedium.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMedium.gridx = 0;
		gbc_lblMedium.gridy = 5;
		contentPane.add(lblMedium, gbc_lblMedium);

		JScrollPane scrollPane_5 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_5 = new GridBagConstraints();
		gbc_scrollPane_5.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_5.gridx = 1;
		gbc_scrollPane_5.gridy = 5;
		contentPane.add(scrollPane_5, gbc_scrollPane_5);

		mediumTextArea = new JTextArea();
		mediumTextArea.setLineWrap(true);
		mediumTextArea.setWrapStyleWord(true);
		scrollPane_5.setViewportView(mediumTextArea);
	}

	public void beVisible() {
		setVisible(true);
	}

	private void send() {
		String searchRequest = null;
		if (isAllEmpty()) {
			if (allUnselected()) {
				JOptionPane.showMessageDialog(null,
						"Please enter something to be searched!",
						"Information", JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				searchRequest = selectPart() + " " + fromPart() + " WHERE "
						+ whereLinkPart();
			}
		} else {
			searchRequest = selectPart() + " " + fromPart() + " " + wherePart();
		}

		gui.sendQuery(searchRequest);

		setVisible(false);
		gui.setEnabled(true);
	}

	private boolean isAllEmpty() {
		return (artistNameTextArea.getText().length() == 0)
				&& (genreTextArea.getText().length() == 0)
				&& (areaTextArea.getText().length() == 0)
				&& (recordingTextArea.getText().length() == 0)
				&& (releaseTextArea.getText().length() == 0)
				&& (mediumTextArea.getText().length() == 0);
	}

	private boolean allUnselected() {
		return !chckbxArea.isSelected() && !chckbxArtist.isSelected()
				&& !chckbxGenre.isSelected() && !chckbxMedium.isSelected()
				&& !chckbxRecording.isSelected() && !chckbxRelease.isSelected();
	}

	private String selectPart() {
		String select = "SELECT DISTINCT ";
		select += getValue(
				artistNameTextArea,
				chckbxArtist,
				"artist.name AS \"Artist name\", artist.type AS \"Artist type\", artist.gender AS \"Artist gender\", ");
		select += getValue(genreTextArea, chckbxGenre,
				"genre.name AS \"Genre name\", genre.count AS \"Genre count\", ");
		select += getValue(areaTextArea, chckbxArea,
				"area.name AS \"Area name\", area.type AS \"Area type\", ");
		select += getValue(recordingTextArea, chckbxRecording,
				"recording.name AS \"Recording name\", recording.length AS \"Recording length\", ");
		select += getValue(releaseTextArea, chckbxRelease,
				"release.name AS \"Release name\", ");
		select += getValue(mediumTextArea, chckbxMedium,
				"medium.format AS \"Medium format\", ");

		select = select.substring(0, select.length() - 2);

		return select;
	}

	private String fromPart() {
		/*
		 * String from = "FROM "; from += getValue(artistNameTextArea,
		 * chckbxArtist, "Artist artist, "); from += getValue(genreTextArea,
		 * chckbxGenre, "Genre genre, "); from += getValue(areaTextArea,
		 * chckbxArea, "Area area, "); from += getValue(recordingTextArea,
		 * chckbxRecording, "Recording recording, "); from +=
		 * getValue(releaseTextArea, chckbxRelease, "Release release, "); from
		 * += getValue(mediumTextArea, chckbxMedium, "Medium medium, "); //
		 * TODO: from += //
		 * "Artist_track artist_track, Track track, Artist_genre artist_genre" ;
		 * 
		 * from = from.substring(0, from.length() - 2);
		 * 
		 * return from;
		 */
		return "FROM Artist artist, Genre genre, Area area, Recording recording, "
				+ "Release release, Medium medium, Artist_track artist_track, Track track, "
				+ "Artist_genre artist_genre";
	}

	private String wherePart() {
		String where = "WHERE ";
		where += getValue(artistNameTextArea, null, "artist.name = '"
				+ artistNameTextArea.getText() + "' and ");
		where += getValue(genreTextArea, null,
				"genre.name = '" + genreTextArea.getText() + "' and ");
		where += getValue(areaTextArea, null,
				"area.name = '" + areaTextArea.getText() + "' and ");
		where += getValue(recordingTextArea, null, "recording.name = '"
				+ recordingTextArea.getText() + "' and ");
		where += getValue(releaseTextArea, null, "release.name = '"
				+ releaseTextArea.getText() + "' and ");
		where += getValue(mediumTextArea, null, "medium.format = '"
				+ mediumTextArea.getText() + "' and ");

		where += whereLinkPart();

		return where;
	}

	private String whereLinkPart() {
		return "area.ID = artist.area_ID and artist.ID = artist_genre.artist_ID "
				+ "and artist_genre.genre_ID = genre.ID and artist.ID = artist_track.artist_ID "
				+ "and artist_track.track_ID = track.ID and track.recording_ID = recording.ID "
				+ "and track.medium_ID = medium.ID and medium.release_ID = release.ID";
	}

	private String getValue(JTextArea textArea, JCheckBox checkBox, String value) {
		if (checkBox == null) {
			if ((textArea == null) || (textArea.getText().length() == 0)) {
				return "";
			}
		} else if ((textArea == null)
				|| ((textArea.getText().length() == 0 && !checkBox.isSelected()))) {
			return "";
		}
		return value;
	}
}
