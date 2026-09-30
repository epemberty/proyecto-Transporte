package transpem.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transpem.Model.Tiquete;
import transpem.Persistence.ITiqueteDAO;
import transpem.Service.TiqueteService;

import java.util.List;
import java.util.Optional;

@Service
public class TiqueteServiceImpl implements TiqueteService {

@Autowired
    private ITiqueteDAO iTiqueteDAO;


    @Override
    public List<Tiquete> findAll() {
        return iTiqueteDAO.findAll();
    }

    @Override
    public Optional<Tiquete> findById(Long id) {
        return iTiqueteDAO.findById(id);
    }

    @Override
    public void save(Tiquete tiquete) {
        iTiqueteDAO.save(tiquete);
    }

    @Override
    public void deleteById(Long id) {
        iTiqueteDAO.deleteById(id);
    }
    @Override
    public List<Tiquete> reporteTiquetes(java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idMina, Long idRuta, Long idConductor, Long valorMin, Long valorMax) {
        return iTiqueteDAO.reporteTiquetes(fechaInicio, fechaFin, idMina, idRuta, idConductor, valorMin, valorMax);
    }
}
