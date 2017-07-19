import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class DialogEditCourse implements KeyListener
{
	private JDialog jd;
	private JFrame jf;
	private String id;
	private JTextField tname,tfee,tduration;
	private JComboBox capprox_duration;
	DialogEditCourse(String i,JFrame f)
	{
		jf=f;
		id=i;
		jd=new JDialog(jf,"Edit Course",true);
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
		
		JLabel lid=new JLabel("Course Id : ");
		JLabel lname=new JLabel("Course Name : ");
		JLabel lfee=new JLabel("Fees : ");
		JLabel lduration=new JLabel("Approx Duration : ");
		String sname="",sfee="",sduration="";
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name,course_fees,approx_duration from course where course_id=\""+id+"\"");
			TheInstituteDatabase.rs.next();
			sname=TheInstituteDatabase.rs.getString("course_name");
			sfee=TheInstituteDatabase.rs.getString("course_fees");
			sduration=TheInstituteDatabase.rs.getString("approx_duration");
		}
		catch(SQLException r)
		{
		System.out.println("B");
		}
				
		JLabel yid=new JLabel("C"+id);
		tname=new JTextField(10);
		tname.setText(sname);
		tfee=new JTextField(10);
		tfee.setText(sfee);
		tfee.addKeyListener(this);
		tduration=new JTextField(10);
		tduration.addKeyListener(this);
		int o; 
		o=sduration.indexOf("Days");
		if(o==-1)
		{
			o=sduration.indexOf("Months");
		}
		if(o==-1)
		{
			o=sduration.indexOf("Year");
		}
		String sdur,sdur_no;
		sdur_no=sduration.substring(0,o);
		sdur=sduration.substring(o);
		tduration.setText(sdur_no);
		String d[]={"Days","Months","Year"};
		capprox_duration=new JComboBox(d);
		capprox_duration.setSelectedItem(sdur);
		
		JButton bdep=new JButton("Edit");
		bdep.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Are you sure you want to edit the database","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createcoursedb();});
		
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
		p.add(lfee,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=2;
		p.add(tfee,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=3;
		p.add(lduration,gbc1);
		
		JPanel p4=new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.LEFT));
		p4.add(tduration);
		p4.add(capprox_duration); 
		
		gbc1.gridx=1;
		gbc1.gridy=3;
		p.add(p4,gbc1);
		
		JPanel p2=new JPanel();
		gbc1.gridx=0;
		gbc1.gridy=4;
		p.add(p2,gbc1);
		
		JPanel p3=new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(bdep);
		gbc1.gridx=1;
		gbc1.gridy=4;
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
		if(t==tduration)
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
		else if(t==tfee)
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
	public void createcoursedb()
	{
		try
		{
			String course_name,course_fees,app_no,app_s,app;
			course_name=tname.getText();
			course_fees=tfee.getText();
			app_no=tduration.getText();
			app_s=(String)capprox_duration.getSelectedItem();
			app=app_no+app_s;
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
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("update course set course_name=?,course_fees=?,approx_duration=? where course_id=\""+id+"\"");
			pstmt.setString(1,course_name);
			pstmt.setInt(2,fees);
			pstmt.setString(3,app);
			pstmt.execute();
			pstmt.close();
			JOptionPane.showMessageDialog(jf,"Course Updated Successfully");
			jd.setVisible(false);
			jd.dispose();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(jf,"This Course name already exists");
			return;
		}
	}
	
}