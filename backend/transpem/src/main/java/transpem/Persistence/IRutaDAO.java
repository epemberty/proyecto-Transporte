package transpem.Persistence;

import transpem.Model.Conductor;
import transpem.Model.Ruta;

import java.util.List;
import java.util.Optional;

public interface IRutaDAO {
    List<Ruta> findAll();
    Optional<Ruta> findById(Long id);

    void save(Ruta ruta);

    void deleteById(Long id);

}
