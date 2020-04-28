package ch.bzz.m254;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public class Create extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtTitle;
    private JComboBox<String> comboKat;
    private JComboBox<String> comboPrio;
    private JComboBox<String> comboStatus;
    private JTextField textCreated;
    private JTextArea textDesc;

    public Create(Function<Void, Void> refresh) {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Neues Ticket");
        getRootPane().setDefaultButton(buttonOK);

        textCreated.setText(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        comboKat.addItem("Hardware");
        comboKat.addItem("Software");

        comboPrio.addItem("Tief");
        comboPrio.addItem("Mittel");
        comboPrio.addItem("Hoch");

        comboStatus.addItem("Offen");

        buttonOK.addActionListener(e -> {
            onOK();
            refresh.apply(null);
        });

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
    
    private void onOK() {
        Ticket ticket = new Ticket();
        ticket.title = txtTitle.getText();
        ticket.description = textDesc.getText();
        ticket.created = LocalDateTime.now();
        ticket.category = (String) comboKat.getSelectedItem();
        ticket.priority = (String) comboPrio.getSelectedItem();
        ticket.requestor = "Benji";
        ticket.status = (String) comboStatus.getSelectedItem();
        Main.tickets.add(ticket);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
