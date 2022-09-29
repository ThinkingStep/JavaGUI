package jtable;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Sign{
	
	Sign(){
		init();
	}

	public void init() {
		JFrame jframe = new JFrame();
		jframe.setTitle("서명");
		jframe.setBounds(590,100,300,330);
		JPanel jpanel = new JPanel();
		jpanel.setLayout(null);
		Font f1 = new Font("바탕", Font.BOLD, 15);
		JTextArea ta = new JTextArea();
		ta.setFont(f1);
		ta.setText("전화번호 입력 및 엔터 후\n이름을 입력해주세요.");
		ta.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ta.setText("");
			}
		});
		ta.setBounds(25,25,235,100);
		jpanel.add(ta);
		JButton btn = new JButton("서명(이름)");
		btn.setBounds(77, 180, 120, 80);
		jpanel.add(btn);
		
		jframe.add(jpanel);
		jframe.setVisible(true);
		
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DAO dao = new DAO();
				String str = ta.getText();
				String[] lines = str.split("\\r?\\n");
				List<People> arrayList = new ArrayList<>();
				boolean agree = true;
				String address = null;
				String tel = lines[0];
				String sign = lines[1];
				arrayList = dao.selectPeopleByTel(tel);
				for (People people : arrayList) {
					agree = people.isAgree();
					address = people.getAddress();
				}
				System.out.println(agree);
				System.out.println(address);
				int result = dao.updateSign(new People(agree,address,sign,tel));
				if (result==0 || !Validation.validTel(tel)) {
					ta.setText("");
					ta.append("서명 실패\n전화번호(-제외 숫자번호만) 입력 후\n 엔터하고 이름을 입력해주세요");
				}else {
					ta.setText("");
					ta.append("즐거운 하루 되세요");
				}
			}
		});
		
		
	}
	
}
