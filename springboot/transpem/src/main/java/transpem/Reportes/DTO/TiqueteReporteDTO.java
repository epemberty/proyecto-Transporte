package transpem.Reportes.DTO;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TiqueteReporteDTO {
    private Long idTiquete;
    private String nombreConductor;
    private String placaVehiculo;
    private String origen;
    private String destino;
    private LocalDate fecha;
    private Double peso;
}
