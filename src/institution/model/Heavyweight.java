package institution.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Heavyweight {

	String quarter;
	String code;
	String stockName;
	int instNumber;
	double holdingNumber;
	double aSharesRatio;
	double holdingIncrease;
	double holdingRatio;
	int preInstNumber;

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

	public double getHoldingNumber() {
		return holdingNumber;
	}

	public void setHoldingNumber(double holdingNumber) {
		this.holdingNumber = holdingNumber;
	}

	public double getASharesRatio() {
		return aSharesRatio;
	}

	public void setASharesRatio(double aSharesRatio) {
		this.aSharesRatio = aSharesRatio;
	}

	public double getHoldingIncrease() {
		return holdingIncrease;
	}

	public void setHoldingIncrease(double holdingIncrease) {
		this.holdingIncrease = holdingIncrease;
	}

	public double getHoldingRatio() {
		return holdingRatio;
	}

	public void setHoldingRatio(double holdingRatio) {
		this.holdingRatio = holdingRatio;
	}

	public int getPreInstNumber() {
		return preInstNumber;
	}

	public void setPreInstNumber(int preInstNumber) {
		this.preInstNumber = preInstNumber;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
