/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.grupocampanha.canhoto;

/**
 *
 * @author victor
 */
public class Counter {

    private long tempoInicial = 0;
    private float tempoLimite = 0.46f;
    private int count = 0;
    private int maxCount;
    private boolean stoped = true;
    private float totalTime;
    public Counter(float tempoLimite, int maxCount) {
        this.tempoLimite = tempoLimite;
        this.maxCount = maxCount;
    }

    public void reset() {

        this.tempoInicial = 0;
        this.count = 0;
        stoped = true;
    }

    public void count() {
        count++;
totalTime = (System.currentTimeMillis() - tempoInicial) / 1000f;
    }
public int getCount(){
return this.count;
}
public boolean isInTime(){
return tempoLimite ==-1 || totalTime <= tempoLimite;
}
    public void init() {
        stoped = false;
        tempoInicial = System.currentTimeMillis();
    }

    public boolean isComplete(){
    return count >= maxCount;
    }
public float getCurrentTime(){
return  totalTime;
}
    public boolean isStoped() {
        return stoped;
    }
}
