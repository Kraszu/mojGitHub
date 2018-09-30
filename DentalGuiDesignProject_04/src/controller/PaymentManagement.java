package controller;
//Maciej Kubiniec R00144142

import model.Invoice;
import model.Patient;
import view.AlertBox;
import java.util.ArrayList;
import java.util.Iterator;
import controller.Controller;
import database.InvoiceDB;
import database.PatientDB;
import database.PaymentDB;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PaymentManagement extends VBox{
    double textToDouble=20.0;
    double newPayment=0.0;
	int invoiceNo=0;
    ListView<String> listView= new ListView<String>();
	int lastPayment;
	int selected;
	ArrayList <Invoice> invList = InvoiceDB.getInstance().selectInvoices();
	ComboBox<String> initialInvoice = populateList();
	
	public PaymentManagement(){
		
		initialInvoice.setOnAction(exe -> {		
            
	        Text tb = new Text();    
	        
			try{
				for(Iterator<Invoice> iter = invList.iterator(); iter.hasNext();) {
					Invoice p=iter.next();
					int s= p.getInvoice();
					int invNum = initialInvoice.getValue().charAt(0)-48;
					System.out.println(invNum);
					if(invNum==s){
						selected = p.getInvoice();
					}
				}

			}catch(Exception exc){
				tb.setText("issue in RETRIEVING INVOICE NUMBER");
			}
  
		});

		Button button = new Button("Add Payment");
		button.setStyle("-fx-font-size: 10pt;-fx-background-color: #0066cc;-fx-text-fill:#ffffff;"
				+ "-fx-font-weight: bold;");
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		button.setOnAction(e -> {
			Stage window = new Stage();//create a new stage
			// Block events to other windows
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Add Payment");
			window.setMinWidth(350);
			window.setMinHeight(400);
			
			Label label = new Label();
			label.setText("PLEASE ENTER PAYMENT DETAILS");       	        	        
	        	        
	        TextField tfAmount = new TextField();
	        tfAmount.setPromptText("Amount:");
	        tfAmount.setMaxWidth(100);
        
	        VBox layout = new VBox(10);
	        layout.setAlignment(Pos.CENTER);
	        layout.setStyle("-fx-background-color: #e7d1d1;");

	        Button submit = new Button("Submit");
	        submit.setStyle("-fx-font-size: 10pt;");
	        submit.setTextFill(Color.GREEN);
	        submit.setOnAction(ex -> {
	        	try{		        
			        if (tfAmount.getText().matches("((([0-9]){1,4})|(([0-9]){1,4}\\.[0-9]{1,2}))"))
			        	textToDouble=Double.parseDouble(tfAmount.getText());

	        	}catch(NumberFormatException nfe){
	        		System.out.println("error with parsing");
	        	}

	        	if(!PaymentDB.getInstance().selectPayments().isEmpty())
	        		lastPayment=PaymentDB.getInstance().selectPayments().size()+1;
	        	else
	        		lastPayment=1;

	        	//update unpaid value in invoiceDB
	        	boolean shown=true;
	        	for(int i =0; i<InvoiceDB.getInstance().selectInvoices().size(); i++){
	        		if(InvoiceDB.getInstance().selectInvoices().get(i).getInvoice()==selected){
	        			Invoice inv=InvoiceDB.getInstance().selectInvoices().get(i);
						if(inv.getUnpaid()>textToDouble){ //if amount outstanding is greater than amount we want to pay     				
		        			inv.setUnpaid(inv.getUnpaid()-textToDouble);
		        			
		        			InvoiceDB.getInstance().updateInvoice(selected,InvoiceDB.getInstance().selectInvoices().get(i).getPatientID(),
    								InvoiceDB.getInstance().selectInvoices().get(i).getInvoiceAmount(),
    								round(InvoiceDB.getInstance().selectInvoices().get(i).getUnpaid()-textToDouble,2)
    								);
		        			
		    	        	//add payment to paymentDB
		    	        	PaymentDB.getInstance().addPayment(lastPayment, selected, textToDouble);
		    	        	
		        			window.close();
							Controller.getInstance().showAlert("New Payment", "Payment Approved", "InvoiceNo: "+selected+"\nAmount: "+textToDouble);
						}
						else if(inv.getUnpaid()==textToDouble){//if amount outstanding is equals to amount we want to pay    
	        				inv.setUnpaid(inv.getUnpaid()-textToDouble);
		        			
		        			InvoiceDB.getInstance().updateInvoice(selected,InvoiceDB.getInstance().selectInvoices().get(i).getPatientID(),
    								InvoiceDB.getInstance().selectInvoices().get(i).getInvoiceAmount(),
    								round(InvoiceDB.getInstance().selectInvoices().get(i).getUnpaid()-textToDouble,2)
    								);
		        			
		    	        	//add payment to paymentDB
		    	        	PaymentDB.getInstance().addPayment(lastPayment, selected, textToDouble);
		    	        	
		       				inv.setIsPaid(true);
		       				AlertBox warning = new AlertBox();
							warning.display("COMPLETE!", "This invoice is fully paid.");
						}
						else if(inv.getUnpaid()<textToDouble){
							
							AlertBox warning = new AlertBox();
							warning.display("!WARNING!", " Entered amount is too high");
							shown=true;

						}
						else{
							if(!shown){
							AlertBox warning = new AlertBox();
							warning.display("ERROR","amount entered too high");
							shown=true;
							}
						}
	        			
	        		}
	        		
	        	}
	        	window.close();
	        	Controller.getInstance().refresh();

	        });	
	        
	        layout.getChildren().addAll(label,initialInvoice,tfAmount,submit);
	    	
	        Scene scene = new Scene(layout, 300, 275);
	        window.setScene(scene);
	        window.show();
		
		});
		this.setPadding(new Insets(20, 20, 20, 20));

		this.getChildren().addAll(PaymentDB.getInstance().displayTablePayment(), button);
	}
    public static double round(double value, int places){//i have added this method to format the output and leave only 2 decimal digits
    	if(places<0)throw new IllegalArgumentException();
    	long factor = (long) Math.pow(10, places);
    	value=value*factor;
    	long temp= Math.round(value);
    	return (double) temp/factor;
    }
    
    
	public ComboBox<String> populateList(){

		ComboBox<String> invoices = new ComboBox<String>();
		try{			
			for(Iterator<Invoice> iter = invList.iterator(); iter.hasNext();) {
				Invoice i=iter.next();
				int s= i.getInvoice();
				int pat=i.getPatientID();
				String out="";
				
				for(int k=0; k<PatientDB.getInstance().selectPatients().size(); k++)
					if(pat==PatientDB.getInstance().selectPatients().get(k).getID())
						out = s+" "
								+PatientDB.getInstance().selectPatients().get(k).getLname()
								+" "+i.getUnpaid();
				
				invoices.getItems().add(out);
				
				
			}
			invoices.setPromptText("Invoices"); 
			
		}catch(Exception e){
			System.out.println("issue in uploading data to DROPDOWN LIST");
		}
		
		return invoices;
		
	}
   
	public void populatepaymentList(){

    	ArrayList <Patient> list = PatientDB.getInstance().selectPatients();
    	
    	try{
	    	for(int i=0; i<list.size(); i++){
	    		for(int k=0; k<list.get(i).getP_invoiceList().size();k++){ 			
	    			
	    			listView.getItems().add(
	    						"Invoice number: "+list.get(i).getInvoice(k).getInvoice()
	    					+"\tInvoice Amount= \u20ac "+list.get(i).getInvoice(k).getInvoiceAmount()
	    					+"\tAmount left to pay= \u20ac "+list.get(i).getInvoice(k).getUnpaid()
	    					);
	
	    			
	    		}
	    	}
    	}catch(Exception e){
    		
    	}
	}

}

