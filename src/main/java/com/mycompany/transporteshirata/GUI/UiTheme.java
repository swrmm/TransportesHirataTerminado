package com.mycompany.transporteshirata.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;

public final class UiTheme {

    public static final Color BACKGROUND = new Color(245, 247, 250);
    public static final Color SURFACE = Color.WHITE;
    public static final Color PRIMARY = new Color(20, 83, 136);
    public static final Color PRIMARY_DARK = new Color(13, 58, 96);
    public static final Color ERROR = new Color(185, 28, 28);
    public static final Color TEXT = new Color(17, 24, 39);
    public static final Color MUTED = new Color(107, 114, 128);
    public static final Color BORDER = new Color(222, 226, 232);

    private static final Font TITLE = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font SUBTITLE = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font LABEL = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font BODY = new Font("Segoe UI", Font.PLAIN, 13);

    private UiTheme() {
    }

    public static JLabel title(String text) {
        JLabel label = new JLabel(text);
        label.setFont(TITLE);
        label.setForeground(TEXT);
        return label;
    }

    public static JLabel subtitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(SUBTITLE);
        label.setForeground(MUTED);
        return label;
    }

    public static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(LABEL);
        label.setForeground(TEXT);
        return label;
    }

    public static JPanel surface() {
        JPanel panel = new JPanel();
        panel.setBackground(SURFACE);
        panel.setBorder(cardBorder());
        return panel;
    }

    public static Border cardBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(16, 16, 16, 16));
    }

    public static void primaryButton(JButton button) {
        button.setFont(LABEL);
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void secondaryButton(JButton button) {
        button.setFont(LABEL);
        button.setForeground(PRIMARY_DARK);
        button.setBackground(new Color(232, 241, 250));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void table(JTable table) {
        table.setFont(BODY);
        table.setRowHeight(28);
        table.setGridColor(BORDER);
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(TEXT);
        JTableHeader header = table.getTableHeader();
        header.setFont(LABEL);
        header.setBackground(new Color(238, 242, 247));
        header.setForeground(TEXT);
    }

    public static void field(JComponent component) {
        component.setFont(BODY);
        component.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        component.setBackground(Color.WHITE);
    }

    public static void applyFont(Component component) {
        component.setFont(BODY);
    }
}
