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
public class Trama {
    int sensor_id;
    double lectura;
    Timestamp fecha;

    public Trama(int sensor_id, double lectura, Timestamp fecha) {
        this.sensor_id = sensor_id;
        this.lectura = lectura;
        this.fecha = fecha;
    }

    public Trama() {
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
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }
    
    
}
