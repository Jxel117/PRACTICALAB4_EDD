package com.example.rest;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import controller.Dao.servicies.RutaViajeServicies;
import controller.tda.list.LinkedList;
import controller.tda.list.graph.GraphLabelNoDirect;
import models.RutaViaje;

@Path("test")
public class test {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap<String, Object> mapa = new HashMap<>();
        GraphLabelNoDirect<RutaViaje> graph = new GraphLabelNoDirect<>(5, RutaViaje.class);

        try {
            RutaViajeServicies ps = new RutaViajeServicies();
            LinkedList<RutaViaje> lp = ps.listAll();
            RutaViaje[] rutas = new RutaViaje[0];
            
            if (!lp.isEmpty()) {
                graph = new GraphLabelNoDirect<>(lp.getSize(), RutaViaje.class);
                rutas = new RutaViaje[lp.getSize()];
                for (int i = 0; i < lp.getSize(); i++) {
                    rutas[i] = lp.get(i);
                }
                for (int i = 0; i < lp.getSize(); i++) {
                    graph.labelsVerticeL(i + 1, rutas[i]);
                }
            }

            // Agregar aristas de ejemplo
            graph.insertEdgeL(rutas[0], rutas[1], 6.0f);
            graph.insertEdgeL(rutas[0], rutas[2], 7.0f);
            graph.insertEdgeL(rutas[1], rutas[3], 5.0f);
            graph.insertEdgeL(rutas[2], rutas[4], 9.0f);
            graph.insertEdgeL(rutas[3], rutas[4], 2.0f);

            // Dibujar el grafo
            graph.drawGraph();

            // Ejecutar y medir el tiempo de Bellman-Ford
            System.out.println("Ejecutando Bellman-Ford...");
            long startTime = System.nanoTime();
            double[] bellmanFordResult = graph.bellmanFord(1);  // Ejemplo, desde el vértice 1
            long endTime = System.nanoTime();
            long bellmanFordDuration = endTime - startTime;
            System.out.println("Bellman-Ford Time: " + bellmanFordDuration + " nanosegundos");

            // Ejecutar y medir el tiempo de Floyd-Warshall
            System.out.println("Ejecutando Floyd-Warshall...");
            startTime = System.nanoTime();
            double[][] floydWarshallResult = graph.floydWarshall();  // Todos los pares de vértices
            endTime = System.nanoTime();
            long floydWarshallDuration = endTime - startTime;
            System.out.println("Floyd-Warshall Time: " + floydWarshallDuration + " nanosegundos");

            // Preparar la respuesta
            mapa.put("msg", "OK");
            mapa.put("data", graph.toString());

        } catch (Exception e) {
            mapa.put("msg", "Error");
            mapa.put("data", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(mapa).build();
        }
        
        // Responder con el gráfico y los datos
        return Response.ok(mapa).build();
    }
}