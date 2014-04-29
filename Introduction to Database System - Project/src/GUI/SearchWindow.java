package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Connection.Client;

/**
 * TODO: Comment this class
 * 
 * @author Gregory Maitre & Patrick Andrade
 * 
 */
public class SearchWindow extends JFrame {

	private JPanel contentPane;
	private JTextArea artistNameTextArea;
	private JTextArea genreTextArea;
	private JTextArea areaTextArea;
	private JTextArea recordingTextArea;
	private JTextArea releaseTextArea;
	private JTextArea mediumTextArea;
	private Client currentClient;

	/**
	 * Create the frame.
	 */
	public SearchWindow() {
		setType(Type.UTILITY);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{76, 0, 0};
		gbl_contentPane.rowHeights = new int[]{16, 16, 16, 16, 16, 16, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
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
	
	public void send() {
		
		currentClient = null;
		setVisible(false);
	}

	public void beVisible(Client client) {
		currentClient = client;
		setVisible(true);
	}
}
