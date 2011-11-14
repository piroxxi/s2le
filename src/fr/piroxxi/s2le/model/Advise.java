package fr.piroxxi.s2le.model;

public class Advise extends BasicEntity {
	private String shortAdvise;
	private String completeAdvise;

	public Advise() {

	}

	public Advise(String shortAdvise, String completeAdvise) {
		this.shortAdvise = shortAdvise;
		this.completeAdvise = completeAdvise;
	}

	/**
	 * @return the shortAdvise
	 */
	public String getShortAdvise() {
		return shortAdvise;
	}

	/**
	 * @param shortAdvise the shortAdvise to set
	 */
	public void setShortAdvise(String shortAdvise) {
		this.shortAdvise = shortAdvise;
	}

	/**
	 * @return the completeAdvise
	 */
	public String getCompleteAdvise() {
		return completeAdvise;
	}

	/**
	 * @param completeAdvise the completeAdvise to set
	 */
	public void setCompleteAdvise(String completeAdvise) {
		this.completeAdvise = completeAdvise;
	}

}
