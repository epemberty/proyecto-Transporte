package transpem.Service;

import org.springframework.stereotype.Service;
import transpem.DTO.ConductorDTO;
import transpem.Model.Conductor;
import transpem.Repository.ConductorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface ConductorService {


    List<Conductor> findAll();
    Optional<Conductor> findById(Long id);

    void save(Conductor conductor);

    void deleteById(Long id);
}
