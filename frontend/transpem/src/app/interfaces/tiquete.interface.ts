import { ConductorInterface } from "./conductor.interface";
import { RutaInterface } from "./ruta.interface";
import { VehiculoInterface } from "./vehiculo.interface";

export interface TiqueteInterface {
  id: number;
  numeroTiquete: number;
  fecha: string;
  nombre: string;
  conductor: ConductorInterface | null;
  vehiculo: VehiculoInterface| null;
  ruta: RutaInterface | null;
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
