package testingApp.activity.test.panels;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;

import fr.piroxxi.s2le.model.question.Translation;

public class TranslationPanel extends QuestionPanel<Translation> implements
		ActionListener {
	private static final long serialVersionUID = -1472101522462645308L;

	private Translation question;

	private Label questionText;
	private JTextField answer;
	private JButton accept;

	public TranslationPanel(boolean chrono) {
		this.questionText = new Label();
		answer = new JTextField();
		answer.addActionListener(this);
		accept = new JButton("ok");
		accept.addActionListener(this);

		this.add(questionText);
		this.add(answer);
		this.add(accept);
	}

	@Override
	public void showQuestion(Translation question) {
		this.question = question;
		questionText.setText("What's the english for '"
				+ question.getFrenchWord().replaceAll("\\(.*?\\)", "").replace("  "," ") + "'");
		answer.setColumns(question.getEnglishWord().length() + 5);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("\nOn a eut '"+answer.getText()+"'");
		System.out.println("\nOn vaoulait '"+question.getEnglishWord()+"'");

		Pattern pattern =  Pattern.compile(question.getEnglishWord());
		
		this.delegate.hasAnswered(true);
	}

}
