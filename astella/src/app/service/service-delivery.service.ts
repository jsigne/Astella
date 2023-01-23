import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Service } from '../model/service.model';
import { Observable, of } from 'rxjs'

import { catchError, map, tap } from 'rxjs/operators';
@Injectable({ providedIn: 'root' })
export class ServiceDeliveryService{

  constructor(private http: HttpClient){}

  getAllServices(): Observable<Service[]> {

      return this.http.get<Service[]>('http://localhost:9000/services');

  }


 }
