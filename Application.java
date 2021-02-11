package org.orgname.app;

import org.orgname.app.database.entity.DateEntity;
import org.orgname.app.database.manager.DateEntityManager;
import org.orgname.app.ui.ClientTableForm;
import org.orgname.app.ui.UsersTableForm;
import org.orgname.app.util.BaseForm;
import org.orgname.app.util.FontUtil;
import org.orgname.app.util.MysqlDatabase;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application
{
    private static Application instance;

    //private final MysqlDatabase database = new MysqlDatabase("116.202.236.174", "DemoExam", "DemoExam", "DemoExam");
    //private final MysqlDatabase database = new MysqlDatabase("nleontnr.beget.tech", "nleontnr_docker", "nleontnr_docker", "8udwX&9bdw");
    private final MysqlDatabase database = new MysqlDatabase("127.0.0.1", "demoexam", "admin", "password");

    private Application()
    {
        instance = this;

        initDatabase();
        initUi();

        new ClientTableForm();
    }

    private void test() throws ParseException, SQLException
    {
        String birthday = "18.07.2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DateEntity dateEntity = new DateEntity(dateFormat.parse(birthday));
        DateEntityManager dateEntityManager = new DateEntityManager(database);
        dateEntityManager.add(dateEntity);
        System.out.println(dateEntity);
    }

    private void initDatabase()
    {
        try(Connection c = database.getConnection()) {
        } catch (Exception e) {
            System.out.println("Ошибка подключения к бд");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void initUi()
    {
        BaseForm.setBaseApplicationTitle("База");

        BaseForm.setBaseApplicationIcon(
                Toolkit.getDefaultToolkit().getImage(Application.class.getClassLoader().getResource("icon.jpg"))
        );

        try {

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Красивый вид на unix системах.
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getClassName().toString());
                if ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel".equals(info.getClassName())) {
                    //javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FontUtil.changeAllFonts(new FontUIResource("Comic Sans", Font.TRUETYPE_FONT, 14));
    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    public static void main(String[] args)
    {
        new Application();
    }

    public static Application getInstance() {
        return instance;
    }
}
