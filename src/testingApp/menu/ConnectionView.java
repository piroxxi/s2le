package testingApp.menu;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectionView extends JPanel {
	private static final long serialVersionUID = 6908652978305253794L;

	/**
	 * Presenter, permetant à la vue de communiquer avec son activité.
	 */
	public interface Presenter {
		String login(String name, String password);

		void logout();
	}

	private Presenter presenter;

	private JPanel login;
	private TextField name;
	private TextField password;
	private JButton loginButton;

	private JPanel logout;
	private Label userName;
	private JButton logoutButton;

	public ConnectionView() {
		// this.setPreferredSize(new Dimension(200, 25));

		login = new JPanel();
		login.setLayout(new FlowLayout(FlowLayout.LEFT));

		login.add(new Label("name : "));
		name = new TextField();
		name.setColumns(10);
		login.add(name);

		login.add(new Label("password : "));
		password = new TextField();
		password.setColumns(10);
		password.setEchoChar('*');
		login.add(password);

		loginButton = new JButton("connection");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		login.add(loginButton);

		logout = new JPanel();
		userName = new Label();
		logoutButton = new JButton("deconnextion");
		logoutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		logout.add(userName);
		logout.add(logoutButton);
	}

	public void userLoggedIn(String userName) {
		this.removeAll();
		this.userName.setText("L'utilisateur " + userName + " est connecté");
		this.add(logout);
	}

	public void noUserLogged() {
		this.removeAll();
		this.add(login);
	}

	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	private void login() {
		if (presenter != null && !name.getText().isEmpty()
				&& !password.getText().isEmpty()) {
			String result = presenter.login(name.getText(), password.getText());
			if (result != null) {
				JOptionPane.showMessageDialog(null, result, "Erreure",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	private void logout() {
		if (presenter != null) {
			presenter.logout();
		}
	}
}
