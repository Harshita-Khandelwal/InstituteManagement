import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
@SuppressWarnings("deprecation")
class TheInstituteLoginGUI implements ActionListener
{
	private JFrame flogin;
	private JRadioButton rbadmin,rbmanager,rbaccount_man;
	private ButtonGroup bg;
	private JPanel framepanel,p1,p2,p3,p21,p22;
	private JLabel lusername,lpassword;
	private JTextField tusername;
	private JPasswordField tpassword;
	private JButton bsubmit,bcancel;
	private String checklogin;
	public static String storelogin;
	
	TheInstituteLoginGUI()
	{
		flogin=new JFrame("The Institute Management System");
		flogin.setSize(380,220);
		flogin.setLocationRelativeTo(null);
		flogin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		flogin.setResizable(false);
		framepanel=(JPanel)flogin.getContentPane();
		framepanel.setLayout(new GridLayout(3,1));
		
		rbadmin=new JRadioButton("Admin",true);
		rbmanager=new JRadioButton("Manager");
		rbaccount_man=new JRadioButton("Accounts_Manager");
		checklogin="admin";
		bg=new ButtonGroup();
		bg.add(rbadmin);
		bg.add(rbmanager);
		bg.add(rbaccount_man);
		
		p1=new JPanel();
		p1.add(rbadmin);
		p1.add(rbmanager);
		p1.add(rbaccount_man);
		
		p2=new JPanel();
		lusername=new JLabel("Username : ");
		lpassword=new JLabel("Password : ");
		tusername=new JTextField(15);
		tpassword=new JPasswordField(15);
		p2.setLayout(new GridLayout(2,1));
		p21=new JPanel();
		p22=new JPanel();
		p21.add(lusername);
		p21.add(tusername);
		p22.add(lpassword);
		p22.add(tpassword);
		p2.add(p21);
		p2.add(p22);
		
		p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER,30,20));
		bsubmit=new JButton("Submit");
		bcancel=new JButton("Cancel");
		p3.add(bsubmit);
		p3.add(bcancel);
		
		bsubmit.addActionListener(this);
		bcancel.addActionListener(this);
		rbadmin.addActionListener(this);
		rbmanager.addActionListener(this);
		rbaccount_man.addActionListener(this);
		
		framepanel.add(p1);
		framepanel.add(p2);
		framepanel.add(p3);
		
		flogin.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		Object o=e.getSource();
		if(o==bsubmit)
		{
			try
			{
				String user,pass;
				int checkuser=0;
				user=tusername.getText();
				pass=tpassword.getText();
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select login_id,username,password,designation from login where designation=\""+checklogin+"\"");
				while(TheInstituteDatabase.rs.next()==true)
				{
					if(user.equals(TheInstituteDatabase.rs.getString("username")))
					{
						checkuser=1;
						if(pass.equals(TheInstituteDatabase.rs.getString("password")))
						{
							storelogin=TheInstituteDatabase.rs.getString("login_id");
							String designation=TheInstituteDatabase.rs.getString("designation");
							
							if(designation.equals("admin"))
								new TheInstituteAdminGUI();
							else if(designation.equals("manager"))
								new TheInstituteManagerGUI();
							else if(designation.equals("accounts_manager"))
								new TheInstituteAccGUI();
								
							flogin.setVisible(false);
							flogin.dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(flogin,"Invalid Password","Message",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				if(checkuser==0 )
				{
					JOptionPane.showMessageDialog(flogin,"Invalid Username","Message",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(SQLException ex)
			{
			}
		}
		else if(o==bcancel)
		{
			flogin.setVisible(false);
			flogin.dispose();
			System.exit(1);
		}
		else if(o==rbadmin)
		{
			checklogin="admin";
		}
		else if(o==rbmanager)
		{
			checklogin="manager";
		}
		else if(o==rbaccount_man)
		{
			checklogin="accounts_manager";
		}
	}
	
	public static void main(String args[])
	{
		new TheInstituteLoginGUI();
	}
}