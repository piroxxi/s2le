package testingApp.activity.test.panels;

import java.util.regex.Pattern;

public class qsdgqsdg {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String phraseVoulue = "(un) pot de beurre";
		String phrase1 = "un pot de beurre";
		String phrase2 = "pot de beurre";
		
		Pattern pattern =  Pattern.compile(phraseVoulue.replace(")","|)"));
		System.out.println("phrase 1 :"+pattern.matcher(phrase1).find());
		System.out.println("phrase 2 :"+pattern.matcher(phrase2).find());
	}

}
