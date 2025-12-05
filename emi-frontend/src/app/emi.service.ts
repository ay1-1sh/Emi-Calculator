import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface EmiRequest {
  loanAmount: number;
  yearlyInterestRate: number;
  loanTermYears: number;
}

export interface EmiResponse {
  emiAmount: number;
  message: string;
}

@Injectable({
  providedIn: 'root'
})
export class EmiService {
  private baseUrl = 'http://localhost:8080/api/emi';

  constructor(private http: HttpClient) {}

  calculateEmi(request: EmiRequest): Observable<EmiResponse> {
    return this.http.post<EmiResponse>(`${this.baseUrl}/calculate`, request);
  }
}