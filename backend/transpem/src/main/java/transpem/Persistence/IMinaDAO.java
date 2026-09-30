package transpem.Persistence;

import transpem.Model.Conductor;
import transpem.Model.Mina;

import java.util.List;
import java.util.Optional;

public interface IMinaDAO {
    List<Mina> findAll();
    Optional<Mina> findById(Long id);

    void save(Mina mina);

    void deleteById(Long id);

}
