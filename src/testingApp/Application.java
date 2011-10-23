package testingApp;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import testingApp.menu.MenuActivity;

import com.google.inject.Inject;

import fr.piroxxi.s2le.storage.api.Storage;

public class Application extends JFrame {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	@Inject
	private Storage storage;
	@Inject
	private MenuActivity menu;
	
	public Application() {

		this.setVisible(true);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		this.setLayout(new BorderLayout());
		JPanel menuPanel = new JPanel();
		this.add(menuPanel, BorderLayout.WEST);
		menu.startActivity(menuPanel);
		
		this.pack();
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}
}
