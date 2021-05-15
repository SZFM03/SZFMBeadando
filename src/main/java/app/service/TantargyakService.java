package app.service;

import app.entity.Tantargy;
import app.repository.TantargyakRepository;

import java.util.List;

public class TantargyakService {

    private final TantargyakRepository tantargyakRepository;

    public TantargyakService(TantargyakRepository tantargyakRepository) {
        this.tantargyakRepository = tantargyakRepository;
    }

    public void saveTantargyak(Tantargy tantargy) {
        tantargyakRepository.save(tantargy);
    }


    public List<Tantargy> MindenTantargy(){
        return tantargyakRepository.selectMindenTantargy();
    }

}
