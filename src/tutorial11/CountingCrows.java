/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial11;

import javafx.concurrent.Task;
import javafx.scene.control.TextField;

/**
 *
 * @author becky
 */
public class CountingCrows extends Task<Void> {
    
    private int counter;
    private TextField tempField;
    
    CountingCrows(TextField inputField)
    {
        counter = 0;
        this.tempField = inputField;
    }
    
    

    @Override
    protected Void call() throws Exception {
        
        for(int ii = 0; ii < 100; ii += 1)
        {
            
            //this.tempField.setText(String.valueOf(ii));
            
            this.updateMessage(String.valueOf(ii));
            System.out.println("index: "+ ii);
            Thread.sleep(1000);
            
        }
        
        
        return null;
    }

//    @Override
//    public void run() {
//        RunTask();
//    }
//    
//    public void RunTask()
//    {
//        for(int ii = 0; ii < 100; ii += 1)
//        {
//            
//            this.tempField.setText(String.valueOf(ii));
//            
//            //this.updateMessage(String.valueOf(ii));
//            System.out.println("index: "+ ii);
//            
//            
//        }
//    }

    
}
