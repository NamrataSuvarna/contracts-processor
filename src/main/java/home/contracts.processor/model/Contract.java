package home.contracts.processor.model;

import java.time.Period;

import home.contracts.processor.util.Utility;

public class Contract {

	private double actualPriceShare;

	private BillingFrequency billingFrequency;

	private String businessUnit;

	private Period periodBetweenDates;

	private double priceShareForDuration;

	public Contract(String billingFrequency, String businessUnit, String endDate, String priceShare,
			String cutOffDate) {
		this.billingFrequency = BillingFrequency.valueOf(billingFrequency);
		this.businessUnit = businessUnit;
		this.periodBetweenDates = Utility.getPeriodBetweenDates(endDate, cutOffDate);
		this.priceShareForDuration = Double.parseDouble(priceShare) / this.billingFrequency.getFrequency();
		this.actualPriceShare = billingFrequency.contains(Utility.ADVANCE_PAYMENT_INDICATOR)
				? getActualPriceShareValue(true)
				: getActualPriceShareValue(false);
	}

	public double getActualPriceShare() {
		return actualPriceShare;
	}

	private double getActualPriceShareValue(boolean isAdvance) {
		int months = periodBetweenDates.getMonths();
		int days = periodBetweenDates.getDays();
		if (months == 0 && days == 0) {
			return 0;
		}
		double actualPriceShareValue = 0;
		while (months >= billingFrequency.getMonths()) {
			actualPriceShareValue = actualPriceShareValue + priceShareForDuration;
			months = months - billingFrequency.getMonths();
		}
		return isAdvance ? getAdditionalPriceShareValue(months, days, actualPriceShareValue) : actualPriceShareValue;
	}

	private double getAdditionalPriceShareValue(int months, int days, double actualPriceShareValue) {
		return (days > 0 || months > 0) ? actualPriceShareValue + priceShareForDuration : actualPriceShareValue;
	}

	public BillingFrequency getBillingFrequency() {
		return billingFrequency;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public Period getPeriodBetweenDates() {
		return periodBetweenDates;
	}

	public double getPriceShareForDuration() {
		return priceShareForDuration;
	}

	public void setActualPriceShare(double actualPriceShare) {
		this.actualPriceShare = actualPriceShare;
	}

	public void setBillingFrequency(BillingFrequency billingFrequency) {
		this.billingFrequency = billingFrequency;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public void setPeriodBetweenDates(Period periodBetweenDates) {
		this.periodBetweenDates = periodBetweenDates;
	}

	public void setPriceShareForDuration(double priceShareForDuration) {
		this.priceShareForDuration = priceShareForDuration;
	}

}
