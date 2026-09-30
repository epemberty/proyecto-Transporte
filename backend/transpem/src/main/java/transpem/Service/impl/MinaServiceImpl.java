package transpem.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transpem.Model.Mina;
import transpem.Persistence.IMinaDAO;
import transpem.Service.MinaService;

import java.util.List;
import java.util.Optional;

@Service
public class MinaServiceImpl implements MinaService {

    @Autowired
    private IMinaDAO iMinaDAO;


    @Override
    public List<Mina> findAll() {
        return iMinaDAO.findAll();
    }

    @Override
    public Optional<Mina> findById(Long id) {
        return iMinaDAO.findById(id);
    }

    @Override
    public void save(Mina mina) {
        iMinaDAO.save(mina);
    }

    @Override
    public void deleteById(Long id) {
        iMinaDAO.deleteById(id);
    }
}
