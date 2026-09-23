package transpem.Persistence;

import transpem.Model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface IVehiculoDAO {

    List<Vehiculo> findAll();
    Optional<Vehiculo> findById(Long id);

    void save(Vehiculo vehiculo);

    void deleteById(Long id);
}
