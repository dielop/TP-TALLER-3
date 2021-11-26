package IGU;


import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class IGcaja extends JFrame implements ActionListener{

	private JTextField apeText;
	private JTextField ingText;
	private JTextField EgrText;
	private JTextField totText;
	JButton btnActualizar, btnCierre, btnAtras, btnExport;

	
	public static void main(String[] args) {
		IGcaja C = new IGcaja();
	}


	public IGcaja() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,300);
		setLocationRelativeTo(null);
		setTitle("Tu cancha - Caja");
	
		
		Container ctx = getContentPane();
		JPanel Box = new JPanel();
		Box.setLayout(null);
		Box.setBackground(Color.WHITE);
		
		JLabel Ape = new JLabel("Apertura");
		Ape.setBounds(44, 11, 66, 14);
		Box.add(Ape);
		
		JLabel Ing = new JLabel("Ingreso");
		Ing.setBounds(146, 11, 46, 14);
		Box.add(Ing);
		
		JLabel Egr = new JLabel("Egreso");
		Egr.setBounds(242, 11, 46, 14);
		Box.add(Egr);
		
		JLabel Tot = new JLabel("Total");
		Tot.setBounds(344, 11, 46, 14);
		Box.add(Tot);
		
		apeText = new JTextField();
		apeText.setBounds(24, 33, 86, 20);
		Box.add(apeText);
		//apeText.setColumns(10);
		
		ingText = new JTextField();
		ingText.setBounds(125, 33, 86, 20);
		Box.add(ingText);
		
		EgrText = new JTextField();
		EgrText.setBounds(221, 33, 86, 20);
		Box.add(EgrText);
		
		totText = new JTextField();
		totText.setBounds(317, 33, 86, 20);
		Box.add(totText);
		
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(173, 79, 99, 23);
		Box.add(btnActualizar);
		
		btnCierre = new JButton("Cerrar caja");
		btnCierre.setBounds(173, 113, 99, 23);
		Box.add(btnCierre);
		
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(24, 227, 89, 23);
		btnAtras.addActionListener(this);
		Box.add(btnAtras);
		
		btnExport = new JButton("Exportar");
		btnExport.setBounds(173, 147, 99, 23);
		Box.add(btnExport);
		
		ctx.add(Box);
		
		setResizable(false);
		//setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnAtras == e.getSource()) {
			dispose();			
		}
		
	}
}
