import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class log {

    static int palabrasTotales;
    public static void ordenamientoArreglos(String[] palabras, int[] apariciones) {
        for (int i = palabras.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (j + 1 <= i && apariciones[j] < apariciones[j + 1]) {
                    String aux = palabras[j];
                    int iux = apariciones[j];
                    palabras[j] = palabras[j + 1];
                    apariciones[j] = apariciones[j + 1];
                    palabras[j + 1] = aux;
                    apariciones[j + 1] = iux;
                }
            }
        }
    }
    public static void main(String args[]) {
        Map<String, Integer> palabras = new HashMap();
        String argumentos[] = args[0].split(",");
        String fileName = argumentos[0];
        String fileName1 = argumentos[1];

        FileReader fileReader = null;

        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("el nombre del archivo no se encuentra");
            System.exit(2);
        }

        BufferedReader in = new BufferedReader(fileReader);
        String textLine = null;
        String[] pv = new String[0];
        while (true) {
            try {
                if ((textLine = in.readLine()) == null)
                    break;
            } catch (IOException e) {
                System.out.println("error al leer el archivo");
                System.exit(3);
            }
            pv = textLine.split(",");

        }



        try {
            fileReader = new FileReader(fileName1);
        } catch (FileNotFoundException e) {
            System.out.println("el nombre del archivo no se encuentra");
            System.exit(2);
        }

        BufferedReader in1 = new BufferedReader(fileReader);

        while (true) {
            try {
                if ((textLine = in1.readLine()) == null)
                    break;
            } catch (IOException e) {
                System.out.println("error al leer el archivo");
                System.exit(3);
            }
            for (String palabra : textLine.replace(",", "").replace(".", "").replace(";", "").replace(":", "").split(" ")) {

                palabras.put(palabra, palabras.containsKey(palabra) ? palabras.get(palabra) + 1 : 1);
            }

            StringTokenizer st = new StringTokenizer(textLine);
            palabrasTotales = palabrasTotales + st.countTokens();

        }
        String[] arreglo_palabras = new String[palabras.size()];
        int[] arreglo_repeticiones = new int[palabras.size()];
        palabras.keySet().toArray(arreglo_palabras);
        for (int i = 0; i < arreglo_palabras.length; i++) {
            arreglo_repeticiones[i] = palabras.get(arreglo_palabras[i]);
        }
        int cv=0;
        int pd=0;
        ordenamientoArreglos(arreglo_palabras, arreglo_repeticiones);
        System.out.println("nombre del archivo: "+argumentos[1]);
        System.out.println("lista de palabras diferentes: ");
        for (int i = 0; i < arreglo_repeticiones.length; i++) {
            System.out.println(arreglo_palabras[i]);
            for(int j = 0; j < pv.length; j++){
                if(arreglo_palabras[i] .equals(pv[j])){
                    cv=cv+arreglo_repeticiones[i];
                    //System.out.println(arreglo_palabras[i] );
                }
            }
        }
        pd=palabrasTotales-cv;
        System.out.println("palabras vacias "+cv+", palabras diferentes "+pd);
        System.out.println("palabras totales en el texto "+palabrasTotales);

    }
}

