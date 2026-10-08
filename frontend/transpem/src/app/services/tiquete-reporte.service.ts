import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface TiqueteReporteDTO {
  id: number;
  numeroTiquete: number;
  fecha: string;
  nombre: string;
  conductor: any;
  vehiculo: any;
  ruta: any;
  pesoKilos: number;
  pesoToneladas: number;
  valorViaje: number;
  anticipo: number;
  saldoBruto: number;
  encarpe: number;
  combustible: number;
  retencion: number;
  reteica: number;
  administracion: number;
  saldoNeto: number;
  estado: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class TiqueteReporteService {
  private http = inject(HttpClient);
  // Endpoint directo a tu backend local de Spring Boot
  private apiUrl = 'http://localhost:8080/api/v1/reportes/tiquetes';

  obtenerReportes(filtros: any): Observable<TiqueteReporteDTO[]> {
    let params = new HttpParams();
    
    if (filtros.fechaInicio) params = params.set('fechaInicio', filtros.fechaInicio);
    if (filtros.fechaFin) params = params.set('fechaFin', filtros.fechaFin);
    if (filtros.idMina) params = params.set('idMina', filtros.idMina);
    if (filtros.idRuta) params = params.set('idRuta', filtros.idRuta);
    if (filtros.idConductor) params = params.set('idConductor', filtros.idConductor);
    if (filtros.valorMin) params = params.set('valorMin', filtros.valorMin);
    if (filtros.valorMax) params = params.set('valorMax', filtros.valorMax);

    return this.http.get<TiqueteReporteDTO[]>(this.apiUrl, { params });
  }
}
