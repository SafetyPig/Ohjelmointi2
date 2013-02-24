package tiedot;

/**
 * Kentt‰, joka sis‰lt‰‰ kokonaisluvun arvonaan
 * @author Hannu Viinikainen
 * @version 4.4.2012
 */
public class IntKentta extends NormiKentta {
    private int arvo = 0;
    
    /**
     * Konstruktori
     * @param tieto tuodaan tieto, mink‰ tyyppist‰ tietoa pit‰‰ sis‰ll‰‰n
     */
    public IntKentta(String tieto) {
        super(tieto);
    }
    
    /**
     * Konstruktori, jolle tuodaan kentt‰‰n laitettava tieto sek‰ tarkastaja
     * @param tieto kentt‰‰ syˆtett‰v‰ tieto
     * @param tarkastaja tarkastaja, joka katsoo syˆtteen oikeellisuuden
     */
    public IntKentta(String tieto, Tarkastaja tarkastaja) {
        super(tieto, tarkastaja);
    }
    
    /**
     * Palauttaa kent‰n arvon
     * @return kent‰n arvo
     */
    public int getArvo() {
        return arvo;
    }
    
    /**
     * Asettaa kent‰lle arvon
     * @param arvo ottaa arvon t‰st‰ ja muuttaa sen kokonaisluvuksi
     * @return palauttaa null, jos onnistuu. Jos ei onnistu palauttaa virheilmoituksen
     * @example
     * <pre name="test">
     * IntKentta kentta = new IntKentta("julkaisuvuosi");
     * kentta.asetaArvo("1512") === null; 
     * kentta.getArvo() === 1512;
     * kentta.asetaArvo("KiviJaKeppi") === "Virhe: For input string: \"KiviJaKeppi\"";
     * kentta.getArvo() === 1512;
     * 
     * </pre>
     */
    @Override
    public String asetaArvo(String arvo) {
        if(arvo.length() <= 0) {
            this.arvo = 0;
            return null;
        }
        
        try {
            this.arvo = Integer.parseInt(arvo);
            
        } catch (NumberFormatException ex) {
            return "Antamasi syˆte ei ole kokonaisluku";
        }
        
        return null;
    }
    
    /**
     * Palauttaa kent‰n arvon merkkijonona
     */
    public String toString() {
        return ""+arvo;
    }
}
