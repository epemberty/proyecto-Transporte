import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { ContratoService } from '../../services/contrato.service';
import { ContratoInterface } from '../../interfaces/contrato.interface';

@Component({
  selector: 'app-contrato-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './contrato-form.component.html',
  styleUrls: []
})
export default class ContratoFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private contratoService = inject(ContratoService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  form?: FormGroup;
  contrato?: ContratoInterface;



  ngOnInit(): void {
    const id= this.route.snapshot.paramMap.get('id');

    if (id) {
      this.contratoService.getXid(parseInt(id))
      .subscribe(contrato => {
        this.contrato= contrato;
        this.form= this.fb.nonNullable.group({
          id: [contrato.id,[Validators.required]],
          nombre: [contrato.nombre,[Validators.required]],
          estado: [true, [Validators.required]],
        });      })
    }else{
      this.form= this.fb.nonNullable.group({
          nombre: ['',[Validators.required]],
          estado: [true, [Validators.required]],
      })
    }
  }

  save(): void {
    const contratoform= this.form?.getRawValue();
    if(this.contrato){
      this.contratoService.actualizar(this.contrato.id, contratoform)
      .subscribe(()=>{
        console.log('Contrato Actualizado Correctamente. ');
        this.router.navigate(['/']);
      });
    }else{
      this.contratoService.crear(contratoform).subscribe(()=>{
        console.log('Nuevo Contrato Creado Correctamente. ');
        this.router.navigate(['/']);
      });
    }

  }

  cancelar(): void {
    this.router.navigate(['/contrato']);
  }
}
