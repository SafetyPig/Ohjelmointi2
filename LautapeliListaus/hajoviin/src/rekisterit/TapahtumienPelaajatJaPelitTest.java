package rekisterit;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import rekisterit.*;
// Generated by ComTest END
import tiedot.Lautapeli;
import tiedot.Pelaaja;
import tiedot.Tapahtuma;

/**
 * Test class made by ComTest
 * @version 2012.04.15 20:39:48 // Generated by ComTest
 *
 */
public class TapahtumienPelaajatJaPelitTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa40 
   * @throws OmaException when error
   */
  @Test
  public void testLisaa40() throws OmaException {    // TapahtumienPelaajatJaPelit: 40
    TapahtumienPelaajatJaPelit tiedot = new TapahtumienPelaajatJaPelit(); 
    Tapahtuma tapahtuma = new Tapahtuma(); 
    Pelaaja pate = new Pelaaja(); 
    Lautapeli peli = new Lautapeli(); 
    Lautapeli peli2 = new Lautapeli(); 
    tapahtuma.rekisteroi(0); 
    pate.rekisteroi(); 
    peli.rekisteroi(); 
    peli2.rekisteroi(); 
    tiedot.lisaa(1,1,1); 
    tiedot.lisaa(1,1,2); 
    try {
    tiedot.lisaa(1,1,2); 
    fail("TapahtumienPelaajatJaPelit: 56 Did not throw OmaException");
    } catch(OmaException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}