package t3grupo2;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class T3Grupo2 {
    public void losMejores(String archivoOrigen, String tipoPersonaje, int nMejores) throws IOException {
        ArrayList<Personaje> personajes;
        ArrayList<Personaje> mejores;

        verificarArchivo(archivoOrigen);
        personajes = leerDatosArchivo(archivoOrigen, tipoPersonaje);
        mejores = seleccionarNMejores(personajes,nMejores);
        guardarNMejores(mejores,tipoPersonaje);
    }

    private void guardarNMejores(ArrayList<Personaje> mejores, String tipoPersonaje) throws IOException {
        File archivoMejores = new File(tipoPersonaje + ".txt");
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(archivoMejores))) {
            for (Personaje personaje : mejores) {
                dos.writeInt(personaje.getIdPersonaje());
                dos.writeInt(personaje.getnVidas());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error en la creación del archivo "+archivoMejores);
        }catch (IOException e){
            throw new IOException("Error al grabar los datos en el archivo "+archivoMejores);
        }
    }

    private ArrayList<Personaje> seleccionarNMejores(ArrayList<Personaje> personajes, int nMejores) {
        ArrayList<Personaje> mejores = new ArrayList<>();
        if (!personajes.isEmpty()) {
            personajes.sort(Comparator.comparing(Personaje::getnVidas).reversed());
        }
        if (personajes.size() <= nMejores) {
            mejores.addAll(personajes);
        } else {
            mejores.addAll(personajes.subList(0, nMejores));
        }
        return mejores;
    }

    private ArrayList<Personaje> leerDatosArchivo(String archivoOrigen, String tipoPersonaje) throws IOException {
        ArrayList<Personaje> personajes =  new ArrayList<>();
        File archivo = new File(archivoOrigen);
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        String[] propiedadesPersonaje;
        if (archivo.length()>0) {
            while( (linea = br.readLine()) != null) {
                propiedadesPersonaje = linea.split(" ");
                if (propiedadesPersonaje.length==4){
                    if (propiedadesPersonaje[1].equals(tipoPersonaje)){
                        personajes.add(new Personaje(Integer.parseInt(propiedadesPersonaje[0]),
                                                    propiedadesPersonaje[1],
                                                    Integer.parseInt(propiedadesPersonaje[2]),
                                                    Integer.parseInt(propiedadesPersonaje[3])
                                ));
                    }
                }
            }
        }
        br.close();
        return personajes;
    }

    private void verificarArchivo(String archivoOrigen) {
        File archivo = new File(archivoOrigen);
        if (!archivo.exists() || !archivo.isFile() || !archivo.canRead()) {
            throw new IllegalArgumentException("Error en verificación de archivo: no existe, o es un directorio o no se puede leer");
        }
    }
}
