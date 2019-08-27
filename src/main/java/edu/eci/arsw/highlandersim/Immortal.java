package edu.eci.arsw.highlandersim;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.swing.text.StyledEditorKit.BoldAction;

public class Immortal extends Thread {

    private ImmortalUpdateReportCallback updateCallback=null;
    
    private int health;
    
    private int defaultDamageValue;

    private final CopyOnWriteArrayList<Immortal> immortalsPopulation;

    private final String name;

    private final Random r = new Random(System.currentTimeMillis());

    private volatile Boolean flag = true;
    

    private volatile Boolean vivo = true;


    public Immortal(String name, List<Immortal> immortalsPopulation, int health, int defaultDamageValue, ImmortalUpdateReportCallback ucb) {
        super(name);
        this.updateCallback=ucb;
        this.name = name;
        this.immortalsPopulation = (CopyOnWriteArrayList<Immortal>) immortalsPopulation;
        this.health = health;
        this.defaultDamageValue=defaultDamageValue;
    }

    public void run() {
        while (vivo && !ControlFrame.stop ){
        	synchronized(this) {
        		if (ControlFrame.isPausa()) {                          
    	             try {
	                    wait();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	    
               }
        	}
        	Immortal im;
            
            int myIndex = immortalsPopulation.indexOf(this);

            int nextFighterIndex = r.nextInt(immortalsPopulation.size());

            //avoid self-fight
            if (nextFighterIndex == myIndex) {
                nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
            }

            im = immortalsPopulation.get(nextFighterIndex);

            this.fight(im);
            
            
        }
            
          
    
        
        
    }


    public synchronized void resumeRunning() {
    
    	this.notify();
        
    }
     

    public void fight(Immortal i2) {
    	
    	Immortal im1 = getId()>i2.getId()?this:i2;
        Immortal im2 = getId()>i2.getId()?i2:this;
        
        synchronized(im1){
            synchronized(im2){
                if (i2.getHealth() > 0) {

                    i2.changeHealth(i2.getHealth() - defaultDamageValue);
                    this.health += defaultDamageValue;
                    updateCallback.processReport("Fight: " + this + " vs " + i2+"\n");
                } else {
                    
                    updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");
                }
            
            }
        }
            
    }
        
    

    public void changeHealth(int v) {
        health = v;
        /*if (health <=  0){
            vivo = false;
            immortalsPopulation.remove(this);
        }*/

    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {

        return name + "[" + health + "]";
    }

}
