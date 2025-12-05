# EMI Calculator - Angular Frontend

## Prerequisites
- Node.js (LTS version recommended - 18.x or 20.x)
- Angular CLI installed globally: `npm install -g @angular/cli`
- Backend running on `http://localhost:8080`

## Installation

1. Install dependencies:
```bash
npm install
```

## Running the Application

1. Start the Angular development server:
```bash
ng serve
```
or
```bash
npm start
```

2. Open your browser and navigate to:
```
http://localhost:4200
```

## Project Structure

- `src/app/app.component.ts` - Main component with form logic and validation
- `src/app/app.component.html` - UI template with form inputs
- `src/app/emi.service.ts` - Service for API communication with backend
- `src/app/app.module.ts` - Angular module that registers components and imports

## Features

- Form with three inputs: Loan Amount (₹), Yearly Interest Rate (%), Loan Term (years)
- Client-side validation matching backend requirements
- Calls Spring Boot backend API at `/api/emi/calculate`
- Displays calculated EMI amount in ₹ with thousand separators
- Shows total payment and total interest over the full tenure
- Loading state on Calculate button (spinner + disabled)
- Reset button clears the form and results

## Validation Rules (frontend and backend)
- Loan amount: positive number
- Yearly interest rate: positive number between 0 and 100
- Loan term (years): positive number between 0 and 30

## Sample Payloads (to test backend directly)
```json
{
  "loanAmount": 1000000,
  "yearlyInterestRate": 8,
  "loanTermYears": 20
}
```
```json
{
  "loanAmount": 500000,
  "yearlyInterestRate": 7.5,
  "loanTermYears": 15
}
```

## Expected Responses
- Success:
```json
{
  "emiAmount": 8364.48,
  "message": "SUCCESS"
}
```
- Validation error (example):
```json
{
  "emiAmount": 0.0,
  "message": "Yearly interest rate must be between 0 and 100"
}
```

## Troubleshooting
- If frontend cannot reach backend, ensure Spring Boot is running on `http://localhost:8080`.
- CORS is enabled on the backend for `http://localhost:4200`.
- If Angular CLI is missing: `npm install -g @angular/cli`.

## For Detailed Explanation
See `ANGULAR_EXPLANATION.md` for a full walkthrough of every file and how Angular bootstraps the app.

