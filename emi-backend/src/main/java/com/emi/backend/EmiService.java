package com.emi.backend;

import org.springframework.stereotype.Service;

@Service
public class EmiService {

    public double calculateEmi(double loanAmount, double yearlyInterestRate, int loanTermYears) {
        int months = loanTermYears * 12;
        double monthlyRate = (yearlyInterestRate / 12.0) / 100.0;

        if (months <= 0) {
            throw new IllegalArgumentException("Loan term must be greater than zero");
        }

        if (monthlyRate == 0.0) {
            return loanAmount / months;
        }

        double factor = Math.pow(1 + monthlyRate, months);
        return loanAmount * monthlyRate * factor / (factor - 1);
    }
}
