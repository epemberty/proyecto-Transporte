package transpem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transpem.Model.Destino;

import java.util.List;

public interface DestinoRepository extends JpaRepository<Destino, Long> {
    List<Destino> findByNombreContainingIgnoreCase(String nombre);
}
