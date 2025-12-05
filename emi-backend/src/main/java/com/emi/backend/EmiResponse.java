package com.emi.backend;

public class EmiResponse {

    private double emiAmount;
    private String message;

    public EmiResponse() {
    }

    public EmiResponse(double emiAmount, String message) {
        this.emiAmount = emiAmount;
        this.message = message;
    }

    public double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
