import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainGUI extends JFrame{
	

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	
	CasePicker C = new CasePicker();
	
	ButtonGroup group;
	
	int[][] result;
	int status = 0;
	
	int n,r;
	
	JRadioButton rdbtnNpr;
	JRadioButton rdbtnNcr;
	JRadioButton rdbtnNr;
	JRadioButton rdbtnNhr;
	
	JList list;
	private JScrollPane scrollPane;
	private JLabel label;
	
	Vector<String> x = new Vector<>();
	private JButton btnClear;
	
	MainGUI(String title){
		super(title);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
		init();
		super.setSize(330, 445);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - frm.getWidth() / 2);
		super.setLocation(xpos, ypos);
		super.setVisible(true);	
	}
	
	private void init(){
		getContentPane().setLayout(null);
		
		JLabel lblSelectType = new JLabel("Select type :");
		lblSelectType.setBounds(12, 10, 81, 15);
		getContentPane().add(lblSelectType);
		
		rdbtnNpr = new JRadioButton("nPr");
		rdbtnNpr.setSelected(true);
		rdbtnNpr.setBounds(101, 6, 50, 23);
		getContentPane().add(rdbtnNpr);
		
		rdbtnNcr = new JRadioButton("nCr");
		rdbtnNcr.setBounds(155, 6, 50, 23);
		getContentPane().add(rdbtnNcr);
		
		rdbtnNr = new JRadioButton("n\u220Fr");
		rdbtnNr.setBounds(209, 6, 50, 23);
		getContentPane().add(rdbtnNr);
		
		rdbtnNhr = new JRadioButton("nHr");
		rdbtnNhr.setBounds(263, 6, 50, 23);
		getContentPane().add(rdbtnNhr);
		
		group = new ButtonGroup();
		group.add(rdbtnNpr);
		group.add(rdbtnNcr);
		group.add(rdbtnNr);
		group.add(rdbtnNhr);
		
		JLabel lblN = new JLabel("n :");
		lblN.setBounds(10, 50, 25, 20);
		getContentPane().add(lblN);
		
		textField = new JTextField();
		textField.setBounds(30, 50, 60, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblR = new JLabel("r : ");
		lblR.setBounds(101, 50, 25, 20);
		getContentPane().add(lblR);
		
		textField_1 = new JTextField();
		textField_1.setBounds(120, 50, 60, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		JButton btnExecute = new JButton("Execute");
		btnExecute.setBounds(192, 49, 111, 23);
		getContentPane().add(btnExecute);
		btnExecute.addActionListener(new btnClickExecute());
		
		JButton btnExportTxt = new JButton("Export txt ..");
		btnExportTxt.setBounds(12, 80, 146, 23);
		getContentPane().add(btnExportTxt);
		btnExportTxt.addActionListener(new btnClickExportTxt());
		list = new JList();
		//list.setBounds(326, 91, 310, 358);
		
		getContentPane().add(list);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(12, 134, 291, 264);
		getContentPane().add(scrollPane);
		
		label = new JLabel("Recommend Number of n : ~30");
		label.setBounds(12, 113, 291, 15);
		getContentPane().add(label);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(162, 80, 141, 23);
		getContentPane().add(btnClear);
		btnClear.addActionListener(new btnClickClear());
		
	}
	
	
	class btnClickExecute implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Thread t = new Thread(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(textField.getText().equals("")){
						JOptionPane.showMessageDialog(getContentPane(),"Input n.");
						return;
					}else if(textField_1.getText().equals("")){				
							JOptionPane.showMessageDialog(getContentPane(),"Input r.");
							return;				
					}
					n = Integer.parseInt(textField.getText());
					r = Integer.parseInt(textField_1.getText());
					
					if(n<r){
						JOptionPane.showMessageDialog(getContentPane(),"n <= r, Insert value again!");				
						return;
					}
					
					if(rdbtnNpr.isSelected()){
						status = 1;
						result = C.Permutation(n,r);
						label.setText(n + "P" + r+" = "+C.PermutationNumber(n,r));
					}
					else if(rdbtnNcr.isSelected()){
						status =2;
						result = C.Combination(n,r);
						label.setText(n + "C" + r+" = "+C.CombinationNumber(n,r));
					}
					else if(rdbtnNr.isSelected()){
						status =3;
						result = C.DuplicatedPermutation(n,r);
						label.setText(n + "PI" + r+" = "+C.DuplicatedPermutationNumber(n,r));
					}
					else{
						status =4;
						result = C.DuplicatedCombination(n,r);
						label.setText(n + "H" + r+" = "+C.DuplicatedCombinationNumber(n,r));
					}
					
					x = new Vector<>();
					
					for(int i=0;i<result.length;i++){
						String temp = new String("");
						for(int j=0;j<result[i].length;j++)
							temp = temp + result[i][j] + " ";
						
						x.add(temp);
					}
					
					list.setListData(x);
				}
				
			});
			
			t.start();
			
		}
		
	}
	
	class btnClickExportTxt implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(x.size() == 0){
				JOptionPane.showMessageDialog(getContentPane(),"Empty list");
				return;
			}
			
			String Filename = label.getText();	
			
			Filename +=".txt";
			try {
			
			File file = new File(Filename);			
			FileWriter fw = new FileWriter(file,false);
			String temp;
			for(int i=0;i<result.length;i++){
				temp = new String("");
				for(int j=0;j<result[i].length;j++){
					temp+= result[i][j];
					if(j!=result[i].length-1)
					temp+= " ";
				}				
				fw.write(temp);
				if(i!=result.length-1)
					fw.write(System.getProperty( "line.separator" ));
			}
			
			fw.flush();
			fw.close();
			
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	class btnClickClear implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			x.clear();
			getContentPane().repaint();
			label.setText("Recommend Number of n : ~30");
		}
		
	}
}
