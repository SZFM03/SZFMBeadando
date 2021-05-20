package app.service;

import app.entity.Hallgato;
import app.repository.HallgatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HallgatoServiceTest {

    @Mock
    private HallgatoRepository hallgatoRepository;

    @InjectMocks
    private HallgatoService hallgatoService;

    @Test
    public void pozitivSzuletesiDatum_NegativSzuletesiSzam_HaMindenRendbenVan() {
        // Given
        Hallgato hallgato=new Hallgato("Hát Izsák",1977,"RR6ZUI");

        // When
        boolean szuletesidatum = hallgatoService.pozitivSzuletesiDatum(hallgato);

        // Then
        assertEquals(false, szuletesidatum);
    }
}