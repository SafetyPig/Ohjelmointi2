/**
 * 
 */
package tiedot;

/**
 * PaivaTarkastaja osaa tarkistaa, onko päiväys oikein kirjoitettuna
 * TODO: hyvin toimiva tarkistin
 * @author Hannu Viinikainen
 * @version 25.4.2012
 */
public class PaivaTarkastaja implements Tarkastaja {
    
    /**
     * Tarkistaa, että päivämäärä on oikeassa muodossa
     */
    @Override
    public String tarkista(String arvo) {
        
        String[] aika = arvo.split(".");
        if (aika.length <= 0)
            return null;
        
        int paiva = Integer.parseInt(aika[0]);
        
        if(paiva > 30)
            return "tidyy";
        
        return null;
    }
    
}
