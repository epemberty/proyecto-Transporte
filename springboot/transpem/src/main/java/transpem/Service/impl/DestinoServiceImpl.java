package transpem.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transpem.Model.Destino;
import transpem.Persistence.IDestinoDAO;
import transpem.Service.DestinoService;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoServiceImpl implements DestinoService {

    @Autowired
    private IDestinoDAO iDestinoDAO;

    @Override
    public List<Destino> findAll() {
        return iDestinoDAO.findAll();
    }

    @Override
    public Optional<Destino> findById(Long id) {
        return iDestinoDAO.findById(id);
    }

    @Override
    public void save(Destino destino) {
        iDestinoDAO.save(destino);
    }

    @Override
    public void deleteById(Long id) {
                iDestinoDAO.deleteById(id);
    }
}
