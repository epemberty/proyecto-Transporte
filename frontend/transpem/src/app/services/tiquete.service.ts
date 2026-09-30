import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TiqueteInterface } from '../interfaces/tiquete.interface';

@Injectable({
  providedIn: 'root'
})
export class TiqueteService {
 private urlEndPoint: string = 'http://localhost:8080/api/v1'+'/tiquete';

   constructor(private http: HttpClient) {}

   // Obtener todos los conductores
   getTodos(): Observable<TiqueteInterface[]> {
     return this.http.get<TiqueteInterface[]>(this.urlEndPoint+'/findAll');
   }

   // Obtener un conductor por ID
   getXid(id:number): Observable<TiqueteInterface> {
     return this.http.get<TiqueteInterface>(this.urlEndPoint+'/find/'+id);
   }

   // Crear un nuevo conductor
   crear(tiquete: TiqueteInterface): Observable<TiqueteInterface> {
     return this.http.post<TiqueteInterface>(this.urlEndPoint+'/save', tiquete);
   }

   actualizar(id:number, tiquete: TiqueteInterface): Observable<TiqueteInterface>{
     return this.http.put<TiqueteInterface>(this.urlEndPoint+'/update/'+id, tiquete);
   }

   // Eliminar un conductor por ID
   borrar(id: number): Observable<any> {
     return this.http.delete(this.urlEndPoint+'/delete/'+id,{responseType: 'text'});
   }
 }
