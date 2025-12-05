package com.emi.backend;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class EmiRequest {

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be a positive number")
    private Double loanAmount;

    @NotNull(message = "Yearly interest rate is required")
    @Positive(message = "Yearly interest rate must be positive")
    @Max(value = 100, message = "Yearly interest rate must be between 0 and 100")
    private Double yearlyInterestRate;

    @NotNull(message = "Loan term in years is required")
    @Positive(message = "Loan term must be positive")
    @Max(value = 30, message = "Loan term must be between 0 and 30 years")
    private Integer loanTermYears;

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getYearlyInterestRate() {
        return yearlyInterestRate;
    }

    public void setYearlyInterestRate(Double yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    public Integer getLoanTermYears() {
        return loanTermYears;
    }

    public void setLoanTermYears(Integer loanTermYears) {
        this.loanTermYears = loanTermYears;
    }
}
