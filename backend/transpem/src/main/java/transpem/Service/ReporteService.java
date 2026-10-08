package transpem.Service;

import java.sql.Date;
import java.util.List;
import transpem.DTO.TiqueteReporteDTO;


public interface ReporteService {

    List<TiqueteReporteDTO> 
    reporteTiquetes(
        Date fechaInicio, 
        Date fechaFin,
        Long idMina,
        Long idRuta,
        Long idConductor,
        Long valorMin,
        Long valorMax
    );


}
