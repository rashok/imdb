/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imdb.util;

/**
 *
 * @author Valter
 */
public class Clock implements Runnable {

    int time = 0;

    public Clock() {}

    public void run() {
        while (time != 100) {
            System.out.println(time++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

        }
    }
}
