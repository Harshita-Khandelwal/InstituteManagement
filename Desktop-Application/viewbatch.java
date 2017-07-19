import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class ViewBatch extends Common
{
	private DefaultTableModel dm;
	private JButton bedit;
	private JTable tb;
	private JScrollPane sp;
	private String sno="1";
	private JTextField tsearch;
	private JButton bsubmit,bcourse,ball;
	private JComboBox ccourse;
	private int l1;
	ViewBatch(JPanel p,JDesktopPane d,JFrame f,int l1)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		this.l1=l1;
		
		String columns[]={"S.No.","ID","Name","Course","StartTime","EndTime","Duration","StartDate","Faculty"};
		dm=new DefaultTableModel(null,columns);
		tb=new JTable(dm){
						public boolean isCellEditable(int row,int column)
						{
							return false;
						}
						};
		tb.setGridColor(new Color(200,200,220));
		tb.setForeground(new Color(120,150,220));
		Font fn1=new Font("Comic Sans Ms",Font.PLAIN,12);
		tb.setFont(fn1);
		//tb.setRowMargin(5);
		tb.getTableHeader().setFont(new Font("Comic Sans Ms",Font.BOLD,15));
		tb.getTableHeader().setForeground(new Color(250,120,160));
		tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb.getTableHeader().setReorderingAllowed(false);
		//tb.setRowSelectionAllowed(false);
		sp=new JScrollPane(tb);
		
		viewbatchpanel();
	}
	public void viewbatchpanel()
	{
		startp();
		if(l1==1)
			add.setText("View/Edit Batch Details :");
		else if(l1==2)
			add.setText("View Batch Details :");
			
		bcourse=new JButton("Search By Course");
		ball=new JButton("All Entries");
		
		bcourse.addActionListener((e)->{search(bcourse);});
		ball.addActionListener((e)->{search(ball);});
		
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(bcourse,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(ball,gbc);
		
		JPanel p1=new JPanel();
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(p1,gbc);
		
		endp();
	}
	public void search(JButton l)
	{
		startp();
		if(l1==1)
			add.setText("View/Edit Batch Details :");
		else if(l1==2)
			add.setText("View Batch Details :");

		tsearch=new JTextField(10);
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
		ccourse=new JComboBox(cc);
		bsubmit=new JButton("Submit/Refresh");
		pce1.setLayout(new BorderLayout());
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.add(new JLabel(l.getText()));
		JPanel p2=new JPanel();
		p2.setLayout(new GridLayout(2,1));
		p2.add(p1);
		
		if(((l.getText()).equals("Search By Course")))
		{
			p1.add(ccourse);
		}
		else if (((l.getText()).equals("All Entries")))
		{
		}
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(bsubmit);
		p2.add(p3);
		pce1.add(p2,"North");
		
		bsubmit.addActionListener((e)->{createtable(l.getText());});
		
		endp();
	}
	public void createtable(String t)
	{	
		int cid=0;
		sno="1";
		java.util.Vector v1=new java.util.Vector();
		try
		{
			if(t.equals("Search By Course"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+(String)(ccourse.getSelectedItem())+"\"");
				v1=new java.util.Vector();
				while(TheInstituteDatabase.rs.next()==true)
				{
					v1.add(TheInstituteDatabase.rs.getInt("course_id"));
				}
			}
			else if(t.equals("All Entries"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course");
				v1=new java.util.Vector();
				while(TheInstituteDatabase.rs.next()==true)
				{
					v1.add(TheInstituteDatabase.rs.getInt("course_id"));
				}
			}
			
			int rowcount=dm.getRowCount();
			for(int u=0;u<rowcount;u++)
			{
				dm.removeRow(0);
			}
			int i=0,k=0;
			int fid;
			String sid,sbname,scname,stime,st,etime,et,duration,sdate,sfac;
		
			while(k<v1.size())
			{	
				cid=(int)v1.get(k);
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_id from batch where course_id=\""+cid+"\"");
				java.util.Vector v=new java.util.Vector();
				while(TheInstituteDatabase.rs.next()==true)
				{
					v.add(TheInstituteDatabase.rs.getString("batch_id"));
				}
				int rc=dm.getRowCount();
				i=0;
				while(i<v.size())
				{
					sid=(String)v.get(i);
					TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_name,batch_start_time,start_am_pm,batch_end_time,end_am_pm,batch_duration,batch_start_date,faculty_id from batch where batch_id=\""+sid+"\" and course_id=\""+cid+"\"");
					TheInstituteDatabase.rs.next();
					sbname=TheInstituteDatabase.rs.getString("batch_name");
					stime=TheInstituteDatabase.rs.getString("batch_start_time");
					st=TheInstituteDatabase.rs.getString("start_am_pm");
					stime=stime+st;
					etime=TheInstituteDatabase.rs.getString("batch_end_time");
					et=TheInstituteDatabase.rs.getString("end_am_pm");
					etime=etime+et;
					duration=TheInstituteDatabase.rs.getString("batch_duration");
					sdate=TheInstituteDatabase.rs.getString("batch_start_date");
					fid=TheInstituteDatabase.rs.getInt("faculty_id");
					if(fid==0)
					{
						sfac="None";
					}
					else
					{
						TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select faculty_name from faculty where faculty_id=\""+fid+"\"");
						TheInstituteDatabase.rs.next();
						sfac=TheInstituteDatabase.rs.getString("faculty_name");
					}
					TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name from course where course_id=\""+cid+"\"");
					TheInstituteDatabase.rs.next();
					scname=TheInstituteDatabase.rs.getString("course_name");
					Object ob[]={sno,"C"+cid+"B"+sid,sbname,scname,stime,etime,duration,sdate,sfac};
					dm.insertRow(rc,ob);
					rc++;
					i++;
					int j=Integer.parseInt(sno);
					j++;
					sno=Integer.toString(j);
				}
				k++;
			}
				
			if(l1==1)
			{
				bedit=new JButton("Edit");
				bedit.addActionListener((e)->{
												int u=tb.getSelectedRow();
												if(u!=-1)
												{
													String sd=(String)tb.getValueAt(u,1);
													new DialogEditBatch(sd,jf);}});
				JPanel p4=new JPanel();
				p4.add(bedit);
				
				pce1.add(sp,"Center");
				pce1.add(p4,"South");
			}
			else if(l1==2)
			{
				pce1.add(sp,"Center");
			}
			jf.validate();
		}
		catch(SQLException e)
		{
			System.out.println("A");
		}
	}
}