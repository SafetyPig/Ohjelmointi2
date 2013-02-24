package tiedot;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import tiedot.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2012.04.09 12:24:39 // Generated by ComTest
 *
 */
public class TietueTest {



  // Generated by ComTest BEGIN
  /** testParse37 */
  @Test
  public void testParse37() {    // Tietue: 37
    Lautapeli Caylus = new Lautapeli("0|Caylus|strategia|2005|2-5|60-150|Olematon|12"); 
    assertEquals("From: Tietue line: 39", 0, Caylus.getID()); 
    assertEquals("From: Tietue line: 40", "Caylus", Caylus.anna(1)); 
    assertEquals("From: Tietue line: 41", "2005", Caylus.anna(3)); 
    assertEquals("From: Tietue line: 42", "12", Caylus.anna(7)); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testTiedostoMuotoon57 */
  @Test
  public void testTiedostoMuotoon57() {    // Tietue: 57
    Lautapeli Caylus = new Lautapeli("0|Caylus|strategia|2005|2-5|60-150|Olematon|12"); 
    assertEquals("From: Tietue line: 59", "0|Caylus|strategia|2005|2-5|60-150|Olematon|12|", Caylus.tiedostoMuotoon()); 
  } // Generated by ComTest END
}