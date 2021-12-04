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
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;



public class AdmCancha extends JFrame implements ActionListener {

	//Variables Globales
	JButton btnCaja, btnCancelar;
	JButton btnReservar;
	private JLabel lblDatos;
	JToggleButton btnCancha, btnCancha2, btnHora[], btnHora2[];
	int c=0;
	Color green = new Color(10, 47, 30);
	Color selectedGreen = new Color(75, 139,  59);
    int contador1 = 0, temp1 = 0, contador2 = 0, temp2 = 0;
    ArrayList <String> horario1 = new ArrayList <String>();
    ArrayList <String> horario2 = new ArrayList <String>();

	public AdmCancha() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(730,560);
		setLocationRelativeTo(null);
		setTitle("Tu cancha");
		
		Container ctx = getContentPane();
		JPanel MenuCanchas = new JPanel();
		MenuCanchas.setLayout(null);
		MenuCanchas.setBackground(Color.BLACK);

		//BOTONES DE RESERVA CANCHA 1 O CANCHA 2
		
		btnCancha = new JToggleButton("Cancha 1", false);
		UIManager.put("ToggleButton.select", selectedGreen);
		SwingUtilities.updateComponentTreeUI(btnCancha);
		btnCancha.setBorder(UIManager.getBorder("RadioButton.border"));
		btnCancha.setBounds(122, 59, 162, 377);
		btnCancha.addActionListener(this);
		btnCancha.setBackground(green);
		btnCancha.setForeground(Color.WHITE);
		MenuCanchas.add(btnCancha);
		
		btnCancha2 = new JToggleButton("Cancha 2", false);
		UIManager.put("ToggleButton.select", selectedGreen);
		SwingUtilities.updateComponentTreeUI(btnCancha2);
		btnCancha2.setBorder(UIManager.getBorder("RadioButton.border"));
		btnCancha2.setBounds(300, 59, 162, 377);
		btnCancha2.addActionListener(this);
		btnCancha2.setBackground(green);
		btnCancha2.setForeground(Color.WHITE);
		MenuCanchas.add(btnCancha2);

		//BOTONES DE HORARIOS - CREACION Y ADHESION
		btnHora = new JToggleButton[10];
		btnHora2 = new JToggleButton[10];
		
		int x = 40, y = 59, z = 75, w = 33;
		int a = 470, b = 59, c = 75, d = 33;
		
		for(int i = 0; i<10; i++) {			
			btnHora[i] = new JToggleButton(Integer.toString(i+13) + "hs", false);
			btnHora[i].setEnabled(false);
			
			btnHora[i].setBounds(x, y, z, w);
			btnHora[i].addActionListener(this);
			MenuCanchas.add(btnHora[i]);
			y = y + 38;
			
			btnHora2[i] = new JToggleButton(Integer.toString(i+13) + "hs", false);
			btnHora2[i].setEnabled(false);
			
			btnHora2[i].setBounds(a, b, c, d);
			btnHora2[i].addActionListener(this);
			MenuCanchas.add(btnHora2[i]);
			b = b + 38;
		}
		
		//BOTON DE CAJA SUPERIOR
		btnCaja = new JButton("Ver caja");
		btnCaja.setBounds(570, 403, 106, 33);
		btnCaja.addActionListener(this);
		MenuCanchas.add(btnCaja);
		
		//BOTONES DE RESERVA Y CANCELACION DE TURNO
		btnReservar = new JButton("Reservar");
		btnReservar.setBounds(570, 58, 106, 51);
		btnReservar.setEnabled(false);
		btnReservar.addActionListener(this);
		MenuCanchas.add(btnReservar);
	
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(570, 120, 106, 51);
		btnCancelar.setEnabled(false);
		btnCancelar.addActionListener(this);
		MenuCanchas.add(btnCancelar);
		
		JButton btnVReservas = new JButton("Reservas");
		btnVReservas.setBounds(570, 366, 106, 33);
		MenuCanchas.add(btnVReservas);

		//ETIQUETAS - TITULOS DE BOTONES
		JLabel lblHorarios = new JLabel("Canchas con sus horarios");
		lblHorarios.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHorarios.setForeground(Color.WHITE);
        lblHorarios.setBounds(170, 20 , 440, 33);
        MenuCanchas.add(lblHorarios);
		
		lblDatos = new JLabel("Acciones");
		lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDatos.setForeground(Color.WHITE);
		lblDatos.setBounds(580, 20, 120, 33);
		MenuCanchas.add(lblDatos);
		
		ctx.add(MenuCanchas);
		
		

		setVisible(true);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(btnReservar == e.getSource()) {
			JOptionPane.showMessageDialog(null, "Es necesario completar los datos del cliente");
			Clientes mover = new Clientes();
			mover.setVisible(true);
		}
		
		if(btnCancelar == e.getSource()) {
			JOptionPane.showMessageDialog(null, "Cancelacion exitosa");
		}
		
		if(btnCaja == e.getSource()) {
			JOptionPane.showMessageDialog(null, "Abriendo caja");
			IGcaja mover = new IGcaja();
			mover.setVisible(true);
		}
		
		// Cuando se selecciona una cancha se deselecciona la otra
			if (btnCancha2 == e.getSource()) {
				btnCancha.setSelected(false);        
			}
	 
			if(btnCancha == e.getSource()) {
				btnCancha2.setSelected(false);
			}
		
		// Tambien se deseleccionan sus horarios		 
		for(int i = 0; i<10; i++) {
			if(btnCancha.isSelected()) {
				btnHora[i].setEnabled(true);	
                btnHora2[i].setEnabled(false);
        //Verifica que la hora se haya seleccionado y que no este contenida en el arraylist de horarios seleccionados anteriormente         
             if(btnHora[i].isSelected() && !horario1.contains(btnHora[i].toString())) {
                	btnReservar.setEnabled(true);
                	if(btnReservar.isSelected()) {
    		 		horario1.add(btnHora[i].toString()); //Agrego el horario seleccionado al arraylist           	   
                	}
            	}  	 	              
	
			}     
			 else if (btnCancha2.isSelected()) {
				btnHora2[i].setEnabled(true);
                btnHora[i].setEnabled(false);
                	if(btnHora2[i].isSelected() && !horario2.contains(btnHora2[i].toString())) {
                		btnReservar.setEnabled(true);
                		if(btnReservar.isSelected()) {
                		horario2.add(btnHora2[i].toString()); //Agrego el horario seleccionado al arraylist   
                		}
            	}  	 	              
                
			} else {
				btnHora2[i].setEnabled(false);
                btnHora[i].setEnabled(false);
                btnReservar.setEnabled(false);
			}
		
		}
		
		
		//Cancelacion de reservas
		for(int i = 0; i<10; i++) {
			if(btnCancha.isSelected() && btnHora[i].isSelected()) {
				btnCancelar.setEnabled(true); 
        //Verifica que este contenido en el arraylist de horarios seleccionados anteriormente y elimina       
             if(btnCancelar.isSelected()){                	              
    		 		horario1.remove(btnHora[i].toString());
    		 		btnHora[i].setSelected(false);
    		 		btnCancha.setSelected(false);
                	
            	}else {
            		
            	}
	
			}   
		}
		
		// Inhabilita luego de reservar
		for(int j=0; j<10; j++) {
			if(btnHora[j].isSelected()  && btnReservar == e.getSource()) {
				btnHora[j].setEnabled(false);
				btnCancha.setSelected(false);
				btnReservar.setEnabled(false);
				
				for(int i = 0; i<10; i++) {
					if(btnHora[j] != btnHora[i]) {
						btnHora[i].setEnabled(false);
					}
				}
				
			} else if(btnHora2[j].isSelected() && btnReservar == e.getSource()) {
				btnHora2[j].setEnabled(false);
				btnCancha2.setSelected(false);
				btnReservar.setEnabled(false);
				
				for(int i = 0; i<10; i++) {
					if(btnHora2[j] != btnHora2[i]) {
						btnHora2[i].setEnabled(false);
					}
				}	
			}
		}
		
	}
}

//CAMPOS DE TEXTO PARA GUARDAR INFORMACION DE QUIEN RESERVA
		//Nombre = new JTextField();
		//Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		//Nombre.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//Nombre.setText("Nombre");
		//Nombre.setBounds(570, 59, 106, 40);
		//Nombre.setColumns(10);
		//MenuCanchas.add(Nombre);
		
		//Apellido = new JTextField();
		//Apellido.setHorizontalAlignment(SwingConstants.CENTER);
		//Apellido.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//Apellido.setText("Apellido");
		//Apellido.setBounds(570, 110, 106, 40);
		//MenuCanchas.add(Apellido);
		
		//Telefono = new JTextField();
		//Telefono.setHorizontalAlignment(SwingConstants.CENTER);
		//Telefono.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//Telefono.setText("Telefono");
		//Telefono.setBounds(570, 161, 106, 40);
		//MenuCanchas.add(Telefono);
