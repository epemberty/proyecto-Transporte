import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContratoInterface } from '../interfaces/contrato.interface';

@Injectable({
  providedIn: 'root'
})
export class ContratoService {
  private urlEndPoint: string = 'http://localhost:8080/api/v1'+'/contrato';

  constructor(private http: HttpClient) {}

  // Obtener todos los contratos
  getTodos(): Observable<ContratoInterface[]> {
    return this.http.get<ContratoInterface[]>(this.urlEndPoint+'/findAll');
  }

  // Obtener un contrato por ID
  getXid(id: number): Observable<ContratoInterface> {
    return this.http.get<ContratoInterface>(this.urlEndPoint+'/find/'+id);
  }

  // Crear un nuevo contrato
  crear(contrato: ContratoInterface): Observable<ContratoInterface> {
    return this.http.post<ContratoInterface>(this.urlEndPoint+'/save', contrato);
  }

  // Actualizar un contrato
  actualizar(id: number, contrato: ContratoInterface): Observable<ContratoInterface>{
    return this.http.post<ContratoInterface>(this.urlEndPoint+'/save', contrato);
  }

  // Eliminar un contrato por ID
  borrar(id: number): Observable<any> {
    return this.http.delete(this.urlEndPoint+'/delete/'+id,{
      responseType: 'text'
    });
  }
}
