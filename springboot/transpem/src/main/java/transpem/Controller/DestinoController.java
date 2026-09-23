package transpem.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transpem.DTO.DestinoDTO;
import transpem.DTO.DestinoDTO;
import transpem.Model.Destino;
import transpem.Service.DestinoService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/destino")

public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Destino> destinoOptional= destinoService.findById(id);
        if (destinoOptional.isPresent()){
            Destino destino= destinoOptional.get();

            DestinoDTO destinoDTO = DestinoDTO.builder()
                    .id(destino.getId())
                    .nombre(destino.getNombre())
                   .estado(destino.getEstado())
                    .build();
            return ResponseEntity.ok(destinoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<DestinoDTO> destinoDTOList =destinoService.findAll()
                .stream()
                .map(destino -> DestinoDTO.builder()
                        .id(destino.getId())
                        .nombre(destino.getNombre())
                        .estado(destino.getEstado())
                        .build())
                .toList();
        return ResponseEntity.ok(destinoDTOList);

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody DestinoDTO destinoDTO) throws URISyntaxException {
        if (destinoDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        destinoService.save(Destino.builder()
                .nombre(destinoDTO.getNombre())
                .estado(destinoDTO.getEstado())
                .build());
        return ResponseEntity.created(new URI("api/v1/destino/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDestino(@PathVariable Long id, @Valid @RequestBody DestinoDTO destinoDTO){
        Optional<Destino> destinoOptional= destinoService.findById(id);

        if (destinoOptional.isPresent()){
            Destino destino= destinoOptional.get();
            destino.setNombre(destinoDTO.getNombre());
            destino.setEstado(destinoDTO.getEstado());
            destinoService.save(destino);
            return ResponseEntity.ok(destino);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino no Encontrado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if (id!= null){
            this.destinoService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }
        return ResponseEntity.badRequest().build();
    }



}
