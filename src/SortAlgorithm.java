import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;


public class SortAlgorithm extends JPanel {
    public final Font smallFont = new Font("Dubai", Font.BOLD, 13);
    public JButton buttonBack = new JButton("<");
    public JButton buttonNext = new JButton(">");
    public JButton buttonBack2 = new JButton("<<");
    public JButton buttonNext2 = new JButton(">>");
    public JButton run = new JButton("Run");
    public JButton auto = new JButton("Auto");
    public JButton reset = new JButton("Reset");

    public int NUM_OF_ITEMS = 15;
    public final int DIM_W = 450;
    public final int DIM_H = 370;
    public final int HORIZON = 360;
    public final int VERT_INC = 19;
    public final int HOR_INC = (DIM_W) / NUM_OF_ITEMS;
    public int j = 0;
    public int j1 = NUM_OF_ITEMS - 1;
    public Integer[] list;
    public Integer[] list2;
    public int currentIndex = 0;
    public int nextCurrentIndex = 1;
    public int currentIndex2 = 0;
    public int tmpIndex = 0;
    public int doi = 0;
    public int doi2 = 0;
    int z;
    public int typeSort = 0;
    public int min;
    public int minIndex = currentIndex;
    public int start = 0;
    public Timer timer = null;
    Stack<Integer> Back = new Stack<>();
    Stack<Integer> minINDEX = new Stack<>();
    Color a = Color.red, b = Color.blue;
    boolean swap = false;

    public SortAlgorithm() {

        list = new Integer[]{0, 0};
        list2 = new Integer[]{0, 0};
        buttonBack2.setEnabled(false);
        buttonBack.setEnabled(false);
        auto.setEnabled(false);
        buttonNext2.setEnabled(false);
        buttonNext.setEnabled(false);
        add(buttonBack2);
        add(buttonBack);
        add(reset);
        add(auto);
        add(run);
        add(buttonNext);
        add(buttonNext2);

        timer = new Timer(100, e-> {
                if (sortDone()) {
                    timer.stop();
                } else {
                        QuickSortNext();
                }
                repaint();
            }
        );
        auto.addActionListener(e->{
                timer.start();
                buttonNext.setEnabled(false);
                buttonBack.setEnabled(false);
            }
        );
        buttonNext.addActionListener(e ->
        {
            if (typeSort == 0) {
                bubbleSortOnlyOneItemNext();
            } else if (typeSort == 1) {
                insertSortOnlyOneItemNext();
            } else if (typeSort == 2) {
                selectionSortOnlyOneItemNext();
            } else {
                QuickSortNext();
            }
        });
        buttonNext2.addActionListener(e -> {
            timer.stop();
            setReset2();
            Quick(0, list.length - 1);
            repaint();

        });
        buttonBack.addActionListener(e ->
        {

            if (typeSort == 0) {
                bubbleSortOnlyOneItemBack();
            } else if (typeSort == 1) {
                insertSortOnlyOneItemBack();
            } else if (typeSort == 2) {
                selectionSortOnlyOneItemBack();
            } else {
                QuickSortBack();
            }
        });
        buttonBack2.addActionListener(e -> {
            buttonNext.setEnabled(true);
            buttonBack.setEnabled(true);
            timer.stop();
            setReset2();
            if (typeSort == 3) {
                j1 = NUM_OF_ITEMS - 1;
                nextCurrentIndex = j1;
            }
            list = Arrays.copyOfRange(list2, 0, list.length);
            repaint();
        });
    }

    public boolean sortDone() {
        for (int i = 0; i < NUM_OF_ITEMS - 1; i++) {
            for (int x = i + 1; x < NUM_OF_ITEMS; x++) {
                if (list[i] > list[x])
                    return false;
            }
        }
        return true;
    }

    public Integer[] initList() {
        Integer[] nums = new Integer[NUM_OF_ITEMS];
        for (int i = 1; i <= nums.length; i++) {
            nums[i - 1] = i;
        }
        Collections.shuffle(Arrays.asList(nums));
        return nums;
    }

    public void setReset2()
    {
        j = 0;
        tmpIndex = 0;
        currentIndex = 0;
        nextCurrentIndex = 1;
        currentIndex2 = 0;
        doi = 0;
        doi2 = 0;
        while (!Back.isEmpty())
            Back.pop();
        while (!minINDEX.isEmpty())
            minINDEX.pop();
        repaint();
    }

    public void setReset() {
        NUM_OF_ITEMS = 15;
        list = new Integer[]{0, 0};
        j = 0;
        tmpIndex = 0;
        currentIndex = 0;
        nextCurrentIndex = 1;
        currentIndex2 = 0;
        doi = 0;
        doi2 = 0;
        start = 0;
        while (!Back.isEmpty())
            Back.pop();
        while (!minINDEX.isEmpty())
            minINDEX.pop();
        repaint();
    }

    public void bubbleSortOnlyOneItemNext() {
        repaint();
        if (j < NUM_OF_ITEMS - 1 && list[j] > list[j + 1]) {
            swap = true;
            int a = list[j];
            list[j] = list[j + 1];
            list[j + 1] = a;
            repaint();
            j--;
            Back.add(1);
            doi = 0;
        } else {
            Back.add(0);
        }
        j++;
        currentIndex2 = j;
        nextCurrentIndex = j + 1;
        if (j == NUM_OF_ITEMS - 1) {
            j = 0;
            currentIndex++;
        }
        if (currentIndex2 == NUM_OF_ITEMS - 1) {
            currentIndex2 = 0;
            nextCurrentIndex = 1;
        }
        if (sortDone()) {
            buttonNext.setEnabled(false);
        }
    }

    public void bubbleSortOnlyOneItemBack() {
        repaint();
        if (Back.peek() == 1) {
            int a = list[j];
            list[j] = list[j + 1];
            list[j + 1] = a;
            swap = true;
            doi2 = 0;
            Back.pop();
            repaint();
        } else {
            if (!Back.isEmpty() && j == 0)
            {
                j = NUM_OF_ITEMS - 1;
            }
            if (j != 0) {
                j--;
            }
            Back.pop();
        }
        currentIndex2 = j;
        nextCurrentIndex = j + 1;
        if (Back.isEmpty())
            buttonBack.setEnabled(false);

    }

    public void selectionSortOnlyOneItemNext() {
        if (currentIndex < list.length) {
            if (j < list.length)
            {
                nextCurrentIndex = j;
                if (list[j] < list[minIndex]) {
                    currentIndex2 = j;
                    minIndex = j;
                    minINDEX.add(j);
                }
                repaint();
                Back.add(0);
            } else {
                if (currentIndex != minIndex)
                {
                    int temp = list[currentIndex];
                    list[currentIndex] = list[minIndex];
                    list[minIndex] = temp;
                    nextCurrentIndex = minIndex;
                    currentIndex2 = currentIndex;
                    swap = true;
                    repaint();
                    Back.add(1);
                }
                else
                {
                    Back.add(0);
                }
                currentIndex++;
                currentIndex2 = currentIndex;
                minIndex = currentIndex;
                j = currentIndex;
            }
            j++;
        }
        if (sortDone()) {
            buttonNext.setEnabled(false);
        }

    }

    public void selectionSortOnlyOneItemBack() {
        if (!Back.isEmpty()) {
            buttonBack.setEnabled(true);
            buttonNext.setEnabled(true);
            repaint();

            if (Back.pop() == 1)
            {
                currentIndex--;
                if (currentIndex != minINDEX.peek()) {
                    currentIndex2 = currentIndex;
                    int temp = list[currentIndex];
                    list[currentIndex] = list[minINDEX.peek()];
                    list[minINDEX.peek()] = temp;
                    swap = true;
                    j = NUM_OF_ITEMS;
                    minINDEX.pop();
                }
                currentIndex2 = currentIndex;
                nextCurrentIndex = minIndex;
                repaint();

            } else {
                if (j > 0) {
                    j--;
                } else if (j == currentIndex) {
                    j = NUM_OF_ITEMS - 1;
                }
                nextCurrentIndex = j;
            }
        } else {
            buttonBack.setEnabled(false);
        }
    }

    public void insertSortOnlyOneItemNext() {

        if (doi == 1)
        {
            if (currentIndex != 0) {
                currentIndex--;
                currentIndex2 = currentIndex;
                nextCurrentIndex--;
                repaint();
            }
            doi = 0;
        }
        else
        {
            if (list[currentIndex] > list[nextCurrentIndex]) {
                repaint();
                int tmp = list[currentIndex];
                list[currentIndex] = list[nextCurrentIndex];
                list[nextCurrentIndex] = tmp;
                doi = 1;
                swap = true;
                z = currentIndex;
                Back.add(1);
                repaint();

            }
            else
            {
                minINDEX.add(z);
                tmpIndex++;
                currentIndex = tmpIndex;
                currentIndex2 = currentIndex;
                nextCurrentIndex = tmpIndex + 1;
                repaint();
                Back.add(0);
            }
        }

        if (sortDone()) {
            buttonNext.setEnabled(false);
        }
    }

    public void insertSortOnlyOneItemBack() {
        if (!Back.isEmpty()) {

            if (Back.pop() == 1) {
                repaint();
                int tmp = list[currentIndex];
                list[currentIndex] = list[nextCurrentIndex];
                list[nextCurrentIndex] = tmp;
                swap = true;
                repaint();
                if (currentIndex != tmpIndex) {
                    currentIndex++;
                    currentIndex2 = currentIndex;
                    nextCurrentIndex++;
                }
            } else {
                tmpIndex--;
                if (tmpIndex >= 0) {
                    currentIndex = minINDEX.pop();
                    currentIndex2 = currentIndex;
                    nextCurrentIndex = currentIndex + 1;
                    repaint();
                }
            }

        } else {
            buttonNext.setEnabled(true);
        }
    }

    public void QuickSortNext() {
        if (doi == 1)
        {
            j--;
            int temp = list[j];
            list[j] = list[j1];
            list[j1] = temp;
            currentIndex2 = j;
            nextCurrentIndex = j1;
            swap = true;
            repaint();
            doi = 0;
            j++;
        } else {
            currentIndex2 = j;
            nextCurrentIndex = j1;
            repaint();
            if (j < j1) {
                if (list[j] > list[j1]) {
                    doi = 1;
                    Back.add(1);
                } else
                    Back.add(0);
                j++;
            } else {
                j1--;
                j = 0;
            }
        }
        if (sortDone()) {
            buttonNext.setEnabled(false);
        }
    }

    public void QuickSortBack() {

        if (!Back.isEmpty()) {
            buttonBack.setEnabled(true);
            buttonNext.setEnabled(true);
            if (Back.pop() == 1) {
                if (j == 0)
                {
                    j1++;
                    j = j1;
                }
                j--;
                int temp = list[j];
                list[j] = list[j1];
                list[j1] = temp;
                currentIndex2 = j;
                nextCurrentIndex = j1;
                swap = true;
                repaint();
            } else {
                if (j == 0) {
                    j1++;
                    j = j1;
                }
                j--;
                currentIndex2 = j;
                nextCurrentIndex = j1;
                repaint();

            }
        } else
            buttonBack.setEnabled(false);
    }

    public void Quick(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            Quick(low, pi - 1);
            Quick(pi + 1, high);
        }
        repaint();
    }

    public int partition(int low, int high) {

        int pivot = list[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list[j] <= pivot) {
                i++;
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }

        }
        int temp = list[i + 1];
        list[i + 1] = list[high];
        list[high] = temp;
        return i + 1;
    }

    public void drawItem(Graphics g, int item, int index) {
        g.setColor(new Color(47, 255, 0));
        int height = item * VERT_INC;
        int y = HORIZON - height;
        int x = (index * HOR_INC) + 4 + (15 - NUM_OF_ITEMS) * 15;
        g.fillRect(x, y, HOR_INC - 7, height);
    }

    public void drawItem2(Graphics g, int item, int index, Color color) {
        g.setColor(color);
        int height = item * VERT_INC;
        int y = HORIZON - height;
        int x = (index * HOR_INC) + 4 + (15 - NUM_OF_ITEMS) * 15;
        g.fillRect(x, y, HOR_INC - 7, height);
    }

    public void showIntroScreen(Graphics2D g2d, int item, int index) {
        g2d.setFont(smallFont);
        String start = Integer.toString(item);
        g2d.setColor(Color.black);
        int height = item * VERT_INC;
        int y = HORIZON - height - 5;
        int x = (index * HOR_INC) + 9 + (15 - NUM_OF_ITEMS) * 15;
        g2d.drawString(start, x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < list.length; i++) {
            if (i != currentIndex2 && i != nextCurrentIndex) {
                drawItem(g, list[i], i);
                showIntroScreen((Graphics2D) g, list[i], i);
            }
        }
        if (start == 1)
        {
            showIntroScreen((Graphics2D) g, list[currentIndex2], currentIndex2);
            showIntroScreen((Graphics2D) g, list[nextCurrentIndex], nextCurrentIndex);
        }
        if (swap) {
            a = Color.blue;
            b = Color.red;
            swap = false;
        } else {
            a = Color.red;
            b = Color.blue;
        }
        drawItem2(g, list[currentIndex2], currentIndex2, a);
        drawItem2(g, list[nextCurrentIndex], nextCurrentIndex, b);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DIM_W, DIM_H);
    }
}
