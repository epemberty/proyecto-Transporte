package transpem.Persistence;


import transpem.Model.Contrato;

import java.util.List;
import java.util.Optional;

public interface IContratoDAO {

    List<Contrato> findAll();
    Optional<Contrato> findById(Long id);

    void save(Contrato contrato);

    void deleteById(Long id);
}
