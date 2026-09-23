package transpem.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transpem.Model.Ruta;
import transpem.Persistence.IRutaDAO;
import transpem.Service.RutaService;

import java.util.List;
import java.util.Optional;

@Service
public class RutaServiceImpl implements RutaService {

    @Autowired
    private IRutaDAO iRutaDAO;

    @Override
    public List<Ruta> findAll() {
        return iRutaDAO.findAll();
    }

    @Override
    public Optional<Ruta> findById(Long id) {
        return iRutaDAO.findById(id);
    }

    @Override
    public void save(Ruta ruta) {
        iRutaDAO.save(ruta);
    }

    @Override
    public void deleteById(Long id) {
            iRutaDAO.deleteById(id);
    }
}
