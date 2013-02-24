package ikkunatGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import java.awt.Font;
import fi.jyu.mit.gui.EditPanel;
import javax.swing.BoxLayout;

import tiedot.Tapahtuma;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Tapahtuman luonti dialogi. 
 * @author Hannu Viinikainen
 * @version 24.01.2012
 *
 */
public class TapahtumanLuonti extends JDialog {
    
    private static final long serialVersionUID = 1L;

    private EditPanel[] tietoPalkit;
    
    /**
     * Launch the application.
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TapahtumanLuonti frame = new TapahtumanLuonti(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Palauttaa luotavan tapahtuman tiedot
     * @param apuTapahtuma aputapahtuma, josta tiedet‰‰n kuinka monta editPanel kentt‰‰
     * tulee luoda
     * @return luotavan tapahtuman tiedot muodossa 0|nimi|p‰iv‰m‰‰r‰|alkamisaika|paikka|jne...
     */
    public static String uusiTapahtuma(Tapahtuma apuTapahtuma) {
        String tiedot = "";
        TapahtumanLuonti dialog = new TapahtumanLuonti(apuTapahtuma);
        dialog.setVisible(true);
        tiedot = dialog.getTulos();
        dialog.dispose();
        
        return tiedot;
        
    }
    
    private String tulos = null;
    
    /**
     * Palauttaa dialogin tuloksen
     * @return dialogin tulos
     */
    private String getTulos() {
        return tulos;
    }
    
    /**
     * Asettaa dialogin tuloksen
     * @param apuTapahtuma tapahtuma, josta saadaan tieto, montako kentt‰‰ tarvitaan
     */
    private void setTulos(Tapahtuma apuTapahtuma) {
        StringBuilder vastaus = new StringBuilder("");
        
        for(int i = 0; i < apuTapahtuma.getEkaTietoKentta(); i++)
            vastaus.append("0|");
        
        for (int i = 0; i < tietoPalkit.length; i++) {
            vastaus.append(tietoPalkit[i].getText() + "|");
        }
        tulos = vastaus.toString();
    }
    
    /**
     * Create the frame.
     * @param apuTapahtuma aputapahtuma, josta selvitet‰‰n miten paljon editPanel kentti‰ tarvitaan
     */
    public TapahtumanLuonti(final Tapahtuma apuTapahtuma) {
        
        setModalityType(ModalityType.APPLICATION_MODAL);
        setTitle("Tapahtuman luonti");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 311, 248);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel_Otsikko = new JPanel();
        contentPane.add(panel_Otsikko, BorderLayout.NORTH);
        
        JLabel lblLuoUusiTapahtuma = new JLabel("Luo uusi tapahtuma");
        lblLuoUusiTapahtuma.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_Otsikko.add(lblLuoUusiTapahtuma);
        
        JPanel panel_napit = new JPanel();
        contentPane.add(panel_napit, BorderLayout.SOUTH);
        
        JSplitPane splitPane_napit = new JSplitPane();
        panel_napit.add(splitPane_napit);
        
        JPanel panel_tapahtumanTiedot = new JPanel();
        contentPane.add(panel_tapahtumanTiedot, BorderLayout.CENTER);
        panel_tapahtumanTiedot.setLayout(new BoxLayout(panel_tapahtumanTiedot,
                BoxLayout.Y_AXIS));
        
        if (apuTapahtuma != null) {
            tietoPalkit = new EditPanel[apuTapahtuma.getKenttia()
                    - apuTapahtuma.getEkaTietoKentta()];
            
            for (int i = apuTapahtuma.getEkaTietoKentta(), k = 0; i < apuTapahtuma
                    .getKenttia(); i++, k++) {
                EditPanel tietoPalkki = new EditPanel();
                tietoPalkit[k] = tietoPalkki;
                tietoPalkki.setCaption(apuTapahtuma.getTieto(i));
                panel_tapahtumanTiedot.add(tietoPalkki);
            }
        }
        
        else {
            
            EditPanel editPanel_tapahtuma = new EditPanel();
            editPanel_tapahtuma.setCaption("Tapahtuman nimi");
            panel_tapahtumanTiedot.add(editPanel_tapahtuma);
            
            EditPanel editPanel_pvm = new EditPanel();
            editPanel_pvm.setCaption("P\u00E4iv\u00E4m\u00E4\u00E4r\u00E4");
            panel_tapahtumanTiedot.add(editPanel_pvm);
            
            EditPanel editPanel_aika = new EditPanel();
            editPanel_aika.setCaption("Alkamisaika");
            panel_tapahtumanTiedot.add(editPanel_aika);
            
            EditPanel editPanel_paikka = new EditPanel();
            editPanel_paikka.setCaption("Paikka");
            panel_tapahtumanTiedot.add(editPanel_paikka);
            
            tietoPalkit = new EditPanel[4];
            tietoPalkit[0] = editPanel_tapahtuma;
            tietoPalkit[1] = editPanel_pvm;
            tietoPalkit[2] = editPanel_aika;
            tietoPalkit[3] = editPanel_paikka;
        }
        
        JButton btnNewButton = new JButton("Luo tapahtuma");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tietoPalkit[0].getText().equals(""))
                    JOptionPane.showMessageDialog(null,
                            "Tapahtumalla on oltava nimi");
                
                else {
                    setTulos(apuTapahtuma);
                    setVisible(false);
                }
            }
        });
        
        splitPane_napit.setLeftComponent(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("       Palaa       ");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        splitPane_napit.setRightComponent(btnNewButton_1);
        
    }
    
}
