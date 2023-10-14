/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Modelo;
import vista.ObjetoLista;
import vista.Vista;

/**
 *
 * @author Santiago
 */
public class Controlador {

    private final Modelo modelo;
    public final Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;

        //Codigo para obtener informacion de las acciones desde la vista
        this.vista.getAgregarNotaJButton().addActionListener(e -> this.agregarNotaAccion());
        this.vista.getBorrarNotaJButton().addActionListener(e -> this.borrarNotaAccion());

    }

    private void agregarNotaAccion() {

        String userInput = JOptionPane.showInputDialog("Ingresa el título de la nota:");
        if (userInput == null) {
            userInput = "Sin título";
        }

        //manipular modelo
        this.modelo.agregarNota(userInput, userInput);
        this.actualizarVista();
    }

    private void borrarNotaAccion() {
        //invertir lista
        List<Integer> lista = this.vista.getNotasSeleccionadas();
        Collections.reverse(lista);

        lista.forEach((i) -> {
            this.modelo.quitarNota(i);
        });
        
       
        this.actualizarVista();
        
    }


    private void actualizarVista() {

        /**
         * Recarga la vista con los datos del modelo, esto incluye la lista de
         * objetos y el texto de la nota actual
         */
        this.vista.limpiarLista();
        this.modelo.getNotasTitulos().entrySet().stream().map((entry) -> {
            return entry;
        }).forEachOrdered((entry) -> {
            ObjetoLista obj = new ObjetoLista(entry.getKey(), entry.getValue());
            obj.getTituloJLabel().addMouseListener(new MouseAdapter() {
                @Override
                
                //Guardar nota y actualizar en cada click en cambio de nota
                public void mouseClicked(MouseEvent e) {
                    modelo.getNotasContenido().put(vista.getNotaSeleccionadaID(),
                            vista.getNotaActualTexto());
                    vista.limpiarColoresLista();
                    obj.setAsNotaSeleccionada(true);
                    obj.getTituloJLabel().setForeground(Color.ORANGE);
                    vista.setNotaSeleccionada(entry.getKey());
                    vista.setNotaActual(modelo.getNotasContenido().get(entry.getKey()));

                }
            });
            this.vista.agregarNota(obj);
            this.vista.revalidarLista();

            this.vista.getNotaActualJTextArea().
                    setText(this.modelo.getNotasContenido().
                            get(this.vista.getNotaSeleccionadaID()));

        });
        if(this.modelo.getNotasTitulos().isEmpty()){
            this.vista.getNotaActualJTextArea().setText("");
        }
        
        

    }
}
