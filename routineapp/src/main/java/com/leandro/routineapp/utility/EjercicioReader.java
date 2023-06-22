package com.leandro.routineapp.utility;

import com.leandro.routineapp.entity.Ejercicio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EjercicioReader {

    public static List<Ejercicio> read(String filename) throws IOException {
        List<Ejercicio> ejercicios = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            String username_creador = parts[0];
            String nombre = parts[1];
            String descripcion = parts[2];
            String grupo_muscular= parts[3];
            String imagen = parts[4];
            String dificultad = parts[5];

            Ejercicio ejercicio = new Ejercicio(username_creador,nombre, descripcion, grupo_muscular, imagen, dificultad);
            ejercicios.add(ejercicio);
        }
        br.close();
        return ejercicios;
    }
}
