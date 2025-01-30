package com.example.rest;

import controller.tda.list.graph.GraphLabelNoDirect;
import models.RutaViaje;

public class Algoritmo {

    public static void main(String[] args) {
        StringBuilder results = new StringBuilder();
        // Imprimir el encabezado con líneas
        results.append("+-----------------+---------------------+---------------------+----------------------+\n");
        results.append("| Tamaño del Grafo | Bellman-Ford (Tiempo) | Floyd-Warshall (Tiempo) | Algoritmo Mas Rápido |\n");
        results.append("+-----------------+---------------------+---------------------+----------------------+\n");

        // Definir los tamaños de los grafos a probar
        int[] sizes = {10, 20, 30, 60};

        for (int size : sizes) {
            try {
                // Crear el grafo con el tamaño actual
                GraphLabelNoDirect<RutaViaje> graph = new GraphLabelNoDirect<>(size, RutaViaje.class);
                RutaViaje[] rutas = new RutaViaje[size];

                // Simular los vértices y agregar etiquetas
                for (int i = 0; i < size; i++) {
                    RutaViaje ruta = new RutaViaje(i + 1, "Ruta " + (i + 1), "Origen " + (i + 1), "Destino " + (i + 1), (i * 10 + 10) + " km");
                    rutas[i] = ruta;
                    graph.labelsVerticeL(i + 1, ruta);
                }

                // Agregar aristas de ejemplo (puedes modificar esta parte para mayor aleatoriedad)
                for (int i = 0; i < size - 1; i++) {
                    graph.insertEdgeL(rutas[i], rutas[i + 1], (i + 1) * 2.0f);
                }

                System.out.println("Algoritmo Bellman-Ford para " + size + " vertices...");
                long startTime = System.nanoTime();
                graph.bellmanFord(1);  // Usando el vértice 1 como origen
                long endTime = System.nanoTime();
                long bellmanFordDuration = endTime - startTime;

                System.out.println("Algoritmo Floyd para " + size + " vertices...");
                startTime = System.nanoTime();
                graph.floydWarshall();  // Todos los pares de vértices
                endTime = System.nanoTime();
                long floydWarshallDuration = endTime - startTime;

                // Determinar el algoritmo más rápido
                String fastestAlgorithm = bellmanFordDuration < floydWarshallDuration ? "Bellman-Ford" : "Floyd";

                // Formatear los resultados con alineación
                results.append(String.format("| %-15s | %-19d | %-19d | %-19s |\n", 
                        size + " vertices", bellmanFordDuration, floydWarshallDuration, fastestAlgorithm));

            } catch (Exception e) {
                results.append("| Error en ejecución | Error en ejecución  | Error en ejecución  | Error en ejecución  |\n");
            }
        }

        // Imprimir la línea final
        results.append("+-----------------+---------------------+---------------------+----------------------+\n");

        System.out.println(results.toString());
    }
}