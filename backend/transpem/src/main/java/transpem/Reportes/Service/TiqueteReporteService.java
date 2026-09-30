package transpem.Reportes.Service;

import org.springframework.stereotype.Service;
import transpem.Reportes.DTO.TiqueteReporteDTO;
import transpem.Repository.TiqueteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TiqueteReporteService {

    private final TiqueteRepository tiqueteRepository;

    public TiqueteReporteService(TiqueteRepository tiqueteRepository) {
        this.tiqueteRepository = tiqueteRepository;
    }

    public List<TiqueteReporteDTO> generarReporte() {
        return tiqueteRepository.findAll().stream().map(t -> {
            TiqueteReporteDTO dto = new TiqueteReporteDTO();
            dto.setIdTiquete(t.getId());
            dto.setNombreConductor(t.getConductor() != null ? t.getConductor().getNombre() : null);
            dto.setPlacaVehiculo(t.getVehiculo() != null ? t.getVehiculo().getPlaca() : null);
            dto.setOrigen(t.getRuta() != null && t.getRuta().getMina() != null ? t.getRuta().getMina().getNombre() : null);
            dto.setDestino(t.getRuta() != null && t.getRuta().getDestino() != null ? t.getRuta().getDestino().getNombre() : null);
            dto.setFecha(t.getFecha() != null ? t.getFecha().toLocalDate() : null);
            dto.setPeso(t.getPesoToneladas() != null ? t.getPesoToneladas().doubleValue() : 0.0);
            return dto;
        }).collect(Collectors.toList());
    }
}
