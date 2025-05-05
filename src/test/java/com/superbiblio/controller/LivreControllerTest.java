package com.superbiblio.controller;

import com.superbiblio.model.Livre;
import com.superbiblio.repository.LivreRepository;
import com.superbiblio.controller.LivreController;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LivreControllerTest {

    @Test
    public void testGetLivreById_LivreNonTrouve(){
        LivreRepository mockRepo = mock(LivreRepository.class);
        LivreController controller = new LivreController();

        controller.setLivreRepository(mockRepo);

        when(mockRepo.findById(42)).thenReturn(Optional.empty());

        ResponseEntity<Livre> response = controller.getLivreById(42);

        assertEquals(404, response.getStatusCodeValue());

        assertNull(response.getBody());
    }

    @Test
    public void testGetLivreByID_LivreTrouve()
    {
        LivreRepository mockRepo = mock(LivreRepository.class);
        LivreController controller = new LivreController();
        controller.setLivreRepository(mockRepo);

        Livre livre = new Livre();
        livre.setLivreId(1);
        livre.setTitre("Le petit prince");

        when(mockRepo.findById(1)).thenReturn(Optional.of(livre));

        ResponseEntity<Livre> response = controller.getLivreById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Le petit prince", response.getBody().getTitre());
    }
}
