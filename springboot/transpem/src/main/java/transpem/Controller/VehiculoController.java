package transpem.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transpem.DTO.VehiculoDTO;
import transpem.DTO.VehiculoDTO;
import transpem.Model.Vehiculo;
import transpem.Service.VehiculoService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vehiculo")

public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Vehiculo> vehiculoOptional= vehiculoService.findById(id);
        if (vehiculoOptional.isPresent()){
            Vehiculo vehiculo= vehiculoOptional.get();

            VehiculoDTO vehiculoDTO = VehiculoDTO.builder()
                    .id(vehiculo.getId())
                    .placa(vehiculo.getPlaca())
                    .marca(vehiculo.getMarca())
                    .modelo(vehiculo.getModelo())
                    .vencimientoTecnomecanica(vehiculo.getVencimientoTecnomecanica())
                    .vencimientoSoat(vehiculo.getVencimientoSoat())
                    .vencimientoPoliza(vehiculo.getVencimientoPoliza())
                    .estado(vehiculo.getEstado())
                    .build();
            return ResponseEntity.ok(vehiculoDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<VehiculoDTO> vehiculoDTOList =vehiculoService.findAll()
                .stream()
                .map(vehiculo -> VehiculoDTO.builder()
                        .id(vehiculo.getId())
                        .placa(vehiculo.getPlaca())
                        .marca(vehiculo.getMarca())
                        .modelo(vehiculo.getModelo())
                        .vencimientoTecnomecanica(vehiculo.getVencimientoTecnomecanica())
                        .vencimientoSoat(vehiculo.getVencimientoSoat())
                        .vencimientoPoliza(vehiculo.getVencimientoPoliza())
                        .estado(vehiculo.getEstado())
                        .build())
                .toList();
        return ResponseEntity.ok(vehiculoDTOList);

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody VehiculoDTO vehiculoDTO) throws URISyntaxException {
        if (vehiculoDTO.getMarca().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        vehiculoService.save(Vehiculo.builder()
                .placa(vehiculoDTO.getPlaca())
                        .marca(vehiculoDTO.getMarca())
                        .modelo(vehiculoDTO.getModelo())
                        .vencimientoTecnomecanica(vehiculoDTO.getVencimientoTecnomecanica())
                        .vencimientoSoat(vehiculoDTO.getVencimientoSoat())
                        .vencimientoPoliza(vehiculoDTO.getVencimientoPoliza())
                .estado(vehiculoDTO.getEstado())
                .build());
        return ResponseEntity.created(new URI("api/v1/vehiculo/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehiculo(@PathVariable Long id, @Valid @RequestBody VehiculoDTO vehiculoDTO){
        Optional<Vehiculo> vehiculoOptional= vehiculoService.findById(id);

        if (vehiculoOptional.isPresent()){
            Vehiculo vehiculo= vehiculoOptional.get();
            vehiculo.setPlaca(vehiculoDTO.getPlaca());
            vehiculo.setMarca(vehiculoDTO.getMarca());
            vehiculo.setModelo(vehiculoDTO.getModelo());
            vehiculo.setVencimientoTecnomecanica(vehiculoDTO.getVencimientoTecnomecanica());
            vehiculo.setVencimientoSoat(vehiculoDTO.getVencimientoSoat());
            vehiculo.setVencimientoPoliza(vehiculoDTO.getVencimientoPoliza());
            vehiculo.setEstado(vehiculoDTO.getEstado());
            vehiculoService.save(vehiculo);
            return ResponseEntity.ok(vehiculo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehiculo no Encontrado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if (id!= null){
            this.vehiculoService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }
        return ResponseEntity.badRequest().build();
    }




}
