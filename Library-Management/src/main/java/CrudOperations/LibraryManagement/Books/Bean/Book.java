package CrudOperations.LibraryManagement.Books.Bean;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Book {

        @Id
        @GeneratedValue
        private long Isbn;

        @Column (name = "Book_Name")
        private String Name;

        @Column
        private String Author;

        @Column
        private int Price;

       public Book(long Isbn, String Name, String Author, int Price) {

            super();
            this.Isbn = Isbn;
            this.Name = Name;
            this.Author = Author;
            this.Price = Price;

        }

    public Book(){

    }

    public long getIsbn() {
            return Isbn;
        }

   public void setIsbn(long Isbn) {
        this.Isbn = Isbn;
    }


    public String getName()
        {
            return Name;
        }

       public void setName(String Name)
       {
       this.Name = Name;
       }



        public String getAuthor() {
            return Author;
        }

        public void setAuthor(String Author)
        {
         this.Author = Author;
        }

        public int getPrice()
        {
            return Price;
        }

        public void setPrice(int Price)
        {
        this.Price = Price;
        }




        @Override

        public String toString()
        {


            return "Book [Isbn ="+Isbn + ", Name = " +Name + ", Author =" +Author + ", Price = " + Price + "]" ;
         
        }





    }



