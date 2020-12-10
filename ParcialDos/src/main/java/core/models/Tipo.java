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
public class Tipo {
    String tipo;
    String nombre;
    int minimo;
    int maximo;
    String promedio;
    double num_horas;

    public Tipo(String tipo, String nombre, int minimo, int maximo, String promedio, double num_horas) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.minimo = minimo;
        this.maximo = maximo;
        this.promedio = promedio;
        this.num_horas = num_horas;
    }

    public Tipo() {
    }
    
    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMinimo() {
        return minimo;
    }

    public void setMinimo(int minimo) {
        this.minimo = minimo;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public String getPromedio() {
        return promedio;
    }

    public void setPromedio(String promedio) {
        this.promedio = promedio;
    }

    public double getNum_horas() {
        return num_horas;
    }

    public void setNum_horas(double num_horas) {
        this.num_horas = num_horas;
    }
    
    
}
