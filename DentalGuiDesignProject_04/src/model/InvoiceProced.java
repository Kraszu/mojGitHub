package model;
//Maciej Kubiniec R00144142
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InvoiceProced {
	int id;
	int invoiceNo;
	int procedureNo;
	
	public InvoiceProced(){
		
	}
	
	public InvoiceProced(int id, int invoiceNo, int procedureNo){
		setIpId(id);
		setInvNo(invoiceNo);
		setrocNo(procedureNo);
	}
	

    private final IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    public IntegerProperty idProperty() {
        return ID ;
    }
    public final int getIpId() {
        return idProperty().get();
    }
    public final void setIpId(int id) {
    	idProperty().set(id);
    }
    
    private final IntegerProperty InvoiceNo = new SimpleIntegerProperty(this, "InvoiceNo");
    public IntegerProperty inNoProperty() {
        return InvoiceNo ;
    }
    public final int getInvNo() {
        return inNoProperty().get();
    }
    public final void setInvNo(int invoiceNo) {
    	inNoProperty().set(invoiceNo);
    }
    
    private final IntegerProperty ProcedureNo = new SimpleIntegerProperty(this, "ProcedureNo");
    public IntegerProperty procNoProperty() {
        return ProcedureNo ;
    }
    public final int getProcNo() {
        return procNoProperty().get();
    }
    public final void setrocNo(int procedureNo) {
    	procNoProperty().set(procedureNo);
    }

    
	

}
