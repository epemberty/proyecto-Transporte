package transpem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import transpem.Model.Contrato;
import transpem.Model.Destino;
import transpem.Model.Mina;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RutaDTO {
    private Long id;
    private String nombre;
    private Mina mina;
    private Destino destino;
    private Contrato contrato;
    private Double valorTonelada;
    private Boolean estado;

}
