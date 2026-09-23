package transpem.Service;

import transpem.Model.Destino;

import java.util.List;
import java.util.Optional;


public interface DestinoService {


    List<Destino> findAll();
    Optional<Destino> findById(Long id);

    void save(Destino destino);

    void deleteById(Long id);
}
