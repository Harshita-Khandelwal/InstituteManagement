import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class ViewUser extends Common
{	
	private JButton bdesignation,ball,bsubmit,bedit;
	private JComboBox cdesignation;
	private String sno="1";  
	private DefaultTableModel dm;
	private JTable tb;
	private JScrollPane sp;
	ViewUser(JPanel p,JDesktopPane d,JFrame f)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		viewUserPanel();
	}
	public void viewUserPanel()
	{
		startp();
		add.setText("View/Edit User Details :");
		
		bdesignation=new JButton("Search By Designation");
		ball=new JButton("All Entries");
		
		bdesignation.addActionListener((e)->{search(bdesignation);});
		ball.addActionListener((e)->{search(ball);});
		
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		pce1.add(bdesignation,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(ball,gbc);
		
		endp();
	}
	public void search(JButton l)
	{
		startp();
		add.setText("View/Edit User Details :");

		String s[]={"admin","manager","accounts_manager"};
		cdesignation=new JComboBox(s);
		
		bsubmit=new JButton("Submit/Refresh");
		pce1.setLayout(new BorderLayout());
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.add(new JLabel(l.getText()));
		JPanel p2=new JPanel();
		p2.setLayout(new GridLayout(2,1));
		p2.add(p1);
		
		if(((l.getText()).equals("Search By Designation")) )
		{
			p1.add(cdesignation);
		}
		else if (((l.getText()).equals("All Entries")))
		{
		}
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER));
		p3.add(bsubmit);
		p2.add(p3);
		pce1.add(p2,"North");
		
				
		String columns[]={"S.No.","ID","Name","UserName","Mobile no","Designation"};
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
		 if(t.equals("Search By Designation"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select login_id from login where designation=\""+(String)(cdesignation.getSelectedItem())+"\"");
			}
			else if(t.equals("All Entries"))
			{
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select login_id from login");
			}
			java.util.Vector v=new java.util.Vector();
			while(TheInstituteDatabase.rs.next()==true)
			{
				v.add(TheInstituteDatabase.rs.getString("login_id"));
			}
			int rowcount=dm.getRowCount();
			for(int u=0;u<rowcount;u++)
			{
				dm.removeRow(0);
			}
			int i=0;
			String sid,userName,name,mobileNo,designation;
			while(i<v.size())
			{
				sid=(String)v.get(i);
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username,name,MobileNo,designation from login where login_id=\""+sid+"\"");
				TheInstituteDatabase.rs.next();
				userName=TheInstituteDatabase.rs.getString("username");
				name=TheInstituteDatabase.rs.getString("name");
				mobileNo=TheInstituteDatabase.rs.getString("MobileNo");
				designation=TheInstituteDatabase.rs.getString("designation");
				
				Object ob[]={sno,sid,name,userName,mobileNo,designation};
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
												String sd=((String)tb.getValueAt(u,1));
												new DialogEditUser(sd,jf);}});
			JPanel p4=new JPanel();
			p4.add(bedit);
			
			pce1.add(sp,"Center");
			pce1.add(p4,"South");
			
			jf.validate();
		}
		catch(SQLException e)
		{
		}
	}
}