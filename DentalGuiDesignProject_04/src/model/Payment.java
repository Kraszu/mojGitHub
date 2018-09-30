package model;
//Maciej Kubiniec R00144142
import java.io.Serializable;
import java.util.*;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	int paymentN,id,invoID;
    double paymentAmount;
    Date paymentDate;

    public Payment (int id, int invoID, double paymentAmount){
        
        setID(id);
        setInvID(invoID);
        setPaymentAmount(paymentAmount);
    }

//    public String toString(){
//        String out="";
//        out="\t\nPayment Details:"
//        	+"\nNumber (as invoice number):\t"+this.paymentN
//       	+"\nAmount:\t\t\t\t\t\u20ac"+this.paymentAmount
//        	+"\nDate:\t\t\t\t\t"+this.paymentDate+"\n";
//        return out;
//    }
//    public void print() {
//		System.out.println(toString());
//	}
    
    
    private final IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    public IntegerProperty idProperty() {
        return ID ;
    }
    public final int getID() {
        return idProperty().get();
    }
    public final void setID(int id) {
    	idProperty().set(id);
    }
    
    private final IntegerProperty ino = new SimpleIntegerProperty(this, "ino");
    public IntegerProperty inoProperty() {
        return ino ;
    }
    public final int getInvID() {
        return inoProperty().get();
    }
    public final void setInvID(int invoID) {
    	inoProperty().set(invoID);
    }
  
    private final DoubleProperty am = new SimpleDoubleProperty(this, "am");
    public DoubleProperty amProperty() {
        return am ;
    }
    public final double getPaymentAmount() {
        return amProperty().get();
    }
    public final void  setPaymentAmount(double paymentAmount) {
    	amProperty().set(paymentAmount);
    }

       
}
