import { Component, OnInit, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { VehiculoService } from '../../services/vehiculo.service';
import { VehiculoInterface } from '../../interfaces/vehiculo.interface';

@Component({
  selector: 'app-vehiculo-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './vehiculo-form.component.html',
  styleUrls: []
})
export default class VehiculoFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private vehiculoService = inject(VehiculoService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  form?: FormGroup;
    vehiculo?: VehiculoInterface;


    ngOnInit(): void {
      const id= this.route.snapshot.paramMap.get('id');

      if (id) {
        this.vehiculoService.getXid(parseInt(id))
        .subscribe(vehiculo => {
          this.vehiculo= vehiculo;
          this.form= this.fb.nonNullable.group({
          id: [vehiculo.id,[Validators.required]],
          placa: [vehiculo.placa, [Validators.required]],
          marca: [vehiculo.marca, [Validators.required]],
          modelo: [vehiculo.modelo, [Validators.required]],
          vencimientoTecnomecanica: [vehiculo.vencimientoTecnomecanica, [Validators.required]],
          vencimientoSoat: [vehiculo.vencimientoSoat, [Validators.required]],
          vencimientoPoliza: [vehiculo.vencimientoPoliza, [Validators.required]],
          estado: [Boolean(vehiculo.estado),[Validators.required]],

        });

      })
    }else{
      this.form = this.fb.nonNullable.group({
          placa: ['', [Validators.required]],
          marca: ['', [Validators.required]],
          modelo: ['', [Validators.required]],
          vencimientoTecnomecanica: ['', [Validators.required]],
          vencimientoSoat: ['', [Validators.required]],
          vencimientoPoliza: ['', [Validators.required]],
          estado: [true,[Validators.required]],

      })
    }
  }


    save(): void {
      const vehiculoform= this.form?.getRawValue();

      if(this.vehiculo){
        this.vehiculoService.actualizar(this.vehiculo.id, vehiculoform)
        .subscribe(()=>{
          console.log('Vehiculo Actualizado Correctamente. ');
          this.router.navigate(['/']);
        });
      }else{
        this.vehiculoService.crear(vehiculoform).subscribe(()=>{
          console.log('Nuevo Vehiculo Creado Correctamente. ');
          this.router.navigate(['/']);
        });
      }
    }
  }
