import javax.swing.*;
import java.awt.*;


public class Option extends JPanel {
    public JComboBox typeSort;
    public JTextField size;
    public JTextField values;

    public Option() {
        JPanel optionPane = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel inputValues = new JLabel("Array Values (optional):");
        optionPane.add(inputValues, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        values = new JTextField(15);
        optionPane.add(values, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.weighty = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        optionPane.add(new JLabel("Array size:"), gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        size = new JTextField(15);
        optionPane.add(size, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        optionPane.add(new JLabel("Type sort:"), gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] sort = {"Bubble", "Insertion", "Selection",
                "Quick"};

        typeSort = new JComboBox(sort);
        typeSort.setBackground(Color.white);
        typeSort.setSelectedIndex(0);
        optionPane.add(typeSort, gbc);

        setLayout(new BorderLayout());
        add(optionPane);
    }

}

