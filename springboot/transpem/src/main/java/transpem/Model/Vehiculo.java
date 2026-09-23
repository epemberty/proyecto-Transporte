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
public class Vehiculo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "marca")
    private String marca;

    @Column(name = "modelo")
    private Integer modelo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "v_tecno")
    private Date vencimientoTecnomecanica;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "v_soat")
    private Date vencimientoSoat;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "v_poliza")
    private Date vencimientoPoliza;

    @OneToMany(mappedBy = "vehiculo", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Tiquete> tiquetes;

    @Column(name = "estado")
    private Boolean estado;


}
