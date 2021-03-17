package app.service;

import app.entity.Hallgato;
import app.entity.Leckekonyv;
import app.repository.LeckekonyvRepository;

import java.util.List;

public class LeckekonyvService {

    public LeckekonyvService(LeckekonyvRepository leckekonyvRepository) {
    }
    public void saveLeckekonyv(Leckekonyv leckekonyv) {
        LeckekonyvRepository.save(leckekonyv);
    }
}