package app.service;

import app.entity.Tantargy;
import app.repository.TantargyakRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TantargyakServiceTest {

    @Mock
    private TantargyakRepository tantargyakRepository;

    @InjectMocks
    private TantargyakService tantargyakService;

    @Test
    public void pozitivKredit_NegativKreditSzam_HaMindenRendbenVan() {
        // Given
        Tantargy tantargy = new Tantargy(1L,"Programozás","ILFSA-22", -5);

        // When
        boolean kredit = tantargyakService.pozitivKredit(tantargy);

        // Then
        assertEquals(true, kredit);
    }

}