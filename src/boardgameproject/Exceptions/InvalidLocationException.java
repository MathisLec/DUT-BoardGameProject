/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardgameproject.Exceptions;

/**
 *
 * @author mlecoeuvre
 */
public class InvalidLocationException extends Exception {

    /**
     * Creates a new instance of <code>NotValidLocationException</code> without
     * detail message.
     */
    public InvalidLocationException() {
    }

    /**
     * Constructs an instance of <code>NotValidLocationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidLocationException(String msg) {
        super(msg);
    }
}
