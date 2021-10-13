package home.contracts.processor.model;

public enum BillingFrequency {

	H10(2, 6), H20(2, 6), M10(12, 1), M20(12, 1), Q10(4, 3), Q20(4, 3), Y10(1, 12), Y20(1, 12);

	private int frequency;

	private int months;

	BillingFrequency(int frequency, int months) {
		this.frequency = frequency;
		this.months = months;
	}

	public int getFrequency() {
		return frequency;
	}

	public int getMonths() {
		return months;
	}

}
