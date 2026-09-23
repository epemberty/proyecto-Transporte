import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { TiqueteReporteService, TiqueteReporteDTO } from '../../services/tiquete-reporte.service';

@Component({
  selector: 'app-tiquete-reporte',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './tiquete-reporte.component.html',
  styleUrls: ['./tiquete-reporte.component.scss']
})
export default class TiqueteReporteComponent implements OnInit {
  private fb = inject(FormBuilder);
  private reporteService = inject(TiqueteReporteService);

  reporteForm!: FormGroup;
  tiquetes: TiqueteReporteDTO[] = [];
  cargando = false;
  totalValorViaje = 0;
  totalSaldoNeto = 0;

  ngOnInit(): void {
    // Inicializar el formulario reactivo con todos los campos opcionales
    this.reporteForm = this.fb.group({
      fechaInicio: [''],
      fechaFin: [''],
      idMina: [''],
      idRuta: [''],
      idConductor: [''],
      valorMin: [''],
      valorMax: ['']
    });

    // Descomenta si quieres que al entrar a la pantalla busque sin filtros automÃ¡ticamente:
    // this.buscarReportes();
  }

  buscarReportes(): void {
    this.cargando = true;
    const filtros = this.reporteForm.value;
    
    // Limpiar campos vacíos para no enviarlos como params vacíos
    const filtrosLimpios = Object.entries(filtros).reduce((acc: any, [key, val]) => {
        if (val !== '' && val !== null) acc[key] = val;
        return acc;
    }, {});

    this.reporteService.obtenerReportes(filtrosLimpios).subscribe({
      next: (data) => {
        this.tiquetes = data;
        this.calcularTotales();
        this.cargando = false;
      },
      error: (err) => {
        console.error('Error obteniendo los reportes', err);
        this.cargando = false;
      }
    });
  }

  calcularTotales(): void {
    this.totalValorViaje = this.tiquetes.reduce((sum, t) => sum + (t.valorViaje || 0), 0);
    this.totalSaldoNeto = this.tiquetes.reduce((sum, t) => sum + (t.saldoNeto || 0), 0);
  }

  limpiarFiltros(): void {
    this.reporteForm.reset();
    this.tiquetes = [];
    this.totalValorViaje = 0;
    this.totalSaldoNeto = 0;
  }
}
