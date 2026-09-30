package transpem.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transpem.DTO.ContratoDTO;
import transpem.DTO.ContratoDTO;
import transpem.Model.Contrato;
import transpem.Service.ContratoService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/contrato")

public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Contrato> contratoOptional= contratoService.findById(id);
        if (contratoOptional.isPresent()){
            Contrato contrato= contratoOptional.get();

            ContratoDTO contratoDTO = ContratoDTO.builder()
                    .id(contrato.getId())
                    .nombre(contrato.getNombre())
                    .estado(contrato.getEstado())
                    .build();
            return ResponseEntity.ok(contratoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<ContratoDTO> contratoDTOList =contratoService.findAll()
                .stream()
                .map(contrato -> ContratoDTO.builder()
                        .id(contrato.getId())
                        .nombre(contrato.getNombre())
                        .estado(contrato.getEstado())
                        .build())
                .toList();
        return ResponseEntity.ok(contratoDTOList);

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ContratoDTO contratoDTO) throws URISyntaxException {
        if (contratoDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        contratoService.save(Contrato.builder()
                .nombre(contratoDTO.getNombre())
                .estado(contratoDTO.getEstado())
                .build());
        return ResponseEntity.created(new URI("api/v1/contrato/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateContrato(@PathVariable Long id, @Valid @RequestBody ContratoDTO contratoDTO){
        Optional<Contrato> contratoOptional= contratoService.findById(id);

        if (contratoOptional.isPresent()){
            Contrato contrato= contratoOptional.get();
            contrato.setNombre(contratoDTO.getNombre());
            contrato.setEstado(contratoDTO.getEstado());
            contratoService.save(contrato);
            return ResponseEntity.ok(contrato);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato no Encontrado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if (id!= null){
            this.contratoService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }
        return ResponseEntity.badRequest().build();
    }

}
