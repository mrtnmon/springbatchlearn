package ebe.customer.migration.opportunitylines;

import java.math.BigInteger;

//import batchpackage.CreditCheckCollectionType;
import be.ebts.schemas._2014._05.qe.SalesStageEnum;

public class OpportunityRequestDTO {
	
	
	private String primaryAccountId;
	private SalesStageEnum salesStage;
	private String name;
	private boolean consortiumFlag;
	private String elecContractStartDate;
	private String currentElecContractEndDate;
	private double elecYearlyVolume;
	private int elecEanCount;
	private String gasContractStartDate;
	private String currentGasContractEndDate;
	private double gasYearlyVolume;
	private int gasEanCount;
	private String injectionContractStartDate;
	private double injectionYearlyVolume;
	private int injectionEanCount;
	private String assignedResponsible;
	private BigInteger elecDuration;
	private BigInteger gasDuration;
	private BigInteger injectionDuration;
	private BigInteger injectDuration;
	
	
	public double getInjectionYearlyVolume() {
		return injectionYearlyVolume;
	}
	public void setInjectionYearlyVolume(double injectionYearlyVolume) {
		this.injectionYearlyVolume = injectionYearlyVolume;
	}
	public int getInjectionEanCount() {
		return injectionEanCount;
	}
	public void setInjectionEanCount(int injectionEanCount) {
		this.injectionEanCount = injectionEanCount;
	}
	public String getAssignedResponsible() {
		return assignedResponsible;
	}
	public void setAssignedResponsible(String assignedResponsible) {
		this.assignedResponsible = assignedResponsible;
	}
	public BigInteger getElecDuration() {
		return elecDuration;
	}
	public void setElecDuration(BigInteger elecDuration) {
		this.elecDuration = elecDuration;
	}
	public BigInteger getGasDuration() {
		return gasDuration;
	}
	public void setGasDuration(BigInteger gasDuration) {
		this.gasDuration = gasDuration;
	}
	public BigInteger getInjectionDuration() {
		return injectionDuration;
	}
	public void setInjectionDuration(BigInteger injectionDuration) {
		this.injectionDuration = injectionDuration;
	}
	public BigInteger getInjectDuration() {
		return injectDuration;
	}
	public void setInjectDuration(BigInteger injectDuration) {
		this.injectDuration = injectDuration;
	}
//	private CreditCheckCollectionType creditChecks;
	
	
	public double getElecYearlyVolume() {
		return elecYearlyVolume;
	}
	public void setElecYearlyVolume(double elecYearlyVolume) {
		this.elecYearlyVolume = elecYearlyVolume;
	}
	public String getPrimaryAccountId() {
		return primaryAccountId;
	}
	public String getElecContractStartDate () {
		return elecContractStartDate;
	}
	public void setElecContractStartDate(String elecContractStartDate) {
		this.elecContractStartDate = elecContractStartDate;
	}
	public void setPrimaryAccountId(String primaryAccountId) {
		this.primaryAccountId = primaryAccountId;
	}
	public SalesStageEnum getSalesStage() {
		return salesStage;
	}
	public void setSalesStage(SalesStageEnum salesStage) {
		this.salesStage = salesStage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isConsortiumFlag() {
		return consortiumFlag;
	}
	public void setConsortiumFlag(boolean consortiumFlag) {
		this.consortiumFlag = consortiumFlag;
	}

	public String getCurrentElecContractEndDate() {
		return currentElecContractEndDate;
	}
	public void setCurrentElecContractEndDate(String currentElecContractEndDate) {
		this.currentElecContractEndDate = currentElecContractEndDate;
	}
//	public CreditCheckCollectionType getCreditChecks() {
//		return creditChecks;
//	}
//	public void setCreditChecks(CreditCheckCollectionType creditChecks) {
//		this.creditChecks = creditChecks;
//	}
	public int getElecEanCount() {
		return elecEanCount;
	}
	public void setElecEanCount(int elecEanCount) {
		this.elecEanCount = elecEanCount;
	}
	public String getGasContractStartDate() {
		return gasContractStartDate;
	}
	public void setGasContractStartDate(String gasContractStartDate) {
		this.gasContractStartDate = gasContractStartDate;
	}
	public String getCurrentGasContractEndDate() {
		return currentGasContractEndDate;
	}
	public void setCurrentGasContractEndDate(String currentGasContractEndDate) {
		this.currentGasContractEndDate = currentGasContractEndDate;
	}
	public double getGasYearlyVolume() {
		return gasYearlyVolume;
	}
	public void setGasYearlyVolume(double gasYearlyVolume) {
		this.gasYearlyVolume = gasYearlyVolume;
	}
	public int getGasEanCount() {
		return gasEanCount;
	}
	public void setGasEanCount(int gasEanCount) {
		this.gasEanCount = gasEanCount;
	}
	public String getInjectionContractStartDate() {
		return injectionContractStartDate;
	}
	public void setInjectionContractStartDate(String injectionContractStartDate) {
		this.injectionContractStartDate = injectionContractStartDate;
	}
}
