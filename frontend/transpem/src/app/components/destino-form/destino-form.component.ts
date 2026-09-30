import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

import { DestinoService } from '../../services/destino.service';
import { DestinoInterface } from '../../interfaces/destino.interface';

@Component({
  selector: 'app-destino-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './destino-form.component.html',
  styleUrls: []
})
export default class DestinoFormComponent implements OnInit {
  private fb = inject(FormBuilder);
  private destinoService = inject(DestinoService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

 form?: FormGroup;
   destino?: DestinoInterface;


   ngOnInit(): void {
     const id= this.route.snapshot.paramMap.get('id');

     if (id) {
       this.destinoService.getXid(parseInt(id))
       .subscribe(destino => {
         this.destino= destino;
         this.form= this.fb.nonNullable.group({
           id: [destino.id,[Validators.required]],
           nombre: [destino.nombre,[Validators.required]],
           estado: [Boolean(destino.estado),[Validators.required]],

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
     const destinoform= this.form?.getRawValue();

     if(this.destino){
       this.destinoService.actualizar(this.destino.id, destinoform)
       .subscribe(()=>{
         console.log('Destino Actualizado Correctamente. ');
         this.router.navigate(['/']);
       });
     }else{
       this.destinoService.crear(destinoform).subscribe(()=>{
         console.log('Nuevo Destino Creado Correctamente. ');
         this.router.navigate(['/']);
       });
     }
   }
 }
