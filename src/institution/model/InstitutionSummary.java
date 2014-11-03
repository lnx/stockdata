package institution.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class InstitutionSummary {

	String quarter;
	String code;
	String stockName;
	int instNumber;
	int instNumberIncrease;
	double holdingRatio;
	double holdingRatioIncrease;
	double aSharesRatio;
	double aSharesRatioIncrease;

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getInstNumber() {
		return instNumber;
	}

	public void setInstNumber(int instNumber) {
		this.instNumber = instNumber;
	}

	public int getInstNumberIncrease() {
		return instNumberIncrease;
	}

	public void setInstNumberIncrease(int instNumberIncrease) {
		this.instNumberIncrease = instNumberIncrease;
	}

	public double getHoldingRatio() {
		return holdingRatio;
	}

	public void setHoldingRatio(double holdingRatio) {
		this.holdingRatio = holdingRatio;
	}

	public double getHoldingRatioIncrease() {
		return holdingRatioIncrease;
	}

	public void setHoldingRatioIncrease(double holdingRatioIncrease) {
		this.holdingRatioIncrease = holdingRatioIncrease;
	}

	public double getASharesRatio() {
		return aSharesRatio;
	}

	public void setASharesRatio(double aSharesRatio) {
		this.aSharesRatio = aSharesRatio;
	}

	public double getASharesRatioIncrease() {
		return aSharesRatioIncrease;
	}

	public void setASharesRatioIncrease(double aSharesRatioIncrease) {
		this.aSharesRatioIncrease = aSharesRatioIncrease;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
