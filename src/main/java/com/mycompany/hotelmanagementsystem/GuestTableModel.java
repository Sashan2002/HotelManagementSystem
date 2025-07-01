package com.mycompany.hotelmanagementsystem;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class GuestTableModel extends AbstractTableModel {
    private String[] columns = {"Name", "Surname", "Room Number", "Nights Stayed"};
    private ArrayList<Guest> guestList;

    public GuestTableModel(ArrayList<Guest> guestList){
        this.guestList = guestList;
    }

    @Override
    public int getRowCount() {
        return guestList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int row, int col){
        Guest g = guestList.get(row);
        switch(col){
            case 0:
                return g.getName();
            case 1:
                return g.getSurname();
            case 2:
                return g.getRoomNumber();
            case 3:
                return g.getNightsStayed();
        }
        return null;
    }
    @Override
    public String getColumnName(int col){
        return columns [col];
    }
}
