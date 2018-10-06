/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ControllerAgenda {

    ModelAgenda modelAgenda;
    ViewAgenda viewAgenda;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_nuevo)  {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_insertar){
                jbtn_insertar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_borrar){
                jbtn_borrar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_modificar){
               jbtn_modificar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_guardar){
                jbtn_guardar_actionPerformed();
            }
        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        initComponents();
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    public void initDB(){
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
    }
    /**
     * Metodo para inicializar la ViewAgenda
     */
    public void initComponents() {
        viewAgenda.setLocationRelativeTo(null);
        viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
        habilitar(false);
    }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    public void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbtn_nuevo.addActionListener(actionListener);
        viewAgenda.jbtn_insertar.addActionListener(actionListener);
        viewAgenda.jbtn_modificar.addActionListener(actionListener);
        viewAgenda.jbtn_guardar.addActionListener(actionListener);
        viewAgenda.jbtn_borrar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
        modelAgenda.moverPrimerRegistro();//invocar al metodo moverPrimerRegistro
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());//mostrar nombre en la vista
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());//mostar email en la vista
    }

    /**
     * Método para ver el registro anterior de la tabla contactos
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        modelAgenda.moverAnteriorRegistro();//invocar al metodo moverAnteriorRegistro
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());//mostrar nombre en la vista
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());//mostar email en la vista
    }

    /**
     * Método para ver el último registro de la tabla contactos
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        modelAgenda.moverUltimoRegistro();//invocar al metodo moverUltimoRegistro
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());//mostrar nombre en la vista
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());//mostar email en la vista
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        modelAgenda.moverSiguienteRegistro();//invocar al metodo moverSiguienteRegistro
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());//mostrar nombre en la vista
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());//mostar email en la vista
    }
    
    /**
     * Método para activar los jtextField y agregar un nuevo registro en la base de datos
     */
    private void jbtn_nuevo_actionPerformed(){    
        habilitar(true);
        this.viewAgenda.jtf_nombre.setText(null);
        this.viewAgenda.jtf_email.setText(null);
    }
    
    /**
     * Método para agregar un registro en la base de datos
     */
    private void jbtn_insertar_actionPerformed(){
        System.out.println("Action del boton jbtn_insertar");
        modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
        modelAgenda.setEmail(viewAgenda.jtf_email.getText());
        if (modelAgenda.getNombre().isEmpty() ||modelAgenda.getEmail().isEmpty())
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        else{
            modelAgenda.Insertar();
            habilitar(false);
        }
    }
    
    /**
     *Método para editar un registro existente en la base de datos 
     */
    private void jbtn_modificar_actionPerformed(){
        System.out.println("Action del boton jbtn_modificar");
        habilitar(true);
    }
    
    /**
     * Método para guardar cambios de un registro existente
     */
    private void jbtn_guardar_actionPerformed(){
        System.out.println("Action del boton jbtn_guardar");
        modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
        modelAgenda.setEmail(viewAgenda.jtf_email.getText());
        modelAgenda.Modificar();
        habilitar(false);
    }
    
    /**
     * Método para eliminar un registro en la base de datos
     */
    private void jbtn_borrar_actionPerformed(){
        System.out.println("Action del boton jbtn_borrar");
    }
    /**
     * Metodo para habilitar o deshabilitar los jTextField al interactuar con los botones
     * @param n 
     */
    private void habilitar(boolean n){
       this.viewAgenda.jtf_email.setEditable(n);
       this.viewAgenda.jtf_nombre.setEditable(n);
    }
}
