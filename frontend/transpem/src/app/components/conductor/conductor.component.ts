import { Component, OnInit, inject } from '@angular/core';
import { ConductorService } from '../../services/conductor.service';
import { ConductorInterface } from '../../interfaces/conductor.interface';
import { Router, RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-conductor',
  standalone: true,
  imports: [RouterModule, NgFor],
  templateUrl: './conductor.component.html',
  styleUrls: []
})
export default class ConductorComponent implements OnInit {
  private conductorService = inject(ConductorService);
  private router = inject(Router);

  conductores: ConductorInterface[] = [];

  ngOnInit(): void {
    this.obtenerTodos();
  }

  obtenerTodos(): void {
    this.conductorService.getTodos().subscribe(data => {
      this.conductores = data;
    });
  }

  borrarConductor(id: number): void {
    this.conductorService.borrar(id).subscribe(() => {
      this.obtenerTodos();
    });
  }

  trackById(index: number, conductor: ConductorInterface): number {
    return conductor.id!;
  }
}
