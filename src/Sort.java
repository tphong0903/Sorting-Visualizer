import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Sort extends JPanel {
    Option choice = new Option();
    SortAlgorithm sortAnimate = new SortAlgorithm();
    public int p;

    public Sort() {
        setBorder(new EmptyBorder(8, 8, 8, 8));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        choice.setBorder(new CompoundBorder(new TitledBorder("Option"), new EmptyBorder(0, 0, 0, 0)));
        add(choice, gbc);
        gbc.gridy++;
        sortAnimate.setBorder(new CompoundBorder(new TitledBorder("Sort Animate"), new EmptyBorder(0, 0, 0, 0)));
        add(sortAnimate, gbc);
        sortAnimate.run.addActionListener(e -> {
            {
                try {
                    sortAnimate.start = 1;
                    initlist();
                    p = choice.typeSort.getSelectedIndex();
                    sortAnimate.typeSort = p;
                    if (p == 3) {
                        sortAnimate.j1 = sortAnimate.NUM_OF_ITEMS - 1;
                        sortAnimate.nextCurrentIndex = sortAnimate.j1;
                    } else if (p == 2) {
                        sortAnimate.minINDEX.add(sortAnimate.j);
                        sortAnimate.nextCurrentIndex = 0;
                    }
                    repaint();
                    sortAnimate.buttonNext.setEnabled(true);
                    sortAnimate.buttonBack.setEnabled(true);
                    sortAnimate.buttonNext2.setEnabled(true);
                    sortAnimate.buttonBack2.setEnabled(true);
                    sortAnimate.auto.setEnabled(true);
                    sortAnimate.run.setEnabled(false);
                } catch (NumberFormatException | NullPointerException ime) {
                    Error(choice.values, choice.size);
                }
            }
        });
        sortAnimate.reset.addActionListener(e-> {

                p = -1;
                sortAnimate.timer.stop();
                sortAnimate.setReset();
                sortAnimate.run.setEnabled(true);
                sortAnimate.buttonNext.setEnabled(false);
                sortAnimate.buttonBack.setEnabled(false);
                sortAnimate.buttonNext2.setEnabled(false);
                sortAnimate.buttonBack2.setEnabled(false);
                sortAnimate.auto.setEnabled(false);
            }
        );
    }

    public  void Error(JTextField nhapSoA, JTextField nhapSoB) {
        sortAnimate.setReset();
        JOptionPane.showMessageDialog(null, "Lỗi, vui lòng nhập lại !!");
        nhapSoA.setText("");
        nhapSoB.setText("");
    }

    public void initlist() {
        String a = choice.values.getText();
        String b = choice.size.getText();
        if (a.isEmpty() && b.isEmpty()) {
            sortAnimate.list = sortAnimate.initList();
        } else if (a.isEmpty())
        {
            sortAnimate.NUM_OF_ITEMS = parseInt(b);
            if(sortAnimate.NUM_OF_ITEMS>14){
                Error(choice.values, choice.size);
                sortAnimate.setReset();
                return;
            }
            sortAnimate.list = sortAnimate.initList();
        }
        else
        {
            a = a.trim().toLowerCase();
            String[] temp = a.split("\\s+");
            sortAnimate.NUM_OF_ITEMS = temp.length;
            sortAnimate.list = new Integer[sortAnimate.NUM_OF_ITEMS];
            for (int i = 0; i < sortAnimate.NUM_OF_ITEMS; i++) {
                if(Integer.parseInt(temp[i])>15)
                {
                    Error(choice.values, choice.size);
                    sortAnimate.setReset();
                    return;
                }
                sortAnimate.list[i] = Integer.parseInt(temp[i]);
            }
        }
        sortAnimate.list2 = Arrays.copyOfRange(sortAnimate.list, 0, sortAnimate.list.length);
        sortAnimate.min = sortAnimate.list[sortAnimate.currentIndex];
    }
}
