import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
@SuppressWarnings({"unchecked","deprecation"})
class TheInstituteManagerGUI extends Common
{
	private JPanel pleftce;
	private JButton bstuprofile,bquery,bcourse,bbatch,bfee_details,bfaculty,blogout;
	TheInstituteManagerGUI()
	{
		bstuprofile=new JButton("Student");
		bquery=new JButton("Query");
		bcourse=new JButton("       Course      ");
		bbatch=new JButton("Batch");
		bfee_details=new JButton("Fee Panel");
		bfaculty=new JButton("Faculty");
		blogout=new JButton("Logout");
		bstuprofile.addActionListener((e)->{new Student(framepanel,desktop,jf);});
		bquery.addActionListener((e)->{new Query(framepanel,desktop,jf);});
		bcourse.addActionListener((e)->{new ViewCourse(framepanel,desktop,jf,2);});
		bbatch.addActionListener((e)->{new ViewBatch(framepanel,desktop,jf,2);});
		bfee_details.addActionListener((e)->{new ViewFee(framepanel,desktop,jf,2);});
		bfaculty.addActionListener((e)->{new ViewFaculty(framepanel,desktop,jf,2);});
		blogout.addActionListener((e)->{jf.setVisible(false);
										jf.dispose();
										System.exit(1);
										});
		
		pleftce=new JPanel();
		pleftce.setLayout(new GridBagLayout());
		gbc=new GridBagConstraints();
		in=new Insets(15,10,15,10);
		gbc.insets=in;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridx=0;
		gbc.gridy=0;
		pleftce.add(bstuprofile,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		pleftce.add(bquery,gbc);
		gbc.gridx=0;
		gbc.gridy=2;
		pleftce.add(bcourse,gbc);
		gbc.gridx=0;
		gbc.gridy=3;
		pleftce.add(bbatch,gbc);
		gbc.gridx=0;
		gbc.gridy=4;
		pleftce.add(bfee_details,gbc);
		gbc.gridx=0;
		gbc.gridy=5;
		pleftce.add(bfaculty,gbc);
		gbc.gridx=0;
		gbc.gridy=6;
		pleftce.add(blogout,gbc);
		
		masterpage();
	}
	public void masterpage()
	{
		jf=new JFrame("The Institute Management System");
		jf.setSize(740,730);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		framepanel=(JPanel)jf.getContentPane();
		framepanel.setLayout(new BorderLayout());
		desktop=new JDesktopPane();
		jif=new JInternalFrame();
		jif.setSize(600,600);
		
		Class c=this.getClass();
		java.net.URL url=c.getResource("/icon/topicalternative.jpg");
		ImageIcon ii=new ImageIcon(url);
		JLabel topic=new JLabel("The Institute Management System");
		topic.setIcon(ii);
		topic.setFont(new Font("Comic Sans MS",Font.BOLD | Font.ITALIC,30));
		JPanel ptop=new JPanel();
		ptop.setLayout(new FlowLayout(FlowLayout.LEFT));
		ptop.add(topic);
		ptop.setBackground(Color.RED);
		framepanel.add(ptop,BorderLayout.NORTH);
		
		JPanel pleft=new JPanel();
		pleft.setLayout(new BorderLayout());
		pleft.add(pleftce,"Center");
		pleftce.setBackground(Color.BLUE);
		framepanel.add(pleft,BorderLayout.WEST);
		
		JPanel pcenter=new JPanel();
		pcenter.setLayout(new BorderLayout());
		label();
		pcenter.add(pc1,"North");
	
		jif.add(pcenter);
		desktop.add(jif);
		((javax.swing.plaf.basic.BasicInternalFrameUI)jif.getUI()).setNorthPane(null);
		jif.setVisible(true);
		framepanel.add(desktop,"Center");
		jif.toFront();
		
		jf.setResizable(false);
		jf.setVisible(true);
	}
}