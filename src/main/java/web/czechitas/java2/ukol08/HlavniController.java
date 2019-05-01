package web.czechitas.java2.ukol08;
import java.text.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import javax.servlet.http.*;
//import org.springframework.dao.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private ClanekRepository repository = new ClanekRepository();

    @RequestMapping("/")
    public ModelAndView zobrazIndex() {
        ModelAndView drzakNaData;
        drzakNaData = new ModelAndView("redirect:/clanky/");
        return drzakNaData;
    }

    @RequestMapping("/clanky/")
    public ModelAndView zobrazSeznam() {
        ModelAndView drzakNaData;

        drzakNaData = new ModelAndView("clanky/index");
        drzakNaData.addObject("seznam", repository.ukazVse());

        return drzakNaData;
    }

    @RequestMapping(value = "/clanky/{id}.html", method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Long id){

        ModelAndView clanekDetail = new ModelAndView("clanky/detail");
        Clanek detailClanku = repository.najdiJeden(id);

        clanekDetail.addObject("clanek", detailClanku);
        return clanekDetail;

    }

    @RequestMapping (value = "/clanky/{id}.html", method = RequestMethod.POST)
    public ModelAndView editDetail(@PathVariable("id") Long id, ClanekForm editaceZaznam){
        ModelAndView editaceClanku = new ModelAndView("redirect:/clanky/");
        Clanek editovany = new Clanek(id,editaceZaznam.getNazev(),editaceZaznam.getAutor(),editaceZaznam.getDatum());
        Clanek editace = repository.uloz(editovany);

        return editaceClanku;
    }

    @RequestMapping(value = "/clanky/novy.html",method = RequestMethod.GET)
    public ModelAndView novy(){
        ModelAndView novyClanek = new ModelAndView("/clanky/novy");
        return novyClanek;
    }

    @RequestMapping(value = "clanky/novy.html", method = RequestMethod.POST)
    public ModelAndView novyZaznam (ClanekForm novyClanekForm){
        Clanek novyClanek = new Clanek(novyClanekForm.getNazev(),novyClanekForm.getAutor(),novyClanekForm.getDatum());
        repository.uloz(novyClanek);
        ModelAndView novyZaznam = new ModelAndView("redirect:/clanky/");
        return novyZaznam;
    }

    @RequestMapping (value = "/clanky/{id}/delete", method = RequestMethod.GET)
    public ModelAndView smazat(@PathVariable("id") Long idClanku){
        repository.smazClanek(idClanku);
        ModelAndView smazat = new ModelAndView("redirect:/clanky/");
        return smazat;
    }





    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFoundException extends RuntimeException {}

}