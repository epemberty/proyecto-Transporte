package transpem.Service;

import org.springframework.stereotype.Service;
import transpem.DTO.VehiculoDTO;
import transpem.Model.Conductor;
import transpem.Model.Vehiculo;
import transpem.Repository.VehiculoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface VehiculoService {

    List<Vehiculo> findAll();
    Optional<Vehiculo> findById(Long id);

    void save(Vehiculo vehiculo);

    void deleteById(Long id);

}
