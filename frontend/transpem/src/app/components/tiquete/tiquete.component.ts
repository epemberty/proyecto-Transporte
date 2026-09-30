import { Component, OnInit, inject } from '@angular/core';
import { TiqueteService } from '../../services/tiquete.service';
import { TiqueteInterface } from '../../interfaces/tiquete.interface';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { NgFor } from '@angular/common';
import { CommonModule } from '@angular/common';
import { CurrencyPipe, DatePipe } from '@angular/common';

@Component({
  selector: 'app-tiquete',
  standalone: true,
  imports: [RouterModule, NgFor, CommonModule, CurrencyPipe, DatePipe],
  templateUrl: './tiquete.component.html',
  styleUrls: []
})
export default class TiqueteComponent implements OnInit {
  private tiqueteService = inject(TiqueteService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  tiquetes: TiqueteInterface[] = [];

  ngOnInit(): void {
    this.obtenerTodos();
  }

  obtenerTodos(): void {
    this.tiqueteService.getTodos().subscribe(response => {
      this.tiquetes = response;
    });
  }

  borrarTiquete(id: number): void {
    this.tiqueteService.borrar(id).subscribe(() => {
      this.obtenerTodos();
    });
  }

  trackById(index: number, tiquete: TiqueteInterface): number {
    return tiquete.id!;
  }
  eliminar(id: number): void {
  this.tiqueteService.borrar(id).subscribe(() => {
    this.tiquetes = this.tiquetes.filter(t => t.id !== id);
  });
}

}
