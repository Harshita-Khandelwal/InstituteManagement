import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
@SuppressWarnings({"unchecked","deprecation"})
class Query extends Common implements KeyListener
{
	private JButton bcreatenewquery,bwatchquery,bcreatequery;
	private JTextField tquery_name,tquery_mobile_no,tquery_father_name,tquery_father_mobile_no,tquery_clg;
	private int slabel;
	private JTextArea tquery_address;
	private JComboBox cquery_course;
	Query(JPanel p,JDesktopPane d,JFrame f)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		querypanel();
	}
	public void querypanel()
	{
		startp();
		add.setText("Queries :");
		
		bcreatenewquery=new JButton("Create New Query");
		bwatchquery=new JButton("View/Edit/Export Queries");
		bcreatenewquery.addActionListener((e)->{createquerypanel();});
		bwatchquery.addActionListener((e)->{new ViewQuery(framepanel,desktop,jf);});
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		pce1.add(bcreatenewquery,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(bwatchquery,gbc);
		
		endp();
	}
	
	public void createquerypanel()
	{
		startp();
		add.setText("Create New Query :");
		
		JLabel lquery_id=new JLabel("Query Id :");
		slabel=0;
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select max(query_id) as highest from query");
			TheInstituteDatabase.rs.next();
			slabel=(TheInstituteDatabase.rs.getInt("highest"));
			slabel++;
		}
		catch(SQLException r)
		{}
		JLabel lquery_id_no=new JLabel("Q"+slabel);
		JLabel lquery_name=new JLabel("Name :");
		JLabel lquery_mobile_no=new JLabel("Mobile No :");
		JLabel lquery_father_name=new JLabel("Father's Name :");
		JLabel lquery_father_mobile_no=new JLabel("Father's Mobile No :");
		JLabel lquery_address=new JLabel("Address :");
		JLabel lquery_clg=new JLabel("College :");
		JLabel lquery_course=new JLabel("Course :");
		tquery_name=new JTextField(10);
		tquery_mobile_no=new JTextField(10);
		tquery_mobile_no.addKeyListener(this);
		tquery_father_name=new JTextField(10);
		tquery_father_mobile_no=new JTextField(10);
		tquery_father_mobile_no.addKeyListener(this);
		tquery_clg=new JTextField(10);
		tquery_address=new JTextArea(5,10);
		JScrollPane sp=new JScrollPane(tquery_address);
		tquery_address.setLineWrap(true);
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
		cquery_course=new JComboBox(c);
		bcreatequery=new JButton("Create");
		bcreatequery.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Do you want to create the database","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createquerydb();});
		
		in=new Insets(10,20,10,20);
		gbc.insets=in;
		gbc.anchor=GridBagConstraints.EAST;
		
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		p1.add(lquery_id);
		p1.add(lquery_id_no);
		
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(p1,gbc);
		
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(lquery_name,gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		pce1.add(tquery_name,gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		pce1.add(lquery_mobile_no,gbc);
		
		gbc.gridx=3;
		gbc.gridy=1;
		pce1.add(tquery_mobile_no,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(lquery_father_name,gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		pce1.add(tquery_father_name,gbc);
		
		gbc.gridx=2;
		gbc.gridy=2;
		pce1.add(lquery_father_mobile_no,gbc);
		
		gbc.gridx=3;
		gbc.gridy=2;
		pce1.add(tquery_father_mobile_no,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		pce1.add(lquery_address,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		pce1.add(sp,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		pce1.add(lquery_clg,gbc);
		
		gbc.gridx=1;
		gbc.gridy=4;
		pce1.add(tquery_clg,gbc);
		
		gbc.gridx=0;
		gbc.gridy=5;
		pce1.add(lquery_course,gbc);
		
		gbc.gridx=1;
		gbc.gridy=5;
		pce1.add(cquery_course,gbc);
		
		JPanel p2=new JPanel();
		gbc.gridx=0;
		gbc.gridy=6;
		pce1.add(p2,gbc);
		
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(bcreatequery);
		gbc.gridx=1;
		gbc.gridy=6;
		pce1.add(p3,gbc);
		
	/*	gbc.gridx=2;
		gbc.gridy=6;
		pce1.add(bcreatequery,gbc);*/
		
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
	
	public void createquerydb()
	{
		try
		{
			int query_id;
			String query_name,query_no,query_f_name,query_f_no,query_address,query_clg,query_course,add_by;
			query_id=slabel;
			query_name=tquery_name.getText();
			query_no=tquery_mobile_no.getText();
			query_f_name=tquery_father_name.getText();
			query_f_no=tquery_father_mobile_no.getText();
			query_address=tquery_address.getText();
			query_clg=tquery_clg.getText();
			if((query_name==null | query_name.equals(""))|(query_no==null | query_no.equals(""))|(query_f_name==null | query_f_name.equals(""))|(query_f_no==null | query_f_no.equals(""))|(query_address==null | query_address.equals(""))|(query_clg==null | query_clg.equals("")))
			{
					JOptionPane.showMessageDialog(jf,"Fields are empty");
					return;
			}
			if(query_name.length()>30)
			{
				JOptionPane.showMessageDialog(jf,"query name cannot be more than 30 characters");
					return;
			}
			if(query_no.length()!=10)
			{
				JOptionPane.showMessageDialog(jf,"Invalid query mobile no");
					return;
			}
			if(query_f_name.length()>30)
			{
				JOptionPane.showMessageDialog(jf,"Father name cannot be more than 30 characters");
					return;
			}
			if(query_f_no.length()!=10)
			{
				JOptionPane.showMessageDialog(jf,"Invalid Father's mobile no");
					return;
			}
			if(query_address.length()>50)
			{
				JOptionPane.showMessageDialog(jf,"Address cannot be more than 50 characters");
					return;
			}
			if(query_clg.length()>20)
			{
				JOptionPane.showMessageDialog(jf,"College name cannot be more than 20 characters");
					return;
			}
			query_course=(String)cquery_course.getSelectedItem();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+query_course+"\"");
			TheInstituteDatabase.rs.next();
			String ci=TheInstituteDatabase.rs.getString("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username from login where login_id=\""+TheInstituteLoginGUI.storelogin+"\"");
			TheInstituteDatabase.rs.next();
			add_by=TheInstituteDatabase.rs.getString("username");
			java.sql.Date add_date=new java.sql.Date(System.currentTimeMillis());
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("insert into query values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,query_id);
			pstmt.setString(2,query_name);
			pstmt.setString(3,query_no);
			pstmt.setString(4,query_f_name);
			pstmt.setString(5,query_f_no);
			pstmt.setString(6,query_address);
			pstmt.setString(7,query_clg);
			pstmt.setString(8,ci);
			pstmt.setString(9,add_by);
			pstmt.setDate(10,add_date);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"New Query Created Successfully");
			querypanel();
		}
		catch(SQLException r)
		{
		}
	}

}