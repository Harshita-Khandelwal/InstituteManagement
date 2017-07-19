import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
@SuppressWarnings({"unchecked","deprecation"})
class User extends Common
{
	private JButton badmin,bmanager,baccount_man,bcreateuser,bcreatenewuser,bwatchuser;
	private JTextField tlogin,tusername,tname,tmobileno,tdesignation;
	private JPasswordField tpassword,tconfirmpassword;
	User(JPanel p,JDesktopPane d,JFrame f)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		userpanel();
	}
	public void userpanel()
	{
		startp();
		add.setText("User Profile :");
		
		bcreatenewuser=new JButton("Create New Profile");
		bwatchuser=new JButton("View/Edit All Profiles");
		bcreatenewuser.addActionListener((e)->{createuserpanel();});
		bwatchuser.addActionListener((e)->{new ViewUser(framepanel,desktop,jf);});
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		pce1.add(bcreatenewuser,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(bwatchuser,gbc);
		
		JPanel p1=new JPanel();
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(p1,gbc);
		
		endp();
	}
	public void createuserpanel()
	{
		startp();
		add.setText("Create New User :");
		
		JLabel selectdesignation=new JLabel("Select Designation:");
		selectdesignation.setFont(new Font("Arial",Font.BOLD,20));
		badmin=new JButton("Admin");
		bmanager=new JButton("Manager");
		baccount_man=new JButton("Accounts Manager");
		badmin.addActionListener((e)->{int l=calc_login_id();
									userformGUI(l,"admin");});
		bmanager.addActionListener((e)->{int l=calc_login_id();
										userformGUI(l,"manager");});
		baccount_man.addActionListener((e)->{int l=calc_login_id();
											userformGUI(l,"account_manager");});
	
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(selectdesignation,gbc);

		JPanel p=new JPanel();
		gbc.gridx=1;
		gbc.gridy=0;
		pce1.add(p,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		pce1.add(badmin,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		pce1.add(bmanager,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		pce1.add(baccount_man,gbc);
		
		endp();
	}
	public int calc_login_id()
	{
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select max(login_id) as highest from login");
			TheInstituteDatabase.rs.next();
			int postno2;
			postno2=TheInstituteDatabase.rs.getInt("highest");
			postno2++;
			return postno2;
		}
		catch(Exception ex)
		{
		}
		return 0;
	}
	public void userformGUI(int loginid,String post)
	{	
		startp();
		add.setText("Create New User :");
		
		tlogin=new JTextField(20);
		tusername=new JTextField(20);
		tpassword=new JPasswordField(20);
		tconfirmpassword=new JPasswordField(20);
		tname=new JTextField(20);
		tmobileno=new JTextField(20);
		tmobileno.addKeyListener(new KeyListener()
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
												if(tmobileno.getText()!=null && tmobileno.getText().length()>9)
												{
													tmobileno.setEditable(false);
													tmobileno.setBackground(Color.WHITE);
												}
												else
													tmobileno.setEditable(true);
											}
											else if(k==KeyEvent.VK_DELETE || k==KeyEvent.VK_BACK_SPACE )
											{
												tmobileno.setEditable(true);
											}
											else if(k==KeyEvent.VK_LEFT || k==KeyEvent.VK_RIGHT)
											{
												tmobileno.setEditable(true);
											}
											else
											{
												tmobileno.setEditable(false);
												tmobileno.setBackground(Color.WHITE);
											}
										}
									});
		
		tdesignation=new JTextField(20);
		JLabel llogin=new JLabel("Login ID : ");
		JLabel lusername=new JLabel("Username : ");
		JLabel lpassword=new JLabel("Password : ");
		JLabel lconfirmpassword=new JLabel("Confirm Password : ");
		JLabel lname=new JLabel("Name : ");
		JLabel lmobileno=new JLabel("Mobile No : ");
		JLabel ldesignation=new JLabel("Designation : ");
		bcreateuser=new JButton("Create");
		bcreateuser.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Do you want to create the database","Confirmation",JOptionPane.YES_NO_OPTION);
											if(x==JOptionPane.YES_OPTION)
												{createuserdb();}});

		gbc.anchor=GridBagConstraints.WEST;
		
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(llogin,gbc);
		
		tlogin.setText(Integer.toString(loginid));
		tlogin.setEditable(false);
		gbc.gridx=1;
		gbc.gridy=0;
		pce1.add(tlogin,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(lusername,gbc);
		
		tusername.setText("");
		gbc.gridx=1;
		gbc.gridy=1;
		pce1.add(tusername,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(lpassword,gbc);
		
		tpassword.setText("");
		gbc.gridx=1;
		gbc.gridy=2;
		pce1.add(tpassword,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		pce1.add(lconfirmpassword,gbc);
		
		tconfirmpassword.setText("");
		gbc.gridx=1;
		gbc.gridy=3;
		pce1.add(tconfirmpassword,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		pce1.add(lname,gbc);
		
		tname.setText("");
		gbc.gridx=1;
		gbc.gridy=4;
		pce1.add(tname,gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;
		pce1.add(lmobileno,gbc);
		
		tmobileno.setText("");
		gbc.gridx=1;
		gbc.gridy=5;
		pce1.add(tmobileno,gbc);
		
		gbc.gridx=0;
		gbc.gridy=6;
		pce1.add(ldesignation,gbc);
		
		tdesignation.setText(post);
		tdesignation.setEditable(false);
		gbc.gridx=1;
		gbc.gridy=6;
		pce1.add(tdesignation,gbc);
		
		JPanel p4=new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
		p4.add(bcreateuser);
		gbc.gridx=1;
		gbc.gridy=7;
		pce1.add(p4,gbc);
			
		endp();
	}
	public void createuserdb()
	{
		try
		{
			int login;
			String username,password,confirmpassword,name,mobileno,sdesignation,add_by;
			login=Integer.parseInt(tlogin.getText());
			username=tusername.getText();
			password=tpassword.getText();
			confirmpassword=tconfirmpassword.getText();
			name=tname.getText();
			mobileno=tmobileno.getText();
			sdesignation=tdesignation.getText();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username from login where login_id=\""+TheInstituteLoginGUI.storelogin+"\"");
			TheInstituteDatabase.rs.next();
			add_by=TheInstituteDatabase.rs.getString("username");
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
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("insert into login values(?,?,?,?,?,?,?)");
			pstmt.setInt(1,login);
			pstmt.setString(2,username);
			pstmt.setString(3,password);
			pstmt.setString(4,name);
			pstmt.setString(5,mobileno);
			pstmt.setString(6,sdesignation);
			pstmt.setString(7,add_by);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"New User Created Successfully");
			createuserpanel();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(jf,"This Username already exists");
			return;
		}
	}

}