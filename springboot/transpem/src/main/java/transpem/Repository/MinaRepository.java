package transpem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transpem.Model.Mina;

import java.util.List;

public interface MinaRepository extends JpaRepository<Mina, Long> {
    List<Mina> findByEstadoTrue();
}
