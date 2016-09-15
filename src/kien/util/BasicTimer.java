/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kien.util;

/**
 *
 * @author 6316036
 */
public class BasicTimer {
    private int f;
    private long t;
    private long cur;
    private long last;
    
    public BasicTimer() {
        this(60);
    }
    
    public BasicTimer(int framerate) {
        this.f = framerate;
        this.t = 1000000000/framerate;
        this.cur = 0;
        this.last = System.nanoTime();
    }
    
    public void changeFrameRate(int framerate){
        this.f = framerate;
        this.t = 1000000000/framerate;
    } 
    
    public int getFrameRate() {
        return this.f;
    }
    
    public void updateTimer() {
        long now = System.nanoTime();
        this.cur += (now - last);
        this.last = now;
    }
    
    public boolean isTickTiming() {
        if (cur>=t){
            cur -= t;
            return true;
        }
        return false;
    }
    
    
}
