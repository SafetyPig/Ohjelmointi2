/**
 * 
 */
package tiedot;

import java.io.PrintStream;

/**
 * @author Hannu Viinikainen
 * @version 4.4.2012
 */
public class Tietue {
    // Oletuksena ei ole yht‰‰n kentt‰‰, kun luodaan tietue, niin siin‰ on m‰‰ritett‰v‰t kent‰t.
    // setKentat metodin avustuksella
    private Kentta kentat[] = null;
    /**
     * Tyhj‰ konstruktori
     */
    public Tietue() {       
    }
    
    /**
    
    
    /**
     * Asettaa uudet kent‰t tietueelle
     * @param uudetKentat halutut kent‰t
     */
    public void setKentat(Kentta[] uudetKentat){
        kentat = uudetKentat;
        
    }
    
    
    /**
     *                               int  String  String       int            int      String   String     String
     * Asettaa merkkijonon muodossa  0  | nimi|   genre|   julkaisuvuosi|pelaajamaara|peliAika| tuuri  |ikasuositus
     * lautapelin tiedoiksi
     * @param jono asetettava merkkijono
     * @example
     * <pre name="test">
     * Lautapeli Caylus = new Lautapeli("0|Caylus|strategia|2005|2-5|60-150|Olematon|12");
     * Caylus.getID() === 0;
     * Caylus.anna(1) === "Caylus";
     * Caylus.anna(3) === "2005";
     * Caylus.anna(7) === "12";
     * </pre>
     */
    public void parse(String jono) {
        String[] tiedot = jono.split("\\|");
        for(int i = 0; i < tiedot.length; i++) {           
            aseta(i,tiedot[i]);
        }
    }
    
    
    /**
     * Tekee olion tiedoista tallennettavan muodon ja palauttaa sen
     * @return palauttaa merkkijonon, joka voidaan tallentaa tiedostoon
     * @example
     * <pre name="test">
     * Lautapeli Caylus = new Lautapeli("0|Caylus|strategia|2005|2-5|60-150|Olematon|12");
     * Caylus.tiedostoMuotoon() === "0|Caylus|strategia|2005|2-5|60-150|Olematon|12|";
     * </pre>
     */
    public String tiedostoMuotoon() {
        StringBuilder tiedot = new StringBuilder();
        String erotin = "";
        for(int i = 0; i < kentat.length; i++) {
            tiedot.append(erotin + anna(i));
            erotin = "|";
        }           
        return tiedot.toString();
    }
    
    
    /**
     * Palauttaa i:n kent‰n tiedot
     * @param i mink‰ kent‰n tiedot palautetaan
     * @return i:n kent‰n arvo
     */
    public String anna(int i) {
        return kentat[i].toString();
    }
    
    /**
     * Asettaa tiedon haluttuun kentt‰‰n
     * @param kentta monenteenko kentt‰‰n tieto asetetaan. 
     * @param arvo arvo, joka kentt‰‰n sijoitetaan
     * @return palauttaa virhe ilmoituksen. Null, jos ei ole virhett‰
     */
    public String aseta(int kentta, String arvo ){
        String virhe = kentat[kentta].asetaArvo(arvo);
        return virhe;
        
    }
    /**
     * @return palauttaa ID numeron
     */
    public int getID() {
        return Integer.parseInt(anna(0));
    }

    /**
     * @return palauttaa kysytyn olion nimen nimen
     */
    public String getNimi() {
        return anna(1);
    }

    
    /**
     * Osaa tulostaa lautapelin tiedot
     * @param out mihin tulostetaan
     */
    public void tulosta(PrintStream out) {
        for(Kentta tieto: kentat)
            out.println(tieto.toString());
    }

    /**
     * Palauttaa lautapelin tieto kohdan tekstin
     * @param i mink‰ kent‰n tieto kohta palautetaan
     * @return tieto kohdan teksti.
     */
    public String getTieto(int i) {
        return kentat[i].getTieto();
    }
    
    /**
     * palauttaa kenttien lukum‰‰r‰n
     * @return kenttien lukum‰‰r‰
     */
    public int getKenttia() {
        return kentat.length;
    }
    
    /**
     * Palauttaa kent‰n numeron, jonka tiedoissa voisi olla k‰ytt‰j‰lle
     * hyˆdyllist‰ tietoa
     * @return ekan k‰ytt‰j‰lle hyˆdyllisen kent‰n numero
     */
    public int getEkaTietoKentta(){
        return 1;
    }
    
    /**
     * Antaa satunnaisen luvun v‰lilt‰ ala-yla
     * @param ala alaraja
     * @param yla ylaraja
     * @return satunnainen luku
     */
    public static int rand(int ala, int yla) {
        double arvo = (yla - ala) * Math.random() + ala;
        return (int) arvo;
    }
    
    
}
