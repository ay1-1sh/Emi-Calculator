# EMI Calculator

A full-stack EMI (Equated Monthly Installment) calculator application built with **Spring Boot backend** and **Angular frontend**. This project demonstrates a clean separation of concerns with a robust REST API backend and a modern, user-friendly frontend interface.

## 📋 Table of Contents

- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
  - [Backend Setup](#backend-setup)
  - [Frontend Setup](#frontend-setup)
- [API Documentation](#-api-documentation)
- [Validation Rules](#-validation-rules)
- [Features](#-features)
- [Testing](#-testing)
- [Troubleshooting](#-troubleshooting)

## 🏗️ Architecture

### Backend
- **Framework:** Spring Boot 3.3.4
- **Language:** Java 17
- **Build Tool:** Maven
- **Key Dependencies:**
  - Spring Web (REST API)
  - Spring Boot Validation (Input validation)
  - JUnit 5 (Unit testing)

### Frontend
- **Framework:** Angular 17
- **Language:** TypeScript
- **Key Features:**
  - FormsModule (Two-way data binding)
  - HttpClientModule (API communication)
  - Reactive forms with validation

## 📦 Prerequisites

Before running this project, ensure you have the following installed:

### Backend Requirements
- **Java:** JDK 17 or higher
- **Maven:** 3.6 or higher
- **IDE:** Spring Tool Suite (STS) or IntelliJ IDEA (optional)

### Frontend Requirements
- **Node.js:** LTS version (18.x or 20.x recommended)
- **npm:** Comes with Node.js
- **Angular CLI:** Install globally with `npm install -g @angular/cli`

### Verify Installations

```bash
# Check Java version
java -version

# Check Maven version
mvn -v

# Check Node.js version
node -v

# Check npm version
npm -v

# Check Angular CLI version
ng version
```

## 📁 Project Structure

```
Housing Assesment/
├── emi-backend/                    # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/emi/backend/
│   │   │   │       ├── EmiApplication.java      # Main Spring Boot class
│   │   │   │       ├── EmiController.java       # REST controller
│   │   │   │       ├── EmiService.java         # Business logic
│   │   │   │       ├── EmiRequest.java         # Request DTO
│   │   │   │       └── EmiResponse.java         # Response DTO
│   │   │   └── resources/
│   │   │       └── application.properties       # Spring Boot config
│   │   └── test/
│   │       └── java/com/emi/backend/
│   │           └── EmiServiceTest.java          # Unit tests
│   └── pom.xml                                   # Maven dependencies
│
└── emi-frontend/                   # Angular frontend
    ├── src/
    │   ├── app/
    │   │   ├── app.component.ts                 # Main component (logic)
    │   │   ├── app.component.html               # Main component (UI)
    │   │   ├── app.component.css                # Component styles
    │   │   ├── app.module.ts                   # Angular module
    │   │   └── emi.service.ts                   # API service
    │   ├── index.html                           # Entry HTML
    │   ├── main.ts                              # Angular bootstrap
    │   └── styles.css                           # Global styles
    ├── angular.json                              # Angular CLI config
    ├── package.json                              # npm dependencies
    └── tsconfig.json                             # TypeScript config
```

## 🚀 Getting Started

### Backend Setup

1. **Navigate to backend directory:**
   ```bash
   cd emi-backend
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```
   This will download dependencies and compile the code.

3. **Run the Spring Boot application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Verify backend is running:**
   - Backend will start on `http://localhost:8080`
   - You should see: "Started EmiApplication" in the console
   - Test the API endpoint (see API Documentation section)

### Frontend Setup

1. **Navigate to frontend directory:**
   ```bash
   cd emi-frontend
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```
   This will download all Angular packages listed in `package.json`.

3. **Start the Angular development server:**
   ```bash
   ng serve
   ```
   Or use:
   ```bash
   npm start
   ```

4. **Open in browser:**
   - Frontend will run on `http://localhost:4200`
   - Angular will automatically open your default browser
   - You should see the EMI Calculator form

### Running Both Together

**Terminal 1 (Backend):**
```bash
cd emi-backend
mvn spring-boot:run
```

**Terminal 2 (Frontend):**
```bash
cd emi-frontend
ng serve
```

Then open `http://localhost:4200` in your browser.

## 📡 API Documentation

### Endpoint
**POST** `http://localhost:8080/api/emi/calculate`

### Request Headers
```
Content-Type: application/json
```

### Request Body
```json
{
  "loanAmount": 1000000,
  "yearlyInterestRate": 8,
  "loanTermYears": 20
}
```

**Field Descriptions:**
- `loanAmount` (Double): Principal loan amount (must be positive)
- `yearlyInterestRate` (Double): Annual interest rate in percentage (0-100)
- `loanTermYears` (Integer): Loan tenure in years (0-30)

### Success Response (200 OK)
```json
{
  "emiAmount": 8364.48,
  "message": "SUCCESS"
}
```

**Response Fields:**
- `emiAmount` (Double): Calculated monthly EMI amount
- `message` (String): Status message ("SUCCESS" or error message)

### Error Response (400 Bad Request)

**Validation Error Example:**
```json
{
  "emiAmount": 0.0,
  "message": "Yearly interest rate must be between 0 and 100"
}
```

**Other Error Examples:**
```json
{
  "emiAmount": 0.0,
  "message": "Loan amount must be a positive number"
}
```

```json
{
  "emiAmount": 0.0,
  "message": "Loan term must be between 0 and 30 years"
}
```

### Testing API with cURL

```bash
curl -X POST http://localhost:8080/api/emi/calculate \
  -H "Content-Type: application/json" \
  -d '{"loanAmount":1000000,"yearlyInterestRate":8,"loanTermYears":20}'
```

### Testing API with Postman

1. Create a new POST request
2. URL: `http://localhost:8080/api/emi/calculate`
3. Headers: `Content-Type: application/json`
4. Body (raw JSON):
   ```json
   {
     "loanAmount": 1000000,
     "yearlyInterestRate": 8,
     "loanTermYears": 20
   }
   ```
5. Click Send

## ✅ Validation Rules

Both **backend** and **frontend** enforce the same validation rules:

| Field | Validation Rule | Error Message |
|-------|----------------|---------------|
| Loan Amount | Must be a positive number | "Loan amount must be a positive number" |
| Yearly Interest Rate | Must be between 0 and 100 | "Yearly interest rate must be between 0 and 100" |
| Loan Term (Years) | Must be between 0 and 30 | "Loan term must be between 0 and 30 years" |

### Backend Validation
- Uses **Bean Validation** annotations (`@NotNull`, `@Positive`, `@Max`)
- Validation happens automatically via `@Valid` annotation
- Errors are caught by `@ExceptionHandler` and returned as JSON

### Frontend Validation
- Client-side validation before API call
- Prevents unnecessary network requests
- Shows error messages immediately

## ✨ Features

### Backend Features
- ✅ RESTful API with proper HTTP status codes
- ✅ Comprehensive input validation
- ✅ EMI calculation using standard formula
- ✅ Exception handling with meaningful error messages
- ✅ CORS configuration for frontend integration
- ✅ Unit tests for business logic
- ✅ Clean separation of concerns (Controller → Service → DTOs)

### Frontend Features
- ✅ Clean, modern UI with responsive design
- ✅ Real-time form validation
- ✅ Currency formatting with ₹ symbol and thousand separators
- ✅ Displays calculated EMI amount
- ✅ Shows total payment over loan tenure
- ✅ Shows total interest payable
- ✅ Loading spinner during API calls
- ✅ Button disabled during calculation
- ✅ Reset button to clear form and results
- ✅ Error handling with user-friendly messages
- ✅ Two-way data binding with Angular FormsModule

### EMI Calculation Formula

The backend uses the standard EMI formula:

```
EMI = [P × R × (1 + R)^N] / [(1 + R)^N - 1]
```

Where:
- **P** = Principal (Loan Amount)
- **R** = Monthly Interest Rate (Yearly Rate / 12 / 100)
- **N** = Number of Monthly Installments (Years × 12)

## 🧪 Testing

### Backend Unit Tests

Run all backend tests:
```bash
cd emi-backend
mvn test
```

**Test Coverage:**
- ✅ Normal EMI calculation with valid inputs
- ✅ Zero interest rate handling (simple division)
- ✅ Edge cases and boundary conditions

**Test File:** `emi-backend/src/test/java/com/emi/backend/EmiServiceTest.java`

### Manual Testing

**Test Case 1: Valid Input**
```json
Request: {"loanAmount": 1000000, "yearlyInterestRate": 8, "loanTermYears": 20}
Expected: EMI ≈ 8364.48
```

**Test Case 2: Zero Interest**
```json
Request: {"loanAmount": 120000, "yearlyInterestRate": 0, "loanTermYears": 1}
Expected: EMI = 10000 (simple division: 120000 / 12)
```

**Test Case 3: Validation Error**
```json
Request: {"loanAmount": -1000, "yearlyInterestRate": 8, "loanTermYears": 20}
Expected: Error message about positive loan amount
```

## 🔧 Troubleshooting

### Backend Issues

**Problem: Port 8080 already in use**
```bash
# Solution 1: Stop the process using port 8080
# Windows:
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Solution 2: Change port in application.properties
# Add: server.port=8081
```

**Problem: Maven dependencies not downloading**
```bash
# Clear Maven cache and rebuild
mvn clean
mvn install -U
```

**Problem: Java version mismatch**
```bash
# Check Java version
java -version
# Should be 17 or higher
# If not, install JDK 17+
```

### Frontend Issues

**Problem: Angular CLI not found**
```bash
# Install Angular CLI globally
npm install -g @angular/cli

# Verify installation
ng version
```

**Problem: Port 4200 already in use**
```bash
# Angular will automatically use next available port (4201, 4202, etc.)
# Or specify a different port:
ng serve --port 4300
```

**Problem: npm install fails**
```bash
# Clear npm cache
npm cache clean --force

# Delete node_modules and reinstall
rmdir /s /q node_modules
npm install
```

**Problem: Frontend cannot connect to backend**
- ✅ Ensure backend is running on `http://localhost:8080`
- ✅ Check browser console for CORS errors
- ✅ Verify backend CORS configuration allows `http://localhost:4200`
- ✅ Check Network tab in browser DevTools for failed requests

**Problem: Blank page or Angular errors**
```bash
# Clear Angular build cache
rmdir /s /q .angular

# Rebuild
ng serve
```

### Common Errors

**Error: "Cannot GET /"**
- This is normal for Angular dev server
- Navigate to `http://localhost:4200` (not root path)

**Error: "NG0908" or dependency injection errors**
- Ensure `HttpClientModule` is imported in `app.module.ts`
- Check that `EmiService` uses `providedIn: 'root'`
- Clear `.angular` cache and rebuild

**Error: CORS policy error**
- Backend CORS is configured for `http://localhost:4200`
- Ensure you're accessing frontend from correct URL
- Check `EmiController` has `@CrossOrigin` annotation

## 📝 Sample Payloads

### Example 1: Home Loan
```json
{
  "loanAmount": 5000000,
  "yearlyInterestRate": 7.5,
  "loanTermYears": 25
}
```
**Expected EMI:** ~₹37,000-38,000

### Example 2: Car Loan
```json
{
  "loanAmount": 800000,
  "yearlyInterestRate": 9.5,
  "loanTermYears": 5
}
```
**Expected EMI:** ~₹16,000-17,000

### Example 3: Personal Loan
```json
{
  "loanAmount": 200000,
  "yearlyInterestRate": 12,
  "loanTermYears": 3
}
```
**Expected EMI:** ~₹6,600-6,700

## 🔒 CORS Configuration

The backend is configured to accept requests from the Angular frontend:

```java
@CrossOrigin(origins = "http://localhost:4200")
```

This allows the frontend running on `http://localhost:4200` to make API calls to the backend.

## 📚 Additional Resources

### Understanding the Code

**Backend Flow:**
1. Client sends POST request to `/api/emi/calculate`
2. `EmiController` receives request and validates with `@Valid`
3. If valid, `EmiService` calculates EMI using formula
4. Response is returned as JSON

**Frontend Flow:**
1. User fills form and clicks "Calculate EMI"
2. `AppComponent` validates inputs client-side
3. If valid, `EmiService` sends POST request to backend
4. Response is received and displayed to user

### Key Files Explained

**Backend:**
- `EmiController.java`: Handles HTTP requests, validation, error handling
- `EmiService.java`: Contains EMI calculation business logic
- `EmiRequest.java`: DTO for incoming request data
- `EmiResponse.java`: DTO for outgoing response data

**Frontend:**
- `app.component.ts`: Component logic, form handling, validation
- `app.component.html`: UI template with form and results
- `emi.service.ts`: Service for API communication
- `app.module.ts`: Angular module configuration

## 🎯 Project Highlights

- ✅ **Clean Architecture:** Separation of concerns (Controller → Service → DTOs)
- ✅ **Comprehensive Validation:** Both client-side and server-side validation
- ✅ **Error Handling:** Meaningful error messages for better UX
- ✅ **Unit Testing:** Test coverage for business logic
- ✅ **Professional Code:** Follows Java and Angular best practices
- ✅ **Well Documented:** Clear code structure and comments
- ✅ **Production Ready:** CORS configuration, proper exception handling

## 📄 License

This project is part of a technical assessment.

---

**Happy Coding! 🚀**

For questions or issues, please refer to the Troubleshooting section above.
