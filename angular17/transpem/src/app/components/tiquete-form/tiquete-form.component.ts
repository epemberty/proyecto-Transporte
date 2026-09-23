import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TiqueteInterface } from '../../interfaces/tiquete.interface';
import { TiqueteService } from '../../services/tiquete.service';
import { RutaService } from '../../services/ruta.service';
import { CommonModule } from '@angular/common';
import { VehiculoService } from '../../services/vehiculo.service';
import { ConductorService } from '../../services/conductor.service';
import { RutaInterface } from '../../interfaces/ruta.interface';
import { VehiculoInterface } from '../../interfaces/vehiculo.interface';
import { ConductorInterface } from '../../interfaces/conductor.interface';

@Component({
  selector: 'app-tiquete-form',
  standalone: true,
  templateUrl: './tiquete-form.component.html',
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
})
export default class TiqueteFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private rutaService = inject(RutaService);
  private vehiculoService = inject(VehiculoService);
  private conductorService = inject(ConductorService);
  private tiqueteService = inject(TiqueteService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  form!: FormGroup;
  tiquete!: TiqueteInterface;
  rutas: RutaInterface[] = [];
  vehiculos: VehiculoInterface[] = [];
  conductores: ConductorInterface[] = [];

  ngOnInit(): void {
    this.cargarRelaciones();
    this.inicializarFormulario();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.tiqueteService.getXid(Number(id)).subscribe({
        next: (tiquete) => {
          this.tiquete = tiquete;
          this.form.patchValue({
            id: tiquete.id,
            numeroTiquete: tiquete.numeroTiquete,
            fecha: tiquete.fecha,
            nombre: tiquete.nombre,
            conductor: tiquete.conductor?.id,
            vehiculo: tiquete.vehiculo?.id,
            ruta: tiquete.ruta?.id,
            pesoKilos: tiquete.pesoKilos,
            anticipo: tiquete.anticipo,
            encarpe: tiquete.encarpe,
            combustible: tiquete.combustible,
            reteica: tiquete.reteica,
            estado: tiquete.estado
          });
          this.recalcular(this.form.getRawValue());
        },
        error: (err) => console.error('Error al cargar tiquete:', err),
      });
    }
  }

  inicializarFormulario(): void {
    this.form = this.fb.group({
      id: [null],
      numeroTiquete: [0, Validators.required],
      fecha: [new Date().toISOString().substring(0, 10), Validators.required],
      nombre: ['', Validators.required],
      conductor: [null, Validators.required],
      vehiculo: [null, Validators.required],
      ruta: [null, Validators.required],
      pesoKilos: [0, Validators.required],
      pesoToneladas: [0],
      valorViaje: [0],
      anticipo: [0, Validators.required],
      saldoBruto: [0],
      encarpe: [0, Validators.required],
      combustible: [0, Validators.required],
      retencion: [0],
      reteica: [0],
      administracion: [0],
      saldoNeto: [0],
      estado: [true]
    });


  }

 cargarRelaciones(): void {
  this.rutaService.getTodos().subscribe(data => {
    this.rutas = data;

    // Ahora que rutas están cargadas, suscribimos y recalculamos
    this.suscribirRecalculos();

    const val = this.form.getRawValue();
    if (val.ruta) this.recalcular(val); // recalculo inicial solo si hay ruta
  });

  this.conductorService.getTodos().subscribe(data => (this.conductores = data));
  this.vehiculoService.getTodos().subscribe(data => (this.vehiculos = data));
}


suscribirRecalculos(): void {
  const camposClave = ['pesoKilos', 'anticipo', 'combustible', 'encarpe'];

  // Recalcular cuando cambian los campos clave
  camposClave.forEach(campo => {
    this.form.get(campo)?.valueChanges.subscribe(() => {
      const val = this.form.getRawValue();
      if (val.ruta) this.recalcular(val);
    });
  });


this.form.get('ruta')?.valueChanges.subscribe((rutaId) => {
  if (!rutaId) return;

  const val = this.form.getRawValue();
  const rutaSeleccionada = this.rutas.find(r => r.id === Number(rutaId));

  if (!rutaSeleccionada) {
    console.warn('Ruta no encontrada para ID:', rutaId);
    return;
  }

  this.recalcular(val);
});


}






  recalcular(val: any): void {
    if (!val.ruta) return;
const rutaSeleccionada = this.rutas.find(r => r.id === Number(val.ruta));
if (!rutaSeleccionada) return;

const valorTonelada = rutaSeleccionada.valorTonelada;

   // const rutaSeleccionada = this.rutas.find(r => r.id === val.ruta);
    //const valorTonelada = rutaSeleccionada?.valorTonelada ?? 0;

    const pesoToneladas = Number(val.pesoKilos) / 1000;
    const valorViaje = pesoToneladas * valorTonelada;
    const retencion = valorViaje * 0.01;
    //const reteica = valorViaje * 0.001;
    const administracion = valorViaje * 0.01;
    const saldoBruto = valorViaje - val.anticipo - val.combustible;
    const saldoNeto = saldoBruto - val.encarpe - retencion - administracion - val.reteica;

    this.form.patchValue({
      pesoToneladas,
      valorViaje,
      retencion,
      administracion,
      saldoBruto,
      saldoNeto
    }, { emitEvent: false });
    if (!rutaSeleccionada) {
  console.warn('Ruta no encontrada para ID:', val.ruta);
  return;
}

  }

  save(): void {
    this.recalcular(this.form.getRawValue());
    const val = this.form.getRawValue();

    const rutaSeleccionada = this.rutas.find(r => r.id === Number(val.ruta)) || null;
    const conductorSeleccionado = this.conductores.find(c => c.id === Number(val.conductor)) || null;
    const vehiculoSeleccionado = this.vehiculos.find(v => v.id === Number(val.vehiculo)) || null;

    const tiquete: TiqueteInterface = {
      id: val.id,
      numeroTiquete: val.numeroTiquete,
      fecha: val.fecha,
      nombre: val.nombre,
      conductor: conductorSeleccionado,
      vehiculo: vehiculoSeleccionado,
      ruta: rutaSeleccionada,
      pesoKilos: val.pesoKilos,
      pesoToneladas: val.pesoToneladas,
      valorViaje: val.valorViaje,
      anticipo: val.anticipo,
      saldoBruto: val.saldoBruto,
      encarpe: val.encarpe,
      combustible: val.combustible,
      retencion: val.retencion,
      reteica: val.reteica,
      administracion: val.administracion,
      saldoNeto: val.saldoNeto,
      estado: val.estado
    };

    console.log('Tiquete a enviar:', tiquete);

    const accion = this.tiquete
      ? this.tiqueteService.actualizar(tiquete.id!, tiquete)
      : this.tiqueteService.crear(tiquete);

    accion.subscribe({
      next: () => {
        console.log('Tiquete guardado correctamente');
        this.router.navigate(['/']);
      },
      error: (err) => console.error('Error al guardar tiquete:', err)
    });
  }
}
