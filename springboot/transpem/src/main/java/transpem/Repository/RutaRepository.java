package transpem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transpem.Model.Ruta;

import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {

    List<Ruta> findByContratoId(Long id);
    List<Ruta> findByDestinoId(Long id);
    List<Ruta> findByMinaId(Long id);

}
