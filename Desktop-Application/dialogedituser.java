import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class DialogEditUser
{
	private JDialog jd;
	private JFrame jf;
	private String id;
	private JTextField tuserName,tpassword,tname,tmobileNo,tconfirmPassword;
	private JComboBox cdesignation;
	DialogEditUser(String i,JFrame f)
	{
		jf=f;
		id=i;
		jd=new JDialog(jf,"Edit User",true);
		jd.setSize(500,500);
		jd.setLocationRelativeTo(jf);
		
		JDesktopPane d=new JDesktopPane();  
		jd.add(d);
		JInternalFrame j=infopanel();
		j.setSize(500,500);
		((javax.swing.plaf.basic.BasicInternalFrameUI)j.getUI()).setNorthPane(null);
		j.setVisible(true);
		d.add(j);
		j.toFront();
		
		jd.setVisible(true);
		jd.validate();
		jf.validate();
	}
	public JInternalFrame infopanel()
	{
		JInternalFrame pa=new JInternalFrame();
		JPanel p=new JPanel();
		p.setLayout(new GridBagLayout());
		
		JLabel lid=new JLabel("Login Id : ");
		JLabel luserName=new JLabel("UserName: ");
		JLabel lpassword=new JLabel("Password : ");
		JLabel lconfirmPassword=new JLabel("Confirm Password : ");
		JLabel lname=new JLabel("Name : ");
		JLabel lmobileNo=new JLabel("Mobile No : ");
		JLabel ldesignation=new JLabel("Designation : ");
		String suserName="",spassword="",sname="",smobileNo="",sdesignation="";
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select * from login where login_id=\""+id+"\"");
			TheInstituteDatabase.rs.next();
			suserName=TheInstituteDatabase.rs.getString("username");
			spassword=TheInstituteDatabase.rs.getString("password");
			sname=TheInstituteDatabase.rs.getString("name");
			smobileNo=TheInstituteDatabase.rs.getString("MobileNo");
			sdesignation=TheInstituteDatabase.rs.getString("designation");
		}
		catch(SQLException r)
		{
		System.out.println("B");
		}
				
		JLabel yid=new JLabel(id);
		tuserName=new JTextField(10);
		tuserName.setText(suserName);
		tpassword=new JTextField(10);
		tpassword.setText(spassword);
		tname=new JTextField(10);
		tname.setText(sname);
		tmobileNo=new JTextField(10);
		tmobileNo.setText(smobileNo);
		tmobileNo.addKeyListener(new KeyListener()
									{
										public void keyTyped(KeyEvent e)
										{}
										public void keyReleased(KeyEvent e)
										{}
										public void keyPressed(KeyEvent e)
										{
											int k;
											k=e.getKeyCode();
											if((k>=KeyEvent.VK_0 && k<=KeyEvent.VK_9) || (k>=KeyEvent.VK_NUMPAD0 && k<=KeyEvent.VK_NUMPAD9))
											{ 
												if(tmobileNo.getText()!=null && tmobileNo.getText().length()>9)
												{
													tmobileNo.setEditable(false);
													tmobileNo.setBackground(Color.WHITE);
												}
												else
													tmobileNo.setEditable(true);
											}
											else if(k==KeyEvent.VK_DELETE || k==KeyEvent.VK_BACK_SPACE )
											{
												tmobileNo.setEditable(true);
											}
											else if(k==KeyEvent.VK_LEFT || k==KeyEvent.VK_RIGHT)
											{
												tmobileNo.setEditable(true);
											}
											else
											{
												tmobileNo.setEditable(false);
												tmobileNo.setBackground(Color.WHITE);
											}
										}
									});
		
		tconfirmPassword=new JTextField(10);
		
		String d[]={"admin","manager","accounts_manager"};
		cdesignation=new JComboBox(d);
		cdesignation.setSelectedItem(sdesignation);
		
		JButton bdep=new JButton("Edit");
		bdep.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Are you sure you want to edit the database","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createuserdb();});
		
		GridBagConstraints gbc1=new GridBagConstraints();
		Insets in1=new Insets(10,10,10,10);
		gbc1.insets=in1;
		
		gbc1.anchor=GridBagConstraints.WEST;
		gbc1.gridx=0;
		gbc1.gridy=0;
		p.add(lid,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=0;
		p.add(yid,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=1;
		p.add(luserName,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=1;
		p.add(tuserName,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=2;
		p.add(lpassword,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=2;
		p.add(tpassword,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=3;
		p.add(lconfirmPassword,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=3;
		p.add(tconfirmPassword,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=4;
		p.add(lname,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=4;
		p.add(tname,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=5;
		p.add(lmobileNo,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=5;
		p.add(tmobileNo,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=6;
		p.add(ldesignation,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=6;
		p.add(cdesignation,gbc1);
		
		JPanel p2=new JPanel();
		gbc1.gridx=0;
		gbc1.gridy=7;
		p.add(p2,gbc1);
		
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(bdep);
		gbc1.gridx=1;
		gbc1.gridy=7;
		p.add(p3,gbc1);
		
		pa.add(p,BorderLayout.CENTER);
		
		return pa;
	}
	public void createuserdb()
	{
		try
		{
			String username,password,confirmpassword,name,mobileno,sdesignation;
			username=tuserName.getText();
			password=tpassword.getText();
			confirmpassword=tconfirmPassword.getText();
			name=tname.getText();
			mobileno=tmobileNo.getText();
			sdesignation=(String)cdesignation.getSelectedItem();
			if((username==null | username.equals(""))|(password==null | password.equals(""))|(confirmpassword==null | confirmpassword.equals(""))|(name==null | name.equals(""))|(mobileno==null | mobileno.equals("")))
			{
				JOptionPane.showMessageDialog(jf,"Fields are empty");
				return;
			}
			if(username.length()>10)
			{
				JOptionPane.showMessageDialog(jf,"username cannot be more than 10 characters long");
				return;
			}
			if(password.length()>10)
			{
				JOptionPane.showMessageDialog(jf,"password cannot be more than 10 characters long");
				return;
			}
			if(name.length()>20)
			{
				JOptionPane.showMessageDialog(jf,"name cannot be more than 20 characters long");
				return;
			}
			if(mobileno.length()!=10)
			{
				JOptionPane.showMessageDialog(jf,"invalid mobileno");
				return;
			}
			if(!(password.equals(confirmpassword)))
			{
				JOptionPane.showMessageDialog(jf,"Confirm Password field is wrong");
				return;
			}
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("update login set username=?,password=?,name=?,MobileNo=?,designation=? where login_id=\""+id+"\"");
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			pstmt.setString(3,name);
			pstmt.setString(4,mobileno);
			pstmt.setString(5,sdesignation);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"User Updated Successfully");
			jd.setVisible(false);
			jd.dispose();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(jf,"This Username already exists");
			return;
		}
	}	
}