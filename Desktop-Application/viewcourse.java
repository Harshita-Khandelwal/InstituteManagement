import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class ViewCourse extends Common
{
	private DefaultTableModel dm;
	private JButton bedit;
	private JTable tb;
	private JScrollPane sp;
	private String sno="1";
	private JButton bsubmit;
	private int l;
	ViewCourse(JPanel p,JDesktopPane d,JFrame f,int l)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		this.l=l;
		String columns[]={"S.No.","Course ID","Course Name","Fees","Duration"};
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
		
		viewcoursepanel();
	}
	public void viewcoursepanel()
	{
		startp();
		if(l==1)
			add.setText("View/Edit All Courses :");
		else if(l==2)
			add.setText("View All Courses :");
		pce1.setLayout(new BorderLayout());
		bsubmit=new JButton("Refresh");
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(bsubmit);
		pce1.add(p3,"North");
		
		bsubmit.addActionListener((e)->{createtable();});
		
		endp();
	}
	public void createtable()
	{
		sno="1";
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id,course_name,course_fees,approx_duration from course");
			int rowcount=dm.getRowCount();
			for(int u=0;u<rowcount;u++)
			{
				dm.removeRow(0);
			}
			int i=0;
			int cid;
			String cname,cfees,cduration;
			while(TheInstituteDatabase.rs.next()==true)
			{
				cname=TheInstituteDatabase.rs.getString("course_name");
				cfees=TheInstituteDatabase.rs.getString("course_fees");
				cid=TheInstituteDatabase.rs.getInt("course_id");
				cduration=TheInstituteDatabase.rs.getString("approx_duration");
				Object ob[]={sno,"C"+cid,cname,cfees,cduration};
				dm.insertRow(i,ob);
				i++;
				int j=Integer.parseInt(sno);
				j++;
				sno=Integer.toString(j);
			}
			
			if(l==1)
			{
				bedit=new JButton("Edit");
				bedit.addActionListener((e)->{
												int u=tb.getSelectedRow();
												if(u!=-1)
												{
													String sd=((String)tb.getValueAt(u,1)).substring(1);
													new DialogEditCourse(sd,jf);}});
												
				JPanel p4=new JPanel();
				p4.add(bedit);
				
				pce1.add(sp,"Center");
				pce1.add(p4,"South");
			}
			else if(l==2)
			{
				pce1.add(sp,"Center");
			}
			jf.validate();		
		}
		catch(SQLException r)
		{}
		
	}
}