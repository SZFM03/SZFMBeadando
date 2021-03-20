package app.service;

import app.entity.Jegy;
import app.repository.JegyRepository;

public class JegyService {

    JegyRepository jegyRepository = new JegyRepository();

    public JegyService(JegyRepository jegyRepository) {
        this.jegyRepository = jegyRepository;
    }

    public void saveJegy(Jegy jegy) {
        jegyRepository.save(jegy);
    }
}
