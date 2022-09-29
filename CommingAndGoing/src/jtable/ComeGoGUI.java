package jtable;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ComeGoGUI {
	JFrame jframe = new JFrame();
	JPanel jpanel = new JPanel();
	JTextField tf1 = new JTextField();
	Choice c2 = new Choice();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf5 = new JTextField();
	JTextArea area = new JTextArea();
	JButton btn1, btn2, btn3, btn4, btn5;
	JLabel jl1 = new JLabel("방문날짜 및 시간 : ");
	JLabel jl2 = new JLabel("개인정보 수집 및 동의 : ");
	JLabel jl3 = new JLabel("시군구(거주지) : ");
	JLabel jl4 = new JLabel("전화번호(숫자만) : ");
	JLabel jl5 = new JLabel("서명(나갈 때) : ");
	
	public ComeGoGUI() {
		init();
	}

	public void init() {
		jframe.setBounds(100,100,500,530);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setTitle("출입명부");
		jpanel.setLayout(null);
		jframe.add(jpanel);
		
		tf1.setBounds(150, 38, 150, 20);
		jpanel.add(tf1);
		c2.setBounds(150, 58, 150, 20);
		c2.add("O");
		c2.add("X");
		jpanel.add(c2);
		tf3.setBounds(150, 79, 150, 20);
		jpanel.add(tf3);
		tf4.setBounds(150, 100, 150, 20);
		jpanel.add(tf4);
		tf5.setBounds(150, 120, 150, 20);
		tf5.setText("퇴실 시 서명");
		tf5.setEnabled(false);
		jpanel.add(tf5);
		
		
		jl1.setBounds(20,24,130,40);
		jpanel.add(jl1);
		jl2.setBounds(20,48,130,40);
		jpanel.add(jl2);
		jl3.setBounds(20,68,130,40);
		jpanel.add(jl3);
		jl4.setBounds(20,88,130,40);
		jpanel.add(jl4);
		jl5.setBounds(20,108,130,40);
		jpanel.add(jl5);
		
		JScrollPane jsp = new JScrollPane(area); 
		jsp.setBounds(13, 210, 460, 220);
		jpanel.add(jsp);

		jpanel.add(btn1=new JButton("입력"));
		btn1.setBounds(350, 24, 100, 30);

		jpanel.add(btn2=new JButton("출력"));
		btn2.setBounds(350, 60, 100, 30);

		jpanel.add(btn3=new JButton("수정"));
		btn3.setBounds(350, 96, 100, 30);

		jpanel.add(btn4=new JButton("서명(퇴실시)"));
		btn4.setBounds(350, 132, 100, 30);
	
		jpanel.add(btn5=new JButton("종료"));
		btn5.setBounds(350, 168, 100, 30);
		
		DAO dao = new DAO();

		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				boolean agree;
				if (c2.getSelectedItem().equals("O")) {
					agree = true;
				}else {
					agree = false;
				}
				LocalDateTime visitDate=null;
				String address = tf3.getText();
				String tel = tf4.getText();
				String sign = tf5.getText();
				if (address.isEmpty() || tel.isEmpty() || !Validation.validTel(tel)) {
					area.append("정보를  다  입력하거나  전화번호란에  -제외하고  번호만  입력해주세요");
				}else {
					dao.insertData(new People(visitDate , agree,address,tel,sign));
					area.append("기입 성공\n 출력버튼을 눌러 기입정보를 확인하실 수 있습니다.");
					tf3.setText("");
					tf4.setText("");
					tf5.setText("");
				}
				
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				List<People> arrayList = new ArrayList<>();
				arrayList = dao.selectPeople();
				area.append("방문날짜 및 시간            개인정보 수집 및 동의    시군구(거주지)          전화번호              서명\n");
				for (People people : arrayList) {
					area.append(people.getVisitDate() + "\t" + people.isAgree() + "\t" + people.getAddress() + "\t" + people.getTel() + "     " + people.getSign()+ "\n");
				}
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText(""); 
				boolean agree;
				if (c2.getSelectedItem().equals("O")) {
					agree = true;
				}else {
					agree = false;
				}
				
				String address = tf3.getText();
				String tel = tf4.getText();
				
				
				if (address.isEmpty() || tel.isEmpty() || !Validation.validTel(tel)) {
					area.append("수정 실패\n");
					area.append("방문날짜 및 시간 제외 다시 전부 입력해주세요(번호는숫자만)");
				}else {
					int result = dao.updateData(new People(agree, address, tel));
					System.out.println(result);
					if (result==0) {
						area.append("수정 실패\n");
						area.append("방문날짜 및 시간 제외 다시 전부 입력해주세요(번호는숫자만)");
					}else {
						area.append("수정 성공\n출력하여 수정사항을 체크하실 수 있습니다.");
					}
				}
				
				
				tf3.setText(""); 
				tf4.setText("");
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sign a = new Sign();
			}
		});
		
		
		
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
}
