package com.levelup.util;
//*
import com.levelup.services.LlmService;

public class PruebaLlm {

    public static void main(String[] args) {
        LlmService llmService = new LlmService();

        String respuesta = llmService.generarDescripcionProducto("Figura Funko de Mario Bros");
        System.out.println("Respuesta IA:");
        System.out.println(respuesta);
    }
}
