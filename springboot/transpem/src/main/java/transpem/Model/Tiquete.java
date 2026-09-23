package transpem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tiquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_tiquete")
    private Integer numeroTiquete;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conductor", nullable = false)
    private Conductor conductor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruta", nullable = false)
    private Ruta ruta;

    @Column(name = "p_kilos")
    private Long pesoKilos;

    @Column(name = "p_ton")
    private Long pesoToneladas;

    @Column(name = "v_viaje")
    private Long valorViaje;

    @Column(name = "anticipo")
    private Long anticipo;

    @Column(name = "s_bruto")
    private Long saldoBruto;

    @Column(name = "v_encarpe")
    private Long encarpe;

    @Column(name = "v_combustible")
    private Long combustible;

    @Column(name = "v_retencion")
    private Long retencion;

    @Column(name = "reteica")
    private Long reteica;



    @Column(name = "v_administracion")
    private Long administracion;

    @Column(name = "s_neto")
    private Long saldoNeto;

    @Column(name = "estado")
    private Boolean estado;
}
