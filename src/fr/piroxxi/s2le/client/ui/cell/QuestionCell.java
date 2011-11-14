package fr.piroxxi.s2le.client.ui.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import fr.piroxxi.s2le.model.question.MultiChoicesQuestion;
import fr.piroxxi.s2le.model.question.Question;
import fr.piroxxi.s2le.model.question.SimpleQuestion;
import fr.piroxxi.s2le.model.question.Translation;
import fr.piroxxi.s2le.model.question.YesNoQuestion;

public class QuestionCell extends AbstractCell<Question> {

	public QuestionCell() {
		super("click");
	}

	@Override
	public void render(Context context, Question value, SafeHtmlBuilder sb) {
		if (value == null) {
			return;
		}

		if (value instanceof MultiChoicesQuestion) {
			sb.appendHtmlConstant("<span class=\"typeQuestion\">Question à choix multiples</span>");
			sb.appendHtmlConstant("<span>"
					+ ((MultiChoicesQuestion) value).getQuestion() + "</span>");
		}
		if (value instanceof Translation) {
			sb.appendHtmlConstant("<span class=\"typeQuestion\">Traduction</span>");
			sb.appendHtmlConstant("<span class=\"translation_title\">anglais</span><span>"
					+ ((Translation) value).getEnglishWord()
					+ " </span><br/><span class=\"translation_title translation_decalage\">francais</span><span>"
					+ ((Translation) value).getFrenchWord() + "</span>");
		}
		if (value instanceof SimpleQuestion) {
			sb.appendHtmlConstant("<span class=\"typeQuestion\">Question Simple</span>");
			sb.appendHtmlConstant("<span>"
					+ ((SimpleQuestion) value).getQuestion() + "</span>");
		}
		if (value instanceof YesNoQuestion) {
			sb.appendHtmlConstant("<span class=\"typeQuestion\">Question Oui/Non</span>");
			sb.appendHtmlConstant("<span>"
					+ ((YesNoQuestion) value).getQuestion() + "</span>");
		}
	}
}