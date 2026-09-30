package transpem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transpem.Model.Contrato;

import java.util.List;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findByEstadoTrue();
}
