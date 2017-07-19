import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class DialogEditQuery implements KeyListener
{
	private JDialog jd;
	private JFrame jf;
	private String id,scourse;
	private JTextField tname,tmobile_no,tfname,tfmobile_no,tclg,tcourse;
	private JScrollPane sp;
	private JTextArea tadd;
	private JComboBox cquery_course;
	DialogEditQuery(String i,JFrame f)
	{
		jf=f;
		id=i;
		jd=new JDialog(jf,"Edit Query",true);
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
		jd.validate();
		jf.validate();
	}
	public JInternalFrame infopanel()
	{
		JInternalFrame pa=new JInternalFrame();
		JPanel p=new JPanel();
		p.setLayout(new GridBagLayout());
		
		JLabel lid=new JLabel("Query Id : ");
		JLabel lname=new JLabel("Query Name : ");
		JLabel lmobile_no=new JLabel("Mobile No : ");
		JLabel lfname=new JLabel("Father's Name : ");
		JLabel lfmobile_no=new JLabel("Father's Mob.No. : ");
		JLabel ladd=new JLabel("Query Address : ");
		JLabel lclg=new JLabel("College : ");
		JLabel lcourse=new JLabel("Course : ");
		
		String sid="",sname="",smobno="",sfname="",sfmobno="",sadd="",sclg="";
		int cid=0;
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
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name from course where course_id=\""+cid+"\"");
			TheInstituteDatabase.rs.next();
			scourse=TheInstituteDatabase.rs.getString("course_name");
			sid="Q"+id;
		}
		catch(SQLException r)
		{
		System.out.println("B");
		}
				
		JLabel yid=new JLabel(sid);
		tname=new JTextField(10);
		tname.setText(sname);
		tmobile_no=new JTextField(10);
		tmobile_no.setText(smobno);
		tmobile_no.addKeyListener(this);
		tfname=new JTextField(10);
		tfname.setText(sfname);
		tfmobile_no=new JTextField(10);
		tfmobile_no.setText(sfmobno);
		tfmobile_no.addKeyListener(this);
		tadd=new JTextArea(5,10);
		sp=new JScrollPane(tadd);
		tadd.setText(sadd);
		tclg=new JTextField(10);
		tclg.setText(sclg);
		tcourse=new JTextField(10);
		
		cquery_course=new JComboBox();
		try
		{
		TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name from course");
		while(TheInstituteDatabase.rs.next()==true)
		{
			cquery_course.addItem(TheInstituteDatabase.rs.getString("course_name"));
		}
		cquery_course.setSelectedItem(scourse);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		JButton bdep=new JButton("Edit");
		bdep.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Are you sure you want to edit the database","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createquerydb();
											});
		
		GridBagConstraints gbc1=new GridBagConstraints();
		Insets in1=new Insets(10,10,10,10);
		gbc1.insets=in1;
		
		gbc1.anchor=GridBagConstraints.WEST;
		gbc1.gridx=0;
		gbc1.gridy=0;
		p.add(lid,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=0;
		p.add(yid,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=1;
		p.add(lname,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=1;
		p.add(tname,gbc1);
		
		gbc1.gridx=2;
		gbc1.gridy=1;
		p.add(lmobile_no,gbc1);
		
		gbc1.gridx=3;
		gbc1.gridy=1;
		p.add(tmobile_no,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=2;
		p.add(lfname,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=2;
		p.add(tfname,gbc1);
		
		gbc1.gridx=2;
		gbc1.gridy=2;
		p.add(lfmobile_no,gbc1);
		
		gbc1.gridx=3;
		gbc1.gridy=2;
		p.add(tfmobile_no,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=3;
		p.add(ladd,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=3;
		p.add(sp,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=4;
		p.add(lclg,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=4;
		p.add(tclg,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=5;
		p.add(lcourse,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=5;
		p.add(cquery_course,gbc1);
		
		JPanel p2=new JPanel();
		gbc1.gridx=0;
		gbc1.gridy=6;
		p.add(p2,gbc1);
		
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(bdep);
		gbc1.gridx=1;
		gbc1.gridy=6;
		p.add(p3,gbc1);
		
		pa.add(p,BorderLayout.CENTER);
		
		return pa;
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
			String query_name,query_no,query_f_name,query_f_no,query_address,query_clg,query_course;
			query_id=Integer.parseInt(id);
			query_name=tname.getText();
			query_no=tmobile_no.getText();
			query_f_name=tfname.getText();
			query_f_no=tfmobile_no.getText();
			query_address=tadd.getText();
			query_clg=tclg.getText();
			if((query_name==null | query_name.equals(""))|(query_no==null | query_no.equals(""))|(query_f_name==null | query_f_name.equals(""))|(query_f_no==null | query_f_no.equals(""))|(query_address==null | query_address.equals(""))|(query_clg==null | query_clg.equals("")))
			{
					JOptionPane.showMessageDialog(jf,"Fields are empty");
					return;
			}
			if(query_name.length()>30)
			{
				JOptionPane.showMessageDialog(jf,"Query name cannot be more than 30 characters");
					return;
			}
			if(query_no.length()!=10)
			{
				JOptionPane.showMessageDialog(jf,"Invalid Query mobile no");
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
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("update query set query_id=?,query_name=?,query_mobile_no=?,query_father_name=?,query_father_mobile_no=?,query_address=?,query_clg=?,course_id=? where query_id="+query_id);
			pstmt.setInt(1,query_id);
			pstmt.setString(2,query_name);
			pstmt.setString(3,query_no);
			pstmt.setString(4,query_f_name);
			pstmt.setString(5,query_f_no);
			pstmt.setString(6,query_address);
			pstmt.setString(7,query_clg);
			pstmt.setString(8,ci);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"Query Profile Updated Successfully");
			jd.setVisible(false);
			jd.dispose();
		}
		catch(SQLException r)
		{
			r.printStackTrace();
		}
	}

}