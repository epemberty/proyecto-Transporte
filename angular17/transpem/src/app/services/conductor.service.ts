import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ConductorInterface } from '../interfaces/conductor.interface';

@Injectable({
  providedIn: 'root'
})
export class ConductorService {
  private urlEndPoint: string = 'http://localhost:8080/api/v1'+'/conductor';

  constructor(private http: HttpClient) {}

  // Obtener todos los conductores
  getTodos(): Observable<ConductorInterface[]> {
    return this.http.get<ConductorInterface[]>(this.urlEndPoint+'/findAll');
  }

  // Obtener un conductor por ID
  getXid(id:number): Observable<ConductorInterface> {
    return this.http.get<ConductorInterface>(this.urlEndPoint+'/find/'+id);
  }

  // Crear un nuevo conductor
  crear(conductor: ConductorInterface): Observable<ConductorInterface> {
    return this.http.post<ConductorInterface>(this.urlEndPoint+'/save', conductor);
  }

  actualizar(id:number, conductor: ConductorInterface): Observable<ConductorInterface>{
    return this.http.put<ConductorInterface>(this.urlEndPoint+'/update/'+id, conductor);
  }

  // Eliminar un conductor por ID
  borrar(id: number): Observable<any> {
    return this.http.delete(this.urlEndPoint+'/delete/'+id,{responseType: 'text'});
  }
}
