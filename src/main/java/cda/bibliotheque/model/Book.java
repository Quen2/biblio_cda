package cda.bibliotheque.model;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private LocalDate date;
    private boolean isAvailable;

    public Book() {
    }

    public Book (int id, String title, LocalDate date, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.isAvailable = isAvailable;
    }

    public Book (int id, String title, java.sql.Date date, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.date = date.toLocalDate();
        this.isAvailable = isAvailable;
    }

    public int getId () {
        return id;
    }

    public String getTitle () {
        return title;
    }

    public LocalDate getDate () {
        return date;
    }

    public boolean getIsAvailable () {
        return isAvailable;
    }

    public void setTile (String title) {
        this.title = title;
    }

    public void setDate (LocalDate date) {
        this.date = date;
    }

    public void setIsAvailable (boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
