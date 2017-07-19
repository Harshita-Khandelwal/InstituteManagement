import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
@SuppressWarnings({"unchecked","deprecation"})
class Student extends Common implements KeyListener
{
	private JButton bcreatenewstu,bwatchstu,bcreatestu;
	private JTextField tstu_name,tstu_mobile_no,tstu_father_name,tstu_father_mobile_no,tstu_clg;
	private int slabel;
	private JTextArea tstu_address;
	private JComboBox cstu_course,cstu_batch;
	Student(JPanel p,JDesktopPane d,JFrame f)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		stupanel();
	}
	public void stupanel()
	{
		startp();
		add.setText("Student Profile :");
		
		bcreatenewstu=new JButton("Create New Profile");
		bwatchstu=new JButton("View/Edit All Profiles");
		bcreatenewstu.addActionListener((e)->{createstupanel();});
		bwatchstu.addActionListener((e)->{new ViewStudent(framepanel,desktop,jf);});
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		pce1.add(bcreatenewstu,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(bwatchstu,gbc);
		
		JPanel p1=new JPanel();
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(p1,gbc);
		
		endp();
	}
	public void createstupanel()
	{
		startp();
		add.setText("Create Student Profile :");
		
		JLabel lstu_id=new JLabel("Student Id :");
		slabel=0;
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select max(stu_id) as highest from student");
			TheInstituteDatabase.rs.next();
			slabel=(TheInstituteDatabase.rs.getInt("highest"));
			slabel++;
		}
		catch(SQLException r)
		{}
		JLabel lstu_id_no=new JLabel("S"+slabel);
		JLabel lstu_name=new JLabel("Name :");
		JLabel lstu_mobile_no=new JLabel("Mobile No :");
		JLabel lstu_father_name=new JLabel("Father's Name :");
		JLabel lstu_father_mobile_no=new JLabel("Father's Mobile No :");
		JLabel lstu_address=new JLabel("Address :");
		JLabel lstu_clg=new JLabel("College :");
		JLabel lstu_course=new JLabel("Course :");
		JLabel lstu_batch=new JLabel("Batch :");
		tstu_name=new JTextField(10);
		tstu_mobile_no=new JTextField(10);
		tstu_mobile_no.addKeyListener(this);
		tstu_father_name=new JTextField(10);
		tstu_father_mobile_no=new JTextField(10);
		tstu_father_mobile_no.addKeyListener(this);
		tstu_clg=new JTextField(10);
		tstu_address=new JTextArea(5,10);
		JScrollPane sp=new JScrollPane(tstu_address);
		tstu_address.setLineWrap(true);
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
		cstu_course=new JComboBox(c);
		cstu_course.addActionListener((e)->{calc_stu_batch();});
		cstu_batch=new JComboBox();
		calc_stu_batch();
		bcreatestu=new JButton("Create");
		bcreatestu.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Do you want to create the database","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createstudb();});
		
		in=new Insets(10,20,10,20);
		gbc.insets=in;
		gbc.anchor=GridBagConstraints.EAST;
		
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		p1.add(lstu_id);
		p1.add(lstu_id_no);
		
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(p1,gbc);
		
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(lstu_name,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		pce1.add(tstu_name,gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		pce1.add(lstu_mobile_no,gbc);
		
		gbc.gridx=3;
		gbc.gridy=1;
		pce1.add(tstu_mobile_no,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(lstu_father_name,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		pce1.add(tstu_father_name,gbc);
		
		gbc.gridx=2;
		gbc.gridy=2;
		pce1.add(lstu_father_mobile_no,gbc);
		
		gbc.gridx=3;
		gbc.gridy=2;
		pce1.add(tstu_father_mobile_no,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		pce1.add(lstu_address,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		pce1.add(sp,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		pce1.add(lstu_clg,gbc);
		
		gbc.gridx=1;
		gbc.gridy=4;
		pce1.add(tstu_clg,gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;
		pce1.add(lstu_course,gbc);
		
		gbc.gridx=1;
		gbc.gridy=5;
		pce1.add(cstu_course,gbc);
		
		gbc.gridx=2;
		gbc.gridy=5;
		pce1.add(lstu_batch,gbc);
		
		gbc.gridx=3;
		gbc.gridy=5;
		pce1.add(cstu_batch,gbc);
		
		JPanel p2=new JPanel();
		gbc.gridx=0;
		gbc.gridy=6;
		pce1.add(p2,gbc);
		
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(bcreatestu);
		gbc.gridx=1;
		gbc.gridy=6;
		pce1.add(p3,gbc);
		
	/*	gbc.gridx=2;
		gbc.gridy=6;
		pce1.add(bcreatestu,gbc);*/
		
		endp();
	}
	public void keyTyped(KeyEvent e)
	{}
	public void keyReleased(KeyEvent e)
	{}
	public void keyPressed(KeyEvent e)
	{
		JTextField t=(JTextField)e.getSource();
		int k;
		k=e.getKeyCode();
		if((k>=KeyEvent.VK_0 && k<=KeyEvent.VK_9) || (k>=KeyEvent.VK_NUMPAD0 && k<=KeyEvent.VK_NUMPAD9))
		{ 
			if(t.getText()!=null && t.getText().length()>9)
			{
				t.setEditable(false);
				t.setBackground(Color.WHITE);
			}
			else
				t.setEditable(true);
		}
		else if(k==KeyEvent.VK_DELETE || k==KeyEvent.VK_BACK_SPACE )
		{
			t.setEditable(true);
		}
		else if(k==KeyEvent.VK_LEFT || k==KeyEvent.VK_RIGHT)
		{
			t.setEditable(true);
		}
		else
		{
			t.setEditable(false);
			t.setBackground(Color.WHITE);
		}
	}
	public void calc_stu_batch()
	{
		try
		{
			String course=(String)cstu_course.getSelectedItem();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+course+"\"");
			TheInstituteDatabase.rs.next();
			String ci=TheInstituteDatabase.rs.getString("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_name from batch where course_id=\""+ci+"\"");
			cstu_batch.removeAllItems();
			while(TheInstituteDatabase.rs.next()==true)
			{
				cstu_batch.addItem(TheInstituteDatabase.rs.getString("batch_name"));
			}
		}
		catch(SQLException r)
		{}
	}
	public void createstudb()
	{
		try
		{
			int stu_id;
			String stu_name,stu_no,stu_f_name,stu_f_no,stu_address,stu_clg,stu_course,stu_batch,add_by;
			stu_id=slabel;
			stu_name=tstu_name.getText();
			stu_no=tstu_mobile_no.getText();
			stu_f_name=tstu_father_name.getText();
			stu_f_no=tstu_father_mobile_no.getText();
			stu_address=tstu_address.getText();
			stu_clg=tstu_clg.getText();
			if((stu_name==null | stu_name.equals(""))|(stu_no==null | stu_no.equals(""))|(stu_f_name==null | stu_f_name.equals(""))|(stu_f_no==null | stu_f_no.equals(""))|(stu_address==null | stu_address.equals(""))|(stu_clg==null | stu_clg.equals("")))
			{
					JOptionPane.showMessageDialog(jf,"Fields are empty");
					return;
			}
			if(stu_name.length()>30)
			{
				JOptionPane.showMessageDialog(jf,"Student name cannot be more than 30 characters");
					return;
			}
			if(stu_no.length()!=10)
			{
				JOptionPane.showMessageDialog(jf,"Invalid Student mobile no");
					return;
			}
			if(stu_f_name.length()>30)
			{
				JOptionPane.showMessageDialog(jf,"Father name cannot be more than 30 characters");
					return;
			}
			if(stu_f_no.length()!=10)
			{
				JOptionPane.showMessageDialog(jf,"Invalid Father's mobile no");
					return;
			}
			if(stu_address.length()>50)
			{
				JOptionPane.showMessageDialog(jf,"Address cannot be more than 50 characters");
					return;
			}
			if(stu_clg.length()>20)
			{
				JOptionPane.showMessageDialog(jf,"College name cannot be more than 20 characters");
					return;
			}
			stu_course=(String)cstu_course.getSelectedItem();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+stu_course+"\"");
			TheInstituteDatabase.rs.next();
			String ci=TheInstituteDatabase.rs.getString("course_id");
			stu_batch=(String)cstu_batch.getSelectedItem();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_id from batch where batch_name=\""+stu_batch+"\"");
			TheInstituteDatabase.rs.next();
			String bi=TheInstituteDatabase.rs.getString("batch_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username from login where login_id=\""+TheInstituteLoginGUI.storelogin+"\"");
			TheInstituteDatabase.rs.next();
			add_by=TheInstituteDatabase.rs.getString("username");
			java.sql.Date add_date=new java.sql.Date(System.currentTimeMillis());
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,stu_id);
			pstmt.setString(2,stu_name);
			pstmt.setString(3,stu_no);
			pstmt.setString(4,stu_f_name);
			pstmt.setString(5,stu_f_no);
			pstmt.setString(6,stu_address);
			pstmt.setString(7,stu_clg);
			pstmt.setString(8,ci);
			pstmt.setString(9,bi);
			pstmt.setString(10,add_by);
			pstmt.setDate(11,add_date);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"New Student Profile Created Successfully");
			stupanel();
		}
		catch(SQLException r)
		{
		}
	}

}