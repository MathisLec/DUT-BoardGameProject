/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Exceptions;

/**
 *
 * @author TheThisma
 */
public class InsufficientRessourcesException extends Exception {

    /**
     * Creates a new instance of <code>InsufficientRessourcesException</code>
     * without detail message.
     */
    public InsufficientRessourcesException() {
        
    }

    /**
     * Constructs an instance of <code>InsufficientRessourcesException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InsufficientRessourcesException(String msg) {
        super(msg);
    }
}
