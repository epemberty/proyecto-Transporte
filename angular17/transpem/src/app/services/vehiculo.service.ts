import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VehiculoInterface } from '../interfaces/vehiculo.interface';

@Injectable({
  providedIn: 'root'
})
export class VehiculoService {
  private urlEndPoint: string = 'http://localhost:8080/api/v1'+'/vehiculo';

  constructor(private http: HttpClient) {}


// Obtener todos los vehiculos
  getTodos(): Observable<VehiculoInterface[]> {
    return this.http.get<VehiculoInterface[]>(this.urlEndPoint+'/findAll');
  }

  // Obtener un conductor por ID
  getXid(id:number): Observable<VehiculoInterface> {
    return this.http.get<VehiculoInterface>(this.urlEndPoint+'/find/'+id);
  }

  // Crear un nuevo conductor
  crear(vehiculo: VehiculoInterface): Observable<VehiculoInterface> {
    return this.http.post<VehiculoInterface>(this.urlEndPoint+'/save', vehiculo);
  }

  actualizar(id:number, vehiculo: VehiculoInterface): Observable<VehiculoInterface>{
    return this.http.put<VehiculoInterface>(this.urlEndPoint+'/update/'+id, vehiculo);
  }

  // Eliminar un conductor por ID
  borrar(id: number): Observable<any> {
    return this.http.delete(this.urlEndPoint+'/delete/'+id,{responseType: 'text'});
  }
}
