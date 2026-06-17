package com.biblioteca.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BibliotecaService {

    private static final String ARQUIVO = "biblioteca.json";

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void salvar(Object objeto) {

        System.out.println("Salvando em: " +
                new File(ARQUIVO).getAbsolutePath());

        try (FileWriter writer = new FileWriter(ARQUIVO)) {

            gson.toJson(objeto, writer);

            System.out.println("Dados salvos em JSON.");

        } catch (IOException e) {

            System.out.println("Erro ao salvar JSON.");
        }
    }

    public <T> T carregar(Class<T> classe) {

        try (FileReader reader = new FileReader(ARQUIVO)) {

            return gson.fromJson(reader, classe);

        } catch (IOException e) {

            System.out.println("Erro ao carregar JSON.");
        }

        return null;
    }
}