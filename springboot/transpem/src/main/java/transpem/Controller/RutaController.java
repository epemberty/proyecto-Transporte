package transpem.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transpem.DTO.RutaDTO;
import transpem.DTO.RutaDTO;
import transpem.Model.Ruta;
import transpem.Service.RutaService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/ruta")

public class RutaController {

    @Autowired
    private RutaService rutaService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Ruta> rutaOptional= rutaService.findById(id);
        if (rutaOptional.isPresent()){
            Ruta ruta= rutaOptional.get();

            RutaDTO rutaDTO = RutaDTO.builder()
                    .id(ruta.getId())
                    .nombre(ruta.getNombre())
                    .mina(ruta.getMina())
                    .destino(ruta.getDestino())
                    .contrato(ruta.getContrato())
                    .valorTonelada(ruta.getValorTonelada())
                    .estado(ruta.getEstado())
                    .build();
            return ResponseEntity.ok(rutaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<RutaDTO> rutaDTOList =rutaService.findAll()
                .stream()
                .map(ruta -> RutaDTO.builder()
                        .id(ruta.getId())
                        .nombre(ruta.getNombre())
                        .mina(ruta.getMina())
                        .destino(ruta.getDestino())
                        .contrato(ruta.getContrato())
                        .valorTonelada(ruta.getValorTonelada())
                        .estado(ruta.getEstado())
                        .build())
                .toList();
        return ResponseEntity.ok(rutaDTOList);

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RutaDTO rutaDTO) throws URISyntaxException {
        if (rutaDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        rutaService.save(Ruta.builder()
                .nombre(rutaDTO.getNombre())
                        .mina(rutaDTO.getMina())
                        .destino(rutaDTO.getDestino())
                        .contrato(rutaDTO.getContrato())
                        .valorTonelada(rutaDTO.getValorTonelada())
                .estado(rutaDTO.getEstado())
                .build());
        return ResponseEntity.created(new URI("api/v1/ruta/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRuta(@PathVariable Long id, @Valid @RequestBody RutaDTO rutaDTO){
        Optional<Ruta> rutaOptional= rutaService.findById(id);

        if (rutaOptional.isPresent()){
            Ruta ruta= rutaOptional.get();
            ruta.setNombre(rutaDTO.getNombre());
            ruta.setMina(rutaDTO.getMina());
            ruta.setDestino(rutaDTO.getDestino());
            ruta.setContrato(rutaDTO.getContrato());
            ruta.setValorTonelada(rutaDTO.getValorTonelada());
            ruta.setEstado(rutaDTO.getEstado());
            rutaService.save(ruta);
            return ResponseEntity.ok(ruta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ruta no Encontrada");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if (id!= null){
            this.rutaService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }
        return ResponseEntity.badRequest().build();
    }





}
