package ch.bzz.m254;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    public static List<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ignored) {
            // ignore
        }
        new Main().setVisible(true);
    }

    public Main() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Magic Service Desk");

        JTable table = new JTable();
        JButton btn = new JButton("Neues Ticket");
        btn.addActionListener(e -> {
            Create dialog = new Create((x) -> {
                updateTable(table);
                return null;
            });
            dialog.pack();
            dialog.setVisible(true);
        });
        
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(btn);
        panel.add(new JScrollPane(table));
        add(panel);
    }

    private void updateTable(JTable table) {
        String[][] data = new String[tickets.size()][5];
        for (int i = 0; i < tickets.size(); i++) {
            Ticket t = tickets.get(i);
            data[i] = new String[]{t.title, t.priority, t.category, t.requestor, t.status};
        }
        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Title", "Priorität", "Kategorie", "Requestor", "Status"});
        table.setModel(model);
        table.revalidate();
    }
}
