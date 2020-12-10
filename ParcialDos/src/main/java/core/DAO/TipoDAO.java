/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.DAO;

/**
 *
 * @author benga
 */
import com.mycompany.parcialDos.ConnectionProvider;
import core.models.Tipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoDAO {
    public static final String SELECT_ALL =
            "SELECT ID_TIPO,TIPO, NOMBRE, MINIMO, MAXIMO, PROMEDIO, NUMHORAS FROM tipo";
    public static final String SELECT_TIPOS = 
            "SELECT TIPO FROM tipo";
    public static final String SELECT_ONE = 
            "SELECT ID_TIPO,NOMBRE, MINIMO, MAXIMO, PROMEDIO, NUMHORAS FROM tipo WHERE ID_TIPO = ?";
    public static final String CREATE = 
            "INSERT INTO tipo(TIPO, NOMBRE, MINIMO, MAXIMO, PROMEDIO, NUMHORAS) VALUES (?,?,?,?,?,?) ";
    public static final String DELETE =
            "DELETE FROM tipo WHERE ID_TIPO = ?";
    public static final String UPDATE =
            "UPDATE tipo SET NOMBRE = ?, MINIMO = ?,MAXIMO = ?,PROMEDIO = ?,NUMHORAS = ? WHERE ID_TIPO = ?";
    
    private static final Connection connection = ConnectionProvider.connection;
    
    public static int create(Tipo t){
        try (PreparedStatement statement = connection.prepareStatement(CREATE)){
                statement.setString(1, t.getTipo());
                statement.setString(2,t.getNombre());
                statement.setInt(3,t.getMinimo());
                statement.setInt(4,t.getMaximo());
                statement.setString(5,t.getPromedio());
                statement.setDouble(6,t.getNum_horas());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }    
    }
    
    public static Tipo findOne(int id){
        Tipo dummy = new Tipo();
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ONE)) {
            statement.setInt(1, id);
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    if(resultset.next()){
                        dummy = new Tipo(
                                resultset.getString("TIPO"),
                                resultset.getString("NOMBRE"),
                                resultset.getInt("MINIMO"),
                                resultset.getInt("MAXIMO"),
                                resultset.getString("PROMEDIO"),
                                resultset.getDouble("NUMHORAS")
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
    
    public static ArrayList<Tipo> findAll(){
        ArrayList<Tipo> data = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    while(resultset.next()){
                        Tipo dummy = new Tipo(
                                resultset.getString("TIPO"),
                                resultset.getString("NOMBRE"),
                                resultset.getInt("MINIMO"),
                                resultset.getInt("MAXIMO"),
                                resultset.getString("PROMEDIO"),
                                resultset.getDouble("NUMHORAS")
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
    
    public static ArrayList<String> findAllTypes(){
        ArrayList<String> data = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(SELECT_TIPOS)) {
            if(statement.execute()){
                 try(ResultSet resultset = statement.getResultSet()){
                    while(resultset.next()){
                        String d = resultset.getString("TIPO");
                        data.add(d);
                    }
                }
            }  
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Unable to bring the sensors types");
        }
        return data;
    }
    
    public static int update(Tipo t){
        try(PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(6, t.getTipo());
            statement.setString(1,t.getNombre());
            statement.setInt(2,t.getMinimo());
            statement.setInt(3,t.getMaximo());
            statement.setString(4,t.getPromedio());
            statement.setDouble(5,t.getNum_horas());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
    
    public static int delete(String tipo){
        try(PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setString(1, tipo);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
}
