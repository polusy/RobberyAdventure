/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adventure.utilities;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author utente
 */
public class SecurityCameraThread extends Thread {
    static public Lock policeArrivalLock = new ReentrantLock();
    private boolean alreadyActivated = false;
    
    public void run(){
        try{
            alreadyActivated = true;
            policeArrivalLock.tryLock(50, TimeUnit.SECONDS);
            
        } catch (InterruptedException exception){};
    }

    public boolean isAlreadyActivated() {
        return alreadyActivated;
    }
    
    
}
