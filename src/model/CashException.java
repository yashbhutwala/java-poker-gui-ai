/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Spring 2015
 *
 * FINAL PROJECT
 * Team: Big Java
 * Date: Apr 14, 2015
 * Time: 7:37:27 PM
 *
 * Project: csci205FinalProject
 * Package: model
 * File: CashException
 * Description:
 *  General exception for use in betting and lose conditions
 * **************************************** */
package model;

/**
 *
 * @author Morgan Eckenroth
 */
public class CashException extends Exception {

    public CashException(String message) {
        super(message);
    }
}
