package transpem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transpem.Model.Tiquete;

import java.sql.Date;
import java.util.List;

public interface TiqueteRepository extends JpaRepository<Tiquete, Long> {

    List<Tiquete> findByFechaBetween(Date inicio, Date fin);
    List<Tiquete> findByConductorId(Long idConductor);
    List<Tiquete> findByVehiculoPlaca(String placa);

    @org.springframework.data.jpa.repository.Query("SELECT t FROM Tiquete t " +
           "LEFT JOIN t.ruta r " +
           "LEFT JOIN r.mina m " +
           "LEFT JOIN t.conductor c " +
           "WHERE (:fechaInicio IS NULL OR t.fecha >= :fechaInicio) " +
           "AND (:fechaFin IS NULL OR t.fecha <= :fechaFin) " +
           "AND (:idMina IS NULL OR m.id = :idMina) " +
           "AND (:idRuta IS NULL OR r.id = :idRuta) " +
           "AND (:idConductor IS NULL OR c.id = :idConductor) " +
           "AND (:valorMin IS NULL OR t.valorViaje >= :valorMin) " +
           "AND (:valorMax IS NULL OR t.valorViaje <= :valorMax)")
    List<Tiquete> findReporteTiquetes(
            @org.springframework.data.repository.query.Param("fechaInicio") java.sql.Date fechaInicio,
            @org.springframework.data.repository.query.Param("fechaFin") java.sql.Date fechaFin,
            @org.springframework.data.repository.query.Param("idMina") Long idMina,
            @org.springframework.data.repository.query.Param("idRuta") Long idRuta,
            @org.springframework.data.repository.query.Param("idConductor") Long idConductor,
            @org.springframework.data.repository.query.Param("valorMin") Long valorMin,
            @org.springframework.data.repository.query.Param("valorMax") Long valorMax
    );

}
