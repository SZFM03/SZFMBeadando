package app.service;

import app.entity.Hallgato;
import app.entity.Jegy;
import app.entity.Leckekonyv;
import app.entity.Tantargy;
import app.repository.JegyRepository;
import app.repository.LeckekonyvRepository;

import java.util.List;

public class LeckekonyvService {

    private final LeckekonyvRepository leckekonyvRepository;
    private final JegyRepository jegyRepository;

    public LeckekonyvService(LeckekonyvRepository leckekonyvRepository, JegyRepository jegyRepository) {
        this.leckekonyvRepository = leckekonyvRepository;
        this.jegyRepository = jegyRepository;
    }

    public double sulyozottAtlag(Hallgato hallgato) {
        long hallgato_id = hallgato.getId();
        int osszeg = 0;
        int szorzat = 0;
        int kreditosszeg = 0;
        List<Tantargy> tantargyak = hallgato.getTantargyak();

        if (tantargyak.isEmpty()) {
            return 0;
        }

        for (var tantargy : tantargyak) {
            long tantargy_id = tantargy.getId();
            Jegy jegy = jegyRepository.selectHallgatoIDTantargyID(hallgato_id, tantargy_id);
            szorzat = tantargy.getKreditszam() * jegy.getJegy();
            osszeg += szorzat;
            kreditosszeg += tantargy.getKreditszam();
        }

        return Double.parseDouble(String.format("%.2f", (double) osszeg / kreditosszeg).replace(",", "."));
    }

    public void saveLeckekonyv(Leckekonyv leckekonyv) {
        leckekonyvRepository.save(leckekonyv);
    }
}