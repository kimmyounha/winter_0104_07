package thread.race;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RaceHorseFrame  extends JFrame {
	JLabel[] horses = new JLabel[5];
	HorseThread[] hts = new HorseThread[horses.length];
	int[] winnerIndex = new int[horses.length];
	int index;
	String[] comboStr = {"1번: Shining White", "2번: Black and White", "3번: Scream Tiger", "4번: Blue Hipo", "5번: Kind Elephant"};
	JComboBox<String> combo = new JComboBox<String>(comboStr);
	int betingIndex;
	
	public RaceHorseFrame() {		
		JPanel pan = new JPanel(null);
		ImageIcon icon = null;
		JLabel lineLbl = new JLabel(new ImageIcon("images/line.png"));
		lineLbl.setBounds(540, 27, 5, 420);
		JLabel flagLbl = new JLabel(new ImageIcon("images/flag.png"));
		flagLbl.setBounds(530, 5, 20, 27);
		pan.add(lineLbl);
		pan.add(flagLbl);
		
		JPanel panN = new JPanel();
		
		JButton btnBeting = new JButton("게임배팅");
		JButton btnStart = new JButton("게임시작");
		btnBeting.addActionListener(btnL);
		btnStart.addActionListener(btnL);
		panN.add(combo);
		panN.add(btnBeting);
		panN.add(btnStart);
		
		for (int i = 0; i < horses.length; i++) {
			icon = new ImageIcon("images/small_horse"+(i+1)+".jpg");
			horses[i] = new JLabel(icon);
			horses[i].setLocation(0, 50 + i*85);
			horses[i].setSize(60, 40);
			pan.add(horses[i]);
		}
		
		
		add(pan, "Center");
		add(panN, "North");
		setTitle("경주마게임");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50, 30, 620, 520);
		setVisible(true);
		setResizable(false);		
	}

	public static void main(String[] args) {
		new RaceHorseFrame();
	}
	
	ActionListener btnL = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "게임배팅":
				betingIndex = combo.getSelectedIndex();
				break;
			case "게임시작":
				for (int i = 0; i < horses.length; i++) {
					HorseThread t = new HorseThread(horses[i],"stop_horse"+(i+1), i);
					t.start();
				}
				break;
			}
			
		}
	};

	public class HorseThread extends Thread{
		JLabel lblHorse;
		String stopImageName;
		int randomValue;
		int horseIndex;
		
		public HorseThread(JLabel lblHorse, String stopImageName, int horseIndex) {
			this.lblHorse = lblHorse;
			this.stopImageName = stopImageName;
			this.horseIndex = horseIndex;
		}

		@Override
		public void run() {
			while (true) {
				lblHorse.setLocation(lblHorse.getX()+5, lblHorse.getY());
				if(lblHorse.getX()==540) {
					lblHorse.setIcon(new ImageIcon("images/"+stopImageName+".jpg"));
					winnerIndex[index++] = horseIndex;
					if(index == horses.length-1) {
						JOptionPane.showMessageDialog(RaceHorseFrame.this, (winnerIndex[0]+1)+"이동욱이 우승!!!");
						if(winnerIndex[0]==betingIndex)
							JOptionPane.showMessageDialog(RaceHorseFrame.this, "축하합니다. 배팅에 성공하였습니다.");
						else
							JOptionPane.showMessageDialog(RaceHorseFrame.this, "다음에 다시 배팅 부탁드려요~. 배팅에 실패하였습니다.");
						index = 0;
						for (int i = 0; i < horses.length; i++) {
							horses[i].setLocation(0, horses[i].getY());
							horses[i].setIcon(new ImageIcon("images/small_horse"+(i+1)+".jpg"));
						}
						
					}
					break;
				}
				try {
					Random random = new Random();
					randomValue = random.nextInt(10);
					sleep(10 * randomValue);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}			
		}
	}
}