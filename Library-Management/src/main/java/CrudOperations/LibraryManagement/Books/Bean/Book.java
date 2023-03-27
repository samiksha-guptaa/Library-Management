package CrudOperations.LibraryManagement.Books.Bean;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DynamicUpdate
@Entity
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long Isbn;

    @Column (name = "Book_Name")
    private String Name;

    @Column
    private String Author;

    @Column (name = "Initial_Price")
    private int InitialPrice;

    @Column (name = "Final_Price")
    private int FinalPrice;


    @CreationTimestamp()
    @Column(name="createdAt",nullable=false, updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp()
    @Column(name="updatedAt")
    private LocalDateTime updatedAt;


    @OneToMany(cascade =CascadeType.ALL,orphanRemoval=true)@JoinColumn(name="Id", referencedColumnName = "Isbn")
    List<Book_track> track=new ArrayList<>();
       public Book(String Name, String Author, int InitialPrice, int FinalPrice, LocalDateTime createdAt, LocalDateTime updatedAt )
       {

            //super();

            this.Name = Name;
            this.Author = Author;
            this.InitialPrice = InitialPrice;
           this.FinalPrice = FinalPrice;

            this.createdAt = createdAt;
            this.updatedAt = updatedAt;

        }


    public Book(String Name, String Author, int InitialPrice)
    {

        //super();

        this.Name = Name;
        this.Author = Author;
        this.InitialPrice = InitialPrice;


    }


    public Book(Long Isbn, String Name, String Author, int InitialPrice)
    {

        //super();
        this.Isbn = Isbn;
        this.Name = Name;
        this.Author = Author;
        this.InitialPrice = InitialPrice;


    }

    public Book(){

    }

    public long getIsbn() {
        return Isbn;
    }

    public void setIsbn(long isbn) {
        this.Isbn = isbn;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public int getInitialPrice() {
        return InitialPrice;
    }

    public void setInitialPrice(int initialPrice) {
        this.InitialPrice = initialPrice;
        this.FinalPrice = initialPrice;

    }

    public int getFinalPrice() {
        return FinalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.FinalPrice = finalPrice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "Isbn=" + Isbn +
                ", Name='" + Name + '\'' +
                ", Author='" + Author + '\'' +
                ", InitialPrice=" + InitialPrice +
                ", FinalPrice=" + FinalPrice +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}



