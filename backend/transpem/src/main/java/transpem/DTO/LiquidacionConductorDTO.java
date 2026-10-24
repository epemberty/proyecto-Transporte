package transpem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 

public class LiquidacionConductorDTO {

        private Long idConductor;
        private Integer cedula;
        private String nombreConductor;
        private Long totalViajes; 
        //Cantidad de viajes realizados por el conductor//
        private Long totalToneladas;
        //Toneladas totales transportadas //
        private Long totalFlete;
        //Total facturado de los viajes//
        private Long totalComision;
        //Total a pagar al conductor por sus viajes//


}
