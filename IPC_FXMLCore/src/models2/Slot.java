/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USER
 */
public class Slot {
    private final StringProperty hour = new SimpleStringProperty();
    private final StringProperty court1 = new SimpleStringProperty();
    private final StringProperty court2 = new SimpleStringProperty();
    private final StringProperty court3 = new SimpleStringProperty();
    private final StringProperty court4= new SimpleStringProperty();
    private final StringProperty court5= new SimpleStringProperty();
    private final StringProperty court6 = new SimpleStringProperty();
		
	public Slot(String hora, String court1, String court2, String court3, String court4, String court5, String court6)
	{
		this.hour.setValue(hora);
		this.court1.setValue(court1);
                this.court2.setValue(court2);
                this.court3.setValue(court3);
                this.court4.setValue(court4);
                this.court5.setValue(court5);
                this.court6.setValue(court6);
	}
	
	public  StringProperty HourProperty() {
		return this.hour;
	}
	public String getHour() {
		return this.HourProperty().get();
	}
	public final void setHour(String Hour) {
		this.HourProperty().set(Hour);
	}
	public  StringProperty Court1Property() {
		return this.court1;
	}
	public String getCourt1() {
		return this.Court1Property().get();
	}
	public  void setCourt1(String Court1) {
		this.Court1Property().set(Court1);
	}
        
        public  StringProperty Court2Property() {
		return this.court2;
	}
	public String getCourt2() {
		return this.Court2Property().get();
	}
	public  void setCourt2(String Court2) {
		this.Court2Property().set(Court2);
	}
        
        public  StringProperty Court3Property() {
		return this.court3;
	}
	public String getCourt3() {
		return this.Court3Property().get();
	}
	public  void setCourt3(String Court3) {
		this.Court3Property().set(Court3);
	}
        
        public  StringProperty Court4Property() {
		return this.court4;
	}
	public String getCourt4() {
		return this.Court4Property().get();
	}
	public  void setCourt4(String Court4) {
		this.Court4Property().set(Court4);
	}
        
        public  StringProperty Court5Property() {
		return this.court5;
	}
	public String getCourt5() {
		return this.Court5Property().get();
	}
	public  void setCourt5(String Court5) {
		this.Court5Property().set(Court5);
	}
        
        public  StringProperty Court6Property() {
		return this.court6;
	}
	public String getCourt6() {
		return this.Court6Property().get();
	}
	public  void setCourt6(String Court6) {
		this.Court6Property().set(Court6);
	}
        
        public String toString( ){
            String s=hour.get() +" "+court1.get()+" "+court2.get()+" "+court3.get()+" "+court4.get()+" "+court5.get()+" "+court6.get();
           return s;
        }
}
