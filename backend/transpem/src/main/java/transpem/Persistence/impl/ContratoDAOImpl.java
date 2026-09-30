package transpem.Persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transpem.Model.Contrato;
import transpem.Persistence.IContratoDAO;
import transpem.Repository.ContratoRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ContratoDAOImpl implements IContratoDAO {


    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public List<Contrato> findAll() {
        return (List<Contrato>) contratoRepository.findAll();
    }

    @Override
    public Optional<Contrato> findById(Long id) {
        return contratoRepository.findById(id);
    }

    @Override
    public void save(Contrato contrato) {
        contratoRepository.save(contrato);
    }

    @Override
    public void deleteById(Long id) {
        contratoRepository.deleteById(id);
    }
}
