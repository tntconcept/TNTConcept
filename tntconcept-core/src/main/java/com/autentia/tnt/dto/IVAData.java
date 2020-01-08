package com.autentia.tnt.dto;

import java.math.BigDecimal;

public class IVAData {

    private boolean existsOnBill;
    private BigDecimal ivaPercentage;
    private BigDecimal basePrice;
    private BigDecimal ivaAmount;
    private String reason;
    private BigDecimal REAGYPCompensationPercentage;
    private BigDecimal REAGYPCompensationAmount;
    private String subject;
    private boolean serviceProvision;
    private BigDecimal amountArt714;
    private BigDecimal TAIAmount;

    public IVAData(BigDecimal ivaPercentage) {
        this.existsOnBill = false;
        this.ivaPercentage = ivaPercentage;
        this.basePrice = new BigDecimal(0);
        this.ivaAmount = new BigDecimal(0);
    }

    public boolean isExistsOnBill() {
        return existsOnBill;
    }

    public void setExistsOnBill(boolean existsOnBill) {
        this.existsOnBill = existsOnBill;
    }

    public BigDecimal getIvaPercentage() {
        return ivaPercentage;
    }

    public void setIvaPercentage(BigDecimal ivaPercentage) {
        this.ivaPercentage = ivaPercentage;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getIvaAmount() {
        return ivaAmount;
    }

    public void setIvaAmount(BigDecimal ivaAmount) {
        this.ivaAmount = ivaAmount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getREAGYPCompensationPercentage() {
        return REAGYPCompensationPercentage;
    }

    public void setREAGYPCompensationPercentage(BigDecimal REAGYPCompensationPercentage) {
        this.REAGYPCompensationPercentage = REAGYPCompensationPercentage;
    }

    public BigDecimal getREAGYPCompensationAmount() {
        return REAGYPCompensationAmount;
    }

    public void setREAGYPCompensationAmount(BigDecimal REAGYPCompensationAmount) {
        this.REAGYPCompensationAmount = REAGYPCompensationAmount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isServiceProvision() {
        return serviceProvision;
    }

    public void setServiceProvision(boolean serviceProvision) {
        this.serviceProvision = serviceProvision;
    }

    public BigDecimal getAmountArt714() {
        return amountArt714;
    }

    public void setAmountArt714(BigDecimal amountArt714) {
        this.amountArt714 = amountArt714;
    }

    public BigDecimal getTAIAmount() {
        return TAIAmount;
    }

    public void setTAIAmount(BigDecimal TAIAmount) {
        this.TAIAmount = TAIAmount;
    }
}
