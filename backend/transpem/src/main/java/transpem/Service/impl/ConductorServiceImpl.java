package transpem.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import transpem.DTO.ConductorDTO;
import transpem.Model.Conductor;
import transpem.Persistence.IConductorDAO;
import transpem.Repository.ConductorRepository;
import transpem.Service.ConductorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConductorServiceImpl implements ConductorService {

@Autowired
private IConductorDAO iConductorDAO;

    @Override
    public List<Conductor> findAll() {
        return iConductorDAO.findAll();
    }

    @Override
    public Optional<Conductor> findById(Long id) {
        return iConductorDAO.findById(id);
    }

    @Override
    public void save(Conductor conductor) {
        iConductorDAO.save(conductor);
    }

    @Override
    public void deleteById(Long id) {
            iConductorDAO.deleteById(id);
    }
}
