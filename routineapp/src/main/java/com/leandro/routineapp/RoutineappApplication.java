package com.leandro.routineapp;

import com.leandro.routineapp.entity.Ejercicio;
import com.leandro.routineapp.repository.EjercicioRepositorio;

import com.leandro.routineapp.utility.EjercicioReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;


import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class RoutineappApplication implements CommandLineRunner {

	@Autowired
	EjercicioRepositorio ejercicioRepositorio;
	@Autowired
	private ResourceLoader resourceLoader;
	public static void main(String[] args) {

		SpringApplication.run(RoutineappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String filePath = "src/main/java/com/leandro/routineapp/utility/bateriaEjercicios.txt";
		try {
			List<Ejercicio> ejercicios = EjercicioReader.read(filePath);
			for (Ejercicio ejercicio: ejercicios){

				if (ejercicioRepositorio.findByNombre(ejercicio.getNombre()) == null) {
					ejercicioRepositorio.save(ejercicio);
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
