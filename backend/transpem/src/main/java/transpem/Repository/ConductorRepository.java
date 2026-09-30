package transpem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transpem.Model.Conductor;

import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long > {
    Optional<Conductor> findByCedula(Integer cedula);


}
