package transpem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transpem.Model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByPlaca(String placa);
    List<Vehiculo> findByEstadoTrue();


}
