package testingApp.activity.test.panels;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import fr.piroxxi.s2le.server.model.question.Translation;

public class TranslationPanel extends QuestionPanel<Translation> implements
		ActionListener {
	private static final long serialVersionUID = -1472101522462645308L;

	private Translation question;

	private Label questionText;
	private Label errorLabel;
	private JTextField answer;
	private JButton accept;

	public TranslationPanel(boolean chrono) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.questionText = new Label();
		
		answer = new JTextField();
		answer.addActionListener(this);

		errorLabel = new Label();
		
		accept = new JButton("ok");
		accept.addActionListener(this);

		
		this.add(questionText);
		this.add(answer);
		this.add(errorLabel);
		this.add(accept);
	}

	@Override
	public void showQuestion(Translation question) {
		this.question = question;
		questionText.setText("What's the english for '"
				+ question.getFrenchWord().replaceAll("\\(.*?\\)", "")
						.replace("  ", " ") + "'");
		answer.setColumns(question.getEnglishWord().length() + 5);
	}

	private boolean answered = false;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Matcher matcher = Pattern.compile(
				question.getEnglishWord().toLowerCase().replace(")", "|)")
						.replace(" ", " ?")).matcher(
				answer.getText().toLowerCase());

		if (answered) {
			this.delegate.hasAnswered(matcher.find());
		} else {
			if (matcher.find()) {
				errorLabel.setText("bonne reponse!");
			} else {
				errorLabel.setText("Faux! La phrase attendue était "
						+ question.getEnglishWord().toLowerCase().replaceAll("\\(.*?\\)", "")
						+ " !");
			}
			answer.setEditable(false);
			accept.setText("next");
			answered = true;
		}
	}
}
