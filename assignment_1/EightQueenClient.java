import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EightQueenClient{
    private JFrame frame = new JFrame("8 Queens");
    private JLabel messageLabel = new JLabel("...");
    private JPanel[][] squares = new JPanel[8][8];
    private Board myBoard;

    public EightQueenClient(){
        frame.getContentPane().add(messageLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.lightGray);
        boardPanel.setLayout(new GridLayout(8,8));

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                squares[i][j] = new JPanel();
                if((i+j)%2==0){
                    squares[i][j].setBackground(Color.white);
                } else {
                    squares[i][j].setBackground(Color.black);
                }
                boardPanel.add(squares[i][j]);
            }
        }

        frame.getContentPane().add(boardPanel, BorderLayout.CENTER);

        JButton nextB = new JButton("Next");
        frame.getContentPane().add(nextB, BorderLayout.SOUTH);
        nextB.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    next();
                }
            });

        myBoard = new Board();
    }

    public void next(){
        messageLabel.setText(myBoard.next());
        int[] data = myBoard.getBoard();
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if((i+j)%2==0){
                    squares[i][j].setBackground(Color.white);
                } else {
                    squares[i][j].setBackground(Color.black);
                }
                if(data[i]!=-1){
                    squares[i][data[i]].setBackground(Color.red);
                }
            }
        }
    }


    public static void main(String[] args){
        EightQueenClient client = new EightQueenClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setSize(500, 500);
        client.frame.setVisible(true);
        client.frame.setResizable(false);
    }
}
