package at.ac.tuwien.big.we14.lab2.api;

public enum Answer {
	
	CORRECT("correct", "Richtig"),
	INCORRECT("incorrect", "Falsch"),
	UNKNOWN("unknown", "Unbekannt");

	private final String classType;
	private final String text;

	Answer(String classType, String text) {
		this.classType = classType;
		this.text = text;
	}

	public String text() {
		return text;
	}
	
	public String classType() {
		return classType;
	}
}
