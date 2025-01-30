package controller.Dao.servicies;

import controller.tda.list.LinkedList;
import models.RutaViaje;
import controller.Dao.RutaViajeDao;

public class RutaViajeServicies {
    private RutaViajeDao objRutaViajeDao;

    public RutaViajeServicies(){
        objRutaViajeDao = new RutaViajeDao();
    }

    // Método para guardar un objeto Ruta
    public Boolean save() throws Exception{
        return objRutaViajeDao.save();
    }

    // Método para actualizar un objeto Ruta
    public Boolean update() throws Exception {
        return objRutaViajeDao.update(); // Llamar al método update de RutaDao
    }

    // Método para eliminar un objeto Ruta por su ID
    public Boolean delete(Integer id) throws Exception {
        return objRutaViajeDao.delete(id); // Llamar al método delete de RutaDao
    }

    // Método para listar todos los objetos Ruta
    public LinkedList listAll() {
        return objRutaViajeDao.getlistAll(); // Obtener todos los objetos Ruta
    }

    // Método para obtener el objeto Ruta actualmente gestionado
    public RutaViaje getRutaViaje() {
        return objRutaViajeDao.getRutaViaje();
    }

    // Método para establecer el objeto Ruta que se gestionará
    public void setRutaViaje(RutaViaje rutaViaje) {
        objRutaViajeDao.setRutaViaje(rutaViaje);
    }

    // Método para obtener un objeto Ruta por su ID
    public RutaViaje get(Integer id) throws Exception {
        return objRutaViajeDao.get(id); // Obtener el objeto Ruta por su ID
    }
}
