/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package one.digitalinnovation.personapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author viniberaldo
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {
    
    public PersonNotFoundException(long id){
        super("Person not found with id" + id);
    }
    
}
