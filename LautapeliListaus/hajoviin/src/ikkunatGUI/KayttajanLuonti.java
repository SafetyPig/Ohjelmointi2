package ikkunatGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.DropMode;
import javax.swing.border.BevelBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Dialogi ikkuna, jolla voi luoda uuden käyttäjän
 * @author Hannu Viinikainen
 * @version 08.04.2012
 *
 */
public class KayttajanLuonti extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_kayttaja;
    
    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KayttajanLuonti frame = new KayttajanLuonti();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    /**
     * Palauttaa luotavan käyttäjän nimen
     * @return luotavan käyttäjän nimi. Asettaa nimen eteen new*, jotta saadaan
     * käsiteltyä erikseen uuden käyttäjän luonti sekä yritys avata vanha käyttäjä
     * listausSwingissä
     */
    public static String uusiKayttaja() {
        String nimi = "";
        KayttajanLuonti dialog = new KayttajanLuonti();
        dialog.setVisible(true);
        nimi = dialog.getTulos();
        return nimi;
        
    }
    
    private String tulos = "";
    
    /**
     * Palauttaa dialogin tuloksen
     * @return dialogin tulos
     */
    private String getTulos() {
        return tulos;
    }
    
    /**
     * Palauttaa TextField_Kayttajan tekstin
     * @return uuden kayttajan nimi
     */
    private String getTextField_kayttaja() {
        return textField_kayttaja.getText();
    }
    
    /**
     * Asettaa tuloksen
     * @param tulos asetettava tulos
     */
    private void setTulos(String tulos) {
        this.tulos = tulos;
    }
    
    /**
     * Create the dialog.
     */
    public KayttajanLuonti() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                setTulos(null);
                setVisible(false);
            }
        });
        setTitle("K\u00E4ytt\u00E4j\u00E4n Luonti");
        setModalityType(ModalityType.APPLICATION_MODAL);
        setType(Type.UTILITY);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 311, 158);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JPanel panel_otsikko = new JPanel();
        panel_otsikko.setBorder(new BevelBorder(BevelBorder.RAISED, null, null,
                null, null));
        contentPane.add(panel_otsikko, BorderLayout.NORTH);
        
        JLabel lblUudenKyttjnLuonti = new JLabel(
                "Uuden k\u00E4ytt\u00E4j\u00E4n luonti");
        lblUudenKyttjnLuonti.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_otsikko.add(lblUudenKyttjnLuonti);
        
        JPanel panel_nappulat = new JPanel();
        contentPane.add(panel_nappulat, BorderLayout.SOUTH);
        
        JSplitPane splitPane_1 = new JSplitPane();
        panel_nappulat.add(splitPane_1);
        
        JButton btnNewButton = new JButton("     Luo     ");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getTextField_kayttaja().equals("")){
                        JOptionPane.showMessageDialog(null,
                                "Anna nimi");
                        return;
                }
                setTulos("new*" + getTextField_kayttaja());
                setVisible(false);
            }
        });
        splitPane_1.setLeftComponent(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("   Palaa   ");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                setTulos(null);
                setVisible(false);
            }
        });
        splitPane_1.setRightComponent(btnNewButton_1);
        
        JPanel panel_kayttaja = new JPanel();
        contentPane.add(panel_kayttaja, BorderLayout.CENTER);
        panel_kayttaja.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel lblNimi = new JLabel("Nimi");
        lblNimi.setFont(new Font("Tahoma", Font.PLAIN, 13));
        panel_kayttaja.add(lblNimi);
        
        textField_kayttaja = new JTextField();
        textField_kayttaja.setDropMode(DropMode.INSERT);
        textField_kayttaja.setAlignmentX(Component.RIGHT_ALIGNMENT);
        textField_kayttaja.setMaximumSize(new Dimension(175, 20));
        textField_kayttaja.setMinimumSize(new Dimension(175, 20));
        textField_kayttaja.setPreferredSize(new Dimension(175, 20));
        panel_kayttaja.add(textField_kayttaja);
        textField_kayttaja.setColumns(10);
        
    }
}
