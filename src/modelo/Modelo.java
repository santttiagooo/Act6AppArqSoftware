/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Santiago
 */
public class Modelo {


    private final Map<Integer, String> notasTitulos;
    private final Map<Integer, String> notasContenido;
    private int cantidadDeNotas;

    public Modelo() {
        this.notasTitulos = new HashMap<>();
        this.notasContenido = new HashMap<>();
        this.cantidadDeNotas = 0;

    }



    public Map<Integer, String> getNotasTitulos() {
        return notasTitulos;
    }

    public Map<Integer, String> getNotasContenido() {
        return notasContenido;
    }

    public void agregarNota(String notaTitulo, String notaTxt) {
        this.cantidadDeNotas++;
        this.notasTitulos.put(this.cantidadDeNotas, notaTitulo);
        this.notasContenido.put(this.cantidadDeNotas, notaTxt);
    }

    public void quitarNota(int notaID) {
        if (this.notasTitulos.containsKey(notaID)) {
            if (this.notasContenido.containsKey(notaID)) {
                notasTitulos.remove(notaID);
                notasContenido.remove(notaID);
                reorganizarNotas(notaID);
                notasTitulos.remove(this.cantidadDeNotas);
                notasContenido.remove(this.cantidadDeNotas);
                this.cantidadDeNotas--;
            }
        }
    }

    private void reorganizarNotas(int notaID) {

        for (int i = notaID; i <= this.cantidadDeNotas; i++) {
            notasTitulos.put(i, notasTitulos.get(i + 1));
            notasContenido.put(i, notasContenido.get(i + 1));
        }

    }

    @Override
    public String toString() {
        return "Modelo{"  + ", notasTitulos=" + notasTitulos + ", notasContenido=" + notasContenido + ", cantidadDeNotas=" + cantidadDeNotas + '}';
    }

}
