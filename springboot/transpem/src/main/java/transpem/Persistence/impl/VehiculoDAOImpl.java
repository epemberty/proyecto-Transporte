package transpem.Persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transpem.Model.Vehiculo;
import transpem.Persistence.IVehiculoDAO;
import transpem.Repository.VehiculoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class VehiculoDAOImpl  implements IVehiculoDAO {


    @Autowired
    private VehiculoRepository vehiculoRepository;


    @Override
    public List<Vehiculo> findAll() {
        return (List<Vehiculo>) vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return vehiculoRepository.findById(id);
    }

    @Override
    public void save(Vehiculo vehiculo) {
            vehiculoRepository.save(vehiculo);
    }

    @Override
    public void deleteById(Long id) {
        vehiculoRepository.deleteById(id);
    }
}
