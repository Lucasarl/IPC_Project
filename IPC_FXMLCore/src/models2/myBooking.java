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
public class myBooking {
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty court = new SimpleStringProperty();
    private final StringProperty time = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();
		
	public myBooking(String d, String c, String t, String s)
	{
		this.date.setValue(d);
		this.court.setValue(c);
                this.time.setValue(t);
                this.status.setValue(s);
	}
	
	public  StringProperty DateProperty() {
		return this.date;
	}
	public String getDate() {
		return this.DateProperty().get();
	}
	public final void setDate(String Date) {
		this.DateProperty().set(Date);
	}
	public  StringProperty CourtProperty() {
		return this.court;
	}
	public String getCourt() {
		return this.CourtProperty().get();
	}
	public  void setCourt(String Court) {
		this.CourtProperty().set(Court);
	}
        
        public  StringProperty TimeProperty() {
		return this.time;
	}
	public String getTime() {
		return this.TimeProperty().get();
	}
	public  void setTime(String Time) {
		this.TimeProperty().set(Time);
	}
        
        public  StringProperty StatusProperty() {
		return this.status;
	}
	public String getStatus() {
		return this.StatusProperty().get();
	}
	public  void setStatus(String Status) {
		this.StatusProperty().set(Status);
	}
        
        public String toString( ){
            String s=date.get() +" "+court.get()+" "+time.get()+" "+status.get();
           return s;
        }
}

