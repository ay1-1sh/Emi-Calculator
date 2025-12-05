package com.emi.backend;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emi")
@CrossOrigin(origins = "http://localhost:4200")
public class EmiController {

    private final EmiService emiService;

    public EmiController(EmiService emiService) {
        this.emiService = emiService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<EmiResponse> calculateEmi(@Valid @RequestBody EmiRequest request) {
        double emi = emiService.calculateEmi(
                request.getLoanAmount(),
                request.getYearlyInterestRate(),
                request.getLoanTermYears()
        );

        double roundedEmi = Math.round(emi * 100.0) / 100.0;
        EmiResponse response = new EmiResponse(roundedEmi, "SUCCESS");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<EmiResponse> handleValidation(MethodArgumentNotValidException ex) {
        FieldError fieldError = (FieldError) ex.getBindingResult().getAllErrors().get(0);
        String message = fieldError.getDefaultMessage();
        EmiResponse response = new EmiResponse(0.0, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<EmiResponse> handleIllegalArgument(IllegalArgumentException ex) {
        EmiResponse response = new EmiResponse(0.0, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}


