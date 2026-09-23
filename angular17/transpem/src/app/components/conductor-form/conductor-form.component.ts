import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { ConductorService } from '../../services/conductor.service';
import { ConductorInterface } from '../../interfaces/conductor.interface';

@Component({
  selector: 'app-conductor-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './conductor-form.component.html',
  styleUrls: []
})
export default class ConductorFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private conductorService = inject(ConductorService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  form?: FormGroup;
  conductor?: ConductorInterface;


  ngOnInit(): void {
    const id= this.route.snapshot.paramMap.get('id');

    if (id) {
      this.conductorService.getXid(parseInt(id))
      .subscribe(conductor => {
        this.conductor= conductor;
        this.form= this.fb.nonNullable.group({
          id: [conductor.id,[Validators.required]],
          nombre: [conductor.nombre,[Validators.required]],
          apellido: [conductor.apellido,[Validators.required]],
          cedula: [conductor.cedula,[Validators.required]],
          estado: [Boolean(conductor.estado),[Validators.required]],

      });

    })
  }else{
    this.form = this.fb.nonNullable.group({
        nombre: ['',[Validators.required]],
          apellido: ['',[Validators.required]],
          cedula: [0,[Validators.required]],
          estado: [true,[Validators.required]],
    })
  }
}


  save(): void {
    const conductorform= this.form?.getRawValue();

    if(this.conductor){
      this.conductorService.actualizar(this.conductor.id, conductorform)
      .subscribe(()=>{
        console.log('Conductor Actualizado Correctamente. ');
        this.router.navigate(['/']);
      });
    }else{
      this.conductorService.crear(conductorform).subscribe(()=>{
        console.log('Nuevo Conductor Creado Correctamente. ');
        this.router.navigate(['/']);
      });
    }
  }
}
