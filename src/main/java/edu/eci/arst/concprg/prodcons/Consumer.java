/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread {

    private Queue<Integer> queue;
    private Producer pro;

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
        pro = new Producer(queue,100);
        pro.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized(pro){
                try{
                    pro.wait();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            
            while  (queue.size() > 0) {
                int elem=queue.poll();
                System.out.println("Consumer consumes "+elem);    
                try {
                    Thread.sleep(15);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }                            
            }
            
        }
    }
}
