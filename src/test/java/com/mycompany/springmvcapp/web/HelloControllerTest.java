/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springmvcapp.web;

/**
 *
 * @author George Shumakov
 */
import com.mycompany.springmvcapp.controllers.ConnectionController;
import org.springframework.web.servlet.ModelAndView; 
import static org.junit.Assert.*; 
import org.junit.Test; 

public class HelloControllerTest { 

    @Test 
    public void testHandleRequestView() throws Exception { 
        ConnectionController controller = new ConnectionController(); 
        ModelAndView modelAndView = controller.handleRequest(null, null); 
        assertEquals("hello.jsp", modelAndView.getViewName()); 
    } 
}