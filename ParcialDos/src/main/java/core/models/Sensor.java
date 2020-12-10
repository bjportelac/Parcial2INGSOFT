/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.models;

/**
 *
 * @author benga
 */
public class Sensor {
    int id_sensor;
    String ubicacion;
    String tipo;

    public Sensor(int id_sensor, String ubicacion, String tipo) {
        this.id_sensor = id_sensor;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }

    public Sensor(String ubicacion, String tipo) {
        this.ubicacion = ubicacion;
        this.tipo = tipo;
    }
    
    

    public Sensor() {
        
    }

    public int getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(int id_sensor) {
        this.id_sensor = id_sensor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
