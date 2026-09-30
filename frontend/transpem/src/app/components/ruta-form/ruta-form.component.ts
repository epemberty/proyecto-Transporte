import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { RutaService } from '../../services/ruta.service';
import { MinaService } from '../../services/mina.service';
import { DestinoService } from '../../services/destino.service';
import { ContratoService } from '../../services/contrato.service';

import { RutaInterface } from '../../interfaces/ruta.interface';
import { MinaInterface } from '../../interfaces/mina.interface';
import { DestinoInterface } from '../../interfaces/destino.interface';
import { ContratoInterface } from '../../interfaces/contrato.interface';

@Component({
  selector: 'app-ruta-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './ruta-form.component.html',
  styleUrls: []
})
export default class RutaFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private rutaService = inject(RutaService);
  private minaService = inject(MinaService);
  private destinoService = inject(DestinoService);
  private contratoService = inject(ContratoService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  form!: FormGroup;
  ruta!: RutaInterface;
  minas: MinaInterface[]=[];
  destinos: DestinoInterface[]=[];
  contratos: ContratoInterface[]=[];



  ngOnInit(): void {

    this.form= this.fb.group({
    nombre: ['', Validators.required],
    mina: [null, Validators.required],
    destino: [null, Validators.required],
    contrato: [null, Validators.required],
    valorTonelada: [0, Validators.required],
    estado: [true, [Validators.required]],
  });



    const id = this.route.snapshot.paramMap.get('id');
    this.cargarRelaciones();

    if (id) {
      this.rutaService.getXid(Number(id)).subscribe({
        next: (ruta)=>{
          this.ruta= ruta;

          this.form = this.fb.group({
            id: [ruta.id,[Validators.required]],
            nombre: [ruta.nombre, [Validators.required]],
            mina: [ruta.mina?.id, [Validators.required]],
            destino: [ruta.destino?.id, [Validators.required]],
            contrato: [ruta.contrato?.id, [Validators.required]],
            valorTonelada: [ruta.valorTonelada, [Validators.required]],
            estado: [Boolean(ruta.estado), [Validators.required]],
  });
        },
         error: (err) => console.error('Error al cargar asignatura:', err),

      });
    } else{
      this.form= this.fb.group({
    nombre: ['', Validators.required],
    mina: [null, Validators.required],
    destino: [null, Validators.required],
    contrato: [null, Validators.required],
    valorTonelada: [0, Validators.required],
    estado: [true, [Validators.required]],
  });
    }
  }

  cargarRelaciones(): void {
    this.minaService.getTodos().subscribe(data => (this.minas = data));
    this.destinoService.getTodos().subscribe(data => (this.destinos = data));
    this.contratoService.getTodos().subscribe(data => (this.contratos = data));
  }

  save(): void {
   console.log('Método save ejecutado')
    const rutaFormData = this.form.getRawValue(); // Obtener datos del formulario

    console.log('Valor de mina del formulario:', rutaFormData.mina);
    console.log('Arreglo de minas disponibles:', this.minas);

    // Convertir el id del mina a número y buscar el objeto en el arreglo
    const minaSeleccionada = this.minas.find((mina) => {
     const minaIdFormulario= Number(rutaFormData.mina);
        console.log('Comparando:', mina.id, 'con', minaIdFormulario); // Comprobamos la comparación
        return mina.id === minaIdFormulario;
  });

  // Convertir el id de destino a número y buscar el objeto en el arreglo
    const destinoSeleccionado = this.destinos.find((destino) => {
     const destinoIdFormulario= Number(rutaFormData.destino);
        console.log('Comparando:', destino.id, 'con', destinoIdFormulario); // Comprobamos la comparación
        return destino.id === destinoIdFormulario;
  });

// Convertir el id del contrato a número y buscar el objeto en el arreglo
    const contratoSeleccionado = this.contratos.find((contrato) => {
     const contratoIdFormulario= Number(rutaFormData.contrato);
        console.log('Comparando:', contrato.id, 'con', contratoIdFormulario); // Comprobamos la comparación
        return contrato.id === contratoIdFormulario;
  });

    console.log('Mina seleccionada:', minaSeleccionada); // Verifica el objeto del área seleccionada
    console.log('Datos del formulario:', this.form.getRawValue()); // Verifica valores enviados

    // Construir la asignatura con el área seleccionada
    const ruta: RutaInterface = {
      id: rutaFormData.id, // Undefined si es nueva asignatura
      nombre: rutaFormData.nombre,
      mina: minaSeleccionada!, // La mina seleccionada ahora será el objeto correcto
      destino: destinoSeleccionado!, // El destino seleccionado ahora será el objeto correcto
      contrato: contratoSeleccionado!, // El contrato seleccionado ahora será el objeto correcto
      valorTonelada: Number(rutaFormData.valorTonelada),
      estado: rutaFormData.estado,

    };

    console.log('Nueva ruta para guardar:', ruta);

    if (this.ruta) {
      // Actualizar asignatura existente
      this.rutaService.actualizar(ruta.id!, ruta).subscribe({
        next: () => {
          console.log('Ruta actualizada correctamente.');
          this.router.navigate(['/']); // Redirige al listado de asignaturas
        }
       // error: (err) => {
        //  console.error('Error al actualizar asignatura:', err);
         // console.log('Headers:', err.headers);
         // console.log('Mensaje:', err.message);
        }

      );
    } else {
      // Crear nueva asignatura
      this.rutaService.crear(ruta).subscribe({
        next: () => {
          console.log('Nueva ruta creada correctamente.');
          this.router.navigate(['/']); // Redirige al listado de asignaturas
        },
        error: (err) => console.error('Error al crear ruta:', err),
      });
    }
  }
}
