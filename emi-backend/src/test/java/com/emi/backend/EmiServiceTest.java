package com.emi.backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmiServiceTest {

    private final EmiService emiService = new EmiService();

    @Test
    void calculateEmi_withValidInputs_returnsPositiveEmi() {
        double loanAmount = 1_000_000; // ₹10,00,000
        double yearlyInterestRate = 8; // 8% per annum
        int loanTermYears = 20;        // 20 years

        double emi = emiService.calculateEmi(loanAmount, yearlyInterestRate, loanTermYears);

        assertTrue(emi > 0, "EMI should be positive for valid inputs");

        // The exact EMI for these values is about 8364.48
        assertEquals(8364.48, Math.round(emi * 100.0) / 100.0, 0.01);
    }

    @Test
    void calculateEmi_withZeroInterest_behavesLikeSimpleDivision() {
        double loanAmount = 120_000;   // ₹1,20,000
        double yearlyInterestRate = 0; // 0% interest
        int loanTermYears = 1;         // 1 year = 12 months

        double emi = emiService.calculateEmi(loanAmount, yearlyInterestRate, loanTermYears);

        // With 0% interest, EMI should be principal / months
        assertEquals(10_000, emi, 0.0001);
    }
}
