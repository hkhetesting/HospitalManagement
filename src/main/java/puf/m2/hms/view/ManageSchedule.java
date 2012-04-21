package puf.m2.hms.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import puf.m2.hms.model.HmsException;
import puf.m2.hms.model.Physician;
import puf.m2.hms.model.Schedule;
import puf.m2.hms.view.datechooser.JDateChooser;

public class ManageSchedule extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	// Variables declaration - do not modify
	private javax.swing.JButton btnSave;
	private javax.swing.JComboBox cboDoctorID;
	private javax.swing.JCheckBox chkAvailable;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel lblDoctorID;
	private javax.swing.JLabel lblEndDate;
	private javax.swing.JLabel lblStartDate;
	private JDateChooser txtEndDate;
	private JDateChooser txtStartDate;

	// End of variables declaration

	public ManageSchedule() {
		initComponents();
		addActionListener();
		fillComboBox();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Save".equals(e.getActionCommand())) {
			int doctorID = Integer.parseInt(cboDoctorID.getSelectedItem()
					.toString());
			Physician physician = null;
			try {
				physician = Physician.getPhysicianById(doctorID);
			} catch (HmsException e1) {
				e1.printStackTrace();
			}
			Schedule schedule = new Schedule(physician, txtStartDate.getDate(),
					txtEndDate.getDate(), chkAvailable.isSelected());
			try {
				schedule.save();
			} catch (HmsException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Save schedule successful");
		}
	}

	private void addActionListener() {
		btnSave.setActionCommand("Save");
		btnSave.addActionListener(this);
	}

	private void fillComboBox() {
		try {
			for (Physician doctor : Physician.getDoctors()) {
				cboDoctorID.addItem(doctor.getId());
			}
		} catch (HmsException e) {
			e.printStackTrace();
		}

	}

	private void initComponents() {

		lblDoctorID = new javax.swing.JLabel();
		cboDoctorID = new javax.swing.JComboBox();
		jLabel2 = new javax.swing.JLabel();
		lblEndDate = new javax.swing.JLabel();
		lblStartDate = new javax.swing.JLabel();
		txtStartDate = new JDateChooser();
		txtEndDate = new JDateChooser();
		chkAvailable = new javax.swing.JCheckBox();
		btnSave = new javax.swing.JButton();

		lblDoctorID.setText("Doctor ID");

		jLabel2.setText("Available");

		lblEndDate.setText("End date");

		lblStartDate.setText("Start date");

		btnSave.setText("Save");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(btnSave)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						lblDoctorID)
																				.addComponent(
																						lblStartDate)
																				.addComponent(
																						lblEndDate)
																				.addComponent(
																						jLabel2))
																.addGap(27, 27,
																		27)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						chkAvailable)
																				.addComponent(
																						txtStartDate,
																						javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						cboDoctorID,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						0,
																						102,
																						Short.MAX_VALUE)
																				.addComponent(
																						txtEndDate))))
								.addContainerGap(33, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblDoctorID)
												.addComponent(
														cboDoctorID,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblStartDate)
												.addComponent(
														txtStartDate,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(8, 8, 8)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblEndDate)
																.addGap(11, 11,
																		11)
																.addComponent(
																		jLabel2))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		txtEndDate,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		chkAvailable)))
								.addGap(1, 1, 1).addComponent(btnSave)
								.addContainerGap(25, Short.MAX_VALUE)));
	}// </editor-fold>
}
