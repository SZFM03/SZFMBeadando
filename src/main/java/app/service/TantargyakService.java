package app.service;

import app.entity.Tantargyak;
import app.repository.TantargyakRepository;

import java.util.List;

public class TantargyakService {

    private final TantargyakRepository tantargyakRepository;

    public TantargyakService(TantargyakRepository tantargyakRepository) {
        this.tantargyakRepository = tantargyakRepository;
    }

    public void saveTantargyak(Tantargyak tantargyak) {
        tantargyakRepository.save(tantargyak);
    }


    public List<Object[]> MindenTantargy(){

        return tantargyakRepository.selectMindenTantargy();

    }

}
