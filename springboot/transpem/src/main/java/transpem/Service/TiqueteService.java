package transpem.Service;

import org.springframework.stereotype.Service;
import transpem.DTO.TiqueteDTO;
import transpem.Model.Tiquete;
import transpem.Repository.TiqueteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface TiqueteService {


    List<Tiquete> findAll();
    Optional<Tiquete> findById(Long id);

    void save(Tiquete tiquete);

    void deleteById(Long id);
    List<Tiquete> reporteTiquetes(java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idMina, Long idRuta, Long idConductor, Long valorMin, Long valorMax);
}
