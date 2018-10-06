/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    private String nombre;
    private String email;
    private int id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Método que realiza las siguietnes acciones:
     * 1.- Conecta con la base agenda_mvc.
     * 2.- Consulta todo los registros de la tabla contactos.
     * 3.- Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "MarioNL", "MVC122018");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos;");
            rs.next();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }

    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro(){
        System.out.print("Programa accion moverPrimerRegistro");
        try{
            rs.first();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 002: " + err.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al siguiente registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverSiguienteRegistro(){
        System.out.print("Programa accion moverSiguienteRegistro");
        try{
            if(rs.isLast() == false){
                rs.next();
                nombre = rs.getString("nombre");
                email = rs.getString("email");
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null,"Error ModelAgenda003: " + err.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al anterior registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverAnteriorRegistro(){
        System.out.print("Programa accion moverAnteriorRegistro");
        try{
            if(rs.isFirst() == false){
               rs.previous();
               nombre = rs.getString("nombre");
               email = rs.getString("email");
            }
        } 
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 004: " + err.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro(){
        System.out.print("Programa accion moverUltimoRegistro");
        try{
            rs.last();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 005: " +  err.getMessage());
        }
    }
    
    /**
     *Método que inserta un nuevo registro que realiza lo siguiente:
     * 1.- Inserta un nuevo registro tomando los datos de la variable nombre y email
     * 2.- Realiza una conexion nuevamente con la base de datos
     * 3.- Muestra los datos del ultimo registro
     */
    public void Insertar(){
        try{
                st.executeUpdate("Insert into contactos (nombre,email)"+ "values( '"+nombre+"','"+email+"');");
                JOptionPane.showMessageDialog(null, "Contacto registrado");
                conectarDB();
                moverUltimoRegistro();
        }catch(SQLException err){
             JOptionPane.showMessageDialog(null, "Error ModelAgenda 006: " + err.getMessage());
        }
    }
    
    /**
     * Método que realiza lo siguiente:
     * 1.- Toma y almacena el numero del registro actual.
     * 2.- Actualiza los valores de nombe y email de dicho registro.
     * 3.- Envia un mensaje de actualizacion.
     * 4.- Realiza una nueva conexion a la base de datos.
     * 5.- Muestra el registro con la actualizacion hecha.
     */
    public void Modificar(){
        try{
            id = rs.getInt("id_contacto");
            st.executeUpdate("update contactos set nombre = '"+ nombre +"', email = '"+ email +"' where id_contacto = "+ id +"; ");
            JOptionPane.showMessageDialog(null, "Agenda actualizada");
            st.executeQuery("select * from contactos");
            conectarDB();
            rs.absolute(id);
        } catch(SQLException err){
            JOptionPane.showMessageDialog(null,"ErrorModelAgenda 007: " + err.getMessage());
        }
    }
    
    /**
     * Método que permite lo siguiente:
     * 1.- Toma el valor del contacto actual.
     * 2.- Elimina el dato de la tabla y envia un mensaje de actualizacion.
     * 3.- ctualiza la base de datos y se reinicia la conexion con la base de datos.
     * 4.- Mostrar los datos de el contacto anterior.
     */
    public void Borrrar(){
        JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro?");
        if(JOptionPane.OK_OPTION == 0){
            try{
                id = rs.getInt("id_contacto");
                st.executeUpdate("delete from contactos where id_contacto =" + id + "; ");
                JOptionPane.showMessageDialog(null, "Agenda actualizada, el registro ha sido eliminado");
                st.executeQuery("select * from contactos");
                conectarDB();
                rs.first();
            }
            catch(SQLException err){
               JOptionPane.showMessageDialog(null, "Error ModelAgenda 008:" + err.getMessage());
            }
        }
    }
}
