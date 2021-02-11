package org.orgname.app.util;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomTableModel<T> extends AbstractTableModel
{
    private final Class<T> cls; //объект который описывает тип данных
    private final String[] columnNames; //массив который хранит названия колонок
    private List<T> values; //коллекция хранящая сущности таблицы

    public CustomTableModel(Class<T> cls, String[] columnNames, List<T> values)
    {
        this.cls = cls;
        this.columnNames = columnNames;
        this.values = values;
    }

    @Override
    public int getRowCount() {
        return this.values.size();
    }

    //ограничение: UserEntity или любой другой класс который будет храниться в таблице
    //не должен содержать лишних полей, вообще, даже статичных
    @Override
    public int getColumnCount() {
        //получаем массив с зарегистрированнными полями в UserEntity и берем длину
        return cls.getDeclaredFields().length;
    }

    @Override
    public String getColumnName(int column) {
        return column >= columnNames.length ? "NAME" : columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        //получаем массив с зарегистрированнными полями в UserEntity и берем тип нужного поля
        return cls.getDeclaredFields()[columnIndex].getType();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            //получаем поле UserEntity по номеру колонки
            Field field = cls.getDeclaredFields()[columnIndex];

            //делаем его доступным (если оно private)
            field.setAccessible(true);

            //получаем значение этого поля из объекта
            return field.get(this.values.get(rowIndex));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public void sort(Comparator<T> comparator) {
        Collections.sort(values, comparator);
        fireTableDataChanged();
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
