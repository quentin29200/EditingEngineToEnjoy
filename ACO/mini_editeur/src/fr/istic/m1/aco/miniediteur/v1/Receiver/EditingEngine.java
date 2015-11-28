package fr.istic.m1.aco.miniediteur.v1.Receiver;


public interface EditingEngine {
   public void copy();
   public void paste();
   public void cut();
   public void select(int start, int length);
   public void enterchar(char c);
   public void remove();
   public Buffer getBuffer();
   public Select returnSelect();
}