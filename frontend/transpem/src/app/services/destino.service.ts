import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DestinoInterface } from '../interfaces/destino.interface';

@Injectable({
  providedIn: 'root'
})
export class DestinoService {
  private urlEndPoint: string = 'http://localhost:8080/api/v1'+'/destino';

  constructor(private http: HttpClient) {}

  // Obtener todos los conductores
  getTodos(): Observable<DestinoInterface[]> {
    return this.http.get<DestinoInterface[]>(this.urlEndPoint+'/findAll');
  }

  // Obtener un conductor por ID
  getXid(id:number): Observable<DestinoInterface> {
    return this.http.get<DestinoInterface>(this.urlEndPoint+'/find/'+id);
  }

  // Crear un nuevo conductor
  crear(destino: DestinoInterface): Observable<DestinoInterface> {
    return this.http.post<DestinoInterface>(this.urlEndPoint+'/save', destino);
  }

  actualizar(id:number, destino: DestinoInterface): Observable<DestinoInterface>{
    return this.http.put<DestinoInterface>(this.urlEndPoint+'/update/'+id, destino);
  }

  // Eliminar un conductor por ID
  borrar(id: number): Observable<any> {
    return this.http.delete(this.urlEndPoint+'/delete/'+id,{responseType: 'text'});
  }
}
