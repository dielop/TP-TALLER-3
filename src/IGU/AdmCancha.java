package IGU;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;



public class AdmCancha extends JFrame implements ActionListener {

//Variables Globales
	private JTextField Nombre;
	private JTextField Apellido;
	private JTextField Telefono;
	JButton btnCaja, btnReservar, btnCancelar;;
	private JLabel lblCanchas;
	private JLabel lblDatos;
	JToggleButton btnCancha, btnCancha2, btnHora[], btnHora2[];
	int c=0;
	

	public AdmCancha() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		setLocationRelativeTo(null);
		setTitle("Tu cancha");
		
		Container ctx = getContentPane();
		JPanel MenuCanchas = new JPanel();
		MenuCanchas.setLayout(null);
		MenuCanchas.setBackground(Color.WHITE);


		
//BOTONES DE RESERVA CANCHA 1 O CANCHA 2
		
		btnCancha = new JToggleButton("Cancha 1", false);
		btnCancha.setBorder(UIManager.getBorder("RadioButton.border"));
		btnCancha.setBounds(222, 59, 162, 377);
		btnCancha.addActionListener(this);		
		MenuCanchas.add(btnCancha);
		
		btnCancha2 = new JToggleButton("Cancha 2", false);
		btnCancha2.setBorder(UIManager.getBorder("RadioButton.border"));
		btnCancha2.setBounds(410, 59, 162, 377);
		btnCancha2.addActionListener(this);
		MenuCanchas.add(btnCancha2);

//BOTONES DE HORARIOS - CREACION Y ADHESION
		
		btnHora = new JToggleButton[10];
		btnHora2 = new JToggleButton[10];
		
		int x = 40, y = 59, z = 65, w = 33;
		int a = 115, b = 59, c = 65, d = 33;
		
		for(int i = 0; i<10; i++) {			
			btnHora[i] = new JToggleButton(Integer.toString(i+13) + "hs", false);
			btnHora[i].setEnabled(false);
		}
		
		for(int i = 0; i<10; i++) {
			btnHora[i].setBounds(x, y, z, w);
			btnHora[i].addActionListener(this);
			MenuCanchas.add(btnHora[i]);
			y = y + 38;
		}
		
		for(int j = 0; j<10; j++) {			
			btnHora2[j] = new JToggleButton(Integer.toString(j+13) + "hs", false);
			btnHora2[j].setEnabled(false);
		}
		
		for(int j = 0; j<10; j++) {
			btnHora2[j].setBounds(a, b, c, d);
			btnHora2[j].addActionListener(this);
			MenuCanchas.add(btnHora2[j]);
			b = b + 38;
		}
       
		
		
//BOTON DE CAJA SUPERIOR
		btnCaja = new JButton("Ver caja");
		btnCaja.setBounds(620, 403, 106, 33);
		btnCaja.addActionListener(this);
		MenuCanchas.add(btnCaja);
		
//BOTONES DE RESERVA Y CANCELACION DE TURNO		
		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(620, 242, 106, 51);
		btnReservar.addActionListener(this);
		MenuCanchas.add(btnReservar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(620, 304, 106, 51);
		btnCancelar.addActionListener(this);
		MenuCanchas.add(btnCancelar);
		
		JButton btnVReservas = new JButton("Reservas");
		btnVReservas.setBounds(620, 366, 106, 33);
		MenuCanchas.add(btnVReservas);

//CAMPOS DE TEXTO PARA GUARDAR INFORMACION DE QUIEN RESERVA
		Nombre = new JTextField();
		Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		Nombre.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Nombre.setText("Nombre");
		Nombre.setBounds(620, 59, 106, 40);
		//Nombre.setColumns(10);
		MenuCanchas.add(Nombre);
		
		Apellido = new JTextField();
		Apellido.setHorizontalAlignment(SwingConstants.CENTER);
		Apellido.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Apellido.setText("Apellido");
		Apellido.setBounds(620, 110, 106, 40);
		MenuCanchas.add(Apellido);
		
		Telefono = new JTextField();
		Telefono.setHorizontalAlignment(SwingConstants.CENTER);
		Telefono.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Telefono.setText("Telefono");
		Telefono.setBounds(620, 161, 106, 40);
		MenuCanchas.add(Telefono);

//ETIQUETAS - TITULOS DE BOTONES
		JLabel lblHorarios = new JLabel("Horarios");
		lblHorarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblHorarios.setBounds(70, 20 , 89, 33);
        MenuCanchas.add(lblHorarios);
		
		lblCanchas = new JLabel("Canchas");
		lblCanchas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCanchas.setBounds(360, 20, 89, 33);
		MenuCanchas.add(lblCanchas);
		
		lblDatos = new JLabel("Datos cliente");
		lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDatos.setBounds(615, 20, 128, 33);
		MenuCanchas.add(lblDatos);
		
		ctx.add(MenuCanchas);
		

		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(btnReservar==e.getSource()) {
			JOptionPane.showMessageDialog(null,"Reserva exitosa");
		}
		
		if(btnCancelar==e.getSource()) {
			JOptionPane.showMessageDialog(null,"Cancelacion exitosa");
		}
		
		if(btnCaja==e.getSource()) {
			JOptionPane.showMessageDialog(null,"Abriendo caja");
			IGcaja mover = new IGcaja();
			mover.setVisible(true);
			
		}
		
		//Funcion cuando selecciono el boton de la cancha 1
		
		if(btnCancha.isSelected()) {
		for(int i = 0; i<10; i++) {
			btnHora[i].setEnabled(true);		
		  }
		}else {
			for(int i = 0; i<10; i++) {
				btnHora[i].setEnabled(false);
			}
		}
		
		for(int j=0; j<10; j++) {
		if(btnHora[j].isSelected()  && btnReservar == e.getSource()) {
				btnHora[j].setEnabled(false);
				btnCancha.setSelected(false);
				for(int i = 0; i<10; i++) {
					if(btnHora[j] != btnHora[i]) {
					btnHora[i].setEnabled(false);
					}
				}
				
			}
		}
		
		//Funcion cuando selecciono el boton de la cancha 2
		
		if(btnCancha2.isSelected()) {
			for(int i = 0; i<10; i++) {
				btnHora2[i].setEnabled(true);
			}
		}else {
			for(int i = 0; i<10; i++) {
				btnHora2[i].setEnabled(false);
			}
		}
		for(int j=0; j<10; j++) {
			if(btnHora2[j].isSelected()  && btnReservar == e.getSource()) {
					btnHora2[j].setEnabled(false);
					btnCancha2.setSelected(false);
					for(int i = 0; i<10; i++) {
						if(btnHora2[j] != btnHora2[i]) {
						btnHora2[i].setEnabled(false);
						}
					}
					
				}
			}
		
		
	
  }
}
