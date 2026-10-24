import { ContratoInterface } from "./contrato.interface";
import { DestinoInterface } from "./destino.interface";
import { MinaInterface } from "./mina.interface";

export interface RutaInterface {
  id: number;
  nombre: string;
  mina: MinaInterface | null;
  destino: DestinoInterface | null;
  contrato: ContratoInterface | null;
  valorComision: number;
  valorTonelada: number;
  estado: boolean;
}
