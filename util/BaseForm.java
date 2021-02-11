package org.orgname.app.util;

import javax.swing.*;
import java.awt.*;

public abstract class BaseForm extends JFrame
{
    private static String baseApplicationTitle = "Application";
    private static Image baseApplicationIcon = null;

    JMenuBar toolBar;

    public BaseForm()
    {
        toolBar= new JMenuBar();

        JMenu tableMenu = new JMenu("Таблицы");

        JMenuItem newItem = new JMenuItem("New") {
            @Override
            public Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                d.width = getFormWidth()/10; // set minimum
                return d;
            }
        };

        tableMenu.add(newItem);
        JMenuItem openItem = new JMenuItem("Open");
        tableMenu.add(openItem);
        JMenuItem saveItem = new JMenuItem("Save");

        toolBar.add(tableMenu);

        setJMenuBar(toolBar);
        setLocationByPlatform(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(baseApplicationTitle);
        if(baseApplicationIcon != null) {
            setIconImage(baseApplicationIcon);
        }
        setMinimumSize(new Dimension(getFormWidth(), getFormHeight()));
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getFormWidth() / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getFormHeight() / 2
        );
    }

    public abstract int getFormWidth();

    public abstract int getFormHeight();

    public static String getBaseApplicationTitle() {
        return baseApplicationTitle;
    }

    public static void setBaseApplicationTitle(String baseApplicationTitle) {
        BaseForm.baseApplicationTitle = baseApplicationTitle;
    }

    public static Image getBaseApplicationIcon() {
        return baseApplicationIcon;
    }

    public static void setBaseApplicationIcon(Image baseApplicationIcon) {
        BaseForm.baseApplicationIcon = baseApplicationIcon;
    }
}
