import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class sanmoku_narabe extends JFrame {

    private JButton[][] buttons;

    public sanmoku_narabe() {

        //フレームの設定
        setTitle("○×ゲーム");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //ゲームに使用するボタンの設定
        buttons = new JButton[3][3];
        setLayout(new GridLayout(3, 3));


        //繰り返し処理によりボタンを作成、フォントの設定
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setFont(new Font("Arial", Font.BOLD, 160));
                buttons[x][y].addActionListener(new ButtonListener(x, y));
                add(buttons[x][y]);
            }
        }

        setVisible(true);
    }

    private char Player = '×';

    private class ButtonListener implements ActionListener {

        private int x;
        private int y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }


        //ゲームの進行
        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[x][y].getText().isEmpty()) {
                buttons[x][y].setText(String.valueOf(Player));
                buttons[x][y].setEnabled(false);
                if (checkWin()) {
                    JOptionPane.showMessageDialog(null, Player + " の勝利！");
                    System.exit(0);
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "引き分け！");
                    System.exit(0);
                } else {
                    Player = (Player == '×') ? '○' : '×';
                }
            }
        }
    }

    private boolean checkWin() {

        //横列の勝利の確認
        for (int row = 0; row < 3; row++) {
            if (buttons[row][0].getText().equals(String.valueOf(Player)) &&
                buttons[row][1].getText().equals(String.valueOf(Player)) &&
                buttons[row][2].getText().equals(String.valueOf(Player))) {
                return true;
            }
        }

        //縦列の勝利の確認
        for (int col = 0; col < 3; col++) {
            if (buttons[0][col].getText().equals(String.valueOf(Player)) &&
                buttons[1][col].getText().equals(String.valueOf(Player)) &&
                buttons[2][col].getText().equals(String.valueOf(Player))) {
                return true;
            }
        }

        //斜めの勝利の確認
        if (buttons[0][0].getText().equals(String.valueOf(Player)) &&
            buttons[1][1].getText().equals(String.valueOf(Player)) &&
            buttons[2][2].getText().equals(String.valueOf(Player))) {
            return true;
        }
        if (buttons[0][2].getText().equals(String.valueOf(Player)) &&
            buttons[1][1].getText().equals(String.valueOf(Player)) &&
            buttons[2][0].getText().equals(String.valueOf(Player))) {
            return true;
        }

        return false;
    }


    //引き分け時の処理
    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new sanmoku_narabe());
    }
}