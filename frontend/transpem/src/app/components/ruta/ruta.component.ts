import { Component, OnInit, inject } from '@angular/core';
import { RutaService } from '../../services/ruta.service';
import { RutaInterface } from '../../interfaces/ruta.interface';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-ruta',
  standalone: true,
  imports: [RouterModule, NgFor],
  templateUrl: './ruta.component.html',
  styleUrls: []
})
export default class RutaComponent implements OnInit {
  private rutaService = inject(RutaService);
  private router = inject(Router);

  rutas: RutaInterface[] = [];

  ngOnInit(): void {
    this.obtenerTodas();
  }
obtenerTodas(): void {
    this.rutaService.getTodos().subscribe(data => {
      this.rutas = data;
    });
  }

  borrarRuta(id: number): void {
    this.rutaService.borrar(id).subscribe(() => {
      this.obtenerTodas();
    });
  }

  trackById(index: number, ruta: RutaInterface): number {
    return ruta.id!;
  }
}
