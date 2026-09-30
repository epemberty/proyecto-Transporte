import { Routes } from '@angular/router';

export const routes: Routes = [
  // Redirección inicial
  { path: '', redirectTo: 'home', pathMatch: 'full' },

  // Página principal
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', loadComponent: () => import('./components/home/home.component') },


  // Conductor
  { path: 'conductor', loadComponent: () => import('./components/conductor/conductor.component') },
  { path: 'newConductor', loadComponent: () => import('./components/conductor-form/conductor-form.component') },
  { path: 'conductor/:id', loadComponent: () => import('./components/conductor-form/conductor-form.component') },

  // Vehículo
  { path: 'vehiculo', loadComponent: () => import('./components/vehiculo/vehiculo.component') },
  { path: 'newVehiculo', loadComponent: () => import('./components/vehiculo-form/vehiculo-form.component') },
  { path: 'vehiculo/:id', loadComponent: () => import('./components/vehiculo-form/vehiculo-form.component') },

  // Contrato
  { path: 'contrato', loadComponent: () => import('./components/contrato/contrato.component') },
  { path: 'newContrato', loadComponent: () => import('./components/contrato-form/contrato-form.component') },
  { path: 'contrato/:id', loadComponent: () => import('./components/contrato-form/contrato-form.component') },

  // Destino
  { path: 'destino', loadComponent: () => import('./components/destino/destino.component') },
  { path: 'newDestino', loadComponent: () => import('./components/destino-form/destino-form.component') },
  { path: 'destino/:id', loadComponent: () => import('./components/destino-form/destino-form.component') },

  // Mina
  { path: 'mina', loadComponent: () => import('./components/mina/mina.component') },
  { path: 'newMina', loadComponent: () => import('./components/mina-form/mina-form.component') },
  { path: 'mina/:id', loadComponent: () => import('./components/mina-form/mina-form.component') },

  // Ruta
  { path: 'ruta', loadComponent: () => import('./components/ruta/ruta.component') },
  { path: 'newRuta', loadComponent: () => import('./components/ruta-form/ruta-form.component') },
  { path: 'ruta/:id', loadComponent: () => import('./components/ruta-form/ruta-form.component') },

  // Tiquete
  { path: 'tiquete', loadComponent: () => import('./components/tiquete/tiquete.component') },
  { path: 'newTiquete', loadComponent: () => import('./components/tiquete-form/tiquete-form.component') },
  { path: 'tiquete/:id', loadComponent: () => import('./components/tiquete-form/tiquete-form.component') },

  // Reporte de Tiquetes
  { path: 'reportes/tiquetes', loadComponent: () => import('./components/tiquete-reporte/tiquete-reporte.component') },

  // Redirección final
  { path: '**', redirectTo: '/home', pathMatch: 'full' }
];
