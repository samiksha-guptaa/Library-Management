package CrudOperations.LibraryManagement.Books.Bean;


import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table
public class Book_track {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int Serial;


    @Column
    private long Id;

    //@Column
    //private String Name;

    @Column
    private int PriceUpdates;

    @UpdateTimestamp()
    @Column
    private LocalDateTime updatedAt;


    public Book_track(){

    }

    public Book_track(long Id, int PriceUpdates, LocalDateTime updatedAt)
    {

       // super();
        this.Id = Id;
        //this.Name = Name;
        this.PriceUpdates = PriceUpdates;
        this.updatedAt = updatedAt;

    }




    public int getSerial() {
        return Serial;
    }

    public void setSerial(int Serial) {
        this.Serial = Serial;
    }

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getPriceUpdates()
    {
        return PriceUpdates;
    }

    public void setPriceUpdates(int PriceUpdates)
    {
        this.PriceUpdates = PriceUpdates;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Book_track{" +
                "Serial=" + Serial +
                ", Id=" + Id +
                ", PriceUpdates=" + PriceUpdates +
                ", updatedAt=" + updatedAt +
                '}';
    }


















}
