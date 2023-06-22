package com.leandro.routineapp.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class GifController {

    @GetMapping("/gifs/{filename:.+}")
    public ResponseEntity<Resource> getGif(@PathVariable String filename) {
        Resource gif = new FileSystemResource("src/main/java/com/leandro/routineapp/utility/gifs/" + filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_GIF);
        return ResponseEntity.ok()
                .headers(headers)
                .body(gif);
    }

    @GetMapping("/miniaturas/{filename:.+}")
    public ResponseEntity<Resource> getThumbnail(@PathVariable String filename) {
        // Ruta del archivo GIF original
        String originalGifPath = "src/main/java/com/leandro/routineapp/utility/gifs/" + filename;

        try {
            // Lee el archivo GIF original
            File originalGifFile = new File(originalGifPath);
            BufferedImage originalImage = ImageIO.read(originalGifFile);

            // Crea una miniatura estática a partir del GIF original
            BufferedImage thumbnailImage = createThumbnail(originalImage);

            // Guarda la miniatura en un archivo temporal
            File thumbnailFile = File.createTempFile("thumbnail", ".png");
            ImageIO.write(thumbnailImage, "png", thumbnailFile);

            // Crea un recurso a partir del archivo temporal
            Resource thumbnailResource = new FileSystemResource(thumbnailFile);

            // Configura el encabezado HTTP para indicar que se trata de una imagen
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            // Devuelve la miniatura como una respuesta HTTP
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(thumbnailResource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    private BufferedImage createThumbnail(BufferedImage originalImage) {
        // Crea una imagen en blanco para la miniatura
        BufferedImage thumbnailImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = thumbnailImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 300, 300);

        // Dibuja el GIF original en la miniatura
        graphics.drawImage(originalImage, 0, 0, 300, 300, null);
        graphics.dispose();

        return thumbnailImage;
    }
}
