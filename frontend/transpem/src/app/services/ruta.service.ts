import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RutaInterface } from '../interfaces/ruta.interface';

@Injectable({
  providedIn: 'root'
})
export class RutaService {
  private urlEndPoint: string = 'http://localhost:8080/api/v1'+'/ruta';

  constructor(private http: HttpClient) {}



  // Obtener todas las rutas
  getTodos(): Observable<RutaInterface[]> {
    return this.http.get<RutaInterface[]>(this.urlEndPoint+'/findAll');
  }

  // Obtener una ruta por ID
  getXid(id:number): Observable<RutaInterface> {
    return this.http.get<RutaInterface>(this.urlEndPoint+'/find/'+id);
  }

  // Crear una nueva ruta
  crear(ruta: RutaInterface): Observable<RutaInterface> {
    return this.http.post<RutaInterface>(this.urlEndPoint+'/save', ruta);
  }

  actualizar(id:number, ruta: RutaInterface): Observable<RutaInterface>{
    return this.http.put<RutaInterface>(this.urlEndPoint+'/update/'+id, ruta);
  }

  // Eliminar una ruta por ID
  borrar(id: number): Observable<any> {
    return this.http.delete(this.urlEndPoint+'/delete/'+id,{responseType: 'text'});
  }
}
