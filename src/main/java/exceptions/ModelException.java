/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author gabriel
 */
public class ModelException extends RuntimeException{
    
    private final String messageError;
    
    public ModelException(String messageError){
        super();
        this.messageError = messageError;
    }
    
    @Override
    public String getMessage(){
        return messageError;
    }
    
}
