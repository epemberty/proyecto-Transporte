export interface VehiculoInterface {
  id: number;
  placa: string;
  marca: string;
  modelo: number;
  vencimientoTecnomecanica: Date; // formato ISO: 'YYYY-MM-DD'
  vencimientoSoat: Date;
  vencimientoPoliza: Date;
  estado: boolean;
}
