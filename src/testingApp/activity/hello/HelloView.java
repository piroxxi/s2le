package testingApp.activity.hello;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HelloView extends JPanel {
	private static final long serialVersionUID = 3185640455004133869L;
	private Delegate delegate;

	public interface Delegate {
		void startTest();
	}

	public HelloView() {
		this.add(new Label("bonjour et bienvenu !"));

		JButton b = new JButton("commencer un nouveau test");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startNewTest();
			}
		});
		this.add(b);
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	private void startNewTest() {
		if (this.delegate != null) {
			delegate.startTest();
		}
	}
}
