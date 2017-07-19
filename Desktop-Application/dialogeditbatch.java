import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class DialogEditBatch
{
	private JDialog jd;
	private JFrame jf;
	private String id;
	private int cid,bid;
	private JTextField tcourse_name,tbatch_name,tbatch_end_time;
	private JComboBox cbatch_start_time,cbatch_st,cbatch_hr,cbatch_min,cbatch_year,cbatch_month,cbatch_date,cfaculty_name;
	private java.util.Vector t;
	DialogEditBatch(String i,JFrame f)
	{
		jf=f;
		id=i;
		cid=Integer.parseInt(id.substring(1,id.indexOf("B")));
		bid=Integer.parseInt(id.substring(id.indexOf("B")+1));
		jd=new JDialog(jf,"Edit Batch",true);
		jd.setSize(600,600);
		jd.setLocationRelativeTo(jf);
		
		JDesktopPane d=new JDesktopPane();  
		jd.add(d);
		JInternalFrame j=infopanel();
		j.setSize(600,600);
		((javax.swing.plaf.basic.BasicInternalFrameUI)j.getUI()).setNorthPane(null);
		j.setVisible(true);
		d.add(j);
		j.toFront();
		
		jd.setVisible(true);
		//jd.setResizable(false);
		jd.validate();
		jf.validate();
	}
	public JInternalFrame infopanel()
	{
		JInternalFrame pa=new JInternalFrame();
		JPanel p=new JPanel();
		p.setLayout(new GridBagLayout());
		
		JLabel course=new JLabel("Course :");
		JLabel lbatch_id=new JLabel("Batch ID :");
		JLabel lbatch_name=new JLabel("Batch Name :");
		JLabel lbatch_start_time=new JLabel("Batch Starting Time :");
		JLabel lbatch_end_time=new JLabel("Batch Ending Time :");
		JLabel lbatch_duration=new JLabel("Batch Duration :");
		JLabel lbatch_hr=new JLabel("hours");
		JLabel lbatch_min=new JLabel("minutes");
		JLabel lbatch_start_date=new JLabel("Batch Starting Date :");
		JLabel lfaculty_name=new JLabel("Faculty Name :");
		
		 String scourseName="",sbatch_name="",sbatch_start_time="",start_am_pm="",sbatch_end_time="",end_am_pm="",sbatch_duration="",sbatch_start_date="",sfaculty_name="";
		 int sfaculty_id;
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select * from batch where course_id="+cid+" and batch_id="+bid);
			TheInstituteDatabase.rs.next();
			 sbatch_name=TheInstituteDatabase.rs.getString("batch_name");
			 sbatch_start_time=TheInstituteDatabase.rs.getString("batch_start_time");
			 start_am_pm=TheInstituteDatabase.rs.getString("start_am_pm");
			 sbatch_end_time=TheInstituteDatabase.rs.getString("batch_end_time");
			 end_am_pm=TheInstituteDatabase.rs.getString("end_am_pm");
			 sbatch_duration=TheInstituteDatabase.rs.getString("batch_duration");
			 sbatch_start_date=TheInstituteDatabase.rs.getString("batch_start_date");
			 sfaculty_id=TheInstituteDatabase.rs.getInt("faculty_id");
			 
			 TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name from course where course_id="+cid);
			TheInstituteDatabase.rs.next();
			scourseName=TheInstituteDatabase.rs.getString("course_name");
			
			 TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select faculty_name from faculty where faculty_id="+sfaculty_id);
			TheInstituteDatabase.rs.next();
			sfaculty_name=TheInstituteDatabase.rs.getString("faculty_name");
			
		}
		catch(SQLException r)
		{
		System.out.println("B");
		}
				
		JLabel yid=new JLabel("C"+cid+"B"+bid);
		tcourse_name=new JTextField(10);
		tcourse_name.setText(scourseName);
		tcourse_name.setEditable(false);
		tbatch_name=new JTextField(10);
		tbatch_name.setText(sbatch_name);
		
		String hr=sbatch_duration.substring(0,sbatch_duration.indexOf(":"));
		String min=sbatch_duration.substring(sbatch_duration.indexOf(":")+1,sbatch_duration.lastIndexOf(":"));
		hr=hr.substring(1);
		Integer hr1=Integer.parseInt(hr);
		Integer min1=Integer.parseInt(min);
		Integer h[]={1,2,3,4,5};
		cbatch_hr=new JComboBox(h);
		cbatch_hr.setSelectedItem(hr1);
		Integer m[]={0,30};
		cbatch_min=new JComboBox(m);
		cbatch_min.setSelectedItem(min1);
		cbatch_hr.addActionListener((e)->{System.out.println("h");
										calc_batch_end_time();});
		cbatch_min.addActionListener((e)->{System.out.println("h");
										calc_batch_end_time();});
										
		java.sql.Date dt=java.sql.Date.valueOf(sbatch_start_date);
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
		cbatch_date.setSelectedItem(r);

		
		tbatch_end_time=new JTextField(10);
		tbatch_end_time.setEditable(false);
		
		java.util.Vector fna=calc_batch_faculty();
		if(fna==null)
		{
			fna=new java.util.Vector();
			fna.add("None");
		}
		cfaculty_name=new JComboBox(fna);
		cfaculty_name.setSelectedItem(sfaculty_name);
		
		cbatch_start_time=new JComboBox();
		t=new java.util.Vector();
		if(start_am_pm.equals("am"))
		{
		for(int i=5;i<12;i++)
			t.add(i+":30:00");
		for(Object x:t)
			cbatch_start_time.addItem(x);
			}
			else
			{
		for(int k=1;k<9;k++)
			t.add(k+":30:00");
		for(Object x:t)
			cbatch_start_time.addItem(x);
			}
		cbatch_start_time.addActionListener((e)->{calc_batch_end_time();
													});
													
		String tim[]={"am","pm"};
		cbatch_st=new JComboBox(tim);	

									
	String s=sbatch_start_time.substring(0,1);
	if(s.equals("0"))
	{
		sbatch_start_time=sbatch_start_time.substring(1);
	}
		cbatch_start_time.setSelectedItem(sbatch_start_time);
		cbatch_st.setSelectedItem(start_am_pm);
		
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
		
			calc_batch_end_time();
						
		JButton bdep=new JButton("Edit");
		bdep.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Are you sure you want to edit the database","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createbatchdb();});
		
		GridBagConstraints gbc1=new GridBagConstraints();
		Insets in1=new Insets(10,10,10,10);
		gbc1.insets=in1;
		
		gbc1.anchor=GridBagConstraints.WEST;
		gbc1.gridx=0;
		gbc1.gridy=0;
		p.add(lbatch_id,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=0;
		p.add(yid,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=1;
		p.add(course,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=1;
		p.add(tcourse_name,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=2;
		p.add(lbatch_name,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=2;
		p.add(tbatch_name,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=3;
		p.add(lbatch_start_time,gbc1);
		
		JPanel p4=new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.LEFT));
		p4.add(cbatch_start_time);
		p4.add(cbatch_st); 
		
		gbc1.gridx=1;
		gbc1.gridy=3;
		p.add(p4,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=4;
		p.add(lbatch_duration,gbc1);
		
		JPanel p6=new JPanel();
		p6.setLayout(new FlowLayout(FlowLayout.LEFT));
		p6.add(cbatch_hr);
		p6.add(lbatch_hr);
		p6.add(cbatch_min); 
		p6.add(lbatch_min); 
		
		gbc1.gridx=1;
		gbc1.gridy=4;
		p.add(p6,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=5;
		p.add(lbatch_end_time,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=5;
		p.add(tbatch_end_time,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=6;
		p.add(lbatch_start_date,gbc1);
		
		JPanel p5=new JPanel();
		p5.setLayout(new FlowLayout(FlowLayout.LEFT));
		p5.add(cbatch_year,gbc1);
		p5.add(cbatch_month,gbc1);
		p5.add(cbatch_date,gbc1);
		gbc1.gridx=1;
		gbc1.gridy=6;
		p.add(p5,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=7;
		p.add(lfaculty_name,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=7;
		p.add(cfaculty_name,gbc1);
		
		JPanel p2=new JPanel();
		gbc1.gridx=0;
		gbc1.gridy=8;
		p.add(p2,gbc1);
		
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(bdep);
		gbc1.gridx=1;
		gbc1.gridy=8;
		p.add(p3,gbc1);
		
		pa.add(p,BorderLayout.CENTER);
		
		return pa;
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
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("update batch set batch_name=?,batch_start_time=?,start_am_pm=?,batch_end_time=?,end_am_pm=?,batch_duration=?,batch_start_date=?,faculty_id=? where course_id="+cid+" and batch_id="+bid);
			pstmt.setString(1,bname);
			pstmt.setString(2,bstime);
			pstmt.setString(3,bs_am_pm);
			pstmt.setString(4,betime);
			pstmt.setString(5,be_am_pm);
			pstmt.setString(6,duration);
			pstmt.setString(7,bdate);
			pstmt.setInt(8,faculty_id);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"Batch Updated Successfully");
			jd.setVisible(false);
			jd.dispose();
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
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select faculty_name from faculty where course_id="+cid);
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
			t="P.M.";
		}
		else if(h==12 & m==30)
		{
			t="P.M.";
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
}