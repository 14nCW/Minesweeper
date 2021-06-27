import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Icon easy = new ImageIcon("src/resource/MainMenu/EasyMode.png");
        Icon medium = new ImageIcon("src/resource/MainMenu/MediumMode.png");
        Icon hard = new ImageIcon("src/resource/MainMenu/HardMode.png");
        Icon scoreboard = new ImageIcon("src/resource/MainMenu/Scoreboard.png");

        Game game = new Game();
        JFrame frameMain = new JFrame("Minesweeper");
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setSize(400, 500);

        JButton buttonEasy = new JButton("", easy);
        JButton buttonMedium = new JButton("", medium);
        JButton buttonHard = new JButton("", hard);
        JButton buttonTest = new JButton("", scoreboard);
        JPanel TopPanel = new JPanel();
        JPanel LeftPanel = new JPanel();
        JPanel RightPanel = new JPanel();
        JPanel BottomPanel = new JPanel();

        TopPanel.setPreferredSize(new Dimension(300, 300));
        TopPanel.setBackground(Color.GREEN);
        TopPanel.setLayout(new GridLayout(4,1));

        RightPanel.setPreferredSize(new Dimension(50, 500));
        RightPanel.setBackground(Color.YELLOW);

        LeftPanel.setPreferredSize(new Dimension(50, 500));
        LeftPanel.setBackground(Color.BLACK);

        BottomPanel.setPreferredSize(new Dimension(50, 500));
        BottomPanel.setBackground(Color.GRAY);

        TopPanel.setVisible(true);
        RightPanel.setVisible(true);
        LeftPanel.setVisible(true);
        BottomPanel.setVisible(true);

        frameMain.add(TopPanel, BorderLayout.NORTH);
        frameMain.add(LeftPanel, BorderLayout.EAST);
        frameMain.add(RightPanel, BorderLayout.WEST);
        frameMain.add(BottomPanel, BorderLayout.SOUTH);

        buttonEasy.setPreferredSize(new Dimension(300,75));
        buttonEasy.setMargin(new Insets(0,0,0,0));
        TopPanel.add(buttonEasy);
        buttonEasy.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.diff(1);
            }
        });

        buttonMedium.setPreferredSize(new Dimension(300,75));
        buttonMedium.setMargin(new Insets(0,0,0,0));
        TopPanel.add(buttonMedium);
        buttonMedium.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.diff(2);
            }
        });

        buttonHard.setPreferredSize(new Dimension(300,75));
        buttonHard.setMargin(new Insets(0,0,0,0));
        TopPanel.add(buttonHard);
        buttonHard.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.diff(3);
            }
        });

        buttonTest.setPreferredSize(new Dimension(300,75));
        buttonTest.setMargin(new Insets(0,0,0,0));
        TopPanel.add(buttonTest);
        buttonTest.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMain.dispose();
                game.diff(4);
            }
        });

//        frameMain.setLayout(new GridLayout(4,1));
       /* frameMain.add(buttonEasy);
        frameMain.add(buttonMedium);
        frameMain.add(buttonHard);
        frameMain.add(buttonTest);*/
        frameMain.setVisible(true);

    }
}
