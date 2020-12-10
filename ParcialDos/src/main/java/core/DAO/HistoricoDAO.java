/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

import com.mycompany.parcialDos.ConnectionProvider;
import core.models.Historico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author benga
 */
public class HistoricoDAO {
    public static final String SELECT_ALL =
            "SELECT ID, IDSENSOR, LECTURA, FECHA, PROCESAMIENTO FROM historico";
    public static final String SELECT_DATES =
            "SELECT * FROM historico WHERE IDSENSOR = ? ORDER BY FECHA DESC LIMIT 5 ";
    public static final String SELECT_MAXONE=
            "SELECT * FROM historico WHERE IDSENSOR = ? ORDER BY FECHA DESC LIMIT 1";
    public static final String SELECT_IDS = 
            "SELECT ID FROM historico ";
    public static final String SELECT_ONE = 
            "SELECT ID, IDSENSOR, LECTURA, FECHA, PROCESAMIENTO FROM historico WHERE ID = ?";
    public static final String CREATE = 
            "INSERT INTO historico(IDSENSOR, LECTURA, FECHA, PROCESAMIENTO) VALUES (?,?,?,?) ";
    public static final String DELETE =
            "DELETE FROM historico WHERE ID = ?";
    public static final String UPDATE =
            "UPDATE historico SET IDSENSOR = ?,LECTURA = ?,FECHA = ?,PROCESAMIENTO = ? WHERE ID = ?";
    
    private static final Connection connection = ConnectionProvider.connection;
    
    public static int create(Historico h){
        try (PreparedStatement statement = connection.prepareStatement(CREATE)){
            statement.setInt(1,h.getSensor_id());
            statement.setDouble(2,h.getLectura());
            statement.setTimestamp(3,h.getFecha());
            statement.setString(4,h.getProcesamiento());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }    
    }
    
    public static Historico findOne(int sensorID){
        Historico dummy = new Historico();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_MAXONE)) {
            statement.setInt(1, sensorID);
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    if(resultset.next()){
                        dummy = new Historico(
                                resultset.getInt("ID"),
                                resultset.getInt("IDSENSOR"),
                                resultset.getDouble("LECTURA"),
                                resultset.getTimestamp("FECHA"),
                                resultset.getString("PROCESAMIENTO")
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
    
    public static ArrayList<Historico> findAll(){
        ArrayList<Historico> data = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    while(resultset.next()){
                        Historico dummy = new Historico(
                                resultset.getInt("ID"),
                                resultset.getInt("IDSENSOR"),
                                resultset.getDouble("LECTURA"),
                                resultset.getTimestamp("FECHA"),
                                resultset.getString("PROCESAMIENTO"));
                        data.add(dummy);
                    }
                }
            }  
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Unable to bring the history");
        }
        return data;
    }
    
    public static ArrayList<Historico> findAllDate(int id){
        ArrayList<Historico> data = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_DATES)) {
            statement.setInt(1, id);
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    while(resultset.next()){
                        Historico dummy;
                        dummy = new Historico(
                                resultset.getInt("ID"),
                                resultset.getInt("IDSENSOR"),
                                resultset.getDouble("LECTURA"),
                                resultset.getTimestamp("FECHA"),
                                resultset.getString("PROCESAMIENTO"));
                        data.add(dummy);
                    }
                }
            }  
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Unable to bring the history");
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
    
    
    
    public static int update(Historico h){
        try(PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1,h.getSensor_id());
            statement.setDouble(2,h.getLectura() );
            statement.setTimestamp(3,h.getFecha());
            statement.setString(4,h.getProcesamiento() );
            statement.setInt(5,h.getId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
    
    public static int delete(int idhistorico){
        try(PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, idhistorico);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
    
}
