package fr.istic.m1.aco.miniediteur.v1.Receiver;

public class Select {
    private int begin;
    private int length;


    // Getter / Setter
    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length > 0) {
            this.length = length;
        } else {
            this.length = 0;
        }
    }


}
