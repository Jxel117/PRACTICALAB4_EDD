package com.example.rest;

import controller.Dao.servicies.RutaViajeServicies;
import java.util.HashMap;
import controller.tda.list.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import models.RutaViaje;
import com.google.gson.Gson;

@Path("rviaje")
public class RutaViajeApi {

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRutas() {
        HashMap<String, Object> map = new HashMap<>();
        RutaViajeServicies ps = new RutaViajeServicies();
        LinkedList<RutaViaje> rutas = ps.listAll();
        
        map.put("msg", "Ok");
        map.put("data", rutas.toArray());
        
        if (rutas.isEmpty()) {
            map.put("data", new Object[] {});
        }
        
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRutaViaje(@PathParam("id") Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        RutaViajeServicies ps = new RutaViajeServicies();
        
        try {
            RutaViaje ruta = ps.get(id);
            if (ruta != null) {
                map.put("msg", "Ok");
                map.put("data", ruta);
                return Response.ok(map).build();
            } else {
                map.put("msg", "No se encontró la ruta con ese identificador");
                return Response.status(Status.NOT_FOUND).entity(map).build();
            }
        } catch (Exception e) {
            map.put("msg", "Error");
            map.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(map).build();
        }
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        RutaViajeServicies ps = new RutaViajeServicies();
        
        try {
            RutaViaje ruta = new RutaViaje();
            ruta.setIdRuta(Integer.parseInt(map.get("idRuta").toString()));
            ruta.setNombre(map.get("nombre").toString());
            ruta.setOrigen(map.get("origen").toString());
            ruta.setDestino(map.get("destino").toString());
            ruta.setDistancia(map.get("distancia").toString());
            
            ps.setRutaViaje(ruta);
            ps.save();
            
            res.put("msg", "Ok");
            res.put("data", "Ruta guardada correctamente");
            return Response.ok(res).build();
            
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        RutaViajeServicies ps = new RutaViajeServicies();
        
        try {
            RutaViaje ruta = new RutaViaje();
            ruta.setIdRuta(Integer.parseInt(map.get("idRuta").toString()));
            ruta.setNombre(map.get("nombre").toString());
            ruta.setOrigen(map.get("origen").toString());
            ruta.setDestino(map.get("destino").toString());
            ruta.setDistancia(map.get("distancia").toString());
            
            ps.setRutaViaje(ruta);
            ps.update();
            
            res.put("msg", "Ok");
            res.put("data", "Ruta actualizada correctamente");
            return Response.ok(res).build();
            
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(HashMap<String, Object> map) {
        HashMap<String, Object> res = new HashMap<>();
        RutaViajeServicies ps = new RutaViajeServicies();
        
        try {
            Integer idRuta = Integer.parseInt(map.get("idRuta").toString());
            Boolean success = ps.delete(idRuta);
            
            if (success) {
                res.put("msg", "Ok");
                res.put("data", "Ruta eliminada correctamente");
                return Response.ok(res).build();
            } else {
                res.put("msg", "Error");
                res.put("data", "No se encontró la ruta con ese identificador");
                return Response.status(Status.NOT_FOUND).entity(res).build();
            }
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}