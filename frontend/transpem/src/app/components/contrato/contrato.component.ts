import { Component, OnInit, inject } from '@angular/core';
import { ContratoService } from '../../services/contrato.service';
import { ContratoInterface } from '../../interfaces/contrato.interface';
import { Router, RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-contrato',
  standalone: true,
  imports: [RouterModule, NgFor],
  templateUrl: './contrato.component.html',
  styleUrls: []
})
export default class ContratoComponent implements OnInit {
  private contratoService = inject(ContratoService);
  private router = inject(Router);

  contratos: ContratoInterface[] = [];

  ngOnInit(): void {
    this.obtenerTodos();
  }

  obtenerTodos(): void {
    this.contratoService.getTodos().subscribe(data => {
      this.contratos = data;
    });
  }

  borrarContrato(id: number): void {
    this.contratoService.borrar(id).subscribe(() => {
      this.obtenerTodos();
    });
  }

  trackById(index: number, contrato: ContratoInterface): number {
    return contrato.id!;
  }
}
