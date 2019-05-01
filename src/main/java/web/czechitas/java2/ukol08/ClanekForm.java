package web.czechitas.java2.ukol08;
import java.time.*;
import org.springframework.format.annotation.*;

public class ClanekForm {

    private String nazev;

    private String autor;

    @DateTimeFormat(pattern = "d. M. yyyy")
    private LocalDate datum;


    public String getNazev() {
        return nazev;
    }

    public void setNazev(String newValue) {
        nazev = newValue;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String newValue) {
        autor = newValue;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate newValue) {
        datum = newValue;
    }

}