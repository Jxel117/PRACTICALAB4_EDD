// package com.example.rest;

// import java.util.HashMap;
// import java.util.Map;
// import javax.ws.rs.GET;
// import javax.ws.rs.Path;
// import javax.ws.rs.Produces;
// import javax.ws.rs.core.MediaType;
// import javax.ws.rs.core.Response;  // Import correcto para Response
// import javax.ws.rs.core.Response.Status;

// import controller.Dao.servicies.SinteticaServicies;
// import controller.tda.list.LinkedList;
// import controller.tda.list.graph.GraphLabelNoDirect;
// import models.Sintetica;

// @Path("myresource")
// public class MyResource {

//     /**
//      * Method handling HTTP GET requests. The returned object will be sent
//      * to the client as "text/plain" media type.
//      *
//      * @return String that will be returned as a text/plain response.
//      */
    
//     @GET
//     @Produces(MediaType.APPLICATION_JSON)
//     public Response getIt() {        
//         HashMap mapa = new HashMap<>();        
//         GraphLabelNoDirect graph = new GraphLabelNoDirect<>(5, String.class);
//         try{
//             SinteticaServicies ps = new SinteticaServicies();
//             LinkedList<Sintetica> lp = ps.listAll();
//             if (!lp.isEmpty()) {
//                 graph = new GraphLabelNoDirect<>(lp.getSize(), Sintetica.class);
//                 Sintetica[] m = lp.toArray();
//                 for (int i = 0; i < lp.getSize(); i++) {
//                     graph.labelsVerticeL((i+1), m[i]);
//                 }
//             }
        
//         graph.drawGraph();

//         } catch(Exception e){
//             mapa.put("msg", "Ok");
//             mapa.put("data", e.toString());
//             return Response.status(Status.BAD_REQUEST).entity(mapa).build();
//         }
//         System.out.println(graph.toString());
        
//         mapa.put("msg", "OK");        
//         mapa.put("data", graph.toString());
//         return Response.ok(mapa).build();
//     }
// }
