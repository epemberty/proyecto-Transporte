import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MinaInterface } from '../interfaces/mina.interface';

@Injectable({
  providedIn: 'root'
})
export class MinaService {
  private urlEndPoint = 'http://localhost:8080/api/v1'+'/mina';

  constructor(private http: HttpClient) {}

  // Obtener todas las minas
  getTodos(): Observable<MinaInterface[]> {
    return this.http.get<MinaInterface[]>(this.urlEndPoint+'/findAll');
  }

  // Obtener una mina por ID
  getXid(id:number): Observable<MinaInterface> {
    return this.http.get<MinaInterface>(this.urlEndPoint+'/find/'+id);
  }

  // Crear un nuevo conductor
  crear(mina: MinaInterface): Observable<MinaInterface> {
    return this.http.post<MinaInterface>(this.urlEndPoint+'/save', mina);
  }

  actualizar(id:number, mina: MinaInterface): Observable<MinaInterface>{
    return this.http.put<MinaInterface>(this.urlEndPoint+'/update/'+id, mina);
  }

  // Eliminar un conductor por ID
  borrar(id: number): Observable<any> {
    return this.http.delete(this.urlEndPoint+'/delete/'+id,{responseType: 'text'});
  }
}
