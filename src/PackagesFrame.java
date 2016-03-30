import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PackagesFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JList<String> list;
	private DefaultListModel<String> listModel;

	private Font font;

	private JTextArea description;
	private JTextArea depends;
	private JTextArea rdepends;

	private JTextArea[] textAreas;

	public PackagesFrame(Vector<String> packages) {
		super("Package Info");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		font = new Font("monospaced", Font.PLAIN, 16);

		listModel = new DefaultListModel<>();
		for (String p : packages) {
			listModel.addElement(p);
		}

		list = new JList<String>(listModel);
		description = new JTextArea("Description");
		depends = new JTextArea("Dependencies");
		rdepends = new JTextArea("Reverse dependencies");

		textAreas = new JTextArea[] { description, depends, rdepends };
		styleTextAreas();

		setLayout(new BorderLayout());

		add(new JScrollPane(list), BorderLayout.WEST);

		JPanel depPanel = new JPanel(new GridLayout(1, 2));
		depPanel.add(new JScrollPane(depends));
		depPanel.add(new JScrollPane(rdepends));

		JSplitPane centerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(description), depPanel);

		add(centerPane, BorderLayout.CENTER);

		pack();
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		centerPane.setResizeWeight(.5d);

		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String p = list.getSelectedValue();

				try {
					description.setText(PackageInfo.info(p));
					depends.setText(PackageInfo.depends(p));
					rdepends.setText(PackageInfo.rdepends(p));
					top();
				} catch (IOException e1) {
					clear();
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}

			}
		});

		list.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// DEL key
				if (e.getKeyCode() == 127) {
					int selectedIndex = list.getSelectedIndex();
					if (selectedIndex != -1) {
						listModel.remove(selectedIndex);
						list.setSelectedIndex(selectedIndex);
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});

		setVisible(true);
	}

	private void styleTextAreas() {
		for (JTextArea ta : textAreas) {
			ta.setFont(font);
			ta.setEditable(false);
		}

	}

	private void clear() {
		for (JTextArea ta : textAreas) {
			ta.setText("");
		}
	}

	private void top() {
		for (JTextArea ta : textAreas) {
			ta.setCaretPosition(0);
		}
	}
	
}
