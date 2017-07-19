import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import java.util.*;
@SuppressWarnings({"unchecked","deprecation"})
class ViewQuery extends Common
{	
	private JButton bid,bname,bcourse,ball,bsubmit,bedit,bexport;
	private JTextField tsearch;
	private JComboBox ccourse;
	private String sno="1";  
	private DefaultTableModel dm;
	private JTable tb;
	private JScrollPane sp;
	ViewQuery(JPanel p,JDesktopPane d,JFrame f)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		viewQueryPanel();
	}
	public void viewQueryPanel()
	{
		startp();
		add.setText("View/Edit/Export Query Details :");
			
		bid=new JButton("Search By Id");
		bname=new JButton("Search By Name");
		bcourse=new JButton("Search By Course");
		ball=new JButton("All Entries");
		
		bid.addActionListener((e)->{search(bid);});
		bname.addActionListener((e)->{search(bname);});
		bcourse.addActionListener((e)->{search(bcourse);});
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
		pce1.add(ball,gbc);
		
		endp();
	}
	public void search(JButton l)
	{
		startp();
		add.setText("View/Edit/Export Query Details :");

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
		else if (((l.getText()).equals("All Entries")))
		{
		}
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(bsubmit);
		p2.add(p3);
		pce1.add(p2,"North");
		
				
		String columns[]={"S.No.","ID","Name","Mob.No.","Father Name","Father No.","Address","College","Course"};
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
					JOptionPane.showMessageDialog(jf,"Enter Query Id");
					return;
				}
				if((tsearch.getText()).substring(0,1).equals("Q"))
				{
					h=(tsearch.getText()).substring(1);
				}
				else
				{
					JOptionPane.showMessageDialog(jf,"Query Id starts with Q");
					tsearch.setText("");
					return;
				}
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select query_id from query where query_id=\""+h+"\"");
			}
			else if(t.equals("Search By Name"))
			{
				if((tsearch.getText()).equals("") || (tsearch.getText())==null)
				{
					JOptionPane.showMessageDialog(jf,"Enter Query Name");
					return;
				}
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select query_id from query where query_name=\""+tsearch.getText()+"\"");
			}
			else if(t.equals("Search By Course"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+(String)(ccourse.getSelectedItem())+"\"");
				TheInstituteDatabase.rs.next();
				int cid=TheInstituteDatabase.rs.getInt("course_id");
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select query_id from query where course_id=\""+cid+"\"");
			}
			else if(t.equals("All Entries"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select query_id from query");
			}
			java.util.Vector v=new java.util.Vector();
			while(TheInstituteDatabase.rs.next()==true)
			{
				v.add(TheInstituteDatabase.rs.getInt("query_id"));
			}
			int rowcount=dm.getRowCount();
			for(int u=0;u<rowcount;u++)
			{
				dm.removeRow(0);
			}
			int i=0;
			int sid;
			String sname,smobno,sfname,sfmobno,sadd,sclg,scourse;
			while(i<v.size())
			{
				sid=(int)v.get(i);
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select query_name,query_mobile_no,query_father_name,query_father_mobile_no,query_address,query_clg,course_id from query where query_id=\""+sid+"\"");
				TheInstituteDatabase.rs.next();
				sname=TheInstituteDatabase.rs.getString("query_name");
				smobno=TheInstituteDatabase.rs.getString("query_mobile_no");
				sfname=TheInstituteDatabase.rs.getString("query_father_name");
				sfmobno=TheInstituteDatabase.rs.getString("query_father_mobile_no");
				sadd=TheInstituteDatabase.rs.getString("query_address");
				sclg=TheInstituteDatabase.rs.getString("query_clg");
				int cid=TheInstituteDatabase.rs.getInt("course_id");
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name from course where course_id=\""+cid+"\"");
				TheInstituteDatabase.rs.next();
				scourse=TheInstituteDatabase.rs.getString("course_name");
				
				String si="Q"+sid;
				Object ob[]={sno,si,sname,smobno,sfname,sfmobno,sadd,sclg,scourse};
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
												new DialogEditQuery(sd,jf);}});
			JPanel p4=new JPanel();
			p4.add(bedit);
			
			bexport=new JButton("Export");
			bexport.addActionListener((e)->{
												int u=tb.getSelectedRow();
												if(u!=-1)
												{
													String sd=((String)tb.getValueAt(u,1)).substring(1);
													exportToStudentProfile(sd);
												}
											});
			p4.add(bexport);
			
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
	public void exportToStudentProfile(String id)
	{
		String sname="",smobno="",sfname="",sfmobno="",sadd="",sclg="",add_by="";
		int cid=0,bid=0,sid=0;
		String bname;
		Vector v=new Vector();
		Object os[],batch_name;
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select query_name,query_mobile_no,query_father_name,query_father_mobile_no,query_address,query_clg,course_id from query where query_id=\""+id+"\"");
			TheInstituteDatabase.rs.next();
			sname=TheInstituteDatabase.rs.getString("query_name");
			smobno=TheInstituteDatabase.rs.getString("query_mobile_no");
			sfname=TheInstituteDatabase.rs.getString("query_father_name");
			sfmobno=TheInstituteDatabase.rs.getString("query_father_mobile_no");
			sadd=TheInstituteDatabase.rs.getString("query_address");
			sclg=TheInstituteDatabase.rs.getString("query_clg");
			cid=TheInstituteDatabase.rs.getInt("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_name from batch where course_id=\""+cid+"\"");
			while(TheInstituteDatabase.rs.next()==true)
			{
				v.add(TheInstituteDatabase.rs.getString("batch_name"));
			}
			os=v.toArray();
			batch_name=JOptionPane.showInputDialog(jf,"Select Batch:","Export Query to Student Profile",JOptionPane.QUESTION_MESSAGE,null,os,os[0]);
			if(batch_name!=null)
			{
				bname=(String)batch_name;
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select batch_id from batch where course_id=\""+cid+"\" and batch_name=\""+bname+"\"");
				TheInstituteDatabase.rs.next();
				bid=TheInstituteDatabase.rs.getInt("batch_id");
				
				
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username from login where login_id=\""+TheInstituteLoginGUI.storelogin+"\"");
			TheInstituteDatabase.rs.next();
			add_by=TheInstituteDatabase.rs.getString("username");
			java.sql.Date add_date=new java.sql.Date(System.currentTimeMillis());
			
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select max(stu_id) as highest from student");
			TheInstituteDatabase.rs.next();
			sid=(TheInstituteDatabase.rs.getInt("highest"));
			sid++;
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1,sid);
			pstmt.setString(2,sname);
			pstmt.setString(3,smobno);
			pstmt.setString(4,sfname);
			pstmt.setString(5,sfmobno);
			pstmt.setString(6,sadd);
			pstmt.setString(7,sclg);
			pstmt.setInt(8,cid);
			pstmt.setInt(9,bid);
			pstmt.setString(10,add_by);
			pstmt.setDate(11,add_date);
			pstmt.execute();
			pstmt.close();
			
			TheInstituteDatabase.stmt.execute("delete from query where query_id=\""+id+"\"");
			
			JOptionPane.showMessageDialog(jf,"Query Exported Successfully");
			}
		}
		catch(SQLException r)
		{
		System.out.println("ty");
		}
	}
}