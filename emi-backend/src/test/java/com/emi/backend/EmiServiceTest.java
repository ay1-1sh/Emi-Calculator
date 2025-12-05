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
        assertTrue(emi > 8000 && emi < 9000, "EMI should be in reasonable range for given inputs");

        // Expected EMI for ₹10,00,000 at 8% for 20 years is approximately 8364.48
        // Using delta of 1.0 to account for floating point precision
        assertEquals(8364.48, emi, 1.0);
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
