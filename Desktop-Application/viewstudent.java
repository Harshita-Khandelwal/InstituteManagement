import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class ViewStudent extends Common
{	
	private JButton bid,bname,bcourse,bbatch,ball,bsubmit,bedit;
	private JTextField tsearch;
	private JComboBox ccourse,cbatch;
	private String sno="1";  
	private DefaultTableModel dm;
	private JTable tb;
	private JScrollPane sp;
	ViewStudent(JPanel p,JDesktopPane d,JFrame f)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		viewstudentpanel();
	}
	public void viewstudentpanel()
	{
		startp();
		add.setText("View/Edit Student Details :");
			
		bid=new JButton("Search By Id");
		bname=new JButton("Search By Name");
		bcourse=new JButton("Search By Course");
		bbatch=new JButton("Search By Batch");
		ball=new JButton("All Entries");
		
		bid.addActionListener((e)->{search(bid);});
		bname.addActionListener((e)->{search(bname);});
		bcourse.addActionListener((e)->{search(bcourse);});
		bbatch.addActionListener((e)->{search(bbatch);});
		ball.addActionListener((e)->{search(ball);});
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		pce1.add(bid,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(bname,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(bcourse,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		pce1.add(bbatch,gbc);
		
		gbc.gridx=0;
		gbc.gridy=4;
		pce1.add(ball,gbc);
		
		endp();
	}
	public void search(JButton l)
	{
		startp();
		add.setText("View/Edit Student Details :");

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
		ccourse=new JComboBox(c);
		bsubmit=new JButton("Submit/Refresh");
		pce1.setLayout(new BorderLayout());
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.add(new JLabel(l.getText()));
		JPanel p2=new JPanel();
		p2.setLayout(new GridLayout(2,1));
		p2.add(p1);
		
		if(((l.getText()).equals("Search By Id")) | ((l.getText()).equals("Search By Name")))
		{
			p1.add(tsearch);
		}
		else if(((l.getText()).equals("Search By Course")))
		{
			p1.add(ccourse);
		}
		else if(((l.getText()).equals("Search By Batch")))
		{
			p1.add(ccourse);
			cbatch=new JComboBox();
			calc_batch();
			ccourse.addActionListener((e)->{calc_batch();});
			p1.add(cbatch);
			
		}
		else if (((l.getText()).equals("All Entries")))
		{
		}
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(bsubmit);
		p2.add(p3);
		pce1.add(p2,"North");
		
				
		String columns[]={"S.No.","ID","Name","Mob.No.","Father Name","Father No.","Address","College","Course","Batch"};
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
		
		bsubmit.addActionListener((e)->{createtable(l.getText());});
		
		endp();
	}
	public void calc_batch()
	{
		try
		{
			String course=(String)ccourse.getSelectedItem();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+course+"\"");
			TheInstituteDatabase.rs.next();
			int ci=TheInstituteDatabase.rs.getInt("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_name from batch where course_id=\""+ci+"\"");
			cbatch.removeAllItems();
			while(TheInstituteDatabase.rs.next()==true)
			{
				cbatch.addItem(TheInstituteDatabase.rs.getString("batch_name"));
			}
		}
		catch(SQLException r)
		{}
	}
	public void createtable(String t)
	{	
		sno="1";
		try
		{
			if(t.equals("Search By Id"))
			{
				String h;
				if((tsearch.getText()).equals("") || (tsearch.getText())==null)
				{
					JOptionPane.showMessageDialog(jf,"Enter Student Id");
					return;
				}
				if((tsearch.getText()).substring(0,1).equals("S"))
				{
					h=(tsearch.getText()).substring(1);
				}
				else
				{
					JOptionPane.showMessageDialog(jf,"Student Id starts with S");
					tsearch.setText("");
					return;
				}
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select stu_id from student where stu_id=\""+h+"\"");
			}
			else if(t.equals("Search By Name"))
			{
				if((tsearch.getText()).equals("") || (tsearch.getText())==null)
				{
					JOptionPane.showMessageDialog(jf,"Enter Student Name");
					return;
				}
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select stu_id from student where stu_name=\""+tsearch.getText()+"\"");
			}
			else if(t.equals("Search By Course"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+(String)(ccourse.getSelectedItem())+"\"");
				TheInstituteDatabase.rs.next();
				int cid=TheInstituteDatabase.rs.getInt("course_id");
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select stu_id from student where course_id=\""+cid+"\"");
			}
			else if(t.equals("Search By Batch"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_id from batch where batch_name=\""+(String)(cbatch.getSelectedItem())+"\"");
				TheInstituteDatabase.rs.next();
				int bid=TheInstituteDatabase.rs.getInt("batch_id");
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+(String)(ccourse.getSelectedItem())+"\"");
				TheInstituteDatabase.rs.next();
				int cid=TheInstituteDatabase.rs.getInt("course_id");
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select stu_id from student where batch_id=\""+bid+"\" and course_id=\""+cid+"\"");
			}
			else if(t.equals("All Entries"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select stu_id from student");
			}
			java.util.Vector v=new java.util.Vector();
			while(TheInstituteDatabase.rs.next()==true)
			{
				v.add(TheInstituteDatabase.rs.getInt("stu_id"));
			}
			int rowcount=dm.getRowCount();
			for(int u=0;u<rowcount;u++)
			{
				dm.removeRow(0);
			}
			int i=0;
			int sid;
			String sname,smobno,sfname,sfmobno,sadd,sclg,scourse,sbatch;
			while(i<v.size())
			{
				sid=(int)v.get(i);
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select stu_name,stu_mobile_no,stu_father_name,stu_father_mobile_no,stu_address,stu_clg,course_id,batch_id from student where stu_id=\""+sid+"\"");
				TheInstituteDatabase.rs.next();
				sname=TheInstituteDatabase.rs.getString("stu_name");
				smobno=TheInstituteDatabase.rs.getString("stu_mobile_no");
				sfname=TheInstituteDatabase.rs.getString("stu_father_name");
				sfmobno=TheInstituteDatabase.rs.getString("stu_father_mobile_no");
				sadd=TheInstituteDatabase.rs.getString("stu_address");
				sclg=TheInstituteDatabase.rs.getString("stu_clg");
				int bid=TheInstituteDatabase.rs.getInt("batch_id");
				int cid=TheInstituteDatabase.rs.getInt("course_id");
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name from course where course_id=\""+cid+"\"");
				TheInstituteDatabase.rs.next();
				scourse=TheInstituteDatabase.rs.getString("course_name");
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_name from batch where batch_id=\""+bid+"\" and course_id=\""+cid+"\"");
				TheInstituteDatabase.rs.next();
				sbatch=TheInstituteDatabase.rs.getString("batch_name");
				
				String si="S"+sid;
				Object ob[]={sno,si,sname,smobno,sfname,sfmobno,sadd,sclg,scourse,sbatch};
				dm.insertRow(i,ob);
				i++;
				int j=Integer.parseInt(sno);
				j++;
				sno=Integer.toString(j);
			}
			bedit=new JButton("Edit");
			bedit.addActionListener((e)->{
											int u=tb.getSelectedRow();
											if(u!=-1)
											{
												String sd=((String)tb.getValueAt(u,1)).substring(1);
												new DialogEditStudent(sd,jf);}});
			JPanel p4=new JPanel();
			p4.add(bedit);
			
			pce1.add(sp,"Center");
			pce1.add(p4,"South");
			
			jf.validate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
			System.out.println("A");
		}
	}
}