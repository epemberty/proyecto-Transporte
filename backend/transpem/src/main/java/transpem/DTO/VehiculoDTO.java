package transpem.DTO;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehiculoDTO {
    private Long id;
    private String placa;
    private String marca;
    private Integer modelo;
    private Date vencimientoTecnomecanica;
    private Date vencimientoSoat;
    private Date vencimientoPoliza;
    private Boolean estado;

}
