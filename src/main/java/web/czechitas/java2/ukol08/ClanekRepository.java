package web.czechitas.java2.ukol08;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import web.czechitas.java2.ukol08.*;

public class ClanekRepository {

    private List<Clanek> seznamClanku = new CopyOnWriteArrayList<>(Arrays.asList(
            new Clanek("Boss podsvětí dostal 20 let", "Clark Kent", LocalDate.of(2018, 1, 31)),
            new Clanek("Lékaři doporučují opatrnost", "Lois Lane", LocalDate.of(2018, 2, 28)),
            new Clanek("Bezkontaktní karty lákají zloděje", "Perry White", LocalDate.of(2017, 12, 24)),
            new Clanek("Ministryně navštívila problematické předměstí", "Jimmy Olsen", LocalDate.of(2016, 7, 31)),
            new Clanek("Soutěž o lístky na fotbal", "Cat Grant", LocalDate.of(2016, 8, 1)),
            new Clanek("Vrah prodavačky je ve vazbě", "Ron Troupe", LocalDate.of(2017, 10, 28))
    ));

    public synchronized List<Clanek> ukazVse (){
        return seznamClanku;
    }

    public synchronized Clanek uloz (Clanek novyZaznam){
        Clanek ulozeny = najdiJeden(novyZaznam.getId());
        Clanek vracenyClanek;
        if (ulozeny == null)
        {
            seznamClanku.add(novyZaznam);
            vracenyClanek = ulozeny;
        }
        else {
            ulozeny.setNazev(novyZaznam.getNazev());
            ulozeny.setAutor(novyZaznam.getAutor());
            ulozeny.setDatum(novyZaznam.getDatum());
            vracenyClanek = ulozeny;
        }
        return vracenyClanek;

    }

    public synchronized Clanek najdiJeden(Long id) {

        for (Clanek clanek : seznamClanku){
            if (clanek.getId().equals(id)){
                return clanek;
            }
        }
        return null;
    }

    public synchronized void smazClanek(Long idClanku){

        Clanek smazat = najdiJeden(idClanku);
        seznamClanku.remove(smazat);
    }

}
