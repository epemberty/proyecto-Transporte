import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { TiqueteReporteService} from '../../services/tiquete-reporte.service';
import { ConductorService } from '../../services/conductor.service';
import { LiquidacionConductor } from '../../interfaces/liquidacion-conductor.interface';

@Component({
  selector: 'app-liquidacion-conductores',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './liquidacion-conductores.component.html'
})
export default class LiquidacionConductoresComponent implements OnInit {
  private reporteService = inject(TiqueteReporteService);
  private conductorService = inject(ConductorService);

  conductores: any[] = [];
  liquidaciones: LiquidacionConductor[] = [];
  cargando: boolean = false;

  filtros = {
    fechaInicio: '',
    fechaFin: '',
    idConductor: undefined as number | undefined
  };

  ngOnInit(): void {
    this.cargarConductores();
    this.consultar();
  }

  cargarConductores(): void {
    this.conductorService.getTodos().subscribe({
      next: (data) => (this.conductores = data),
      error: (e) => console.error('Error cargando conductores', e)
    });
  }

  consultar(): void {
    this.cargando = true;
    this.reporteService.obtenerLiquidacion(this.filtros).subscribe({
      next: (data) => {
        this.liquidaciones = data;
        this.cargando = false;
      },
      error: (e) => {
        console.error('Error liquidación', e);
        this.cargando = false;
      }
    });
  }

  limpiarFiltros(): void {
    this.filtros = { fechaInicio: '', fechaFin: '', idConductor: undefined };
    this.consultar();
  }

  get totalComisiones(): number {
    return this.liquidaciones.reduce((acc, curr) => acc + (curr.totalComision || 0), 0);
  }

  get totalViajes(): number {
    return this.liquidaciones.reduce((acc, curr) => acc + (curr.totalViajes || 0), 0);
  }
}