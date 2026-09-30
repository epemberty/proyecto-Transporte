package transpem.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transpem.DTO.TiqueteDTO;
import transpem.DTO.TiqueteDTO;
import transpem.Model.Tiquete;
import transpem.Service.TiqueteService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tiquete")

public class TiqueteController {

    @Autowired
    private TiqueteService tiqueteService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Tiquete> tiqueteOptional= tiqueteService.findById(id);
        if (tiqueteOptional.isPresent()){
            Tiquete tiquete= tiqueteOptional.get();

            TiqueteDTO tiqueteDTO = TiqueteDTO.builder()
                    .id(tiquete.getId())
                    .numeroTiquete(tiquete.getNumeroTiquete())
                    .fecha(tiquete.getFecha())
                    .nombre(tiquete.getNombre())
                    .conductor(tiquete.getConductor())
                    .vehiculo(tiquete.getVehiculo())
                    .ruta(tiquete.getRuta())
                    .pesoKilos(tiquete.getPesoKilos())
                    .pesoToneladas(tiquete.getPesoToneladas())
                    .valorViaje(tiquete.getValorViaje())
                    .anticipo(tiquete.getAnticipo())
                    .saldoBruto(tiquete.getSaldoBruto())
                    .encarpe(tiquete.getEncarpe())
                    .combustible(tiquete.getCombustible())
                    .retencion(tiquete.getRetencion())
                    .reteica(tiquete.getReteica())
                    .administracion(tiquete.getAdministracion())
                    .saldoNeto(tiquete.getSaldoNeto())
                    .estado(tiquete.getEstado())
                    .build();
            return ResponseEntity.ok(tiqueteDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<TiqueteDTO> tiqueteDTOList =tiqueteService.findAll()
                .stream()
                .map(tiquete -> TiqueteDTO.builder()
                        .id(tiquete.getId())
                        .numeroTiquete(tiquete.getNumeroTiquete())
                        .fecha(tiquete.getFecha())
                        .nombre(tiquete.getNombre())
                        .conductor(tiquete.getConductor())
                        .vehiculo(tiquete.getVehiculo())
                        .ruta(tiquete.getRuta())
                        .pesoKilos(tiquete.getPesoKilos())
                        .pesoToneladas(tiquete.getPesoToneladas())
                        .valorViaje(tiquete.getValorViaje())
                        .anticipo(tiquete.getAnticipo())
                        .saldoBruto(tiquete.getSaldoBruto())
                        .encarpe(tiquete.getEncarpe())
                        .combustible(tiquete.getCombustible())
                        .retencion(tiquete.getRetencion())
                        .reteica(tiquete.getReteica())
                        .administracion(tiquete.getAdministracion())
                        .saldoNeto(tiquete.getSaldoNeto())
                        .estado(tiquete.getEstado())
                        .build())
                .toList();
        return ResponseEntity.ok(tiqueteDTOList);

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TiqueteDTO tiqueteDTO) throws URISyntaxException {
        if (tiqueteDTO.getNombre().isBlank()){
            return ResponseEntity.badRequest().build();
        }

        tiqueteService.save(Tiquete.builder()
                        .numeroTiquete(tiqueteDTO.getNumeroTiquete())
                        .fecha(tiqueteDTO.getFecha())
                        .nombre(tiqueteDTO.getNombre())
                        .conductor(tiqueteDTO.getConductor())
                        .vehiculo(tiqueteDTO.getVehiculo())
                        .ruta(tiqueteDTO.getRuta())
                        .pesoKilos(tiqueteDTO.getPesoKilos())
                        .pesoToneladas(tiqueteDTO.getPesoToneladas())
                        .valorViaje(tiqueteDTO.getValorViaje())
                        .anticipo(tiqueteDTO.getAnticipo())
                        .saldoBruto(tiqueteDTO.getSaldoBruto())
                        .encarpe(tiqueteDTO.getEncarpe())
                        .combustible(tiqueteDTO.getCombustible())
                        .retencion(tiqueteDTO.getRetencion())
                        .reteica(tiqueteDTO.getReteica())
                        .administracion(tiqueteDTO.getAdministracion())
                        .saldoNeto(tiqueteDTO.getSaldoNeto())
                .estado(tiqueteDTO.getEstado())
                .build());
        return ResponseEntity.created(new URI("api/v1/tiquete/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTiquete(@PathVariable Long id, @Valid @RequestBody TiqueteDTO tiqueteDTO){
        Optional<Tiquete> tiqueteOptional= tiqueteService.findById(id);

        if (tiqueteOptional.isPresent()){
            Tiquete tiquete= tiqueteOptional.get();
            tiquete.setNumeroTiquete(tiqueteDTO.getNumeroTiquete());
            tiquete.setFecha(tiqueteDTO.getFecha());
            tiquete.setNombre(tiqueteDTO.getNombre());
            tiquete.setConductor(tiqueteDTO.getConductor());
            tiquete.setVehiculo(tiqueteDTO.getVehiculo());
            tiquete.setRuta(tiqueteDTO.getRuta());
            tiquete.setPesoKilos(tiqueteDTO.getPesoKilos());
            tiquete.setPesoToneladas(tiqueteDTO.getPesoToneladas());
            tiquete.setValorViaje(tiqueteDTO.getValorViaje());
            tiquete.setAnticipo(tiqueteDTO.getAnticipo());
            tiquete.setSaldoBruto(tiqueteDTO.getSaldoBruto());
            tiquete.setEncarpe(tiqueteDTO.getEncarpe());
            tiquete.setCombustible(tiqueteDTO.getCombustible());
            tiquete.setRetencion(tiqueteDTO.getRetencion());
            tiquete.setReteica(tiqueteDTO.getReteica());
            tiquete.setAdministracion(tiqueteDTO.getAdministracion());
            tiquete.setSaldoNeto(tiqueteDTO.getSaldoNeto());
            tiquete.setEstado(tiqueteDTO.getEstado());
            tiqueteService.save(tiquete);
            return ResponseEntity.ok(tiquete);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tiquete no Encontrada");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        if (id!= null){
            this.tiqueteService.deleteById(id);
            return ResponseEntity.ok("Registro Eliminado");
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/reportes")
    public ResponseEntity<?> generarReporte(
            @RequestParam(name = "fechaInicio", required = false) java.sql.Date fechaInicio,
            @RequestParam(name = "fechaFin", required = false) java.sql.Date fechaFin,
            @RequestParam(name = "idMina", required = false) Long idMina,
            @RequestParam(name = "idRuta", required = false) Long idRuta,
            @RequestParam(name = "idConductor", required = false) Long idConductor,
            @RequestParam(name = "valorMin", required = false) Long valorMin,
            @RequestParam(name = "valorMax", required = false) Long valorMax) {
        
        List<TiqueteDTO> reportesDTO = tiqueteService.reporteTiquetes(fechaInicio, fechaFin, idMina, idRuta, idConductor, valorMin, valorMax)
                .stream()
                .map(tiquete -> TiqueteDTO.builder()
                        .id(tiquete.getId())
                        .numeroTiquete(tiquete.getNumeroTiquete())
                        .fecha(tiquete.getFecha())
                        .nombre(tiquete.getNombre())
                        .conductor(tiquete.getConductor())
                        .vehiculo(tiquete.getVehiculo())
                        .ruta(tiquete.getRuta())
                        .pesoKilos(tiquete.getPesoKilos())
                        .pesoToneladas(tiquete.getPesoToneladas())
                        .valorViaje(tiquete.getValorViaje())
                        .anticipo(tiquete.getAnticipo())
                        .saldoBruto(tiquete.getSaldoBruto())
                        .encarpe(tiquete.getEncarpe())
                        .combustible(tiquete.getCombustible())
                        .retencion(tiquete.getRetencion())
                        .reteica(tiquete.getReteica())
                        .administracion(tiquete.getAdministracion())
                        .saldoNeto(tiquete.getSaldoNeto())
                        .estado(tiquete.getEstado())
                        .build())
                .toList();
        
        return ResponseEntity.ok(reportesDTO);
    }

}
