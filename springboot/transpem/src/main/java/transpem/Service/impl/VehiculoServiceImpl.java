package transpem.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transpem.Model.Vehiculo;
import transpem.Persistence.IVehiculoDAO;
import transpem.Service.VehiculoService;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {


    @Autowired
    private IVehiculoDAO iVehiculoDAO;
    @Override
    public List<Vehiculo> findAll() {
        return iVehiculoDAO.findAll();
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return iVehiculoDAO.findById(id);
    }

    @Override
    public void save(Vehiculo vehiculo) {
        iVehiculoDAO.save(vehiculo);
    }

    @Override
    public void deleteById(Long id) {
      iVehiculoDAO.deleteById(id);
    }
}
