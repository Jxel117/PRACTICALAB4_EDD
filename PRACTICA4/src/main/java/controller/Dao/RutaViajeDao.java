package controller.Dao;

import models.RutaViaje;
import controller.Dao.implement.AdapterDao;
import controller.tda.list.LinkedList;

public class RutaViajeDao extends AdapterDao<RutaViaje> {
    private RutaViaje rutaViaje;
    private LinkedList<RutaViaje> listAll;

    public RutaViajeDao() {
        super(RutaViaje.class);
        this.listAll = new LinkedList<>();
    }

    public RutaViaje getRutaViaje() {
        if (rutaViaje == null) {
            rutaViaje = new RutaViaje();
        }
        return this.rutaViaje;
    }

    public void setRutaViaje(RutaViaje rutaViaje) {
        this.rutaViaje = rutaViaje;
    }

    public LinkedList<RutaViaje> getlistAll() {
        if (listAll.isEmpty()) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean update() throws Exception {
        try {
            this.merge(getRutaViaje(), getRutaViaje().getIdRuta() - 1);
            this.listAll = getlistAll();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        public Boolean save() throws Exception {
            Integer id = getlistAll().getSize() + 1;
            rutaViaje.setIdRuta(id);
            this.persist(this.rutaViaje);
            this.listAll = getlistAll();
            return true;
        }



    public Boolean delete(Integer id) throws Exception {
        LinkedList<RutaViaje> list = getlistAll();
        RutaViaje rutaViaje = get(id);
        if (rutaViaje != null) {
            list.remove(rutaViaje);
            String info = g.toJson(list.toArray());
            saveFile(info);
            this.listAll = list;
            return true;
        } else {
            System.out.println("La ruta de viaje con ese id " + id + " no encontrada.");
            return false;
        }
    }

    
    
}
