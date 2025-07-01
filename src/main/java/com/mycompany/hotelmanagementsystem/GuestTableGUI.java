package com.mycompany.hotelmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuestTableGUI extends JFrame{
    JTable myTable;
    GuestTableModel tableModel;

    public GuestTableGUI(ArrayList<Guest>list){
        setTitle("Guest List");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tableModel = new GuestTableModel(list);
        myTable = new JTable(tableModel);
        myTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(myTable);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        add(scrollPane, BorderLayout.CENTER);
    }
}
