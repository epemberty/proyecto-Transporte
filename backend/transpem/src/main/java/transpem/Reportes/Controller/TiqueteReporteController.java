package transpem.Reportes.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import transpem.Reportes.DTO.TiqueteReporteDTO;
import transpem.Reportes.Service.TiqueteReporteService;

import java.util.List;

@RestController
@RequestMapping("/reportes/tiquetes")
public class TiqueteReporteController {

    private final TiqueteReporteService tiqueteReporteService;

    public TiqueteReporteController(TiqueteReporteService tiqueteReporteService) {
        this.tiqueteReporteService = tiqueteReporteService;
    }

    @GetMapping
    public List<TiqueteReporteDTO> obtenerReporte() {
        return tiqueteReporteService.generarReporte();
    }
}
