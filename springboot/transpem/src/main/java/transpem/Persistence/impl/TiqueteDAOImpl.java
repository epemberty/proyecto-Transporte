package transpem.Persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transpem.Model.Tiquete;
import transpem.Persistence.ITiqueteDAO;
import transpem.Repository.TiqueteRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TiqueteDAOImpl implements ITiqueteDAO {

  @Autowired
  private TiqueteRepository tiqueteRepository;

    @Override
    public List<Tiquete> findAll() {
        return (List<Tiquete>) tiqueteRepository.findAll();
    }

    @Override
    public Optional<Tiquete> findById(Long id) {
        return tiqueteRepository.findById(id);
    }

    @Override
    public void save(Tiquete tiquete) {
    tiqueteRepository.save(tiquete);
    }

    @Override
    public void deleteById(Long id) {
        tiqueteRepository.deleteById(id);
    }
    @Override
    public List<Tiquete> reporteTiquetes(java.sql.Date fechaInicio, java.sql.Date fechaFin, Long idMina, Long idRuta, Long idConductor, Long valorMin, Long valorMax) {
        return tiqueteRepository.findReporteTiquetes(fechaInicio, fechaFin, idMina, idRuta, idConductor, valorMin, valorMax);
    }
}
