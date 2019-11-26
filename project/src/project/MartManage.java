package project;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MartManage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfNum;
	private JTextField tfPlace;
	private JTextField tfName;
	private JTextField tfCompany;
	private JTextField tfDate;
	private JTextField tfPrice;
	private JTextField tfAmount;
	private JTextField tfSearch;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private MartDAO dao;
	private Vector<String> col;
	private DefaultTableModel model;
	private MartDTO dto;
	private JButton btnSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MartManage frame = new MartManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MartManage() {
		setTitle("재고관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 570);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 301, 450, 221);
		contentPane.add(scrollPane);
		
		dao=new MartDAO();
		col=new Vector<String>();
		col.add("제품번호");
		col.add("지점");
		col.add("제품명");
		col.add("제조회사");
		col.add("입고일자");
		col.add("가격");
		col.add("수량");
		col.add("총금액");
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfNum.setEditable(true);
				tfNum.setText(table.getValueAt(idx, 0)+"");
				tfPlace.setText(table.getValueAt(idx, 1)+"");
				tfName.setText(table.getValueAt(idx, 2)+"");
				tfCompany.setText(table.getValueAt(idx, 3)+"");
				tfDate.setText(table.getValueAt(idx, 4)+"");
				tfPrice.setText(table.getValueAt(idx, 5)+"");
				tfAmount.setText(table.getValueAt(idx, 6)+"");
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNum = new JLabel("제품번호");
		lblNum.setBounds(12, 10, 57, 15);
		contentPane.add(lblNum);
		
		JLabel lblPlace = new JLabel("지점");
		lblPlace.setBounds(12, 35, 57, 15);
		contentPane.add(lblPlace);
		
		JLabel lblName = new JLabel("제품명");
		lblName.setBounds(12, 60, 57, 15);
		contentPane.add(lblName);
		
		JLabel lblCompany = new JLabel("제조회사");
		lblCompany.setBounds(12, 85, 57, 15);
		contentPane.add(lblCompany);
		
		JLabel lblDate = new JLabel("입고일자");
		lblDate.setBounds(12, 110, 57, 15);
		contentPane.add(lblDate);
		
		JLabel lblPrice = new JLabel("가격");
		lblPrice.setBounds(12, 135, 57, 15);
		contentPane.add(lblPrice);
		
		JLabel lblAmount = new JLabel("수량");
		lblAmount.setBounds(12, 160, 57, 15);
		contentPane.add(lblAmount);
		
		tfNum = new JTextField();
		tfNum.setBounds(81, 7, 116, 21);
		contentPane.add(tfNum);
		tfNum.setColumns(10);
		
		tfPlace = new JTextField();
		tfPlace.setBounds(81, 32, 116, 21);
		contentPane.add(tfPlace);
		tfPlace.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(81, 57, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfCompany = new JTextField();
		tfCompany.setBounds(81, 82, 116, 21);
		contentPane.add(tfCompany);
		tfCompany.setColumns(10);
		
		tfDate = new JTextField();
		tfDate.setBounds(81, 107, 116, 21);
		contentPane.add(tfDate);
		tfDate.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(81, 132, 116, 21);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		tfAmount = new JTextField();
		tfAmount.setBounds(81, 157, 116, 21);
		contentPane.add(tfAmount);
		tfAmount.setColumns(10);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.insertMart(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(MartManage.this, "추가했습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnAdd.setBounds(12, 223, 97, 23);
		contentPane.add(btnAdd);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.updateMart(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(MartManage.this, "수정되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnUpdate.setBounds(135, 223, 97, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=(tfNum.getText());
				int result=0;
				int response=JOptionPane.showConfirmDialog(MartManage.this, "삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteMart(num);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(MartManage.this, "삭제되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnDelete.setBounds(256, 223, 97, 23);
		contentPane.add(btnDelete);
		
		JLabel numSearch = new JLabel("제품번호 : ");
		numSearch.setBounds(12, 276, 71, 15);
		contentPane.add(numSearch);
		
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		
		tfSearch.setBounds(81, 273, 116, 21);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(230, 268, 97, 23);
		contentPane.add(btnSearch);
		
		JButton btnClean = new JButton("초기화");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfNum.setEditable(true);
				clear();
			}
		});
		btnClean.setBounds(365, 223, 97, 23);
		contentPane.add(btnClean);
		refreshTable();
	}
	public void search() {
		String num=tfNum.getText();
		model=new DefaultTableModel(dao.searchMart(num),col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}
	public void input() {
		String num=tfNum.getText();
		String name=tfName.getText();
		String date=tfDate.getText();
		String company=tfCompany.getText();
		String place=tfPlace.getText();
		int price=Integer.parseInt(tfPrice.getText());
		int amount=Integer.parseInt(tfAmount.getText());
		int money=price*amount;
		
		dto=new MartDTO(num,place,name,company,date,price,amount,money);
	}
	
	public void clear() {
		tfNum.setText("");
		tfPlace.setText("");
		tfName.setText("");
		tfCompany.setText("");
		tfDate.setText("");
		tfPrice.setText("");
		tfAmount.setText("");
	}
	public void refreshTable() {
		DefaultTableModel model=new DefaultTableModel(dao.listMart(),col);
		table.setModel(model);
	}
	public void list() {
		model=new DefaultTableModel(dao.listMart(),col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}
}
