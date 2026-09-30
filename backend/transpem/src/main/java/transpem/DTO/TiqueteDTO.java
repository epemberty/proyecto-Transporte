package transpem.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import transpem.Model.Conductor;
import transpem.Model.Ruta;
import transpem.Model.Vehiculo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TiqueteDTO {
    private Long id;
    private Integer numeroTiquete;
    private Date fecha;
    private String nombre;
    private Conductor conductor;
    private Vehiculo vehiculo;
    private Ruta ruta;
    private Long pesoKilos;
    private Long pesoToneladas;
    private Long valorViaje;
    private Long anticipo;
    private Long saldoBruto;
    private Long encarpe;
    private Long combustible;
    private Long retencion;
    private Long reteica;
    private Long administracion;
    private Long saldoNeto;
    private Boolean estado;

}
