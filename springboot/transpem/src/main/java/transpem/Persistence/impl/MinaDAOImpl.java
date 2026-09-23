package transpem.Persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transpem.Model.Mina;
import transpem.Persistence.IMinaDAO;
import transpem.Repository.MinaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class MinaDAOImpl implements IMinaDAO {

    @Autowired
    private MinaRepository minaRepository;

    @Override
    public List<Mina> findAll() {
        return (List<Mina>) minaRepository.findAll();
    }

    @Override
    public Optional<Mina> findById(Long id) {
        return minaRepository.findById(id);
    }

    @Override
    public void save(Mina mina) {
    minaRepository.save(mina);
    }

    @Override
    public void deleteById(Long id) {
            minaRepository.deleteById(id);
    }
}
