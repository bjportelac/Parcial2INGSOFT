/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import com.mycompany.parcialDos.ConnectionProvider;

import core.models.Trama;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author benga
 */
public class TramaDAO {
    public static final String SELECT_ALL =
            "SELECT IDSENSOR, LECTURA, FECHA FROM trama";
    public static final String CREATE = 
            "INSERT INTO trama(IDSENSOR, LECTURA, FECHA) VALUES (?,?,?) ";
    public static final String SELECT_IDS = 
            "SELECT IDSENSOR FROM trama ";
    public static final String SELECT_ONE = 
            "SELECT IDSENSOR, LECTURA, FECHA FROM trama WHERE IDSENSOR = ? ";

    
    private static final Connection connection = ConnectionProvider.connection;
    
    public static int create(Trama t){
        try (PreparedStatement statement = connection.prepareStatement(CREATE)){
                statement.setInt(1, t.getSensor_id());
                statement.setDouble(2,t.getLectura());
                statement.setTimestamp(3,t.getFecha());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }    
    }
    
    public static Trama findOne(int sensorID){
        Trama dummy = new Trama();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ONE)) {
            statement.setInt(1, sensorID);
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    if(resultset.next()){
                        dummy = new Trama(
                                resultset.getInt("IDSENSOR"),
                                resultset.getDouble("LECTURA"),
                                resultset.getTimestamp("FECHA")
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
    
    public static ArrayList<Integer> findAllIds(){
        ArrayList<Integer> data = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_IDS)) {
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    while(resultset.next()){
                        int d = resultset.getInt("IDSENSOR");
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
    
    
    public static ArrayList<Trama> findAll(){
        ArrayList<Trama> data = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    while(resultset.next()){
                        Trama dummy = new Trama(
                                resultset.getInt("IDSENSOR"),
                                resultset.getDouble("LECTURA"),
                                resultset.getTimestamp("FECHA")
                                );
                        data.add(dummy);
                    }
                }
            }  
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Unable to bring the types");
        }
        return data;
    }
        
}
