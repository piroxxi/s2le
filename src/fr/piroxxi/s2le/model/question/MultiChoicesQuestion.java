package fr.piroxxi.s2le.model.question;

import fr.piroxxi.s2le.model.Category;
import fr.piroxxi.s2le.model.Difficulty;

public class MultiChoicesQuestion extends Question {
	private String question;
	private String[] answers;
	private int rightAnswer;

	public MultiChoicesQuestion() {
		super();
	}

	public MultiChoicesQuestion(String serializedQuestion) {
		// return "MultiChoicesQuestion||" + question + "||"
		// + Joiner.on(";;").join(answers) + "||" + rightAnswer;
		String[] split = serializedQuestion.split("\\|\\|", -1);
		this.question = split[1];
		this.answers = split[2].split(";;", -1);
		this.rightAnswer = Integer.parseInt(split[3]);
	}

	public MultiChoicesQuestion(String creator, Category category,
			Difficulty difficulty, String question, String[] answers,
			int rightAnswer) {
		super(creator, category, difficulty);
		this.question = question;
		this.answers = answers;
		this.rightAnswer = rightAnswer;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answers
	 */
	public String[] getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	/**
	 * @return the rightAnswer
	 */
	public int getRightAnswer() {
		return rightAnswer;
	}

	/**
	 * @param rightAnswer
	 *            the rightAnswer to set
	 */
	public void setRightAnswer(int rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	@Override
	public String serialize() {
		String seri = "MultiChoicesQuestion||" + question + "||";
		for (int i = 0; i < answers.length; i++) {

			seri += answers[i];
			if (i < answers.length - 1) {
				seri += ";;";
			}
		}
		// TODO + Joiner.on(";;").join(answers) + -- marche pas car guava pas
		// trouvé ! :/
		return seri + "||" + rightAnswer;
	}

	/**
	 * To string d'une question a choix multiples.
	 * 
	 * TODO toutes les toString pouraient être un
	 * this.class.getCanonicalName()+" "+serialize();
	 */
	@Override
	public String toString() {
		return "MultyChoicesQuestion : " + question + " => " + answers
				+ " (good : " + answers[rightAnswer] + ")";
	}
}
