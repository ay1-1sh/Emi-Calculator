<<<<<<< HEAD
<<<<<<< HEAD
# Emi-Calculator
=======
# EMI Calculator (Spring Boot backend, Angular frontend)

Lean full-stack EMI calculator with a **backend-first focus**: Spring Boot provides validation and the EMI computation API; Angular offers a thin UI to call it.

## Stack (backend-focused)
- **Backend:** Java 17, Spring Boot (Web, Validation), Maven
- **Frontend:** Angular (FormsModule, HttpClient), kept minimal

## What the backend does
- Exposes **POST** `/api/emi/calculate`
- Validates: loan > 0; yearly interest 0–100; term 0–30 years
- Calculates EMI, returns `{ emiAmount, message }`
- Handles validation errors and illegal arguments with clear messages
- Unit tests for EMI logic (normal and zero-interest cases)
- CORS enabled for `http://localhost:4200`

## Run backend
```bash
cd emi-backend
mvn clean install
mvn spring-boot:run   # http://localhost:8080
```

## API (contract)
- **POST** `http://localhost:8080/api/emi/calculate`
- Body:
```json
{ "loanAmount": 1000000, "yearlyInterestRate": 8, "loanTermYears": 20 }
```
- Success:
```json
{ "emiAmount": 8364.48, "message": "SUCCESS" }
```
- Validation error example:
```json
{ "emiAmount": 0.0, "message": "Yearly interest rate must be between 0 and 100" }
```

## Run frontend (optional UI)
```bash
cd emi-frontend
npm install
ng serve   # http://localhost:4200
```
- UI features: simple form, ₹ formatting with thousand separators, totals (months, total payment, total interest), loading spinner, reset.

## Tests
```bash
cd emi-backend
mvn test
```

## Notes
- Java 17+ and Maven required.
- Use Node.js LTS (18.x or 20.x) for Angular CLI if running the UI.
- CORS configured for `http://localhost:4200`.

>>>>>>> 9ea7468 (Initial commit: EMI calculator (Spring Boot + Angular))
=======
# Emi-Calculator
>>>>>>> ee6c60fbb75839bbd46c2d9f45820d98246efedd
