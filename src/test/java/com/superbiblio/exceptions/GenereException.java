package com.superbiblio.exceptions;

public class GenereException {
    public String methodWithException() {
        throw new RuntimeException("Une erreur s'est produite dans methodWithException");
    }
}
