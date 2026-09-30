package transpem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transpem.DTO.ConductorDTO;
import transpem.Model.Conductor;
import transpem.Service.ConductorService;
import jakarta.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/conductor")

public class ConductorController {

    @Autowired
    private ConductorService conductorService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Conductor> conductorOptional= conductorService.findById(id);
        if (conductorOptional.isPresent()){
            Conductor conductor= conductorOptional.get();

            ConductorDTO conductorDTO = ConductorDTO.builder()
                    .id(conductor.getId())
                    .nombre(conductor.getNombre())
                    .apellido(conductor.getApellido())
                    .cedula(conductor.getCedula())
                    .estado(conductor.getEstado())
                    .build();
            return ResponseEntity.ok(conductorDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<ConductorDTO> conductorDTOList =conductorService.findAll()
                .stream()
                .map(conductor -> ConductorDTO.builder()
                        .id(conductor.getId())
                        .nombre(conductor.getNombre())
                        .apellido(conductor.getApellido())
                        .cedula(conductor.getCedula())
                        .estado(conductor.getEstado())
                        .build())
                .toList();
        return ResponseEntity.ok(conductorDTOList);

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ConductorDTO conductorDTO) throws URISyntaxException {
        if (conductorDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        conductorService.save(Conductor.builder()
                .nombre(conductorDTO.getNombre())
                .apellido(conductorDTO.getApellido())
                .cedula(conductorDTO.getCedula())
                .estado(conductorDTO.getEstado())
                .build());
        return ResponseEntity.created(new URI("api/v1/conductor/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateConductor(@PathVariable Long id, @Valid @RequestBody ConductorDTO conductorDTO){
        Optional<Conductor> conductorOptional= conductorService.findById(id);

        if (conductorOptional.isPresent()){
            Conductor conductor= conductorOptional.get();
            conductor.setNombre(conductorDTO.getNombre());
            conductor.setApellido(conductorDTO.getApellido());
            conductor.setCedula(conductorDTO.getCedula());
            conductor.setEstado(conductorDTO.getEstado());
            conductorService.save(conductor);
            return ResponseEntity.ok(conductor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conductor no Encontrado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if (id!= null){
            this.conductorService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }
        return ResponseEntity.badRequest().build();
    }



}
