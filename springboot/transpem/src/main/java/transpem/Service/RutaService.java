package transpem.Service;

import org.springframework.stereotype.Service;
import transpem.DTO.RutaDTO;
import transpem.Model.*;
import transpem.Repository.RutaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface RutaService {

    List<Ruta> findAll();
    Optional<Ruta> findById(Long id);

    void save(Ruta ruta);

    void deleteById(Long id);

}
