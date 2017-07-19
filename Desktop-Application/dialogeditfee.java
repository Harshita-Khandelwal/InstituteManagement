import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
@SuppressWarnings({"unchecked","deprecation"})
class DialogEditFee implements KeyListener
{
	private JDialog jd;
	private int slabel;
	private JTextField yfeedeposit;
	private JFrame jf;
	private String id;
	DialogEditFee(int l,String i,JFrame f)
	{
		
		slabel=l;
		System.out.println(slabel);
		jf=f;
		id=i;
		jd=new JDialog(jf,"Edit Fee",true);
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
		
		JLabel lreceipt=new JLabel("Receipt No : ");
		JLabel lreceipt_no=new JLabel("R"+slabel);
		
		JLabel lid=new JLabel("Student Id : ");
		JLabel lname=new JLabel("Student Name : ");
		JLabel lfname=new JLabel("Father's Name : ");
		JLabel lcourse=new JLabel("Course Name : ");
		JLabel lcfees=new JLabel("Course Fees : ");
		JLabel lfeedeposit=new JLabel("Deposit : ");
		JLabel ldue=new JLabel("Amount Due : ");
		String yn="",yfn="",yco="",ycfee="";
		try
		{
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select stu_name,stu_father_name,course_id from student where stu_id=\""+id+"\"");
			TheInstituteDatabase.rs.next();
			yn=TheInstituteDatabase.rs.getString("stu_name");
			yfn=TheInstituteDatabase.rs.getString("stu_father_name");
			String yc=TheInstituteDatabase.rs.getString("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_name,course_fees from course where course_id=\""+yc+"\"");
			TheInstituteDatabase.rs.next();
			yco=TheInstituteDatabase.rs.getString("course_name");
			ycfee=TheInstituteDatabase.rs.getString("course_fees");
		}
		catch(SQLException r)
		{
		System.out.println("B");
		}
				
		JLabel yid=new JLabel("S"+id);
		JLabel yname=new JLabel(yn);
		JLabel yfname=new JLabel(yfn);
		JLabel ycourse=new JLabel(yco);
		JLabel ycfees=new JLabel(ycfee);
		yfeedeposit=new JTextField(10);
		yfeedeposit.addKeyListener(this);
		int due=calc_due();
		JLabel ydue=new JLabel(Integer.toString(due));
		JButton bdep=new JButton("Deposit");
		bdep.addActionListener((e)->{int x=JOptionPane.showConfirmDialog(jf,"Are you sure you want to deposit the fees","Confirmation",JOptionPane.YES_NO_OPTION);
										if(x==JOptionPane.YES_OPTION)
											createfeedb();});
		
		GridBagConstraints gbc1=new GridBagConstraints();
		Insets in1=new Insets(10,10,10,10);
		gbc1.insets=in1;
		gbc1.anchor=GridBagConstraints.EAST;
		
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		p1.add(lreceipt);
		p1.add(lreceipt_no);
		
		gbc1.gridx=0;
		gbc1.gridy=0;
		p.add(p1,gbc1);
		
		gbc1.anchor=GridBagConstraints.WEST;
		gbc1.gridx=0;
		gbc1.gridy=1;
		p.add(lid,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=1;
		p.add(yid,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=2;
		p.add(lname,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=2;
		p.add(yname,gbc1);
		
		gbc1.gridx=2;
		gbc1.gridy=2;
		p.add(lfname,gbc1);
		
		gbc1.gridx=3;
		gbc1.gridy=2;
		p.add(yfname,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=3;
		p.add(lcourse,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=3;
		p.add(ycourse,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=4;
		p.add(lcfees,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=4;
		p.add(ycfees,gbc1);
		
		gbc1.gridx=0;
		gbc1.gridy=5;
		p.add(lfeedeposit,gbc1);
		
		gbc1.gridx=1;
		gbc1.gridy=5;
		p.add(yfeedeposit,gbc1);
		
		gbc1.gridx=2;
		gbc1.gridy=5;
		p.add(ldue,gbc1);
		
		gbc1.gridx=3;
		gbc1.gridy=5;
		p.add(ydue,gbc1);
		
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
	public int calc_due()
	{
		int due=0;
		try
		{
			java.util.Vector rno=new java.util.Vector();
			int temp,dep=0,i=0;
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select receipt_no from fee where stu_id=\""+id+"\"");
			while(TheInstituteDatabase.rs.next()==true)
			{
				rno.add(TheInstituteDatabase.rs.getString("receipt_no"));
			}
			while(i<rno.size())
			{
				String r=(String)rno.get(i);
				TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select fee_deposit from receipt where receipt_no=\""+r+"\"");
				TheInstituteDatabase.rs.next();
				temp=TheInstituteDatabase.rs.getInt("fee_deposit");
				dep=dep+temp;
				i++;
			}
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_id from student where stu_id=\""+id+"\"");
			TheInstituteDatabase.rs.next();
			String yc=TheInstituteDatabase.rs.getString("course_id");
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select course_fees from course where course_id=\""+yc+"\"");
			TheInstituteDatabase.rs.next();
			String ycfee=TheInstituteDatabase.rs.getString("course_fees");
			due=Integer.parseInt(ycfee)-dep;
		}
		catch(SQLException e)
		{
		}
		return due;
	}
	public void createfeedb()
	{
		try
		{
			String receipt_gen_by;
			int rec_no=slabel;
			String dep=yfeedeposit.getText();
			if((dep==null | dep.equals("")))
			{
				JOptionPane.showMessageDialog(jf,"Fields are empty");
				return;
			}
			int idep=Integer.parseInt(dep);
			int due=calc_due();
			if(idep>due)
			{
				JOptionPane.showMessageDialog(jf,"Amount more than due can't be deposited");
				return;
			}
			TheInstituteDatabase.rs=TheInstituteDatabase.stmt.executeQuery("select username from login where login_id=\""+TheInstituteLoginGUI.storelogin+"\"");
			TheInstituteDatabase.rs.next();
			receipt_gen_by=TheInstituteDatabase.rs.getString("username");
			java.sql.Date rec_date=new java.sql.Date(System.currentTimeMillis());
			java.sql.Time rec_time=new java.sql.Time(System.currentTimeMillis());
			PreparedStatement pstmt=TheInstituteDatabase.con.prepareStatement("insert into receipt values(?,?,?,?,?)");
			pstmt.setInt(1,rec_no);
			pstmt.setInt(2,idep);
			pstmt.setString(3,receipt_gen_by);
			pstmt.setDate(4,rec_date);
			pstmt.setTime(5,rec_time);
			pstmt.execute();
			pstmt.close();
			PreparedStatement pstmt1=TheInstituteDatabase.con.prepareStatement("insert into fee values(?,?)");
			pstmt1.setInt(1,rec_no);
			pstmt1.setString(2,id);
			pstmt1.execute();
			pstmt1.close();
			JOptionPane.showMessageDialog(jf,"Fees Deposited Successfully");
			jd.setVisible(false);
			jd.dispose();
		}
		catch(SQLException r)
		{
		}
	}
}