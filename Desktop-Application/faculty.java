import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
@SuppressWarnings({"unchecked","deprecation"})
class Faculty extends Common
{
	private JButton bcreatenewfac,bwatchfac,bcreatefac;
	private int slabel1,l;
	private JTextField tfac_name,tfac_mobile_no,tfac_qualification;
	private JTextArea tfac_address;
	private JComboBox cfac_course;
	Faculty(JPanel p,JDesktopPane d,JFrame f,int l)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		this.l=l;
		faculty_panel();
	}
	public void faculty_panel()
	{
		startp();
		add.setText("Faculty Profile :");
		
			bcreatenewfac=new JButton("Create New Profile");
			bwatchfac=new JButton("View/Edit All Profiles");
			bcreatenewfac.addActionListener((e)->{createfacpanel();});
			bwatchfac.addActionListener((e)->{new ViewFaculty(framepanel,desktop,jf,l);});
			
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.fill=GridBagConstraints.HORIZONTAL;
			pce1.add(bcreatenewfac,gbc);
			
			gbc.gridx=0;
			gbc.gridy=1;
			pce1.add(bwatchfac,gbc);
			
			JPanel p=new JPanel();
			gbc.gridx=0;
			gbc.gridy=2;
			pce1.add(p,gbc);
		
		endp();
	}
	public void createfacpanel()
	{
		startp();
		add.setText("Create Faculty Profile :");
		
		JLabel lfac_id=new JLabel("Faculty Id :");
		slabel1=0;
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select max(faculty_id) as highest from faculty");
			TheInstituteDatabase.rs.next();
			slabel1=(TheInstituteDatabase.rs.getInt("highest"));
			slabel1++;
		}
		catch(SQLException r)
		{}
		JLabel lfac_id_no=new JLabel("F"+slabel1);
		JLabel lfac_name=new JLabel("Name :");
		JLabel lfac_mobile_no=new JLabel("Mobile No :");
		JLabel lfac_address=new JLabel("Address :");
		JLabel lfac_qualification=new JLabel("Qualification :");
		JLabel lfac_course=new JLabel("Course :");
		tfac_name=new JTextField(10);
		tfac_mobile_no=new JTextField(10);
		tfac_mobile_no.addKeyListener(new KeyListener()
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
												if(tfac_mobile_no.getText()!=null && tfac_mobile_no.getText().length()>9)
												{
													tfac_mobile_no.setEditable(false);
													tfac_mobile_no.setBackground(Color.WHITE);
												}
												else
													tfac_mobile_no.setEditable(true);
											}
											else if(k==KeyEvent.VK_DELETE || k==KeyEvent.VK_BACK_SPACE )
											{
												tfac_mobile_no.setEditable(true);
											}
											else if(k==KeyEvent.VK_LEFT || k==KeyEvent.VK_RIGHT)
											{
												tfac_mobile_no.setEditable(true);
											}
											else
											{
												tfac_mobile_no.setEditable(false);
												tfac_mobile_no.setBackground(Color.WHITE);
											}
										}
									});
		
		tfac_address=new JTextArea(5,10);
		JScrollPane sp=new JScrollPane(tfac_address);
		tfac_address.setLineWrap(true);
		tfac_qualification=new JTextField(10);
		java.util.Vector c=new java.util.Vector();
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name from course");
			while(TheInstituteDatabase.rs.next()==true)
			{
				c.add(TheInstituteDatabase.rs.getString("course_name"));
			}
		}
		catch(SQLException r)
		{}
		Object cc[];
		cc=c.toArray();
		cfac_course=new JComboBox(c);
		bcreatefac=new JButton("Create");
		bcreatefac.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Do you want to create the database","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createfacdb();});
		
		in=new Insets(10,20,10,20);
		gbc.insets=in;
		gbc.anchor=GridBagConstraints.EAST;
		
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		p1.add(lfac_id);
		p1.add(lfac_id_no);
		
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(p1,gbc);
		
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(lfac_name,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		pce1.add(tfac_name,gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		pce1.add(lfac_mobile_no,gbc);
		
		gbc.gridx=3;
		gbc.gridy=1;
		pce1.add(tfac_mobile_no,gbc);
	
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(lfac_address,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		pce1.add(sp,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		pce1.add(lfac_qualification,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		pce1.add(tfac_qualification,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		pce1.add(lfac_course,gbc);
		
		gbc.gridx=1;
		gbc.gridy=4;
		pce1.add(cfac_course,gbc);
		
		JPanel p2=new JPanel();
		gbc.gridx=0;
		gbc.gridy=5;
		pce1.add(p2,gbc);
		
		gbc.anchor=GridBagConstraints.EAST;
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(bcreatefac);
		gbc.gridx=1;
		gbc.gridy=5;
		pce1.add(p3,gbc);
		
		endp();
	}
	public void createfacdb()
	{
		try
		{
			int fac_id;
			String fac_name,fac_no,fac_address,fac_qualification,fac_course,add_by;
			fac_id=slabel1;
			fac_name=tfac_name.getText();
			fac_no=tfac_mobile_no.getText();
			fac_address=tfac_address.getText();
			fac_qualification=tfac_qualification.getText();
			if((fac_name==null | fac_name.equals(""))|(fac_no==null | fac_no.equals(""))|(fac_address==null | fac_address.equals(""))|(fac_qualification==null | fac_qualification.equals("")))
			{
					JOptionPane.showMessageDialog(jf,"Fields are empty");
					return;
			}
			if(fac_name.length()>20)
			{
				JOptionPane.showMessageDialog(jf,"Faculty name cannot be more than 20 characters");
					return;
			}
			if(fac_address.length()>50)
			{
				JOptionPane.showMessageDialog(jf,"Faculty address cannot be more than 50 characters");
					return;
			}
			if(fac_no.length()!=10)
			{
				JOptionPane.showMessageDialog(jf,"Invalid Faculty mobile no");
					return;
			}
			if(fac_qualification.length()>10)
			{
				JOptionPane.showMessageDialog(jf,"Faculty qualification cannot be more than 10 characters");
					return;
			}
			fac_course=(String)cfac_course.getSelectedItem();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+fac_course+"\"");
			TheInstituteDatabase.rs.next();
			String ci=TheInstituteDatabase.rs.getString("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username from login where login_id=\""+TheInstituteLoginGUI.storelogin+"\"");
			TheInstituteDatabase.rs.next();
			add_by=TheInstituteDatabase.rs.getString("username");
			java.sql.Date add_date=new java.sql.Date(System.currentTimeMillis());
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("insert into faculty values(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,fac_id);
			pstmt.setString(2,fac_name);
			pstmt.setString(3,fac_address);
			pstmt.setString(4,fac_no);
			pstmt.setString(5,fac_qualification);
			pstmt.setString(6,ci);
			pstmt.setDate(7,add_date);
			pstmt.setString(8,add_by);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"New Faculty Profile Created Successfully");
			faculty_panel();
		}
		catch(SQLException r)
		{}
	}

}