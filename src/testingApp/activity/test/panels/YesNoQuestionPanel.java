package testingApp.activity.test.panels;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.piroxxi.s2le.model.question.YesNoQuestion;

public class YesNoQuestionPanel extends QuestionPanel<YesNoQuestion> implements
		ActionListener {
	private static final long serialVersionUID = -2344240871400232325L;

	private Label questionText;
	private Label errorLabel;
	private JButton yes;
	private JButton no;
	private JButton next;

	private YesNoQuestion question;

	public YesNoQuestionPanel(boolean chronometre) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		questionText = new Label();

		yes = new JButton("yes");
		yes.addActionListener(this);

		next = new JButton("next");
		next.addActionListener(this);

		no = new JButton("no");
		no.addActionListener(this);

		errorLabel = new Label();

		JPanel p = new JPanel();

		this.add(questionText);
		this.add(errorLabel);
		p.add(yes);
		p.add(no);
		p.add(next);
		this.add(p);
		next.setVisible(false);
	}

	@Override
	public void showQuestion(YesNoQuestion question) {
		this.question = question;
		this.questionText.setText(question.getQuestion());
	}

	private boolean answered = false;
	private boolean correct;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (answered) {
			this.delegate.hasAnswered(correct);
		} else {
			this.correct = (e.getSource().equals(yes) && this.question
					.isAnswer())
					|| (e.getSource().equals(no) && !this.question.isAnswer());
			if (correct) {
				errorLabel.setText("Bonne reponse!");
			} else {
				errorLabel.setText("Faux!");
			}
			no.setVisible(false);
			yes.setVisible(false);
			next.setVisible(true);
			answered = true;
		}
	}
}
