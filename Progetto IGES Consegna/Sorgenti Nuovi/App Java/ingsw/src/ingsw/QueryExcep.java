/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingsw;
import java.sql.*;
/**
 *
 * @author Dario
 */
class QueryExcep extends SQLException {
    String err;    
    QueryExcep() {
            super();
    }
    QueryExcep(String err) {
            this.err=err;
    }
    @Override
    public String getMessage() {
        return err;
    }
}

