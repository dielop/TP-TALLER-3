package IGU;


import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Clientes extends JFrame implements ActionListener{
	private JTextField nomText;
	private JTextField apeText;
	private JTextField telText;
	JButton btnActualizar, btnCierre, btnAtras, btnExport;

	
	public static void main(String[] args) {
		Clientes C = new Clientes();
	}


	public Clientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(375,200);
		setLocationRelativeTo(null);
		setTitle("Datos de la reserva");
	
		
		Container ctx = getContentPane();
		JPanel Box = new JPanel();
		Box.setLayout(null);
		Box.setBackground(Color.WHITE);
		
		JLabel Nom = new JLabel("Nombre");
		Nom.setBounds(48, 25, 100, 30);
		Box.add(Nom);
		
		JLabel Ape = new JLabel("Apellido");
		Ape.setBounds(156, 25, 100, 30);
		Box.add(Ape);
		
		JLabel Tel = new JLabel("Teléfono");
		Tel.setBounds(268, 25, 100, 30);
		Box.add(Tel);
		
		nomText = new JTextField();
		nomText.setBounds(24, 50, 100, 30);
		Box.add(nomText);
		
		apeText = new JTextField();
		apeText.setBounds(135, 50, 100, 30);
		Box.add(apeText);
		
		telText = new JTextField();
		telText.setBounds(245, 50, 100, 30);
		Box.add(telText);
	
		
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(24, 120, 89, 23);
		btnAtras.addActionListener(this);
		Box.add(btnAtras);
		
		btnActualizar = new JButton("Crear reserva");
		btnActualizar.setBounds(195, 120, 150, 23);
		btnActualizar.addActionListener(this);
		Box.add(btnActualizar);
		
		ctx.add(Box);
		
		setResizable(false);
		//setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(btnAtras == e.getSource()) {
			dispose();			
		}
		
		if(btnActualizar == e.getSource()) {
			String dataNombre = nomText.getText();			
			String dataApellido = apeText.getText();
			String dataTelefono = telText.getText();	
			
			if(dataNombre.length() > 0 && dataApellido.length() > 0 && dataTelefono.length() > 0) {
				JOptionPane.showMessageDialog(null, "Reserva realizada con exito");
			} else {
				JOptionPane.showMessageDialog(null, "Es necesario completar todos los campos");
			}
		}	
		
	}
}
