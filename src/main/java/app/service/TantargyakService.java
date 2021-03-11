package app.service;

import app.entity.Tantargyak;
import app.repository.TantargyakRepository;

public class TantargyakService {

    private final TantargyakRepository tantargyakRepository;

    public TantargyakService(TantargyakRepository tantargyakRepository) {
        this.tantargyakRepository = tantargyakRepository;
    }

    public void saveTantargyak(Tantargyak tantargyak) {
        tantargyakRepository.save(tantargyak);
    }

}
