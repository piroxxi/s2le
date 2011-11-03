package testingApp.activity.test.panels;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.piroxxi.s2le.shared.model.question.MultyChoicesQuestion;

public class MultyChoicesQuestionPanel extends
		QuestionPanel<MultyChoicesQuestion> implements ActionListener {
	private static final long serialVersionUID = -2344240871400232325L;

	private Label questionText;
	private Label errorLabel;
	private JButton[] propositions;
	private JButton next;
	private JPanel p;

	private MultyChoicesQuestion question;

	public MultyChoicesQuestionPanel(boolean chronometre) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		questionText = new Label();

		next = new JButton("next");
		next.addActionListener(this);

		errorLabel = new Label();

		p = new JPanel();

		this.add(questionText);
		this.add(errorLabel);
		this.add(p);
		this.add(next);
		next.setVisible(false);
	}

	@Override
	public void showQuestion(MultyChoicesQuestion question) {
		this.question = question;
		this.questionText.setText(question.getQuestion());
		p.removeAll();
		this.answered = false;
		
		propositions = new JButton[question.getAnswers().length];
		for (int i = 0; i < propositions.length; i++) {
			propositions[i] = new JButton(question.getAnswers()[i]);
			propositions[i].addActionListener(this);
			p.add(propositions[i]);
		}
	}

	private boolean answered = false;
	private boolean correct;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (answered) {
			this.delegate.hasAnswered(correct);
		} else {
			this.correct = e.getSource().equals(
					propositions[question.getRightAnswer()]);
			if (correct) {
				errorLabel.setText("Bonne reponse!");
			} else {
				errorLabel.setText("Faux!, la bonne reponse était '"
						+ question.getAnswers()[question.getRightAnswer()]+"'");
			}
			p.setVisible(false);
			next.setVisible(true);
			answered = true;
		}
	}
}
