package fr.istic.m1.aco.miniediteur.v2.Receiver;

public class Buffer {
  	private StringBuffer areaTxt;

	public Buffer() {
		this.areaTxt = new StringBuffer();
		this.areaTxt.append("");
	}

	public String toString() {
		return areaTxt.toString();
	}


	public StringBuffer getAreaTxt() {
	return areaTxt;
}



	public void setAreaTxt(StringBuffer areaTxt) {
		this.areaTxt = areaTxt;
	}

}