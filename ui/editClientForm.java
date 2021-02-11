package org.orgname.app.ui;

import org.orgname.app.database.entity.ClientEntity;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.BaseSubForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editClientForm extends BaseSubForm<ClientTableForm> {

    private JButton okButton;
    private JPanel mainPannel;
    ClientEntity client = null;
    private int row;

    public editClientForm(ClientTableForm mainForm) {
        super(mainForm);
        setContentPane(mainPannel);

        initFields();
        initButtons();

        setVisible(true);
    }
    public editClientForm(ClientTableForm mainForm, int row,ClientEntity client) {
        this(mainForm);
        this.client = client;
        this.row = row;
    }


    private void initFields(){
        if(client == null){
            // Создание нового
        }else{
            // Редактирование
        }

    }
    private void updateFromFields(){
        boolean isNew = (client == null) || row < 0;

        // обновление
        try {
            if (isNew) {
                mainForm.getClientEntityManager().add(client);
                mainForm.getModel().getValues().add(client);
            } else {
                mainForm.getClientEntityManager().update(client);
                mainForm.getModel().getValues().set(row,client);
            }

            mainForm.getModel().fireTableDataChanged();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void initButtons(){
        okButton.addActionListener(e -> {
           updateFromFields();
        });
    }

    @Override
    public int getFormWidth() {
        return 200;
    }

    @Override
    public int getFormHeight() {
        return 400;
    }
}
