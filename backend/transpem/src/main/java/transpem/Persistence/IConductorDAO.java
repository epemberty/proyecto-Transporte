package transpem.Persistence;

import transpem.Model.Conductor;

import java.util.List;
import java.util.Optional;

public interface IConductorDAO {

    List<Conductor> findAll();
    Optional<Conductor> findById(Long id);

    void save(Conductor conductor);

    void deleteById(Long id);

}
