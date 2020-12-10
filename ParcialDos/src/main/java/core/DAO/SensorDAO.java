/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import com.mycompany.parcialDos.ConnectionProvider;
import core.models.Sensor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author benga
 */
public class SensorDAO {
    public static final String SELECT_ALL =
            "SELECT ID, UBICACION, TIPO FROM sensor ";
    public static final String SELECT_IDS = 
            "SELECT ID FROM `sensor` ";
    public static final String SELECT_ONE = 
            "SELECT ID, UBICACION, TIPO FROM `sensor` WHERE ID = ?";
    public static final String CREATE = 
            "INSERT INTO `sensor`(ID, UBICACION, TIPO) VALUES (?,?,?)";
    public static final String DELETE =
            "DELETE FROM `sensor` WHERE ID = ?";
    public static final String UPDATE =
            "UPDATE `sensor` SET UBICACION = ?,TIPO = ? WHERE ID = ?";
    
    private static final Connection connection = ConnectionProvider.connection;
             
    public static int create(Sensor s){
        try (PreparedStatement statement = connection.prepareStatement(CREATE)){
            statement.setInt(1,s.getId_sensor());
            statement.setString(2, s.getUbicacion());
            statement.setString(3, s.getTipo());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }    
    }
    
    public static Sensor findOne(int sensorID){
        Sensor dummy = new Sensor();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ONE)) {
            statement.setInt(1, sensorID);
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    if(resultset.next()){
                        dummy = new Sensor(
                                resultset.getInt("ID"),
                                resultset.getString("UBICACION"),
                                resultset.getString("TIPO")
                        );
                    }
                }catch (SQLException ex){
                     System.out.println(ex);
                }
            }  
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return dummy;
    }
    
    public static ArrayList<Sensor> findAll(){
        ArrayList<Sensor> data = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    while(resultset.next()){
                        Sensor dummy;
                        dummy = new Sensor(
                                resultset.getInt("ID"),
                                resultset.getString("UBICACION"),
                                resultset.getString("TIPO"));
                        data.add(dummy);
                    }
                }
            }  
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Unable to bring the sensors");
        }
        return data;
    }
    
    public static ArrayList<Integer> findAllIds(){
        ArrayList<Integer> data = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_IDS)) {
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    while(resultset.next()){
                        int d = resultset.getInt("ID");
                        data.add(d);
                    }
                }
            }  
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Unable to bring the sensors ids");
        }
        return data;
    }
    
    public static int update(Sensor s){
        try(PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, s.getUbicacion());
            statement.setString(2, s.getTipo());
            statement.setInt(3, s.getId_sensor());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
    
    public static int delete(int id_sensor){
        try(PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id_sensor);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
}
