import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-reportes-menu',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './reportes-menu.component.html'
 
})
export default class ReportesMenuComponent {
  reportes = [{
    titulo: 'Informe de Tiquetes',
    descripcion: 'Consulta detallada con filtros por fecha, mina, ruta, y fletes',
    path: '/reportes/tiquetes',
    icono: 'bi-file-earmark-spreadsheet',
    badge: 'Detallado',
    color: 'primary'},
    {
       titulo: 'Liquidación de Comisiones',
      descripcion: 'Cálculo de viajes y total de comisiones a pagar por conductor.',
      path: '/reportes/liquidacion',
      icono: 'bi-cash-coin',
      badge: 'Financiero',
      color: 'success'
    }
  ];
}