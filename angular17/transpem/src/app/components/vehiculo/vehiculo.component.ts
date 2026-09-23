import { Component, OnInit, inject } from '@angular/core';
import { VehiculoService } from '../../services/vehiculo.service';
import { VehiculoInterface } from '../../interfaces/vehiculo.interface';
import { Router, RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-vehiculo',
  standalone: true,
  imports: [RouterModule, NgFor],
  templateUrl: './vehiculo.component.html',
  styleUrls: []
})
export default class VehiculoComponent implements OnInit {
  private vehiculoService = inject(VehiculoService);
  private router = inject(Router);

  vehiculos: VehiculoInterface[] = [];

  ngOnInit(): void {
    this.obtenerTodos();
  }

  obtenerTodos(): void {
    this.vehiculoService.getTodos().subscribe(data => {
      this.vehiculos = data;
    });
  }

  borrarVehiculo(id: number): void {
    this.vehiculoService.borrar(id).subscribe(() => {
      this.obtenerTodos();
    });
  }

  trackById(index: number, vehiculo: VehiculoInterface): number {
    return vehiculo.id!;
  }
}
