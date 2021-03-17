package app.service;

import app.entity.Hallgato;
import app.repository.LeckekonyvRepository;

import java.util.List;

public class LeckekonyvService {

    private final LeckekonyvRepository leckekonyvRepository;

    public LeckekonyvService(LeckekonyvRepository leckekonyvRepository) {
        this.leckekonyvRepository = leckekonyvRepository;
    }

}