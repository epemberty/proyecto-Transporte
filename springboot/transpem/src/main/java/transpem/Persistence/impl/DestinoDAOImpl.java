package transpem.Persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transpem.Model.Destino;
import transpem.Persistence.IDestinoDAO;
import transpem.Repository.DestinoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class DestinoDAOImpl implements IDestinoDAO {

    @Autowired
    private DestinoRepository destinoRepository;

    @Override
    public List<Destino> findAll() {
        return (List<Destino>) destinoRepository.findAll();
    }

    @Override
    public Optional<Destino> findById(Long id) {
        return destinoRepository.findById(id);
    }

    @Override
    public void save(Destino destino) {
        destinoRepository.save(destino);
    }

    @Override
    public void deleteById(Long id) {
        destinoRepository.deleteById(id);
    }
}
