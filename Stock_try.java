/*
@Description: This is the main java file. Input is text file which have stock values crawled over a period of time for different companies. 
The calculations for predicting buy/sell/hold  are done using 3 methods Bollinger Bands, MACD and Auto Regression. 
Output is in the form of an applet or web page.
@Author: 	Shweta Medhekar
@Created: Aug 2007
*/

package Stock1;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.security.*;

class MyWindowAdapter1 extends WindowAdapter
{
	SampleFrame sampleframe;
	public MyWindowAdapter1(SampleFrame sampleframe)
	{
		this.sampleframe = sampleframe;
	}
	public void windowClosing(WindowEvent we)
	{
		sampleframe.setVisible(false);
	}
}

class SampleFrame extends Frame
{
	SampleFrame(String title)
	{
		super(title);
		MyWindowAdapter1 adapter = new MyWindowAdapter1(this);
		addWindowListener(adapter);
		 
		int siz;
		siz = Stock_try.try_size;
				
		Graph2D graph;
	    DataSet data1;
	    Axis    xaxis;
	    Axis    yaxis_left;
	    int k = 1;
			
	    graph = new Graph2D();
        graph.drawzero = false;
        graph.drawgrid = false;
        graph.borderTop = 50;
		
        int i;
        int j;
        double data_macd[] = new double[2*(siz-1)];
        double data_temp[] = new double[2*(siz-9)];
        double data_ar[] = new double[2*(siz+1)];
        
		setLayout( new BorderLayout() );
        add("Center", graph);
        
        if(title == "BOLLINGER")
        { 
        	k=1;
        	//-----------------------upper band----------------------------//
        	for (i=0, j=0; i<(siz-9); i++, j=j+2)
        	{
        		data_temp[j+1] = Stock_try.rcv[0][i];//y co-ordinate
        		data_temp[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_temp,(siz-9));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(0,0,255);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(0,0,255);
        	data1.legend(100,60,"UPPER BAND");
        	data1.legendColor(Color.black);

//**      Attach both data sets to the Xaxis
        	xaxis = graph.createAxis(Axis.BOTTOM);
        	xaxis.attachDataSet(data1);
        	xaxis.setTitleText("Days");
        	xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        	xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        	xaxis.setTitleColor( new Color(120,100,255) );
        	
//**      Attach the first data set to the Left Axis
        	yaxis_left = graph.createAxis(Axis.LEFT);
        	yaxis_left.attachDataSet(data1);
        	yaxis_left.setTitleText("Stock Price");
        	yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        	yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        	yaxis_left.setTitleColor( new Color(120,100,255) );
        	
//        	-----------------------middle band----------------------------//
        	k=1;
        	for (i=0, j=0; i<(siz-9); i++, j=j+2)
        	{
        		data_temp[j+1] = Stock_try.rcv[1][i];//y co-ordinate
        		data_temp[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_temp,(siz-9));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(255,0,0);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(255,0,0);
        	data1.legend(100,80,"MIDDLE BAND");
        	data1.legendColor(Color.black);
        	
        	xaxis.attachDataSet(data1);  	
        	yaxis_left.attachDataSet(data1);
        	
//        	-----------------------lower band----------------------------//
        	k=1;
        	for (i=0, j=0; i<(siz-9); i++, j=j+2)
        	{
        		data_temp[j+1] = Stock_try.rcv[2][i];//y co-ordinate
        		data_temp[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_temp,(siz-9));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(0,255,0);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(0,255,0);
        	data1.legend(100,100,"LOWER BAND");
        	data1.legendColor(Color.black);
        	
        	xaxis.attachDataSet(data1);  	
        	yaxis_left.attachDataSet(data1);
        }
        
        else if(title=="MOVING_AVERAGE")
        { 
        	k=1;
        	//-----------------------26----------------------------//
        	for (i=0, j=0; i<(siz-1); i++, j=j+2)
        	{
        		data_macd[j+1] = Stock_try.rcv1[0][i];//y co-ordinate
        		data_macd[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_macd,(siz-1));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(0,0,255);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(0,0,255);
        	data1.legend(100,60,"EMA(26)");
        	data1.legendColor(Color.black);

//**      Attach both data sets to the Xaxis
        	xaxis = graph.createAxis(Axis.BOTTOM);
        	xaxis.attachDataSet(data1);
        	xaxis.setTitleText("Days");
        	xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        	xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        	xaxis.setTitleColor( new Color(120,100,255) );
        	
//**      Attach the first data set to the Left Axis
        	yaxis_left = graph.createAxis(Axis.LEFT);
        	yaxis_left.attachDataSet(data1);
        	yaxis_left.setTitleText("Stock Price");
        	yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        	yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        	yaxis_left.setTitleColor( new Color(120,100,255) );
        	
//        	-----------------------12----------------------------//
        	k=1;
        	for (i=0, j=0; i<(siz-1); i++, j=j+2)
        	{
        		data_macd[j+1] = Stock_try.rcv1[1][i];//y co-ordinate
        		data_macd[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_macd,(siz-1));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(255,0,0);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(255,0,0);
        	data1.legend(100,80,"EMA(12)");
        	data1.legendColor(Color.black);
        	
        	xaxis.attachDataSet(data1);  	
        	yaxis_left.attachDataSet(data1);
        	
//        	-----------------------SIGNAL----------------------------//
        	k=1;
        	for (i=0, j=0; i<(siz-1); i++, j=j+2)
        	{
        		data_macd[j+1] = Stock_try.rcv1[2][i];//y co-ordinate
        		data_macd[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_macd,(siz-1));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(0,255,0);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(0,255,0);
        	data1.legend(100,100,"SIGNAL");
        	data1.legendColor(Color.black);
        	
        	xaxis.attachDataSet(data1);  	
        	yaxis_left.attachDataSet(data1);
        	
//        	-----------------------macd----------------------------//
        	k=1;
        	for (i=0, j=0; i<(siz-1); i++, j=j+2)
        	{
        		data_macd[j+1] = Stock_try.rcv1[3][i];//y co-ordinate
        		data_macd[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_macd,(siz-1));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(0,0,0);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(0,0,0);
        	data1.legend(100,120,"MACD");
        	data1.legendColor(Color.black);
        	
        	xaxis.attachDataSet(data1);  	
        	yaxis_left.attachDataSet(data1);
        }
        
        else if(title=="AUTO_REGRESSION")
        { 
        	k=1;
        	//-----------------------ACTUAL----------------------------//
        	for (i=0, j=0; i<(siz+1); i++, j=j+2)
        	{
        		data_ar[j+1] = Stock_try.rcv2[0][i];//y co-ordinate
        		data_ar[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_ar,(siz+1));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(0,0,255);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(0,0,255);
        	data1.legend(100,60,"ACTUAL");
        	data1.legendColor(Color.black);

//**      Attach both data sets to the Xaxis
        	xaxis = graph.createAxis(Axis.BOTTOM);
        	xaxis.attachDataSet(data1);
        	xaxis.setTitleText("Days");
        	xaxis.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        	xaxis.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        	xaxis.setTitleColor( new Color(120,100,255) );
        	
//**      Attach the first data set to the Left Axis
        	yaxis_left = graph.createAxis(Axis.LEFT);
        	yaxis_left.attachDataSet(data1);
        	yaxis_left.setTitleText("Stock Price");
        	yaxis_left.setTitleFont(new Font("TimesRoman",Font.PLAIN,20));
        	yaxis_left.setLabelFont(new Font("Helvetica",Font.PLAIN,15));
        	yaxis_left.setTitleColor( new Color(120,100,255) );
        	
//        	-----------------------ESTIMATE----------------------------//
        	k=1;
        	for (i=0, j=0; i<(siz+1); i++, j=j+2)
        	{
        		data_ar[j+1] = Stock_try.rcv2[1][i];//y co-ordinate
        		data_ar[j] = k;//x co-ordinate
        		k=k+1;
        	}

        	data1 = graph.loadDataSet(data_ar,(siz+1));
        	data1.linestyle = 2;
        	data1.linecolor   =  new Color(255,0,0);
        	data1.marker    = 1;
        	data1.markerscale = 1.5;
        	data1.markercolor = new Color(255,0,0);
        	data1.legend(100,80,"ESTIMATE");
        	data1.legendColor(Color.black);
        	
        	xaxis.attachDataSet(data1);  	
        	yaxis_left.attachDataSet(data1);
       }
	}
	
	public void paint(Graphics g)
	{
		g.drawString("This is in Frame Window", 1, 1);
	}
}

public class Stock_try extends Applet implements ActionListener, ItemListener
{
	public Stock_try() 
	{
		super();
	}	
	
	Choice combo;
	Label company;
	Frame f;
	String msg_b = "";
	String msg_m = "";
	String msg_a = "";
	String msg1 = "";
	String msg2 = "";
	String msg3 = "";
	String str = "";
	Button BOLLINGER, MOVING_AVERAGE, AUTO_REGRESSION;
	//, BLANK;
	
	public static double [][] rcv = new double [3][];
	public static double [][] rcv1 = new double [4][];
	public static double [][] rcv2 = new double [2][];
	public static String select = null;
	public static int try_size = 0;
	
	public void init() //init is main for applet
	{		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints con = new GridBagConstraints();
		setFont(new Font("Courier",Font.BOLD,14));
		setLayout(gridbag);
		con.fill = GridBagConstraints.HORIZONTAL;
		
		company = new Label("COMPANY");
		con.gridx = 0;
		con.gridy = 0;
		gridbag.setConstraints(company, con);
		add(company);
		con.weighty = 0;
		con.gridwidth = GridBagConstraints.REMAINDER;
		combo = new Choice();
		combo.add("Please Select a Company...");
		combo.add("Shell");
		combo.add("Exxon");
		combo.add("Toyota");
		combo.add("Ford");
		combo.add("General Motors");
		combo.add("HaliBurton");
		combo.add("Boeing");	
		
		Color col=new Color(255, 255, 204);
		setBackground(col);
		
		con.gridx = 1;
		con.gridy = 0;
		gridbag.setConstraints(combo, con);
		add(combo);
		combo.addItemListener(this);
		
		con.weighty = 1.0;
		con.weightx = 2.0;
		con.fill = GridBagConstraints.NONE;
		con.gridheight=1;
		BOLLINGER = new Button("BOLLINGER");
		con.anchor = GridBagConstraints.CENTER;
		con.gridy = 300;
		gridbag.setConstraints(BOLLINGER,con);
		add(BOLLINGER);
		MOVING_AVERAGE = new Button("MOVING_AVERAGE");
		con.anchor = GridBagConstraints.CENTER;
		con.gridy = 310;
		gridbag.setConstraints(MOVING_AVERAGE,con);
		add(MOVING_AVERAGE);
		AUTO_REGRESSION = new Button("AUTO_REGRESSION");
		con.anchor = GridBagConstraints.CENTER;
		con.gridy = 320;
		gridbag.setConstraints(AUTO_REGRESSION,con);
		add(AUTO_REGRESSION);

		BOLLINGER.addActionListener(this);
		MOVING_AVERAGE.addActionListener(this);
		AUTO_REGRESSION.addActionListener(this);
		
		setSize(900,300);
	}
	
	public void itemStateChanged( ItemEvent event )
	{
		if( event.getSource() == combo && event.getStateChange() == ItemEvent.SELECTED )
		{
			System.out.println( "Change:" + combo.getSelectedItem() );
			select = combo.getSelectedItem();
		}
	}

	public void actionPerformed( ActionEvent ae)
	{
		int i, j;
		double macd_p = 0;
		double sign = 0;
		double boll_p = 0;
		double ar_p = 0;
		double val, tmp_max, tmp_min, tmp_diff;
		double news = 5.0;
						
		FileRead t = new FileRead();   //calling function to read file
		Vector<Float> a1=new Vector<Float>(70,7);
		a1=t.readMyFile();
		System.out.println( " " + a1);//contents of file
		System.out.print("\n");
		System.out.println(a1.size()/3);
		
		Stock_try.try_size = (a1.size()/3);
		
		Float anArray[] = (Float [])a1.toArray(new Float[0]);
		double close [] = new double [a1.size()/3];
		double percent_b[] = new double [10];
		double bw = 50;
		
		for (i=2, j=0; i<a1.size(); i=i+3, j++)
		{
			close[j] = (double)(anArray[i].floatValue());
		}
		
		//public static double [][] rcv = new double [(a1.size()/3)-9][3];
		Bollinger b = new Bollinger(); //calling function to calculate the estimated Bollinger value with a 10 day window
		Stock_try.rcv = b.CalcBollinger(a1);
		/*System.out.print("\n");
		for(i=0;i<(a1.size()/3)-9;i++)
		{
			for(j=0;j<3;j++)			
				System.out.print(Stock_try.rcv[j][i] + "\t\t");
			System.out.print("\n");
		}*/
		
		bw = ((Stock_try.rcv[0][(a1.size()/3) - 10] - Stock_try.rcv[2][(a1.size()/3) - 10]) / Stock_try.rcv[1][(a1.size()/3) - 10]) * 100;
		System.out.println("\n bw=" + bw);
		
		if (bw<10)
		{
			//calc -b
			boll_p = 0;
			j = a1.size()/3;
			for (i=9; i>=0; i--)
			{
				percent_b[i] = ((close[j-1] - Stock_try.rcv[2][j - 10]) / (Stock_try.rcv[0][j - 10] - Stock_try.rcv[2][j - 10])) * 100;
				j--;
				boll_p = boll_p + percent_b[i];
			}
			boll_p = boll_p / 10;
			
			if (boll_p >= 70)
			{
				msg1 = "BUY";
			}
			else if (boll_p <= 30)
			{
				msg1 = "SELL";
			}
			else
			{
				msg1 = "DO NOTHING";
			}			
		}
		else
		{
			msg1 = "DO NOTHING";
		}
				
		//double [][] rcv1 = new double [(a1.size()/3)-1][3];
		MACD m = new MACD();//calling function to calculate MACD
		Stock_try.rcv1 = m.CalcMACD(a1);
		
		tmp_max = Stock_try.rcv1[2][0];
		tmp_min = Stock_try.rcv1[2][0];
		for(i=0;i<((a1.size()/3)-2);i++)
		{
			if(Stock_try.rcv1[2][i]<Stock_try.rcv1[2][i+1])
			{
				if(tmp_max<Stock_try.rcv1[2][i+1])
				{
					tmp_max = Stock_try.rcv1[2][i+1];
				}
			}
			else
			{
				if(tmp_min>Stock_try.rcv1[2][i+1])
				{
					tmp_min = Stock_try.rcv1[2][i+1];
				}
			}
		}
		
		tmp_diff = (tmp_max - tmp_min);
		if (tmp_diff<0)
		{
			tmp_diff = tmp_diff * (-1);
		}
				
		macd_p = Stock_try.rcv1[2][(a1.size()/3)-2] - Stock_try.rcv1[3][(a1.size()/3)-2];
		sign = macd_p;
		if (macd_p<0)
		{
			macd_p = macd_p * (-1);
		}
		macd_p = (macd_p / tmp_diff) * 100;
				
		if (sign>0 && macd_p>2)
			msg2 = "SELL";
		else if (sign<0 && macd_p>2)
			msg2 = "BUY";	
		else
			msg2 = "DO NOTHING";
		
		/*System.out.print("\n");
		for(i=0;i<(a1.size()/3)-1;i++)
		{
			for(j=0;j<3;j++)			
				System.out.print(Stock_try.rcv1[j][i] + "\t\t");
			System.out.print("\n");
		}*/

		//double [][] rcv2 = new double [(a1.size()/3) + 1][2];
		AR r = new AR();
		Stock_try.rcv2 = r.CalcAR(a1);	
		
		Vector<Float> a_news=new Vector<Float>(60,1);
		a_news=t.readNews();
		
		Float anArray_news[] = (Float [])a_news.toArray(new Float[0]);
		news = (double)(anArray_news[a_news.size()-1].floatValue());
				
		/*System.out.print("\n");
		for(i=0;i<(a1.size()/3)+1;i++)
		{
			System.out.print(Stock_try.rcv2[0][i] + "\t");
		}
		System.out.print("\n");
		for(i=0;i<(a1.size()/3)+1;i++)
		{
			System.out.print(Stock_try.rcv2[1][i] + "\t");
		}*/
		val = Stock_try.rcv2[1][a1.size()/3] - Stock_try.rcv2[0][a1.size()/3 - 1];
		System.out.print("\nval=" + val + "\n");
		if (val>0)
		{
			val = 10;
		}
		else if (val<0)
		{
			val = 1;
		}
		else
		{
			val = 5;
		}
		
		news = news+1;
		System.out.print("\nnews=" + news + "\n");
		ar_p = (val * 0.8) + (news * 0.2);
		if (ar_p>5)
			msg3 = "BUY";
		else if (ar_p<5)
			msg3 = "SELL";	
		else
			msg3 = "DO NOTHING";
		
		str = ae.getActionCommand();
		if(str.equals("BOLLINGER"))
		{
			f=new SampleFrame("BOLLINGER");
			f.setSize(500,500);
			f.setVisible(true);
			msg_b = "You Selected Bollinger Bands";
			msg_b = msg_b.concat(" -- ").concat(msg1);
		}
				
		else if(str.equals("MOVING_AVERAGE"))
		{
			f=new SampleFrame("MOVING_AVERAGE");
			f.setSize(500,500);
			f.setVisible(true);
			msg_m = "You Selected Moving Average Convergence Divergence";
			msg_m = msg_m.concat(" -- ").concat(msg2);
		}
		
		else if(str.equals("AUTO_REGRESSION"))
	    {
			f=new SampleFrame("AUTO_REGRESSION");
			f.setSize(500,500);
			f.setVisible(true);
			msg_a = "You Selected Auto Regression";
			msg_a = msg_a.concat(" -- ").concat(msg3);
		}
		repaint();
	}
		
	public void paint(Graphics g)
	{
		if(str.equals("BOLLINGER"))
		{
			g.drawString(msg_b, 330 , 100);
		}
		else if(str.equals("MOVING_AVERAGE"))
		{
			g.drawString(msg_m, 250 , 200);
		}
		else if(str.equals("AUTO_REGRESSION"))
		{
			g.drawString(msg_a, 350 , 280);
		}
	}
}//End of class Stock_try

class FileRead 
{ 
	public Vector<Float> a=new Vector<Float>(70,7);
	public Vector<Float> a_news=new Vector<Float>(60,1);
	
	Vector readMyFile()//reading file now 
	{  
		String record = null;
		String[] temp = null;
		String pre = new String ("W:/java workspace/Stock_try/");
		//String pre = new String ("/empyrean/home/u1/madhur/public_html/");
		String name = Stock_try.select;
		String ext = new String (".txt");
		//name = name.concat(ext);
		pre = pre.concat(name).concat(ext);
				
		try 
		{ 
			try
			{
				FilePermission perm = new FilePermission(pre, "read");
				//FilePermission perm = new FilePermission(name, "read");
			    AccessController.checkPermission(perm);
			    FileReader fr     = new FileReader(pre);
			    //FileReader fr     = new FileReader(name);
				BufferedReader br = new BufferedReader(fr);
				record=new String();
        	
				while ( (record=br.readLine()) != null)
				{
					temp=record.split(",");
					for(int i=0;i<temp.length;i++)
					{
						Float anArray = Float.valueOf(temp[i]);
						a.addElement(anArray);
          	  		}
				}
			}
			catch (FileNotFoundException f)
			{
				System.out.println("Error during file open operation: FileNotFoundException error!");
				f.printStackTrace();	
			}
		} 
		catch (IOException e) 
		{ 
			// catch posible io errors from readLine()
			System.out.println("Uh oh, got an IOException error!");
			e.printStackTrace();
		}
		return a;
	} // end of readMyFile()
	
	Vector readNews() //reading file now
	{  
		String record = null;
		String[] temp = null;
		try 
		{ 
			try
			{
				//FilePermission perm1 = new FilePermission("C:/Stock_try/news.txt", "read");
				FilePermission perm1 = new FilePermission("W:/java workspace/Stock_try/news.txt", "read");
				//FilePermission perm1 = new FilePermission("/empyrean/home/u1/madhur/public_html/news.txt", "read");
				AccessController.checkPermission(perm1);
				//FileReader fr     = new FileReader("C:/Stock_try/news.txt");
				FileReader fr     = new FileReader("W:/java workspace/Stock_try/news.txt");
				//FileReader fr     = new FileReader("/empyrean/home/u1/madhur/public_html/news.txt");
				BufferedReader br = new BufferedReader(fr);
				record=new String();
        	
				while ( (record=br.readLine()) != null)
				{
					temp=record.split(",");
					for(int i=0;i<temp.length;i++)
					{
						Float anArray = Float.valueOf(temp[i]);
						a_news.addElement(anArray);
          	  		}
				}
			}
			catch (FileNotFoundException f)
			{
				System.out.println("Error during file open operation: FileNotFoundException error!");
				f.printStackTrace();	
			}
		} 
		catch (IOException e) 
		{ 
			// catch posible io errors from readLine()
			System.out.println("Uh oh, got an IOException error!");
			e.printStackTrace();
		}
		return a_news;
	} // end of readNews()
}//end of class FileRead
		
class Bollinger
{
	double temp;
	double [] AvgValueDay = new double[10];
	
	double [][] CalcBollinger(Vector a1)
	{
		int i, j, k, datapts, cntr, cnt=0;
		double sigma;
		Float anArray[] = (Float [])a1.toArray(new Float[0]);
		datapts=(a1.size()/3)-9;
		double [][] Band = new double[3][datapts];//upper middle lower close
		float arr[]=new float[3];
	
		cntr=0;
		for(k=0;k<datapts;k++)
		{
			temp=0;
			for(j=0;j<10;j++)
			{
				for(i=0;i<3;i++)
				{
					arr[i]=anArray[cnt*3+i].floatValue();
				}
				cnt++;
				AvgValueDay[j]=(double)(arr[0]+arr[1]+arr[2])/3;
				temp=temp+AvgValueDay[j];
			}
			cnt=k+1;
			
			Band[1][k]=temp/10;//Middle band
			sigma=CalcDeviation(Band[1][k]);
			Band[0][k]=Band[1][k]+(2*sigma);//upper band
			Band[2][k]=Band[1][k]-(2*sigma);//lower band
			cntr++;
		}
		return Band;
	}
		
	double CalcDeviation(double mean)
	{
		int i;
		double tmp=0, sig=0;
		
		for(i=0;i<10;i++)
		{
			sig=tmp+Math.pow((double)(AvgValueDay[i]-mean),2);
		}
		sig=Math.sqrt(sig/10);
		
		return sig;
	}//End of CalcBollinger
}//End class Bollinger

class MACD
{
	double [][] CalcMACD(Vector a1)
	{
		int datapts, datapts_26, datapts_12, i, j;
		datapts = (a1.size()/3)-1;
		double [][] MACD_val = new double [datapts][4];//col 1--26 day; col 2--12 day; col 3--signal; col 4--macd
		double [][] MACD_val1 = new double [4][datapts];
		Float anArray[] = (Float [])a1.toArray(new Float[0]);
		double [] macd = new double [datapts];
		
		float alpha_26;
		float alpha_12;
		float alpha_9;
		
		alpha_26 = (float)(2.0/27.0);
		alpha_12 = (float)(2.0/13.0);
		alpha_9 = (float)(2.0/10.0);
		
		MACD_val[0][0] = (double)(anArray[0].floatValue() + anArray[1].floatValue() + anArray[2].floatValue());
		MACD_val[0][1] = MACD_val[0][0];
		macd[0] = MACD_val[0][1] - MACD_val[0][0];
		
		i=5;
		for(j=1;j<datapts;j++)
		{
			MACD_val[j][0] = (double)((anArray[i].floatValue() * alpha_26) + (MACD_val[j-1][0] * (1-alpha_26)));
			MACD_val[j][1] = (double)((anArray[i].floatValue() * alpha_12) + (MACD_val[j-1][1] * (1-alpha_12)));
			macd[j] = MACD_val[j][1] - MACD_val[j][0];
			MACD_val[j][3] = macd[j];
			i = i + 3;
		}
		
		MACD_val[1][2] = (macd[1]*(double)(alpha_9)) + (macd[0]*(double)(1-alpha_9));
		MACD_val[0][2] = MACD_val[1][2];
		for(j=2;j<datapts;j++)
		{
			MACD_val[j][2] = (macd[j]*(double)(alpha_9)) + (MACD_val[j-1][2]*(double)(1-alpha_9)); 
		}
		
		for(i=0; i<datapts; i++)
		{
			for(j=0; j<4; j++)
			{
				MACD_val1[j][i]=MACD_val[i][j];
			}
		}
		
		return MACD_val1;
	}//End of CalcMACD
}//End class MACD

class AR
{
	double [][] CalcAR(Vector a1)
	{
		int datapts, i, j, x, y, k, cnt, p, r, s;
		double temp, lhs, var;
		datapts = a1.size()/3;
		double [][] ar = new double [datapts + 1][2];//0 is actual + 1 for predicted value
		double [][] ar1 = new double [2][datapts + 1];
		double [][] temp_gamma = new double [6][6];
		double [] temp_col = new double [6];
		double [][] phi = new double [6][1];
		Float anArray[] = (Float [])a1.toArray(new Float[0]);
		cnt = 0;
		var = 0;		
		i=0;
		for(j=0; j<(a1.size()/3); j++)
		{
			ar[j][0] = (double)((anArray[i].floatValue() + anArray[i+1].floatValue() + anArray[i+2].floatValue())/3);
			i=i+3;
		}
				
		for(i=0; i<12; i++)
		{
			ar[i][1] = ar[i][0];
		}
		
		for (p=5; p<(datapts-6); p++)
		{
			y=0;
			k=p;
			for(j=p; j>(p-6); j--)
			{
				x=0;
				for(i=j; i<(j+6); i++)
				{
					temp_gamma[x][y] = ar[i][0];
					x++;
				}
				k++;
				temp_col[y] = ar[k][0];
				y++;
			}
		
			Matrix gamma = new Matrix(temp_gamma);
			if(gamma.det()==0)
			{
				for(r=0; r<2 ;r++)
				{
					for(s=0; s<(datapts+1); s++)
					{
						ar1[r][s]=0;
					}
				}
				return ar1;
			}
			
			Matrix col = new Matrix(temp_col, 6);
			Matrix inv = gamma.inverse();
			Matrix ans = inv.times(col);
			phi = ans.getArray();
		
			var = 0;
			temp = 0;
			for (j=5, i=0; i<6; i++, j--)
			{
				temp = temp + (phi[i][0] * temp_col[j]);
			}
			ar[p+7][1] = temp + var;		
		}
		
		for(i=0; i<(datapts+1); i++)
		{
			for(j=0; j<2; j++)
			{
				ar1[j][i]=ar[i][j];
				if (ar1[j][i]<0)
				{
					ar1[j][i] = 0;
				}					
				System.out.print(ar1[j][i] + "\t");
			}
			System.out.print("\n");
		}
		return ar1;
	}//End of CalcAR
}//End class AR