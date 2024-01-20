package empaquetadoApp;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class EmpaquetadoMain {

    public static void main(String[] args) {

        String[] eleccion = {"TXT", "PDF", "IMAGEN"};
        String tipoArchivo = null;
        while (tipoArchivo == null) {
            tipoArchivo = (String) JOptionPane.showInputDialog(null, "TIPO DE ARCHIVO", "ELECCION DE TIPO DE ARCHIVO", JOptionPane.INFORMATION_MESSAGE, null, eleccion, eleccion[0]);
        }

        switch (tipoArchivo) {

            case "TXT":
                String nombre = null;
                while (nombre == null) {
                    nombre = JOptionPane.showInputDialog(null, "NOMBRE DE DOCUMENTOS A MOVER");
                }
                archivos(nombre, tipoArchivo);
                break;
            case "PDF":
                String nombre1 = null;
                while (nombre1 == null) {
                    nombre1 = JOptionPane.showInputDialog(null, "NOMBRE DE DOCUMENTOS A MOVER");
                }
                archivos(nombre1, tipoArchivo);
                break;
            case "IMAGEN":
                String nombre2 = null;
                while (nombre2 == null) {
                    nombre2 = JOptionPane.showInputDialog(null, "NOMBRE DE DOCUMENTOS A MOVER");
                }
                archivosImagen(nombre2);

        }
    }

    private static void archivos(String nombre, String tipo) {

        //CREACION DE LA CARPETA DONDE SE MOVERAN LOS ARCHIVOS; INDICAR LA CARPETA
        String directorioNuevo = "CARPETA" + File.separator + nombre;

        //INDICAR EL DIRECTORIO DONDE SE ENCUENTRAN LOS DOCUMENTOS A EMPAQUETAR
        String directorio = "../";

        File carpeta = new File(directorio);

        File[] archivos = carpeta.listFiles();

        ArrayList<String> nombre_archivos = new ArrayList<>();

        if (archivos != null) {
            for (File documentos : archivos) {
                if (documentos.getName().endsWith("." + tipo.toLowerCase()) && documentos.getName().toLowerCase().contains(nombre.toLowerCase())) {
                    File nuevaCarpeta = new File(directorioNuevo);
                    if (!nuevaCarpeta.exists()) {
                        if (nuevaCarpeta.mkdir()) {
                            JOptionPane.showMessageDialog(null, "SE CREO LA CARPETA " + nombre);
                        }
                    }
                    nombre_archivos.add(documentos.getName());
                }
            }
            for (String nombres : nombre_archivos) {
                Path origen = Path.of(directorio, nombres);
                Path destino = Path.of(directorioNuevo, nombres);

                try {
                    Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            String contenido = contenidoArrayList(nombre_archivos);
            JOptionPane.showMessageDialog(null, contenido, "ARCHIVOS MOVIDOS", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("NO EXISTEN DOCUMENTOS");
        }
    }

    private static void archivosImagen(String nombre) {

        //CREACION DE LA CARPETA DONDE SE MOVERAN LOS ARCHIVOS; INDICAR LA CARPETA
        String directorioNuevo = "CARPETA" + File.separator + nombre;

        String directorio = "../";

        File carpeta = new File(directorio);

        File[] archivos = carpeta.listFiles();

        ArrayList<String> nombre_archivos = new ArrayList<>();

        ArrayList<String> extensionesPermitidas = new ArrayList<>();
        extensionesPermitidas.add(".jpg");
        extensionesPermitidas.add(".jpeg");
        extensionesPermitidas.add(".png");
        extensionesPermitidas.add(".gif");

        if (archivos != null) {
            for (File documentos : archivos) {
                for (String ext : extensionesPermitidas) {
                    if (documentos.getName().endsWith(ext) && documentos.getName().toLowerCase().contains(nombre.toLowerCase())) {
                        File nuevaCarpeta = new File(directorioNuevo);
                        if (!nuevaCarpeta.exists()) {
                            if (nuevaCarpeta.mkdir()) {
                                JOptionPane.showMessageDialog(null, "SE CREO LA CARPETA " + nombre);
                            }
                        }
                        nombre_archivos.add(documentos.getName());
                    }
                }
            }
            for (String nombres : nombre_archivos) {
                Path origen = Path.of(directorio, nombres);
                Path destino = Path.of(directorioNuevo, nombres);

                try {
                    Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            String contenido = contenidoArrayList(nombre_archivos);
            JOptionPane.showMessageDialog(null, contenido, "ARCHIVOS MOVIDOS", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("NO EXISTEN DOCUMENTOS");
        }
    }

    private static String contenidoArrayList(ArrayList<String> lista) {
        StringBuilder contenido = new StringBuilder();
        for (String elemento : lista) {
            contenido.append(elemento).append("\n");
        }
        return contenido.toString();
    }
}
