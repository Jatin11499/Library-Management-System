import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;

public class Lib extends JFrame
{
	JLabel l1,l2;
	JButton btnAd,btnLib;
	Lib()
	{
		l1 = new JLabel("Library Management - ");
		l1.setBounds(30,25,250,30);
		l1.setFont(new Font("Arial",Font.BOLD,20));
		l2 = new JLabel("Java Project");
		l2.setBounds(85,55,250,30);
		l2.setFont(new Font("Arial",Font.BOLD,20));
		btnAd = new JButton("Admin Login");
		btnAd.setBounds(70,125,150,50);
		btnLib = new JButton("Librarian Login");
		btnLib.setBounds(70,200,150,50);
		
		btnAd.addActionListener(e -> {new AdminLogin();});
		btnLib.addActionListener(e -> {new LibLogin();});
		
		add(l1);add(l2);add(btnAd);add(btnLib);
		
		setTitle("Library Mangement System");
		setLayout(null);
		setSize(300,350);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
		new Lib();
	}	
}

class AdminLogin extends JFrame
{
	JLabel l,username,password;
	JTextField name;
	JPasswordField pass;
	JButton btnAdLogin;
	public AdminLogin()
	{
		l = new JLabel("Admin Login Form");
		l.setBounds(100,50,200,30);
		l.setFont(new Font("Arial",Font.BOLD,20));
		username = new JLabel("Enter Name:");
		username.setBounds(25,110,100,25);
		password = new JLabel("Enter Password:");
		password.setBounds(25,160,100,25);
		name = new JTextField();
		name.setBounds(175,110,150,25);
		pass = new JPasswordField();
		pass.setBounds(175,160,150,25);
		btnAdLogin = new JButton("Login");
		btnAdLogin.setBounds(150,225,85,35);
		
		btnAdLogin.addActionListener(e ->
		{
			String p = new String(pass.getPassword());
			if(name.getText().equals("admin") && p.equals("admin123"))
			{
				new AdminSection();
				dispose();
			}
			else
				JOptionPane.showMessageDialog(this,"Please Enter correct Password","Alert",JOptionPane.WARNING_MESSAGE);
		});
		
		add(l);add(username);add(name);add(password);add(pass);add(btnAdLogin);
		setTitle("Admin Login");
		setLayout(null);
		setSize(400,350);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class AdminSection extends JFrame
{
	JLabel l;
	JButton btnAdd,btnView,btnDelete,btnLogout;
	public AdminSection()
	{
		l = new JLabel("Admin Setion");
		l.setBounds(115,25,200,30);
		l.setFont(new Font("Arial",Font.BOLD,20));
		btnAdd = new JButton("Add Librarian");
		btnAdd.setBounds(100,80,155,40);
		btnView = new JButton("View Librarian");
		btnView.setBounds(100,150,155,40);
		btnDelete = new JButton("Delete Librarian");
		btnDelete.setBounds(100,220,155,40);
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(100,290,155,40);
		
		btnAdd.addActionListener(e -> {new AddLibrarian();});
		btnView.addActionListener(e ->{new ViewLibrarian();});
		btnDelete.addActionListener(e -> {new DeleteLibrarian();});
		btnLogout.addActionListener(e -> 
		{
			int a=JOptionPane.showConfirmDialog(this,"Are you sure you want to Logout?");  
			if(a==JOptionPane.YES_OPTION)
				dispose();
		});
		
		add(l);add(btnAdd);add(btnView);add(btnDelete);add(btnLogout);
		setLayout(null);
		setSize(375,425);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class AddLibrarian extends JFrame
{
	JLabel l,name,password,email,addr,city,c_no;
	JTextField n,em,a,c,no;
	JPasswordField pass;
	JButton btnAdd,btnBack;
	public AddLibrarian()
	{
		l = new JLabel("Add Librarian");
		l.setBounds(125,25,200,30);
		l.setFont(new Font("Arial",Font.BOLD,20));
		name = new JLabel("Name:");
		name.setBounds(25,75,100,25);
		password = new JLabel("Password:");
		password.setBounds(25,125,100,25);
		email = new JLabel("Email:");
		email.setBounds(25,175,100,25);
		addr = new JLabel("Address:");
		addr.setBounds(25,225,100,25);
		city = new JLabel("City:");
		city.setBounds(25,275,100,25);
		c_no = new JLabel("Contact No:");
		c_no.setBounds(25,325,100,25);
		
		n = new JTextField();
		n.setBounds(175,75,150,25);
		pass = new JPasswordField();
		pass.setBounds(175,125,150,25);
		em = new JTextField();
		em.setBounds(175,175,150,25);
		a = new JTextField();
		a.setBounds(175,225,150,25);
		c = new JTextField();
		c.setBounds(175,275,150,25);
		no = new JTextField();
		no.setBounds(175,325,150,25);
		btnAdd = new JButton("Add Librarian");
		btnAdd.setBounds(120,375,150,35);
		btnBack = new JButton("Back");
		btnBack.setBounds(145,425,100,35);
		
		btnAdd.addActionListener(e -> {
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("Select id from librarian");
	    	rs.last();
	    	String ID = String.valueOf(rs.getInt(1));
	    	int idInt = Integer.parseInt(ID)+1;
	    	
	    	int abc = st.executeUpdate("Insert into librarian values("+idInt+",'"+n.getText()+"','"+new String(pass.getPassword())+"','"+em.getText()+"','"+a.getText()+"','"+c.getText()+"','"+no.getText()+"')");
	    	System.out.println(abc+" rows affected!");
	    	
	    	st.close();
	    	con.close();
			
			JOptionPane.showMessageDialog(this,"Librarian Added Successfully!");
			n.setText("");
			em.setText("");
			a.setText("");
			c.setText("");
			no.setText("");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		});
		btnBack.addActionListener(e -> {dispose();});
		
		add(l);add(name);add(n);add(password);add(pass);add(email);add(em);add(addr);add(a);add(city);add(c);add(c_no);add(no);add(btnAdd);add(btnBack);
		
				
		setLayout(null);
		setSize(400,525);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class ViewLibrarian extends JFrame
{
	JTable jt;
	ViewLibrarian()
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
    	Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from librarian");
		ResultSetMetaData md = rs.getMetaData();
		int colCount = md.getColumnCount();
		String col[] = new String[colCount];
		for(int i=0;i<colCount;i++)
		{
			int temp = i+1;
			col[i]=md.getColumnName(temp);
		}
		
		rs.last();
		int rowCount = rs.getRow();
		rs.beforeFirst();
		
		String data[][] = new String[rowCount][colCount];
		int i=0;
		while(rs.next())
    	{
    		data[i][0]=String.valueOf(rs.getInt(1));
    		for(int l=1;l<colCount;l++)
    		{
    			data[i][l]=rs.getString(l+1);
    		}
    		i++;
    	}
		
		jt=new JTable(data,col);
		jt.setBounds(0,0,700,700);          
    	JScrollPane sp=new JScrollPane(jt);    
    	add(sp);
		
		st.close();
    	con.close();
		setSize(700,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

class DeleteLibrarian extends JFrame
{
	JLabel l;
	JTextField id;
	JButton btnDel,btnBack;
	public DeleteLibrarian()
	{
		l = new JLabel("Enter Id:");
		l.setBounds(35,50,75,30);
		id = new JTextField();
		id.setBounds(125,50,150,30);
		btnDel = new JButton("Delete");
		btnDel.setBounds(160,100,85,35);
		btnBack = new JButton("Back");
		btnBack.setBounds(225,200,75,35);
		
		btnDel.addActionListener(e -> {
			if(id.getText().isEmpty())
				JOptionPane.showMessageDialog(this,"Please Enter an ID","Alert",JOptionPane.WARNING_MESSAGE);
			else
			{
				try
				{
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
	    		Statement st = con.createStatement();
	    		int idInt = Integer.parseInt(id.getText());
	    		int abc = st.executeUpdate("delete from librarian where id="+idInt);
				System.out.println(abc+" rows affected!");
	    	
		    	st.close();
		    	con.close();
				JOptionPane.showMessageDialog(this,"Record Deleted Successfully!");
				id.setText("");
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			});
		btnBack.addActionListener(e -> {dispose();});
		
		add(l);add(id);add(btnDel);add(btnBack);
		setLayout(null);
		setSize(350,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class LibLogin extends JFrame
{
	JLabel l,username,password;
	JTextField name;
	JPasswordField pass;
	JButton btnLibLogin;
	public LibLogin()
	{
		l = new JLabel("Librarian Login Form");
		l.setBounds(100,50,200,30);
		l.setFont(new Font("Arial",Font.BOLD,20));
		username = new JLabel("Enter Name:");
		username.setBounds(25,110,100,25);
		password = new JLabel("Enter Password:");
		password.setBounds(25,160,100,25);
		name = new JTextField();
		name.setBounds(175,110,150,25);
		pass = new JPasswordField();
		pass.setBounds(175,160,150,25);
		btnLibLogin = new JButton("Login");
		btnLibLogin.setBounds(150,225,85,35);
		
		btnLibLogin.addActionListener(e ->
		{
			try{
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("Select name,password from librarian");
			
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();
			
			String data[][] = new String[rowCount][2];
			int i=0;
			while(rs.next())
	    	{
	    		data[i][0]=rs.getString("name");
	    		data[i][1]=rs.getString("password");
	    		i++;
	    	}
	    	st.close();
	    	con.close();
	    	
	    	for(int j=0;j<rowCount;j++)
	    	{
		    	String p = new String(pass.getPassword());	
		    	if(name.getText().equals(data[j][0]) && p.equals(data[j][1]))
		    	{				
					new LibSection();
					dispose();
					i=1;
		    	}
	    	}
	    	if(i!=1)
		    	JOptionPane.showMessageDialog(this,"Please Enter correct Password","Alert",JOptionPane.WARNING_MESSAGE);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		});
		
		add(l);add(username);add(name);add(password);add(pass);add(btnLibLogin);
		setTitle("Librarian Login");
		setLayout(null);
		setSize(400,350);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class LibSection extends JFrame
{
	JLabel l;
	JButton btnAdd,btnView,btnIssue,btnViewIssued,btnReturn,btnLogout;
	public LibSection()
	{
		l = new JLabel("Librarian Setion");
		l.setBounds(100,25,200,30);
		l.setFont(new Font("Arial",Font.BOLD,20));
		btnAdd = new JButton("Add Books");
		btnAdd.setBounds(100,80,155,40);
		btnView = new JButton("View Books");
		btnView.setBounds(100,150,155,40);
		btnIssue = new JButton("Issue Book");
		btnIssue.setBounds(100,220,155,40);
		btnViewIssued = new JButton("View Issued Books");
		btnViewIssued.setBounds(100,290,155,40);
		btnReturn = new JButton("Return Book");
		btnReturn.setBounds(100,360,155,40);
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(100,430,155,40);
		
		btnAdd.addActionListener(e -> {new AddBooks();});
		btnView.addActionListener(e -> {new ViewBooks();});
		btnIssue.addActionListener(e -> {new IssueBook();});
		btnViewIssued.addActionListener(e -> {new ViewIssuedBooks();});
		btnReturn.addActionListener(e -> {new ReturnBook();});
		btnLogout.addActionListener(e -> 
		{
			int a=JOptionPane.showConfirmDialog(this,"Are you sure you want to Logout?");  
			if(a==JOptionPane.YES_OPTION)
			    dispose();
		});
		
		add(l);add(btnAdd);add(btnView);add(btnIssue);add(btnViewIssued);add(btnReturn);add(btnLogout);
		setLayout(null);
		setSize(375,550);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class AddBooks extends JFrame
{
	JLabel l,callno,name,author,pub,quan;
	JTextField cn,n,a,p,q;
	JButton btnAdd,btnBack;
	public AddBooks()
	{
		l = new JLabel("Add Books");
		l.setBounds(125,25,200,30);
		l.setFont(new Font("Arial",Font.BOLD,20));
		callno = new JLabel("Call No:");
		callno.setBounds(25,75,100,25);
		name = new JLabel("Name:");
		name.setBounds(25,125,100,25);
		author = new JLabel("Author:");
		author.setBounds(25,175,100,25);
		pub = new JLabel("Publisher:");
		pub.setBounds(25,225,100,25);
		quan = new JLabel("Quantity:");
		quan.setBounds(25,275,100,25);
		
		cn = new JTextField();
		cn.setBounds(175,75,150,25);
		n = new JTextField();
		n.setBounds(175,125,150,25);
		a = new JTextField();
		a.setBounds(175,175,150,25);
		p = new JTextField();
		p.setBounds(175,225,150,25);
		q = new JTextField();
		q.setBounds(175,275,150,25);
		btnAdd = new JButton("Add Books");
		btnAdd.setBounds(120,325,150,35);
		btnBack = new JButton("Back");
		btnBack.setBounds(145,375,100,35);
		
		btnAdd.addActionListener(e -> {
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
	    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("Select id from books");
	    	rs.last();
	    	int idInt = Integer.parseInt(rs.getString(1))+1;
	    	LocalDate d = LocalDate.now();
	    	String date = String.valueOf(d);
	    	
	    	int abc = st.executeUpdate("Insert into books values("+idInt+",'"+cn.getText()+"','"+n.getText()+"','"+a.getText()+"','"+p.getText()+"','"+q.getText()+"','0','"+date+"')");
	    	System.out.println(abc+" rows affected!");
	    	
	    	st.close();
	    	con.close();
			
			JOptionPane.showMessageDialog(this,"Book Added Successfully!");
			cn.setText("");
			n.setText("");
			a.setText("");
			p.setText("");
			q.setText("");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		});
		btnBack.addActionListener(e -> {dispose();});
		
		add(l);add(callno);add(cn);add(name);add(n);add(author);add(a);add(pub);add(p);add(quan);add(q);add(btnAdd);add(btnBack);
		setLayout(null);
		setSize(400,475);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class ViewBooks extends JFrame
{
	JTable jt;
	ViewBooks()
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
    	Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from books");
		ResultSetMetaData md = rs.getMetaData();
		int colCount = md.getColumnCount();
		String col[] = new String[colCount];
		for(int i=0;i<colCount;i++)
		{
			int temp = i+1;
			col[i]=md.getColumnName(temp);
		}
		
		rs.last();
		int rowCount = rs.getRow();
		rs.beforeFirst();
		
		String data[][] = new String[rowCount][colCount];
		int i=0;
		while(rs.next())
    	{
    		for(int l=0;l<colCount;l++)
    		{
    			if(l==6)
    				data[i][l]=String.valueOf(rs.getInt(l+1));
    			if(l==7)
    				data[i][l]=String.valueOf(rs.getInt(l+1));
    			data[i][l]=rs.getString(l+1);
    		}
    		i++;
    	}
		
		jt=new JTable(data,col);
		jt.setBounds(0,0,700,700);          
    	JScrollPane sp=new JScrollPane(jt);    
    	add(sp);
		
		st.close();
    	con.close();
		setSize(700,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

class IssueBook extends JFrame
{
	JLabel l,callno,id,name,con,warn;
	JTextField cn,i,n,c;
	JButton btnIssue,btnBack;
	public IssueBook()
	{
		l = new JLabel("Issue Book");
		l.setBounds(125,25,200,30);
		l.setFont(new Font("Arial",Font.BOLD,20));
		callno = new JLabel("Book Call No:");
		callno.setBounds(25,75,100,25);
		id = new JLabel("Student ID:");
		id.setBounds(25,125,100,25);
		name = new JLabel("Student Name:");
		name.setBounds(25,175,100,25);
		con = new JLabel("Student Contact:");
		con.setBounds(25,225,100,25);
		warn = new JLabel("Note: Please check Student ID carefully before issuing book!");
		warn.setBounds(20,370,380,30);
		warn.setForeground(Color.RED);
		
		cn = new JTextField();
		cn.setBounds(175,75,150,25);
		i = new JTextField();
		i.setBounds(175,125,150,25);
		n = new JTextField();
		n.setBounds(175,175,150,25);
		c = new JTextField();
		c.setBounds(175,225,150,25);

		btnIssue = new JButton("Issue Book");
		btnIssue.setBounds(120,275,150,35);
		btnBack = new JButton("Back");
		btnBack.setBounds(145,325,100,35);
		
		btnIssue.addActionListener(e -> {
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
	    		Statement st = con.createStatement();
	    		int abc = st.executeUpdate("update books set quantity=quantity-1, issued=issued+1 where callno='"+cn.getText()+"'");
				LocalDate d = LocalDate.now();
	    		String date = String.valueOf(d);
				ResultSet rs = st.executeQuery("Select id from issuedbooks");
	    		rs.last();
	    		String ID = String.valueOf(rs.getInt("id"));
	    		int idInt = Integer.parseInt(ID)+1;
	    		abc = st.executeUpdate("Insert into issuedbooks values("+String.valueOf(idInt)+",'"+cn.getText()+"','"+i.getText()+"','"+n.getText()+"','"+c.getText()+"','"+date+"')");
				System.out.println(abc+" rows affected!");
				st.close();
		    	con.close();
			
				JOptionPane.showMessageDialog(this,"Book issued Successfully!");
				cn.setText("");
				i.setText("");
				n.setText("");
				c.setText("");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		});
		btnBack.addActionListener(e -> {dispose();});
		
		add(l);add(callno);add(cn);add(id);add(i);add(name);add(n);add(con);add(c);add(btnIssue);add(btnBack);add(warn);
		setLayout(null);
		setSize(400,455);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class ViewIssuedBooks extends JFrame
{
	JTable jt;
	ViewIssuedBooks()
	{
		try{
		Class.forName("com.mysql.jdbc.Driver");
    	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
    	Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("Select * from issuedBooks");
		ResultSetMetaData md = rs.getMetaData();
		int colCount = md.getColumnCount();
		String col[] = new String[colCount];
		for(int i=0;i<colCount;i++)
		{
			int temp = i+1;
			col[i]=md.getColumnName(temp);
		}
		
		rs.last();
		int rowCount = rs.getRow();
		rs.beforeFirst();
		
		String data[][] = new String[rowCount][colCount];
		int i=0;
		while(rs.next())
    	{
    		for(int l=0;l<colCount;l++)
    		{
    			data[i][l]=rs.getString(l+1);
    		}
    		i++;
    	}
		
		jt=new JTable(data,col);
		jt.setBounds(0,0,700,700);          
    	JScrollPane sp=new JScrollPane(jt);    
    	add(sp);
		
		st.close();
    	con.close();
		setSize(700,300);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

class ReturnBook extends JFrame
{
	JLabel l,callno,id,warn;
	JTextField cn,i;
	JButton btnReturn,btnBack;
	public ReturnBook()
	{
		l = new JLabel("Return Book");
		l.setBounds(125,25,200,30);
		l.setFont(new Font("Arial",Font.BOLD,20));
		callno = new JLabel("Book Call No:");
		callno.setBounds(25,75,100,25);
		id = new JLabel("Student ID:");
		id.setBounds(25,125,100,25);
		warn = new JLabel("Note: Check the book properly!");
		warn.setBounds(25,260,200,30);
		warn.setForeground(Color.RED);
		
		cn = new JTextField();
		cn.setBounds(175,75,150,25);
		i = new JTextField();
		i.setBounds(175,125,150,25);

		btnReturn = new JButton("Return Book");
		btnReturn.setBounds(120,175,120,35);
		btnBack = new JButton("Back");
		btnBack.setBounds(275,225,75,35);
		
		btnReturn.addActionListener(e -> {
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
	    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Library","root","");
	    		Statement st = con.createStatement();
	    		int abc = st.executeUpdate("update books set quantity=quantity+1, issued=issued-1 where callno='"+cn.getText()+"'");
	    		abc = st.executeUpdate("delete from issuedbooks where studentID="+i.getText());
				System.out.println(abc+" rows affected!");
				st.close();
		    	con.close();
			
				JOptionPane.showMessageDialog(this,"Book Returned Successfully!");
				cn.setText("");
				i.setText("");
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		});
		btnBack.addActionListener(e -> {dispose();});
		
		add(l);add(callno);add(cn);add(id);add(i);add(btnReturn);add(btnBack);add(warn);
		setLayout(null);
		setSize(400,350);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}