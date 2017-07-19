import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
@SuppressWarnings({"unchecked","deprecation"})
class Course extends Common implements KeyListener
{
	private JButton addcourse,addbatch,viewcourse,viewbatch,bcreatecourse,bcreatebatch;
	private JTextField tcourse_id,tcourse_name,tcourse_fees,tapprox_duration_no,tbatch_id,tbatch_name,tbatch_end_time;
	private JComboBox capprox_duration,ccourse,cbatch_start_time,cbatch_st,cbatch_hr,cbatch_min,cbatch_year,cbatch_month,cbatch_date,cfaculty_name;
	private java.util.Vector t;
	private int cid,bid;
	private int c,l;
	Course(JPanel p,JDesktopPane d,JFrame f,int l)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		this.l=l;
		createcoursepanel();
	}
	public void createcoursepanel()
	{	
		startp();
		add.setText("Course :");
		
			addcourse=new JButton("Add New Course");
			addbatch=new JButton("Add New Batch");
			viewcourse=new JButton("View/Edit Course");
			viewbatch=new JButton("View/Edit Batch");
			addcourse.addActionListener((e)->{c=calc_course_id();
											addcoursepanel("C"+c);});
			addbatch.addActionListener((e)->{addbatchpanel();});
			viewcourse.addActionListener((e)->{new ViewCourse(framepanel,desktop,jf,l);});
			viewbatch.addActionListener((e)->{new ViewBatch(framepanel,desktop,jf,l);});
			
			gbc.fill=GridBagConstraints.HORIZONTAL;
			
			gbc.gridx=0;
			gbc.gridy=0;
			pce1.add(addcourse,gbc);
			
			gbc.gridx=1;
			gbc.gridy=0;
			pce1.add(addbatch,gbc);
			
			gbc.gridx=0;
			gbc.gridy=1;
			pce1.add(viewcourse,gbc);
			
			gbc.gridx=1;
			gbc.gridy=1;
			pce1.add(viewbatch,gbc);
			
			JPanel p=new JPanel();
			gbc.gridx=0;
			gbc.gridy=2;
			pce1.add(p,gbc);
		
		endp();
	}
	public void addcoursepanel(String courseid)
	{
		startp();
		add.setText("Add Course :");
		
		JLabel lcourse_id=new JLabel("Course Id :");
		JLabel lcourse_name=new JLabel("Course Name :");
		JLabel lcourse_fees=new JLabel("Course Fees :");
		JLabel lapprox_duration=new JLabel("Approx Duration :");
		tcourse_id=new JTextField(20);
		tcourse_name=new JTextField(20);
		tcourse_fees=new JTextField(20);
		tapprox_duration_no=new JTextField(5);
		String d[]={"Days","Months","Year"};
		capprox_duration=new JComboBox(d);
		bcreatecourse=new JButton("Create");
		bcreatecourse.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Do you want to create the database","Confirmation",JOptionPane.YES_NO_OPTION);
											if(x==JOptionPane.YES_OPTION)
												createcoursedb();});
		
		
		gbc.anchor=GridBagConstraints.WEST;
		
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(lcourse_id,gbc);
		
		tcourse_id.setText(courseid);
		tcourse_id.setEditable(false);
		gbc.gridx=1;
		gbc.gridy=0;
		pce1.add(tcourse_id,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(lcourse_name,gbc);
		
		tcourse_name.setText("");
		gbc.gridx=1;
		gbc.gridy=1;
		pce1.add(tcourse_name,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(lcourse_fees,gbc);
		
		tcourse_fees.setText("");
		tcourse_fees.addKeyListener(this);
		gbc.gridx=1;
		gbc.gridy=2;
		pce1.add(tcourse_fees,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		pce1.add(lapprox_duration,gbc);
		
		JPanel pa=new JPanel();
		pa.setLayout(new FlowLayout(FlowLayout.LEFT));
		tapprox_duration_no.setText("");
		tapprox_duration_no.addKeyListener(this);
		capprox_duration.setSelectedItem("Days");
		pa.add(tapprox_duration_no);
		pa.add(capprox_duration);
		
		gbc.gridx=1;
		gbc.gridy=3;
		pce1.add(pa,gbc);
		
		JPanel p4=new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.CENTER,30,30));
		p4.add(bcreatecourse);
		gbc.gridx=1;
		gbc.gridy=4;
		pce1.add(p4,gbc);	
		
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
		if(t==tapprox_duration_no)
		{	
			if(k==KeyEvent.VK_DECIMAL)
			{
				t.setEditable(true);
			}
			else if((k>=KeyEvent.VK_0 && k<=KeyEvent.VK_9) || (k>=KeyEvent.VK_NUMPAD0 && k<=KeyEvent.VK_NUMPAD9))
			{ 
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
		else if(t==tcourse_fees)
		{
			if((k>=KeyEvent.VK_0 && k<=KeyEvent.VK_9) || (k>=KeyEvent.VK_NUMPAD0 && k<=KeyEvent.VK_NUMPAD9))
			{ 
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
	}
	public int calc_course_id()
	{
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select max(course_id) as highest from course");
			TheInstituteDatabase.rs.next();
			int c;
			c=TheInstituteDatabase.rs.getInt("highest");
			c++;
			return c;
		}
		catch(Exception r)  //have not use SQLException here as 
							//if initially there was some rows in table and then we 
							//deleted them all and then if we use it then NUllpointer exception 
							//occurs at substring line and not on next() line so to cover it up 
							//Exception is received here and now null is returned so course id is 
							//set to be C0 without any issue
		{
		}		
		return 0;
	}
	public void createcoursedb()
	{
		try
		{
			int course_id;
			String course_name,course_fees,app_no,app_s,app,add_by;
			course_id=Integer.parseInt((tcourse_id.getText()).substring(1));
			course_name=tcourse_name.getText();
			course_fees=tcourse_fees.getText();
			app_no=tapprox_duration_no.getText();
			app_s=(String)capprox_duration.getSelectedItem();
			app=app_no+app_s;
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username from login where login_id=\""+TheInstituteLoginGUI.storelogin+"\"");
			TheInstituteDatabase.rs.next();
			add_by=TheInstituteDatabase.rs.getString("username");
			if((course_name==null | course_name.equals(""))|(course_fees==null | course_fees.equals(""))|(app_no==null | app_no.equals("")))
			{
					JOptionPane.showMessageDialog(jf,"Fields are empty");
					return;
			}
			if(course_name.length()>10)
			{
				JOptionPane.showMessageDialog(jf,"course name cannot be more than 10 characters long");
				return;
			}
			int fees=Integer.parseInt(course_fees);
			if(fees>65535)
			{
				JOptionPane.showMessageDialog(jf,"course fees cannot be more than 65535");
				return;
			}
			if(fees<0)
			{
				JOptionPane.showMessageDialog(jf,"course fees cannot be negative");
				return;
			}
			if(app.length()>10)
			{
				JOptionPane.showMessageDialog(jf,"invalid duration");
				return;
			}
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("insert into course values(?,?,?,?,?)");
			pstmt.setInt(1,course_id);
			pstmt.setString(2,course_name);
			pstmt.setInt(3,fees);
			pstmt.setString(4,app);
			pstmt.setString(5,add_by);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"New Course Created Successfully");
			createcoursepanel();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(jf,"This Course name already exists");
			return;
		}
	}
	public void addbatchpanel()
	{ 
		startp();
		add.setText("Add Batch :");
		
		JLabel selectcourse=new JLabel("Select Course :");
		JLabel lbatch_id=new JLabel("Batch ID :");
		JLabel lbatch_name=new JLabel("Batch Name :");
		JLabel lbatch_start_time=new JLabel("Batch Starting Time :");
		JLabel lbatch_end_time=new JLabel("Batch Ending Time :");
		JLabel lbatch_duration=new JLabel("Batch Duration :");
		JLabel lbatch_hr=new JLabel("hours");
		JLabel lbatch_min=new JLabel("minutes");
		JLabel lbatch_start_date=new JLabel("Batch Starting Date :");
		JLabel lfaculty_name=new JLabel("Faculty Name :");
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
		ccourse=new JComboBox(c);
		ccourse.addActionListener((e)->{bid=calc_batch_id();
										String b="C"+cid+"B"+bid;
										tbatch_id.setText(b);
										java.util.Vector fna=calc_batch_faculty();
										if(fna==null)
										{
											fna=new java.util.Vector();
											fna.add("None");
										}
										cfaculty_name.removeAllItems();
										for(Object x:fna)
											cfaculty_name.addItem(x);});
		tbatch_id=new JTextField(20);
		tbatch_name=new JTextField(20);
		cbatch_start_time=new JComboBox();
		String tim[]={"am","pm"};
		cbatch_st=new JComboBox(tim);
		Integer h[]={1,2,3,4,5};
		cbatch_hr=new JComboBox(h);
		Integer m[]={0,30};
		cbatch_min=new JComboBox(m);
		tbatch_end_time=new JTextField(10);
		
		java.sql.Date dt=new java.sql.Date(System.currentTimeMillis());
		String u=dt.toString();
		int a,b,r;
		a=Integer.parseInt(u.substring(0,u.indexOf("-")));
		b=Integer.parseInt(u.substring(u.indexOf("-")+1,u.lastIndexOf("-")));
		r=Integer.parseInt(u.substring(u.lastIndexOf("-")+1));
		Integer y[]=new Integer[5];
		y[0]=a;
		for(int k=1;k<5;k++)
		{
			y[k]=y[k-1]+1;
		}
		cbatch_year=new JComboBox(y);
		Integer month[]=new Integer[12];
		for(int k=0;k<12;k++)
		{
			month[k]=k+1;
		}
		cbatch_month=new JComboBox(month);
		cbatch_month.setSelectedItem(b);
		Integer d[]=new Integer[31];
		for(int k=0;k<31;k++)
		{
			d[k]=k+1;
		}
		cbatch_date=new JComboBox(d);
		cbatch_date.setSelectedItem(r+1);

		java.util.Vector fna=calc_batch_faculty();
		if(fna==null)
		{
			fna=new java.util.Vector();
			fna.add("None");
		}
		cfaculty_name=new JComboBox(fna);
		bcreatebatch=new JButton("Create");
		bcreatebatch.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Do you want to create the database","Confirmation",JOptionPane.YES_NO_OPTION);
											if(x==JOptionPane.YES_OPTION)
												createbatchdb();});
												
		cbatch_start_time.addActionListener((e)->{
														calc_batch_end_time();
													});
													
		cbatch_st.addActionListener((e)->{
										String st=(String)cbatch_st.getSelectedItem();
										if(st.equals("am"))
										{			
										//code to remove actionListener from any component
											for(ActionListener act : cbatch_start_time.getActionListeners()) {
												cbatch_start_time.removeActionListener(act);
											}
											cbatch_start_time.removeAllItems();
											int size=t.size();
											for(int i=0;i<size;i++)
											{
												t.remove(0);
											}
											for(int k=5;k<12;k++)
											{	
												t.add(k+":30:00");
											}
											for(Object x:t)
											{
												cbatch_start_time.addItem(x);
											}
											cbatch_start_time.addActionListener((ex)->{
															calc_batch_end_time();
													});
										}
										else if(st.equals("pm"))
										{
											for(ActionListener act : cbatch_start_time.getActionListeners()) {
												cbatch_start_time.removeActionListener(act);
											}
											cbatch_start_time.removeAllItems();
													int size=t.size();
											for(int i=0;i<size;i++)
											{	
												t.remove(0);
											}
											t.add("12:30:00");
											for(int k=1;k<9;k++)
											{	
												t.add(k+":30:00");
											}
											for(Object x:t)
											{
												cbatch_start_time.addItem(x);
											}
											cbatch_start_time.addActionListener((ex)->{
															calc_batch_end_time();
																});
										}
										calc_batch_end_time();});
		t=new java.util.Vector();
		for(int i=5;i<12;i++)
			t.add(i+":30:00");
		for(Object x:t)
			cbatch_start_time.addItem(x);

		cbatch_hr.addActionListener((e)->{System.out.println("h");
										calc_batch_end_time();});
		cbatch_min.addActionListener((e)->{System.out.println("h");
										calc_batch_end_time();});
		
		in=new Insets(10,20,10,20);
		gbc.insets=in;
		gbc.anchor=GridBagConstraints.WEST;
		
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(selectcourse,gbc);
		
		bid=calc_batch_id();
		String bi="C"+cid+"B"+bid;
		tbatch_id.setText(bi);
		tbatch_id.setEditable(false);
		gbc.gridx=1;
		gbc.gridy=0;
		pce1.add(ccourse,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(lbatch_id,gbc);

		gbc.gridx=1;
		gbc.gridy=1;
		pce1.add(tbatch_id,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(lbatch_name,gbc);

		gbc.gridx=1;
		gbc.gridy=2;
		pce1.add(tbatch_name,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		pce1.add(lbatch_start_time,gbc);

		JPanel p=new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		p.add(cbatch_start_time,gbc);
		p.add(cbatch_st,gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		pce1.add(p,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		pce1.add(lbatch_duration,gbc);
		
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(cbatch_hr,gbc);
		p1.add(lbatch_hr,gbc);
		p1.add(cbatch_min,gbc);
		p1.add(lbatch_min,gbc);
		gbc.gridx=1;
		gbc.gridy=4;
		pce1.add(p1,gbc);
	
		gbc.gridx=0;
		gbc.gridy=5;
		pce1.add(lbatch_end_time,gbc);
		
		//calc_batch_end_time();
		tbatch_end_time.setEditable(false);
		gbc.gridx=1;
		gbc.gridy=5;
		pce1.add(tbatch_end_time,gbc);
		
		gbc.gridx=0;
		gbc.gridy=6;
		pce1.add(lbatch_start_date,gbc);

		JPanel p2=new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.add(cbatch_year,gbc);
		p2.add(cbatch_month,gbc);
		p2.add(cbatch_date,gbc);
		gbc.gridx=1;
		gbc.gridy=6;
		pce1.add(p2,gbc);
		
		gbc.gridx=0;
		gbc.gridy=7;
		pce1.add(lfaculty_name,gbc);

		gbc.gridx=1;
		gbc.gridy=7;
		pce1.add(cfaculty_name,gbc);

		gbc.gridx=1;
		gbc.gridy=8;
		pce1.add(bcreatebatch,gbc);
		endp();
	}
	public void calc_batch_end_time()
	{
		String time,t,endt,temp;
		Integer hr,min;
		int h,m;
		time=(String)cbatch_start_time.getSelectedItem();
		t=(String)cbatch_st.getSelectedItem();
		hr=(Integer)cbatch_hr.getSelectedItem();
		min=(Integer)cbatch_min.getSelectedItem();
		h=Integer.parseInt(time.substring(0,time.indexOf(":")));
		m=Integer.parseInt(time.substring(time.indexOf(":")+1,time.lastIndexOf(":")));
		h=h+hr;
		if(min==30)
		{
			m=0;
			h=h+1;
		}
		if((h>12))
		{
			h=h-12;
			t="pm";
		}
		else if(h==12 & m==30)
		{
			t="pm";
		}
		else if(h==12 & m==0)
		{
			t="noon";
		}
		if(m==0)
		{
			temp="00";
		}
		else
		{
			temp=Integer.toString(m);
		}
		endt=Integer.toString(h)+":"+temp+":00 "+t;
		tbatch_end_time.setText(endt);
	}
	public int calc_batch_id()
	{
		try
		{
		String course=(String)ccourse.getSelectedItem();
		TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+course+"\"");
		TheInstituteDatabase.rs.next();
		cid=TheInstituteDatabase.rs.getInt("course_id");
		TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select max(batch_id) as highest from batch where course_id=\""+cid+"\"");
		TheInstituteDatabase.rs.next();
		int bid=TheInstituteDatabase.rs.getInt("highest");
		bid++;
		return bid;
		}
		catch(Exception r) // handling exception and not SQLException bcoz if there is no entry in resultset then getString returns nothing so null pointer exception will come which need to be handled
		{
			System.out.println("Hello");
		}
		return 0;
	}
	public void createbatchdb()
	{
		try
		{
			int faculty_id;
			String bname,bstime,bs_am_pm,be,betime,be_am_pm,duration,temp,bdate,faculty_name,add_by;
			bname=tbatch_name.getText();
			bstime=(String)cbatch_start_time.getSelectedItem();
			bs_am_pm=(String)cbatch_st.getSelectedItem();
			be=tbatch_end_time.getText();
			betime=be.substring(0,be.indexOf(" "));
			be_am_pm=be.substring(be.indexOf(" ")+1);
			Integer h,m;
			h=(Integer)cbatch_hr.getSelectedItem();
			m=(Integer)cbatch_min.getSelectedItem();
			temp=m.toString();
			if(temp.equals("0"))
			{
				temp="00";
			}
			duration=h.toString()+":"+temp+":00";
			bdate=((Integer)cbatch_year.getSelectedItem()).toString()+"-"+((Integer)cbatch_month.getSelectedItem()).toString()+"-"+((Integer)cbatch_date.getSelectedItem()).toString();
			java.sql.Date d1=new Date(System.currentTimeMillis());
			java.sql.Date d2=Date.valueOf(bdate);
			faculty_name=(String)cfaculty_name.getSelectedItem();
			if(faculty_name.equals("None"))
			{
				faculty_id=0;
			}
			else
			{System.out.println("A");
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select faculty_id from faculty where faculty_name=\""+faculty_name+"\"");
				TheInstituteDatabase.rs.next();
				faculty_id=TheInstituteDatabase.rs.getInt("faculty_id");
			}
			if((bname==null | bname.equals("")))
			{
					JOptionPane.showMessageDialog(jf,"Fields are empty");
					return;
			}
			if(bname.length()>20)
			{
				JOptionPane.showMessageDialog(jf,"Batch name cannot be more than 20 characters");
					return;
			}
			if(d2.before(d1)==true)
			{
				JOptionPane.showMessageDialog(jf,"date selected has passed");
				return;
			}
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username from login where login_id=\""+TheInstituteLoginGUI.storelogin+"\"");
			TheInstituteDatabase.rs.next();
			add_by=TheInstituteDatabase.rs.getString("username");
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("insert into batch values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,cid);
			pstmt.setInt(2,bid);
			pstmt.setString(3,bname);
			pstmt.setString(4,bstime);
			pstmt.setString(5,bs_am_pm);
			pstmt.setString(6,betime);
			pstmt.setString(7,be_am_pm);
			pstmt.setString(8,duration);
			pstmt.setString(9,bdate);
			pstmt.setInt(10,faculty_id);
			pstmt.setString(11,add_by);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"New Batch Created Successfully");
			createcoursepanel();
		}
		catch(SQLException s)
		{
			JOptionPane.showMessageDialog(jf,"Incorrect Date"); // this occur when suppose june has only 30 days n u select 31 
				return;
		}
	}
	public java.util.Vector calc_batch_faculty()
	{
		try
		{
			String cc1;
			java.util.Vector fn=new java.util.Vector();
			cc1=(String)ccourse.getSelectedItem();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+cc1+"\"");
			TheInstituteDatabase.rs.next();
			int cc2=TheInstituteDatabase.rs.getInt("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select faculty_name from faculty where course_id=\""+cc2+"\"");
			if(TheInstituteDatabase.rs.next()==true)
			{
				TheInstituteDatabase.rs.previous();
				while(TheInstituteDatabase.rs.next()==true)
				{
					fn.add(TheInstituteDatabase.rs.getString("faculty_name"));
				}
			}
			else
			{
				return null;
			}
			return fn;
		}
		catch(SQLException r)
		{
		}
		return null;
	}
}