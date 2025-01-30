package controller.tda.list.graph;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

class RutaViaje {
    private int idRuta;
    private String nombre;
    private String origen;
    private String destino;
    private String distancia;

    // Getters y setters
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
}

public class AplicacionGrafo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<RutaViaje> rutas = leerRutasDesdeJson("/home/joel117/PRACTICALAB4/PRACTICA4/src/main/java/Data/RutaViaje.json");

        int vertices = rutas.size();
        double[][] grafo = new double[vertices][vertices];
        String[] ubicaciones = new String[vertices];

        // Inicializar distancias con valores infinitos
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i == j) {
                    grafo[i][j] = 0;
                } else {
                    grafo[i][j] = Double.MAX_VALUE;
                }
            }
        }

        // Llenar el grafo con las distancias desde el JSON
        for (int i = 0; i < rutas.size(); i++) {
            RutaViaje ruta = rutas.get(i);
            ubicaciones[i] = ruta.getNombre();
            for (int j = 0; j < rutas.size(); j++) {
                if (i != j) {
                    RutaViaje otraRuta = rutas.get(j);
                    if (ruta.getDestino().equals(otraRuta.getOrigen())) {
                        grafo[i][j] = Double.parseDouble(ruta.getDistancia().replace(" km", ""));
                    }
                }
            }
        }

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Mostrar Grafo");
            System.out.println("2. Calcular Distancias (Floyd)");
            System.out.println("3. Calcular Distancias (Bellman)");
            System.out.println("4. Calcular Camino más Corto");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Mostrar grafo
                    System.out.println("Grafo (Distancias):");
                    for (int i = 0; i < vertices; i++) {
                        for (int j = 0; j < vertices; j++) {
                            if (grafo[i][j] == Double.MAX_VALUE) {
                                System.out.print("INF ");
                            } else {
                                System.out.print(grafo[i][j] + " ");
                            }
                        }
                        System.out.println();
                    }
                    break;
                case 2:
                    double[][] distanciasFW = floydWarshall(grafo);
                    System.out.println("Resultados de Floyd-Warshall:");
                    for (int i = 0; i < distanciasFW.length; i++) {
                        for (int j = 0; j < distanciasFW[i].length; j++) {
                            if (distanciasFW[i][j] == Double.MAX_VALUE) {
                                System.out.println(ubicaciones[j] + ": INF");
                            } else {
                                System.out.println(ubicaciones[j] + ": " + distanciasFW[i][j]);
                            }
                        }
                        System.out.println();
                    }
                    break;
                case 3:
                    // Calcular usando Bellman-Ford
                    System.out.print("Introduce el vértice de inicio (0 - " + ubicaciones[0] + "): ");
                    int verticeInicioBF = scanner.nextInt();
                    double[] distanciasBF = bellmanFord(grafo, verticeInicioBF);
                    System.out.println("Resultados de Bellman-Ford:");
                    for (int i = 0; i < distanciasBF.length; i++) {
                        if (distanciasBF[i] == Double.MAX_VALUE) {
                            System.out.println(ubicaciones[i] + ": INF");
                        } else {
                            System.out.println(ubicaciones[i] + ": " + distanciasBF[i]);
                        }
                    }
                    break;
                case 4:
                    // Camino más corto entre dos ubicaciones
                    System.out.print("Introduce el índice de la ubicación de inicio (0 - " + ubicaciones[0] + "): ");
                    int inicio = scanner.nextInt();
                    System.out.print("Introduce el índice de la ubicación de destino (0 - " + ubicaciones[0] + "): ");
                    int destino = scanner.nextInt();
                    double caminoMasCorto = grafo[inicio][destino];
                    if (caminoMasCorto == Double.MAX_VALUE) {
                        System.out.println("No existe un camino.");
                    } else {
                        System.out.println("Distancia de " + ubicaciones[inicio] + " a " + ubicaciones[destino] + ": " + caminoMasCorto);
                    }
                    break;
                case 5:
                    // Salir
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static List<RutaViaje> leerRutasDesdeJson(String filePath) {
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            Type listType = new TypeToken<ArrayList<RutaViaje>>() {}.getType();
            List<RutaViaje> rutas = gson.fromJson(reader, listType);
            reader.close();
            return rutas;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Algoritmo de Floyd-Warshall
    public static double[][] floydWarshall(double[][] dist) {
        int V = dist.length;
        double[][] distCopia = new double[V][V];

        // Copiar la matriz de distancias original
        for (int i = 0; i < V; i++) {
            System.arraycopy(dist[i], 0, distCopia[i], 0, V);
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (distCopia[i][k] + distCopia[k][j] < distCopia[i][j]) {
                        distCopia[i][j] = distCopia[i][k] + distCopia[k][j];
                    }
                }
            }
        }
        return distCopia;
    }

    // Algoritmo de Bellman-Ford
    public static double[] bellmanFord(double[][] dist, int verticeInicio) {
        int V = dist.length;
        double[] distancias = new double[V];
        for (int i = 0; i < V; i++) {
            distancias[i] = Double.MAX_VALUE;
        }
        distancias[verticeInicio] = 0;

        // Relajación de todas las aristas V-1 veces
        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (dist[u][v] != Double.MAX_VALUE && distancias[u] != Double.MAX_VALUE &&
                        distancias[u] + dist[u][v] < distancias[v]) {
                        distancias[v] = distancias[u] + dist[u][v];
                    }
                }
            }
        }
        return distancias;
    }
}