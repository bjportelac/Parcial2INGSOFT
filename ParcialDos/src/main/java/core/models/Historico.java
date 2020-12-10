/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.models;

import java.sql.Timestamp;

/**
 *
 * @author benga
 */
public class Historico {
    int id;
    int sensor_id;
    double lectura;
    Timestamp Fecha;
    String procesamiento;

    public Historico(int id, int id_sensor, double lectura, Timestamp Fecha, String procesamiento) {
        this.id = id;
        this.sensor_id = id_sensor;
        this.lectura = lectura;
        this.Fecha = Fecha;
        this.procesamiento = procesamiento;
    }

    public Historico(int id_sensor, double lectura, Timestamp Fecha, String procesamiento) {
        this.sensor_id = id_sensor;
        this.lectura = lectura;
        this.Fecha = Fecha;
        this.procesamiento = procesamiento;
    }

    public Historico() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }


    public double getLectura() {
        return lectura;
    }

    public void setLectura(double lectura) {
        this.lectura = lectura;
    }

    public Timestamp getFecha() {
        return Fecha;
    }

    public void setFecha(Timestamp Fecha) {
        this.Fecha = Fecha;
    }

    public String getProcesamiento() {
        return procesamiento;
    }

    public void setProcesamiento(String procesamiento) {
        this.procesamiento = procesamiento;
    }
    
    
    
}
