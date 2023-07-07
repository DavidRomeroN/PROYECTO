/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import pe.edu.upeu.app.modelo.UserTO;

/**
 *
 * @author ACER
 */
public interface UserDaoI {
    
    public int create(UserTO u);
    public int delete(UserTO u);
    public boolean login(UserTO u);
    
    
}
