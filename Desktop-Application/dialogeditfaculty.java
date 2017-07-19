import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class DialogEditFaculty
{
	private JDialog jd;
	private JFrame jf;
	private String id;
	private JTextField tname,tmobileno,tqual;
	private JScrollPane sp;
	private JTextArea tadd;
	private	JComboBox ccourse;
	DialogEditFaculty(String i,JFrame f)
	{
		jf=f;
		id=i;
		jd=new JDialog(jf,"Edit Faculty",true);
		jd.setSize(500,500);
		jd.setLocationRelativeTo(jf);
		
		JDesktopPane d=new JDesktopPane();  
		jd.add(d);
		JInternalFrame j=infopanel();
		j.setSize(500,500);
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
		
		JLabel lid=new JLabel("Faculty Id : ");
		JLabel lname=new JLabel("Faculty Name : ");
		JLabel ladd=new JLabel("Faculty Address : ");
		JLabel lmobile_no=new JLabel("Mobile No : ");
		JLabel lqual=new JLabel("Qualification : ");
		JLabel lcourse=new JLabel("Course : ");
		String sid="",sname="",sadd="",smobileno="",squal="",cid="",scourse="";
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select faculty_name,faculty_address,faculty_mobile_no,fac_qualification,course_id from faculty where faculty_id=\""+id+"\"");
			TheInstituteDatabase.rs.next();
			sname=TheInstituteDatabase.rs.getString("faculty_name");
			sadd=TheInstituteDatabase.rs.getString("faculty_address");
			smobileno=TheInstituteDatabase.rs.getString("faculty_mobile_no");
			squal=TheInstituteDatabase.rs.getString("fac_qualification");
			cid=TheInstituteDatabase.rs.getString("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name from course where course_id=\""+cid+"\"");
			TheInstituteDatabase.rs.next();
			scourse=TheInstituteDatabase.rs.getString("course_name");
		}
		catch(SQLException r)
		{
		System.out.println("B");
		}
				
		JLabel yid=new JLabel("F"+id);
		tname=new JTextField(10);
		tname.setText(sname);
		tadd=new JTextArea(5,10);
		sp=new JScrollPane(tadd);
		tadd.setText(sadd);
		tmobileno=new JTextField(10);
		tmobileno.setText(smobileno);
		tmobileno.addKeyListener(new KeyListener()
									{
										public void keyTyped(KeyEvent e)
										{}
										public void keyReleased(KeyEvent e)
										{}
										public void keyPressed(KeyEvent e)
										{
											int k;
											k=e.getKeyCode();
											if((k>=KeyEvent.VK_0 && k<=KeyEvent.VK_9) || (k>=KeyEvent.VK_NUMPAD0 && k<=KeyEvent.VK_NUMPAD9))
											{ 
												if(tmobileno.getText()!=null && tmobileno.getText().length()>9)
												{
													tmobileno.setEditable(false);
													tmobileno.setBackground(Color.WHITE);
												}
												else
													tmobileno.setEditable(true);
											}
											else if(k==KeyEvent.VK_DELETE || k==KeyEvent.VK_BACK_SPACE )
											{
												tmobileno.setEditable(true);
											}
											else if(k==KeyEvent.VK_LEFT || k==KeyEvent.VK_RIGHT)
											{
												tmobileno.setEditable(true);
											}
											else
											{
												tmobileno.setEditable(false);
												tmobileno.setBackground(Color.WHITE);
											}
										}
									});
		
		tqual=new JTextField(10);
		tqual.setText(squal);
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
		ccourse.setSelectedItem(scourse);
		
		JButton bdep=new JButton("Edit");
		bdep.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Are you sure you want to edit the database","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createfacultydb();});
		
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
		
		gbc1.gridx=0;
		gbc1.gridy=2;
		p.add(ladd,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=2;
		p.add(sp,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=3;
		p.add(lmobile_no,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=3;
		p.add(tmobileno,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=4;
		p.add(lqual,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=4;
		p.add(tqual,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=5;
		p.add(lcourse,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=5;
		p.add(ccourse,gbc1);
		
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
	public void createfacultydb()
	{
		try
		{
			String fac_name,fac_no,fac_address,fac_qualification,fac_course;
			fac_name=tname.getText();
			fac_no=tmobileno.getText();
			fac_address=tadd.getText();
			fac_qualification=tqual.getText();
			if((fac_name==null | fac_name.equals(""))|(fac_no==null | fac_no.equals(""))|(fac_address==null | fac_address.equals(""))|(fac_qualification==null | fac_qualification.equals("")))
			{
					JOptionPane.showMessageDialog(jf,"Fields are empty");
					return;
			}
			if(fac_name.length()>20)
			{
				JOptionPane.showMessageDialog(jf,"Faculty name cannot be more than 20 characters");
					return;
			}
			if(fac_address.length()>50)
			{
				JOptionPane.showMessageDialog(jf,"Faculty address cannot be more than 50 characters");
					return;
			}
			if(fac_no.length()!=10)
			{
				JOptionPane.showMessageDialog(jf,"Invalid Faculty mobile no");
					return;
			}
			if(fac_qualification.length()>10)
			{
				JOptionPane.showMessageDialog(jf,"Faculty qualification cannot be more than 10 characters");
					return;
			}
			fac_course=(String)ccourse.getSelectedItem();
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from course where course_name=\""+fac_course+"\"");
			TheInstituteDatabase.rs.next();
			String ci=TheInstituteDatabase.rs.getString("course_id");
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("update faculty set faculty_name=?,faculty_address=?,faculty_mobile_no=?,fac_qualification=?,course_id=? where faculty_id=\""+id+"\"");
			pstmt.setString(1,fac_name);
			pstmt.setString(2,fac_address);
			pstmt.setString(3,fac_no);
			pstmt.setString(4,fac_qualification);
			pstmt.setString(5,ci);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"Faculty Profile Updated Successfully");
			jd.setVisible(false);
			jd.dispose();
		}
		catch(SQLException r)
		{
		System.out.println("V");
		}
	}
}
