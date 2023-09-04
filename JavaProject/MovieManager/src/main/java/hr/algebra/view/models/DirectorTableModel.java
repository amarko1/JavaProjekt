/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.models;

import hr.algebra.model.Director;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author antem_jizaagf
 */
public class DirectorTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"Id", "First name", "Last name"};
    
    private List<Director> directors;

    public DirectorTableModel(List<Director> directors) {
        this.directors = directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }
    @Override
    public int getRowCount() {
        return directors.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return directors.get(rowIndex).getId();
            case 1:
                return directors.get(rowIndex).getFirstName();
            case 2:
                return directors.get(rowIndex).getLastName();
            default:
                throw new RuntimeException("Wrong column");
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        
        return super.getColumnClass(columnIndex);
    }
}
