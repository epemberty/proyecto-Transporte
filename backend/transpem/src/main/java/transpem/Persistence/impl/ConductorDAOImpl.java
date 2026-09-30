package transpem.Persistence.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transpem.Model.Conductor;
import transpem.Persistence.IConductorDAO;
import transpem.Repository.ConductorRepository;

import java.util.List;
import java.util.Optional;

@Component
public class ConductorDAOImpl implements IConductorDAO {

    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    public List<Conductor> findAll() {

        return (List<Conductor>) conductorRepository.findAll();
    }

    @Override
    public Optional<Conductor> findById(Long id) {

        return conductorRepository.findById(id);
    }

    @Override
    public void save(Conductor conductor) {

        conductorRepository.save(conductor);
    }


    @Override
    public void deleteById(Long id) {

        conductorRepository.deleteById(id);
    }
}
