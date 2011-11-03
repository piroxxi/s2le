package fr.piroxxi.s2le.shared.model;

public enum Difficulty {
	verySimple("Very Simple"),
	simple("Simple"),
	medium("Medium"),
	hard("Hard"),
	veryHard("VeryHard");

	private final String libelle;

	private Difficulty(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}
}
