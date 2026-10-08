package transpem.Controller;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transpem.DTO.TiqueteReporteDTO;
import transpem.Service.ReporteService;

@CrossOrigin 
@RestController
@RequestMapping("/api/v1/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/tiquetes")
        public ResponseEntity<List<TiqueteReporteDTO>>
        reporteTiquetes(
            @RequestParam(name = "fechaInicio", required = false) Date fechaInicio,
            @RequestParam(name = "fechaFin", required = false) Date fechaFin,
            @RequestParam(name = "idMina", required = false) Long idMina,
            @RequestParam(name = "idRuta", required = false) Long idRuta,
            @RequestParam(name = "idConductor", required = false) Long idConductor,
            @RequestParam(name = "valorMin", required = false) Long valorMin,
            @RequestParam(name = "valorMax", required = false) Long valorMax)
            {List <TiqueteReporteDTO>
                listaReportes = 
                reporteService.reporteTiquetes(
                    fechaInicio,
                    fechaFin,
                    idMina,
                    idRuta,
                    idConductor,
                    valorMin,
                    valorMax);
                    return ResponseEntity.ok(listaReportes);
            }}
    
