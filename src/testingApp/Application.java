package testingApp;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import testingApp.menu.ConnectionActivity;
import testingApp.ui.eventBus.EventBus;
import testingApp.ui.eventBus.EventListener;
import testingApp.ui.placeControler.PlaceControler;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Application extends JFrame {
	private static final long serialVersionUID = -6384107007332354486L;

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private final PlaceControler placeControler;

	@Inject
	public Application(EventBus eventBus,
			ConnectionActivity connectionActivity, PlaceControler placeControler) {
		this.placeControler = placeControler;
		this.setVisible(true);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		eventBus.registerEventListener(RefreshingEvent.class,
				new EventListener<RefreshingEvent>() {

					@Override
					public void onEvent(RefreshingEvent event) {
						System.out.println("demande de rafraichissement");
						pack();
					}
				});

		this.setLayout(new BorderLayout());

		// menu connection
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		JPanel connectionPanel = new JPanel();
		this.add(north, BorderLayout.NORTH);
		north.add(connectionPanel, BorderLayout.EAST);
		north.add(new JPanel(), BorderLayout.CENTER);
		connectionActivity.startActivity(connectionPanel);

		// center
		JPanel center = new JPanel();
		this.add(center, BorderLayout.CENTER);
		this.placeControler.initialize(center);
		this.placeControler.goTo(null);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.pack();
	}

}
