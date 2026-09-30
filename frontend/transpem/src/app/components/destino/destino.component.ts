import { Component, OnInit, inject } from '@angular/core';
import { DestinoService } from '../../services/destino.service';
import { DestinoInterface } from '../../interfaces/destino.interface';
import { Router, RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-destino',
  standalone: true,
  imports: [RouterModule, NgFor],
  templateUrl: './destino.component.html',
  styleUrls: []
})
export default class DestinoComponent implements OnInit {
  private destinoService = inject(DestinoService);
  private router = inject(Router);

  destinos: DestinoInterface[] = [];

  ngOnInit(): void {
     this.obtenerTodos();
   }

   obtenerTodos(): void {
     this.destinoService.getTodos().subscribe(data => {
       this.destinos = data;
     });
   }

   borrarDestino(id: number): void {
     this.destinoService.borrar(id).subscribe(() => {
       this.obtenerTodos();
     });
   }

   trackById(index: number, destino: DestinoInterface): number {
     return destino.id!;
   }
 }
