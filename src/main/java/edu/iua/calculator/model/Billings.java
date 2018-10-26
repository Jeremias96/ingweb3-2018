package edu.iua.calculator.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "billing_details")
public class Billings {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "amount", precision = 3)
    private Float amount;

    @Column(name = "date")
    private Date date;

    public Billings() {}

    public Billings(Float amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String toString() {
	    return "Amount: '" + this.amount + "', Date: '" + this.date;
	}
}
