package transpem.Service;

import org.springframework.stereotype.Service;
import transpem.DTO.ContratoDTO;
import transpem.Model.Contrato;
import transpem.Repository.ContratoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface ContratoService {
    List<Contrato> findAll();
    Optional<Contrato> findById(Long id);

    void save(Contrato contrato);

    void deleteById(Long id);



}
