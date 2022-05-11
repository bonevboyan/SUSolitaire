package View.UIElements;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class JPlaceholderTextField extends JTextField {

    public static void main(final String[] args) {
        final JPlaceholderTextField tf = new JPlaceholderTextField("");
        tf.setColumns(20);
        tf.setPlaceholder("All your base are belong to us!");
        final Font f = tf.getFont();
        tf.setFont(new Font(f.getName(), f.getStyle(), 30));
        JOptionPane.showMessageDialog(null, tf);
    }

    private String placeholder;

    public JPlaceholderTextField() {
    }

    public JPlaceholderTextField(
            final Document pDoc,
            final String pText,
            final int pColumns)
    {
        super(pDoc, pText, pColumns);
    }

    public JPlaceholderTextField(final int pColumns) {
        super(pColumns);
    }

    public JPlaceholderTextField(final String pText) {
        super(pText);
    }

    public JPlaceholderTextField(final String pText, final int pColumns) {
        super(pText, pColumns);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);

        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }

        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getDisabledTextColor());
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
                .getMaxAscent() + getInsets().top);
    }

    public void setPlaceholder(final String s) {
        placeholder = s;
    }

}
