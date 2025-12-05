import { Component } from '@angular/core';
import { EmiService, EmiRequest, EmiResponse } from './emi.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'EMI Calculator';

  loanAmount: number | null = null;
  yearlyInterestRate: number | null = null;
  loanTermYears: number | null = null;

  emiResult: number | null = null;
  totalPayment: number | null = null;
  totalInterest: number | null = null;
  totalMonths: number | null = null;
  errorMessage: string | null = null;
  loading = false;

  constructor(private emiService: EmiService) {}

  onCalculate() {
    this.errorMessage = null;
    this.emiResult = null;
    this.totalPayment = null;
    this.totalInterest = null;
    this.totalMonths = null;

    if (!this.isValidInput()) {
      return;
    }

    const request: EmiRequest = {
      loanAmount: this.loanAmount!,
      yearlyInterestRate: this.yearlyInterestRate!,
      loanTermYears: this.loanTermYears!
    };

    this.loading = true;
    this.emiService.calculateEmi(request).subscribe({
      next: (response: EmiResponse) => {
        this.loading = false;
        if (response.message === 'SUCCESS') {
          this.emiResult = response.emiAmount;
          this.totalMonths = this.loanTermYears! * 12;
          this.totalPayment = this.emiResult * this.totalMonths;
          this.totalInterest = this.totalPayment - this.loanAmount!;
        } else {
          this.errorMessage = response.message;
        }
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = err.error?.message || 'Something went wrong while calculating EMI.';
      }
    });
  }

  private isValidInput(): boolean {
    if (this.loanAmount == null || this.loanAmount <= 0) {
      this.errorMessage = 'Loan amount must be a positive number.';
      return false;
    }

    if (this.yearlyInterestRate == null || this.yearlyInterestRate <= 0 || this.yearlyInterestRate > 100) {
      this.errorMessage = 'Yearly interest rate must be a positive number between 0 and 100.';
      return false;
    }

    if (this.loanTermYears == null || this.loanTermYears <= 0 || this.loanTermYears > 30) {
      this.errorMessage = 'Loan term must be a positive number between 0 and 30 years.';
      return false;
    }

    return true;
  }

  onReset(): void {
    this.loanAmount = null;
    this.yearlyInterestRate = null;
    this.loanTermYears = null;
    this.emiResult = null;
    this.totalPayment = null;
    this.totalInterest = null;
    this.totalMonths = null;
    this.errorMessage = null;
    this.loading = false;
  }

  formatCurrency(value: number | null): string {
    if (value === null || value === undefined) {
      return '-';
    }
    const formatted = value.toLocaleString('en-IN', {
      minimumFractionDigits: 2,
      maximumFractionDigits: 2,
    });
    return `₹ ${formatted}`;
  }
}

