package transpem.Persistence;

import transpem.Model.Conductor;
import transpem.Model.Destino;

import java.util.List;
import java.util.Optional;

public interface IDestinoDAO {

    List<Destino> findAll();
    Optional<Destino> findById(Long id);

    void save(Destino destino);

    void deleteById(Long id);
}
