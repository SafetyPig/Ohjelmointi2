package ikkunatGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DropMode;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Lautapelilistauksen alkuikkuna, jolla kysytään käyttäjä. Mahdollista valita myös
 * uuden käyttäjän luominen
 * @author Hannu Viinikainen
 * @version 30.03.2012
 *
 */
public class Alkuikkuna extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtKayttaja;
    
    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Alkuikkuna frame = new Alkuikkuna(null, null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        });
    }
    
    private String tulos;
    
    /**
     * Aliohjelma kysyy alkuikkuna dialogin avulla käyttäjältä avattavan
     * henkilön lautapelilistauksen nimen.
     * @param pelaajat lista, jonka avulla tarkastetaan, että onko pelaaja jo olemassa
     * @return palauttaa pelaajan nimen, jonka profiili halutaan avata
     */
    public static String kysyNimi(ArrayList<String> pelaajat) {
        String nimi = "";
        Alkuikkuna dialog = new Alkuikkuna(null, pelaajat);
        dialog.setVisible(true);
        nimi = dialog.getTulos();
        dialog.dispose();
        return nimi;
    }
    
    /**
     * Palauttaa tulos
     * @return tulos, jonka dialogi palauttaa
     */
    private String getTulos() {
        return tulos;
    }
    
    /**
     * Asettaa tuloksen
     * @param tulos tulos, jonka dialogiin halutaan asettaa
     */
    private void setTulos(String tulos) {
        this.tulos = tulos;
    }
    
    /**
     * Palauttaa textKayttajaan kirjoitetun tekstin
     */
    private String getTextKayttaja() {
        return txtKayttaja.getText();
        
    }
    
    /**
     * Create the frame.
     * @param parent millä framelle on moduulinen
     * @param pelaajat tietokannassa olevat pelaajat tarkastamista varten
     */
    public Alkuikkuna(Frame parent, final ArrayList<String> pelaajat) {
        super(parent, true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }
        });
        setMaximumSize(new Dimension(450, 300));
        setTitle("Lautapelilistaus");
        setMinimumSize(new Dimension(450, 300));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 456, 302);
        contentPane = new JPanel();
        contentPane.setMaximumSize(new Dimension(450, 300));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblVersion = new JLabel("Version 1.0");
        lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblVersion.setHorizontalAlignment(SwingConstants.CENTER);
        lblVersion.setBounds(151, 94, 100, 34);
        contentPane.add(lblVersion);
        
        JLabel lblKyttj = new JLabel("K\u00E4ytt\u00E4j\u00E4");
        lblKyttj.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblKyttj.setBounds(37, 154, 61, 34);
        contentPane.add(lblKyttj);
        
        txtKayttaja = new JTextField();
        txtKayttaja.setDropMode(DropMode.INSERT);
        txtKayttaja.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtKayttaja.setBounds(108, 155, 191, 34);
        contentPane.add(txtKayttaja);
        txtKayttaja.setColumns(10);
        
        JButton btnNewButton = new JButton("Kirjaudu");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (onkoTaulukossa(getTextKayttaja())) {
                    setTulos(getTextKayttaja());
                    dispose();
                }
                
                else {
                    JOptionPane.showMessageDialog(null, "Pelaajaa "
                            + getTextKayttaja() + " ei löydy");
                }
            }
            
            /**
             * Tarkistaa onko annettu pelaaja tietokannassa
             * @param pelaaja tarkastettava pelaaja
             */
            private boolean onkoTaulukossa(String pelaaja) {
                if(pelaajat == null)
                    return false;
                
                for (String nimi: pelaajat)
                    if (nimi.equals(pelaaja))
                        return true;
                
                return false;
            }
        });
        
        btnNewButton.setBounds(309, 155, 115, 34);
        contentPane.add(btnNewButton);
        
        JLabel lblOletkoUusiKyttj = new JLabel(
                "Oletko uusi k\u00E4ytt\u00E4j\u00E4?");
        lblOletkoUusiKyttj.setBounds(37, 204, 262, 14);
        contentPane.add(lblOletkoUusiKyttj);
        
        JButton btnNewButton_1 = new JButton("Luo profiili");
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tulos = kysyUudenkayttajanNimi();
                if(tulos != null)
                    dispose();
            }
        });
        
        btnNewButton_1.setBounds(309, 200, 115, 23);
        contentPane.add(btnNewButton_1);
        
        JLabel lblTekijHannuViinikainen = new JLabel(
                "Tekij\u00E4: Hannu Viinikainen");
        lblTekijHannuViinikainen.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblTekijHannuViinikainen.setBounds(63, 236, 135, 14);
        contentPane.add(lblTekijHannuViinikainen);
        
        JPanel panel = new JPanel();
        panel.setBounds(63, 44, 262, 65);
        contentPane.add(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("Lautapelilistaus");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 28));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel, BorderLayout.NORTH);
    }
    
    /**
     * Avaa profiilin luonti-ikkunan
     * @return palauttaa uuden käyttäjän nimen
     */
    public String kysyUudenkayttajanNimi() {
        return KayttajanLuonti.uusiKayttaja();
    }
    
}
