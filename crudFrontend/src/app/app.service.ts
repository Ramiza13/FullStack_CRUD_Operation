import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { Employee } from './Employee';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private url = "http://localhost:8080/";
  constructor(private http: HttpClient) { }

  addEmployee(employee: Employee): Observable<any>{
    const url = this.url + 'add'
      return this.http.post<Employee>(url, employee);
  }

  getEmployee(): Observable<any[]>{
    return this.http.get<any[]>(this.url + 'employee')
  }

  getEmployeeById(id: number): Observable<Employee>{
    return this.http.get<Employee>(`${this.url}employee/${id}`)
  }

  updateEmployee(employee?: any): Observable<any>{
    const url = this.url + 'update'
    return this.http.put<any>(url, employee)   ///(`${this.url}update`, employee)
  }

  deleteEmployee(id: number): Observable<any>{
    return this.http.delete<any>(`${this.url}delete/${id}`)
  }
}
