import { Component, OnInit, inject } from '@angular/core';
import { MinaService } from '../../services/mina.service';
import { MinaInterface } from '../../interfaces/mina.interface';
import { Router, RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-mina',
  standalone: true,
  imports: [RouterModule, NgFor],
  templateUrl: './mina.component.html',
  styleUrls: []
})
export default class MinaComponent implements OnInit {
  private minaService = inject(MinaService);
  private router = inject(Router);

    minas: MinaInterface[] = [];

    ngOnInit(): void {
      this.obtenerTodos();
    }

    obtenerTodos(): void {
      this.minaService.getTodos().subscribe(data => {
        this.minas = data;
      });
    }

    borrarMina(id: number): void {
      this.minaService.borrar(id).subscribe(() => {
        this.obtenerTodos();
      });
    }

    trackById(index: number, mina: MinaInterface): number {
      return mina.id!;
    }
  }
