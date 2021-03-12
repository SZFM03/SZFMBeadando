package app.service;

import app.entity.Hallgato;
import app.repository.HallgatoRepository;

public class HallgatoService {

    private final HallgatoRepository hallgatoRepository;

    public HallgatoService(HallgatoRepository hallgatoRepository) {
        this.hallgatoRepository = hallgatoRepository;
    }

    public void saveHallgato(Hallgato hallgato) {
        hallgatoRepository.save(hallgato);
    }

    public boolean deleteHallgato(String neptunKod){
        if (!neptunKod.isBlank()) {
            return hallgatoRepository.hallgatoTorles(neptunKod);
        }
        return false;
    }

    public boolean modositHallgato(String nev, String ev, String neptunKod){
        if(!nev.isBlank() && !ev.isBlank() && !neptunKod.isBlank()){
            return hallgatoRepository.updateHallgato(nev, ev, neptunKod);
        }
        return false;
    }

    public Hallgato lekerdezHallgato(String neptunKod){
        if (!neptunKod.isBlank()) {
            return hallgatoRepository.selectHallgato(neptunKod);
        }
       throw new RuntimeException("Nem adtál meg neptunkódot");
    }
}
