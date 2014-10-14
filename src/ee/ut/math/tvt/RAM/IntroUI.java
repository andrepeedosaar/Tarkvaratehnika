package ee.ut.math.tvt.RAM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.image.*;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.*;

public class IntroUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private BufferedImage image;
	private JLabel teamName, teamLeader, leaderMail, teamMembers, softVer, logo;

	public IntroUI() {

		// Initialize components
		try {
			initTextFields();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Set layout & place components
		setLayout(new GridBagLayout());
		GridBagConstraints gc = getConstraints();

		// Logo
		add(logo, gc);

		// Team name
		gc.gridy = 1;
		add(teamName, gc);

		// Team leader
		gc.gridy = 2;
		add(teamLeader, gc);

		// Team leader mail
		gc.gridy = 3;
		add(leaderMail, gc);

		// Team members
		gc.gridy = 4;
		add(teamMembers, gc);

		// Software version
		gc.gridy = 5;
		add(softVer, gc);

		// Adjust window

		int width = 700;
		int height = 500;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);
		getContentPane().setBackground(Color.white);

	}

	private GridBagConstraints getConstraints() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.VERTICAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 1.0;

		return gc;
	}

	private void initTextFields() throws IOException {
		// Get current version
		Properties prop = new Properties();
		prop.load(getClass().getResourceAsStream("/version.properties"));

		// Get logo
		image = ImageIO.read(getClass().getResource("/images/ram_logo.png"));
		logo = new JLabel(new ImageIcon(image));

		teamName = new JLabel("Team RAM");
		teamLeader = new JLabel("Team leader Martin Kütt");
		leaderMail = new JLabel("nool@hot.ee");
		teamMembers = new JLabel("Ragnar Vent, Andre Peedosaar, Martin K\u00FCtt");
		softVer = new JLabel("Current version: " + prop.getProperty("build.number"));

		Font f = new Font("Serif", Font.BOLD, 19);
		teamName.setFont(f);
		teamLeader.setFont(f);
		leaderMail.setFont(f);
		teamMembers.setFont(f);
		softVer.setFont(f);
	}
}
