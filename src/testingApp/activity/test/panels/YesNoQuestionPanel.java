package testingApp.activity.test.panels;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import fr.piroxxi.s2le.model.question.YesNoQuestion;

public class YesNoQuestionPanel extends QuestionPanel<YesNoQuestion> implements ActionListener {
	private static final long serialVersionUID = -2344240871400232325L;

	private Label questionText;
	private JButton yes;
	private JButton no;

	private YesNoQuestion question;
	private final boolean chronometre;

	public YesNoQuestionPanel(boolean chronometre) {
		// TODO gerer le chrono
		this.chronometre = chronometre;

		questionText = new Label();
		yes = new JButton("yes");
		yes.addActionListener(this);
		no = new JButton("no");
		no.addActionListener(this);
		
		this.add(questionText);
		this.add(yes);
		this.add(no);
	}

	@Override
	public void showQuestion(YesNoQuestion question) {
		this.question = question;
		this.questionText.setText(question.getQuestion());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(yes)){
			delegate.hasAnswered(this.question.isAnswer());
		}else{
			delegate.hasAnswered(!this.question.isAnswer());
		}
	}

}
