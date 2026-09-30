package transpem.Persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transpem.Model.Ruta;
import transpem.Persistence.IRutaDAO;
import transpem.Repository.RutaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class RutaDAOImpl implements IRutaDAO {

    @Autowired
    private RutaRepository rutaRepository;

    @Override
    public List<Ruta> findAll() {
        return (List<Ruta>) rutaRepository.findAll();
    }

    @Override
    public Optional<Ruta> findById(Long id) {
        return rutaRepository.findById(id);
    }

    @Override
    public void save(Ruta ruta) {
        rutaRepository.save(ruta);
    }

    @Override
    public void deleteById(Long id) {
        rutaRepository.deleteById(id);
    }
}
