package transpem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConductorDTO {
    private Long id;
    private Integer cedula;
    private String nombre;
    private String apellido;
    private Boolean estado;

}
