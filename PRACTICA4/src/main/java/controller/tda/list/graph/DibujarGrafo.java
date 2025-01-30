package controller.tda.list.graph;

import java.io.File;
import java.io.FileWriter;

import controller.tda.LabelException;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;

public class DibujarGrafo {
    String URL = "d3" + File.separator + "grafo.js";

    public void crearArchivo(GraphLabelNoDirect g) throws LabelException, IndexOutOfBoundsException, ListEmptyException {

        StringBuilder vertices = new StringBuilder();
        StringBuilder aristas = new StringBuilder();
        vertices.append("var nodes = new vis.DataSet([\n");
        aristas.append("var edges = new vis.DataSet([\n");

        for (int i = 1; i <= g.nro_vertices(); i++) {
            vertices.append("\t{ id: ").append(i).append(", label: \" ")
                    .append(g.getLabelL(i)).append("\" },\n");
            LinkedList<Adycencia> listaAdyacencia = g.adycencias(i);

            for (int j = 0; j < listaAdyacencia.getSize(); j++) {
                Adycencia a = listaAdyacencia.get(j);
                aristas.append("\t{ from: ").append(i)
                        .append(", to: ").append(a.getDestination())
                        .append(", label: \"").append(a.getWeight()).append("\" },\n");
            }
        }
        vertices.append("]);\n");
        aristas.append("]);\n");

        System.out.println(vertices);
        System.out.println(aristas);

        String finalArchivo = "var container = document.getElementById(\"mynetwork\");\n" +
                "      var data = {\n" +
                "        nodes: nodes,\n" +
                "        edges: edges,\n" +
                "      };\n" +
                "      var options = {};\n" +
                "      var network = new vis.Network(container, data, options);";

        try {
            // Asegurarse de que las carpetas necesarias existan
            File filePath = new File(URL).getParentFile();
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            // Crear y escribir el archivo
            FileWriter file = new FileWriter(URL);
            file.write(vertices + "\n" + aristas + "\n" + finalArchivo);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}