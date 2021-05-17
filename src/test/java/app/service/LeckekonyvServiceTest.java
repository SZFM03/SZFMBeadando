package app.service;

import app.entity.Hallgato;
import app.entity.Jegy;
import app.entity.Tantargy;
import app.repository.JegyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LeckekonyvServiceTest {

    @Mock
    private JegyRepository jegyRepository;

    @InjectMocks
    private LeckekonyvService leckekonyvService;

    @Test
    public void sulyozottAtlag_HelyesErteketAdVissza_HaMindenRendbenVan() {
        // Given
        List<Tantargy> targyak = List.of(
                new Tantargy(1L, "Kalkulus", "INDK-123", 5),
                new Tantargy(2L, "Webfejlesztés", "INDK-648", 6)
        );

        Hallgato hallgato = new Hallgato(1L, "Test User", 1970, "ASD123", targyak);

        when(jegyRepository.selectHallgatoIDTantargyID(anyLong(), anyLong()))
                .thenReturn(new Jegy(1L, 1L, 1L, 5))
                .thenReturn(new Jegy(2L, 1L, 2L, 3));

        // When
        double sulyozottAtlag = leckekonyvService.sulyozottAtlag(hallgato);


        // Then
        assertEquals(3.91, sulyozottAtlag);
    }

    @Test
    public void sulyozottAtlag_NullatAdVissza_HaNincsAHallgatonakTantargya() {
        // Given
        List<Tantargy> targyak = List.of();
        Hallgato hallgato = new Hallgato(1L, "Test User", 1970, "ASD123", targyak);

        // When
        double sulyozottAtlag = leckekonyvService.sulyozottAtlag(hallgato);

        // Then
        assertEquals(0, sulyozottAtlag);
    }

}