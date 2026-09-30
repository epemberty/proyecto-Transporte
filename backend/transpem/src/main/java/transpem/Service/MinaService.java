package transpem.Service;

import org.springframework.stereotype.Service;
import transpem.DTO.MinaDTO;
import transpem.Model.Conductor;
import transpem.Model.Mina;
import transpem.Repository.MinaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface MinaService {

    List<Mina> findAll();
    Optional<Mina> findById(Long id);

    void save(Mina mina);

    void deleteById(Long id);

}
