package org.orgname.app.ui;

import org.orgname.app.Application;
import org.orgname.app.database.entity.GenderEnum;
import org.orgname.app.database.entity.UserEntity;
import org.orgname.app.database.manager.UserEntityManager;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.CustomTableModel;
import org.orgname.app.util.DialogUtil;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Random;

public class UsersTableForm extends BaseForm
{
    private final UserEntityManager userEntityManager = new UserEntityManager(Application.getInstance().getDatabase());

    private CustomTableModel<UserEntity> model;

    private JPanel mainPanel;
    private JTable table;
    private JButton addButton;

    public UsersTableForm()
    {
        setContentPane(mainPanel);

        initTable();
        initButtons();

        setVisible(true);
    }

    private void initTable()
    {
        table.getTableHeader().setReorderingAllowed(false);

        try {
            model = new CustomTableModel<UserEntity>(
                    UserEntity.class,
                    new String[] {
                        "ID", "Логин", "Пароль", "Гендер", "Возраст", "Работа"
                    },
                    userEntityManager.getAll()
            );
            table.setModel(model);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int row = table.getSelectedRow();
                if(e.getKeyCode() == KeyEvent.VK_DELETE && row != -1)
                {
                    try {
                        if(DialogUtil.showConfirm(UsersTableForm.this, "Вы точно хотите удалить данную форму?")) {
                            userEntityManager.delete(model.getValues().get(row));
                            model.getValues().remove(row);
                            model.fireTableDataChanged();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

    private void initButtons()
    {
        addButton.addActionListener(e -> {
            Random rand = new Random();
            int i = rand.nextInt(9999);

            UserEntity newUser = new UserEntity(
                    "user-" + i,
                    "user-" + i,
                    GenderEnum.MALE,
                    rand.nextInt(99),
                    "coder"
            );

            try {
                userEntityManager.add(newUser);
                model.getValues().add(newUser);
                model.fireTableDataChanged();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 800;
    }

    @Override
    public int getFormHeight() {
        return 400;
    }
}
