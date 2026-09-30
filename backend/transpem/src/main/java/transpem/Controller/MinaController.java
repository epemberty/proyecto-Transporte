package transpem.Controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transpem.DTO.MinaDTO;
import transpem.DTO.MinaDTO;
import transpem.Model.Mina;
import transpem.Service.MinaService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/mina")


public class MinaController {
    @Autowired
    private MinaService minaService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Mina> minaOptional= minaService.findById(id);
        if (minaOptional.isPresent()){
            Mina mina= minaOptional.get();

            MinaDTO minaDTO = MinaDTO.builder()
                    .id(mina.getId())
                    .nombre(mina.getNombre())
                    .estado(mina.getEstado())
                    .build();
            return ResponseEntity.ok(minaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<MinaDTO> minaDTOList =minaService.findAll()
                .stream()
                .map(mina -> MinaDTO.builder()
                        .id(mina.getId())
                        .nombre(mina.getNombre())
                        .estado(mina.getEstado())
                        .build())
                .toList();
        return ResponseEntity.ok(minaDTOList);

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MinaDTO minaDTO) throws URISyntaxException {
        if (minaDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        minaService.save(Mina.builder()
                .nombre(minaDTO.getNombre())
                .estado(minaDTO.getEstado())
                .build());
        return ResponseEntity.created(new URI("api/v1/mina/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMina(@PathVariable Long id, @Valid @RequestBody MinaDTO minaDTO){
        Optional<Mina> minaOptional= minaService.findById(id);

        if (minaOptional.isPresent()){
            Mina mina= minaOptional.get();
            mina.setNombre(minaDTO.getNombre());
            mina.setEstado(minaDTO.getEstado());
            minaService.save(mina);
            return ResponseEntity.ok(mina);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mina no Encontrado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if (id!= null){
            this.minaService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }
        return ResponseEntity.badRequest().build();
    }





}
