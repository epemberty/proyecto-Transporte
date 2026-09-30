import { Component, OnInit, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule
} from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { MinaService } from '../../services/mina.service';
import { MinaInterface } from '../../interfaces/mina.interface';

@Component({
  selector: 'app-mina-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './mina-form.component.html',
  styleUrls: []
})
export default class MinaFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private minaService = inject(MinaService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  form?: FormGroup;
  mina?: MinaInterface;


    ngOnInit(): void {
      const id= this.route.snapshot.paramMap.get('id');

      if (id) {
        this.minaService.getXid(parseInt(id))
        .subscribe(mina => {
          this.mina= mina;
          this.form= this.fb.nonNullable.group({
            id: [mina.id,[Validators.required]],
            nombre: [mina.nombre,[Validators.required]],
            estado: [Boolean(mina.estado),[Validators.required]],

        });

      })
    }else{
      this.form = this.fb.nonNullable.group({
        nombre: ['',[Validators.required]],
        estado: [true,[Validators.required]],
      })
    }
  }


    save(): void {
      const minaform= this.form?.getRawValue();

      if(this.mina){
        this.minaService.actualizar(this.mina.id, minaform)
        .subscribe(()=>{
          console.log('Mina Actualizada Correctamente. ');
          this.router.navigate(['/']);
        });
      }else{
        this.minaService.crear(minaform).subscribe(()=>{
          console.log('Nueva Mina Creada Correctamente. ');
          this.router.navigate(['/']);
        });
      }
    }
  }
