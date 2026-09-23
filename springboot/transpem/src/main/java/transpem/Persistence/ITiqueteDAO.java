package transpem.Persistence;

import transpem.Model.Conductor;
import transpem.Model.Tiquete;

import java.util.List;
import java.util.Optional;

public interface ITiqueteDAO {
    List<Tiquete> findAll();
    Optional<Tiquete> findById(Long id);

    void save(Tiquete tiquete);

    void deleteById(Long id);
    List<Tiquete> reporteTiquetes(java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idMina, Long idRuta, Long idConductor, Long valorMin, Long valorMax);
}
