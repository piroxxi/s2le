package fr.piroxxi.s2le.shared.model;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

import fr.piroxxi.s2le.server.model.Test;
import fr.piroxxi.s2le.server.model.question.Question;

@ProxyFor(Test.class)
public interface TestProxy extends EntityProxy {
	Question getNextQuestion();

	void addGoodAnswer();

	int getGoodAnswerScore();

	int getSize();

	boolean chrono();
}
