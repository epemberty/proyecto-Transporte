package transpem.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transpem.Model.Contrato;
import transpem.Service.ContratoService;
import transpem.Persistence.IContratoDAO;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoServiceImpl implements ContratoService {


    @Autowired
    private IContratoDAO iContratoDAO;

    @Override
    public List<Contrato> findAll() {
        return iContratoDAO.findAll();
    }

    @Override
    public Optional<Contrato> findById(Long id) {
        return iContratoDAO.findById(id);
    }

    @Override
    public void save(Contrato contrato) {
        iContratoDAO.save(contrato);
    }

    @Override
    public void deleteById(Long id) {
            iContratoDAO.deleteById(id);
    }
}
