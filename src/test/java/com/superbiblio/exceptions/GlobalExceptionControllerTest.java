package com.superbiblio.exceptions;

import com.superbiblio.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionControllerTest {

    @Mock
    private GenereException genereException;

    @InjectMocks
    private GlobalExceptionController globalExceptionController;

    @Test
    public void testExceptionHandler() {
        lenient().when(genereException.methodWithException()).thenThrow(new RuntimeException("Une erreur s'est produite"));

        ResponseEntity<ErrorResponse> responseEntity = globalExceptionController.exceptionHandler(new RuntimeException("Une erreur s'est produite"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Erreur interne du serveur", responseEntity.getBody().getMessage());
        assertEquals("Une erreur s'est produite", responseEntity.getBody().getDetails());
    }
}