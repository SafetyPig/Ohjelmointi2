package tiedot;

/**
 * Kentt‰, joka sis‰lt‰‰ merkkijonon arvonaan
 * @author Hannu Viinikainen
 * @version 24.4.2012
 */
public class JonoKentta extends NormiKentta {
    
    private String arvo = "";
    
    /**
     * Konstruktori
     * @param tieto mink‰tyyppist‰ tietoa kentt‰ sis‰lt‰‰
     * @example
     * <pre name="test">
     * JonoKentta jono = new JonoKentta("nimi");
     * jono.getArvo() === "";
     * jono.getTieto() === "nimi";
     * </pre>
     */
    public JonoKentta(String tieto) {
        super(tieto);
    }
    
    /**
     * Konstruktori, jolle tuodaan kentt‰‰n laitettava tieto sek‰ tarkastaja
     * @param tieto kentt‰‰ syˆtett‰v‰ tieto
     * @param tarkastaja tarkastaja, joka katsoo syˆtteen oikeellisuuden
     */
    public JonoKentta(String tieto, Tarkastaja tarkastaja) {
        super(tieto, tarkastaja);
    }
    
    /**
     * Pyrkii asettamaan arvon kentt‰‰, ja jos kentt‰ omaa tarkastajan niin tarkistaa, ett‰
     * syotett‰v‰ tieto on validi.
     */
    @Override
    public String asetaArvo(String arvo) {
        String virhe = null;
        if (tarkastaja != null)
            virhe = tarkastaja.tarkista(arvo);
        
        if (arvo.contains("|"))
            return "Kielletty merkki \"|\"";
        
        if (virhe != null)
            return virhe;
        
        this.arvo = arvo;
        return null;
    }
    
    /**
     * Palauttaa kent‰n arvon
     * @return kent‰n arvo
     */
    public String getArvo() {
        return arvo;
    }
    
    /**
     * Palauttaa kent‰n arvon merkkijonona
     */
    public String toString() {
        return getArvo();
    }
    
}
