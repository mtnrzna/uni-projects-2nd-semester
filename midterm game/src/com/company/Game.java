package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Game {
    //instance Variables
    private static JFrame frame;
    private static JPanel gamePanel;

    private static Dimension dim;

    private static int row;
    private static int column;
    private static JButton[][] cell;
    private static CellInfos[][] cellInfos;

    public static JButton[][] getCell() {
        return cell;
    }

    public static CellInfos[][] getCellInfos() {
        return cellInfos;
    }


    private static Player player1 = new Player();
    private static Player player2 = new Player();

    private static ArrayList<Star> stars = new ArrayList<Star>();

    public static ArrayList<Star> getStars() {
        return stars;
    }

    private static JButton nextButton;
    private static JLabel promptLabel;

    private static JLabel starNumberLabel;
    private static JLabel player1Points;
    private static JLabel player2Points;
    private static JPanel player1Turn;
    private static JPanel player2Turn;
    private static JLabel checkIcon;


    //this method is for showing the window which the user can select where every item should be
    public static void showPlacementWindow() {
        frame = new JFrame();
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel bottomPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        nextButton = new JButton("Next");
        nextButton.setFont(FontsAndColors.getNormalFont());
        nextButton.setBackground(new Color(120, 200, 50));
        bottomPanel.add(nextButton);
        promptLabel = new JLabel();
        promptLabel.setFont(FontsAndColors.getNormalFont());
        frame.getContentPane().add(BorderLayout.NORTH, promptLabel);


        makeGamePanel();
        choosePlace4Firstplyr();


        frame.setVisible(true);
        frame.pack();
        frame.repaint();
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2 - 100);

    }


    //this method create a JPanel with the inputted row and column and then with calling some other methods inside it
    //ask user to choose places for players and walls and stars
    public static void makeGamePanel() {
        row = StartWindow.getInsertedRow();
        column = StartWindow.getInsertedColumn();
        cell = new JButton[row][column];
        cellInfos = new CellInfos[row][column];
        gamePanel = new JPanel(new GridLayout(row, column));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cell[i][j] = new JButton("     ");
                cell[i][j].setBackground(new Color(157, 187, 183));
                cellInfos[i][j] = new CellInfos();
                cell[i][j].setFont(new Font("Arial", Font.PLAIN, 24));
                gamePanel.add(cell[i][j]);
                System.out.println("cell with index of "+ i +"," + j+ " created");
            }
        }
        frame.repaint();
        frame.add(gamePanel);
    }


    public static void choosePlace4Firstplyr() {
        promptLabel.setText("Select The Cell Which The First PLayer Should Go,Then Press Next");
        //adding actionlisteners to cells for the first player
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                JButton selectedCell = cell[i][j];
                int finalI = i;
                int finalJ = j;
                selectedCell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        moveFirstPlayerTo(finalI, finalJ, selectedCell);
                        frame.repaint();
                        frame.revalidate();
                    }
                });
            }
        }
    }

    public static void moveFirstPlayerTo(int i, int j, JButton cell) {
        cell.setLayout(new BorderLayout());
        if ((player1.getPlayerLocationX() != -1 || player1.getPlayerLocationY() != -1) && (!getCellInfos()[i][j].isPlayer1InIt())) {
            getCell()[player1.getPlayerLocationX()][player1.getPlayerLocationY()].remove(player1.getIcon());
            getCellInfos()[player1.getPlayerLocationX()][player1.getPlayerLocationY()].setPlayer1InIt(false);
            getCellInfos()[i][j].setPlayer1InIt(true);

        }
        System.out.println("player1 added to cell " +i +","+j);
        player1.setPlayerLocationX(i);
        player1.setPlayerLocationY(j);
        getCellInfos()[i][j].setPlayer1InIt(true);
        cell.add(BorderLayout.CENTER, player1.getIcon());

        //enabling next button/ this does'nt allow the user click next button atleast he choose ONE place for the player
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeActnLsnrs4AllGamePanelButtons();
                removeActnLsnrs4OneButton(nextButton);

                choosePlace4SeceondPLyr();
                frame.repaint();
                frame.revalidate();
            }
        });
    }

    public static void choosePlace4SeceondPLyr() {
        promptLabel.setText("Select The Cell Which The Second Player Should Go,Then Press Next");
        //adding actionlistener for the second player
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if ((cellInfos[i][j].isPlayer1InIt() == false)) {
                    JButton selectedCell = cell[i][j];
                    int finalI = i;
                    int finalJ = j;
                    selectedCell.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            moveSecondPlayerTo(finalI, finalJ, selectedCell);
                            frame.repaint();
                            frame.revalidate();
                        }
                    });
                }
            }
        }
    }

    public static void moveSecondPlayerTo(int i, int j, JButton cell) {
        cell.setLayout(new BorderLayout());
        if ((player2.getPlayerLocationX() != -1 || player2.getPlayerLocationY() != -1) && (!getCellInfos()[i][j].isPlayer1InIt())
                && (!getCellInfos()[i][j].isPlayer2InIt())) {
            getCell()[player2.getPlayerLocationX()][player2.getPlayerLocationY()].remove(player2.getIcon());
            getCellInfos()[player2.getPlayerLocationX()][player2.getPlayerLocationY()].setPlayer1InIt(false);
            getCellInfos()[i][j].setPlayer2InIt(true);

        }
        System.out.println("player2 added to cell " +i +","+j);
        player2.setPlayerLocationX(i);
        player2.setPlayerLocationY(j);
        getCellInfos()[i][j].setPlayer1InIt(true);
        cell.add(BorderLayout.CENTER, player2.getIcon());

        //enabling next button/ this does'nt allow the user click next button atleast he choose ONE place for the player
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeActnLsnrs4AllGamePanelButtons();
                removeActnLsnrs4OneButton(nextButton);

                choosePlacesWallsShouldGo();
                frame.repaint();
                frame.revalidate();
            }
        });
    }

    public static void choosePlacesWallsShouldGo() {
        promptLabel.setText("Select The Cells Which The Walls Should Go,Then Press Next");
        //adding actionlistener for walls
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column ; j++) {
                if ((cellInfos[i][j].isPlayer1InIt() == false) && (cellInfos[i][j].isPlayer2InIt() == false)) {
                    JButton selectedCell = cell[i][j];
                    int finalI = i;
                    int finalJ = j;
                    selectedCell.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            selectedCell.setBackground(new Color(60, 66, 74));
                            removeActnLsnrs4OneButton(selectedCell);
                            cellInfos[finalI][finalJ].setWallInIt(true);


                            frame.repaint();
                            frame.revalidate();
                        }
                    });
                }
            }
        }

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeActnLsnrs4AllGamePanelButtons();
                removeActnLsnrs4OneButton(nextButton);

                choosePlacesStarsShouldGo();
                frame.repaint();
                frame.revalidate();
            }
        });

    }

    public static void choosePlacesStarsShouldGo() {
        promptLabel.setText("Select The Cells Which The Stars Should Go,Then Press Play");
        //adding actionlistener for stars
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (!cellInfos[i][j].isPlayer1InIt() && !cellInfos[i][j].isPlayer2InIt()
                        && !cellInfos[i][j].isWallInIt() && !cellInfos[i][j].isStarInIt()) {
                    JButton selectedCell = cell[i][j];
                    int finalI = i;
                    int finalJ = j;
                    selectedCell.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Star newStar = new Star();
                            newStar.addStarTo(finalI, finalJ, selectedCell);

                            removeActnLsnrs4OneButton(selectedCell);

                            frame.repaint();
                            frame.revalidate();


                            //enables next button when user select the first cell for a star
                            if(stars.size() ==1) {
                                nextButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        frame.setVisible(false);
                                        removeActnLsnrs4AllGamePanelButtons();
                                        nextButton = null;

                                        showGameWindow();
                                    }
                                });
                            }

                        }
                    });
                }
            }
        }

        nextButton.setBackground(new Color(220, 55, 56));
        nextButton.setForeground(new Color(233, 238, 107));
        nextButton.setFont(FontsAndColors.getBigFont());
        nextButton.setText("Play");

    }


    //method below is where actual game will happen!!
    public static void showGameWindow() {
        frame = new JFrame();
        int player1point = player1.getPoint();
        int player2point = player2.getPoint();
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.SOUTH, gamePanel);
        frame.setResizable(false);
        importCheckPic();


        JPanel topPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(topPanel);

        JPanel topLeftPanel = new JPanel(new GridLayout(3, 3));

        JLabel pointsLabel = new JLabel("Points");
        pointsLabel.setFont(FontsAndColors.getLabelFont());
        JLabel turnLabel = new JLabel("Turn");
        turnLabel.setFont(FontsAndColors.getLabelFont());
        JLabel player1Label = new JLabel("Player 1");
        player1Label.setFont(FontsAndColors.getLabelFont());
        player1Turn = new JPanel();
        player1Turn.setFont(FontsAndColors.getLabelFont());
//        player1Turn.add(checkIcon);
        player1Points = new JLabel(Integer.toString(player1point));
        player1Points.setFont(FontsAndColors.getLabelFont());
        JLabel player2Label = new JLabel("Player 2");
        player2Label.setFont(FontsAndColors.getLabelFont());
        player2Turn = new JPanel();
        player2Turn.setFont(FontsAndColors.getLabelFont());
        player2Points = new JLabel(Integer.toString(player2point));
        player2Points.setFont(FontsAndColors.getLabelFont());

        topLeftPanel.add(pointsLabel);
        topLeftPanel.add(turnLabel);
        topLeftPanel.add(new JLabel());
        topLeftPanel.add(player1Points);
        topLeftPanel.add(player1Turn);
        topLeftPanel.add(player1Label);
        topLeftPanel.add(player2Points);
        topLeftPanel.add(player2Turn);
        topLeftPanel.add(player2Label);


        JPanel topRightPanel = new JPanel();
        topRightPanel.setLayout(new GridLayout(2, 1));
        JLabel starsLeft = new JLabel("Stars Left:");
        starsLeft.setFont(FontsAndColors.getLabelFont());
        starNumberLabel = new JLabel("\t"+ stars.size());
        starNumberLabel.setFont(FontsAndColors.getLabelFont());

        topRightPanel.add(starsLeft);
        topRightPanel.add(starNumberLabel);


        topPanel.add(BorderLayout.WEST, topLeftPanel);
        topPanel.add(BorderLayout.EAST, topRightPanel);


        frame.repaint();
        frame.revalidate();
        frame.pack();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2 - 100);
        frame.setVisible(true);



        playerTurn(player1);

    }


    public static void playerTurn(Player player) {
        removeActnLsnrs4AllGamePanelButtons();
        if(stars.size() != 0) {
            Player nextPlayer;
            if (player == player1) {
                nextPlayer = player2;
                player1Turn.add(checkIcon);
            } else {
                nextPlayer = player1;
                player2Turn.add(checkIcon);
            }

            //add action lisner to cells right of the player with same y coordinates
            for (int i = player.getPlayerLocationX() + 1; i < row; i++) {
                int finalI = i;
                JButton selectedCell = cell[i][player.getPlayerLocationY()];
                selectedCell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (!isWallInTheWayHorizentalCase(player.getPlayerLocationX(), finalI, player.getPlayerLocationY())
                                && !cellInfos[finalI][player.getPlayerLocationY()].isPlayer1InIt()
                                && !cellInfos[finalI][player.getPlayerLocationY()].isPlayer2InIt()) {
                            eatUpStarsOnTheHorizontalWay(player.getPlayerLocationX(), finalI, player.getPlayerLocationY(), player);
                            moveHorizontal(player.getPlayerLocationX(), finalI, player.getPlayerLocationY(), player);
                            System.out.println("player moved to " + finalI +","+ player.getPlayerLocationY());
                            playerTurn(nextPlayer);
                        }
                    }
                });
            }

            //add action lisner to cells left of the player with same y coordinates
            for (int i = player.getPlayerLocationX() - 1; i >= 0; i--) {
                int finalI = i;
                JButton selectedCell = cell[i][player.getPlayerLocationY()];
                selectedCell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!isWallInTheWayHorizentalCase(player.getPlayerLocationX(), finalI, player.getPlayerLocationY())
                                && !cellInfos[finalI][player.getPlayerLocationY()].isPlayer1InIt()
                                && !cellInfos[finalI][player.getPlayerLocationY()].isPlayer2InIt()) {
                            eatUpStarsOnTheHorizontalWay(player.getPlayerLocationX(), finalI, player.getPlayerLocationY(), player);
                            moveHorizontal(player.getPlayerLocationX(), finalI, player.getPlayerLocationY(), player);
                            System.out.println("player moved to " + finalI +","+ player.getPlayerLocationY());
                            playerTurn(nextPlayer);
                        }
                    }
                });
            }
            //add action lisner to cells above he player with same x coordinates
            for (int j = player.getPlayerLocationY() - 1; j >= 0; j--) {
                int finalJ = j;
                JButton selectedCell = cell[player.getPlayerLocationX()][j];
                selectedCell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (!isWallInTheWayVerticalCase(player.getPlayerLocationY(), finalJ, player.getPlayerLocationX())
                                && !cellInfos[player.getPlayerLocationX()][finalJ].isPlayer1InIt()
                                && !cellInfos[player.getPlayerLocationX()][finalJ].isPlayer2InIt()) {
                            eatUpStarsOnTheVerticalWay(player.getPlayerLocationY(), finalJ, player.getPlayerLocationX(), player);
                            moveVertical(player.getPlayerLocationY(), finalJ, player.getPlayerLocationX(), player);
                            System.out.println("player moved to " + player.getPlayerLocationX()+"," + finalJ);
                            playerTurn(nextPlayer);
                        }
                    }
                });
            }

            //add action lisner to cells under he player with same x coordinates
            for (int j = player.getPlayerLocationY() + 1; j < column; j++) {
                int finalJ = j;
                JButton selectedCell = cell[player.getPlayerLocationX()][j];
                selectedCell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (!isWallInTheWayVerticalCase(player.getPlayerLocationY(), finalJ, player.getPlayerLocationX())
                                && !cellInfos[player.getPlayerLocationX()][finalJ].isPlayer1InIt()
                                && !cellInfos[player.getPlayerLocationX()][finalJ].isPlayer2InIt()) {
                            eatUpStarsOnTheVerticalWay(player.getPlayerLocationY(), finalJ, player.getPlayerLocationX(), player);
                            moveVertical(player.getPlayerLocationY(), finalJ, player.getPlayerLocationX(), player);
                            System.out.println("player moved to " + player.getPlayerLocationX()+"," + finalJ);
                            playerTurn(nextPlayer);
                        }
                    }
                });
            }

        }
        if(stars.size() ==  0){
            String endOfGame = "";
            if(player1.getPoint() > player2.getPoint())
                endOfGame = "The Winner Is Player1";
            if(player1.getPoint() < player2.getPoint())
                endOfGame = "The Winner Is Player2";
            if(player1.getPoint() == player2.getPoint())
                endOfGame = "Draw!!";
            JOptionPane.showMessageDialog(frame,endOfGame);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }

    }


    public static void moveHorizontal(int originX, int destinationX, int y, Player player) {
        cell[originX][y].remove(player.getIcon());
        cell[destinationX][y].add(player.getIcon());
        player.setPlayerLocationX(destinationX);
        if(player == player1) {
            cellInfos[originX][y].setPlayer1InIt(false);
            cellInfos[destinationX][y].setPlayer1InIt(true);
        }
        if(player == player2){
            cellInfos[originX][y].setPlayer2InIt(false);
            cellInfos[destinationX][y].setPlayer2InIt(true);
        }
        frame.repaint();
        frame.revalidate();
    }
    public static void moveVertical(int originY, int destinationY, int x, Player player){
        cell[x][originY].remove(player.getIcon());
        cell[x][destinationY].add(player.getIcon());
        player.setPlayerLocationY(destinationY);
        if(player == player1) {
            cellInfos[x][originY].setPlayer1InIt(false);
            cellInfos[x][destinationY].setPlayer1InIt(true);
        }
        if(player == player2){
            cellInfos[x][originY].setPlayer2InIt(false);
            cellInfos[x][destinationY].setPlayer2InIt(true);
        }
        frame.repaint();
        frame.revalidate();
    }



    public static void eatUpStarsOnTheHorizontalWay(int originX, int destinationX, int y, Player player) {
        for (int i = Math.min(originX, destinationX); i <= Math.max(originX, destinationX); i++) {
            if (cellInfos[i][y].isStarInIt()) {
                System.out.println("player got the star!");
                player.setPoint(player.getPoint() + 1);
                if(player == player1)
                    player1Points.setText(Integer.toString(player1.getPoint()));
                else
                    player2Points.setText(Integer.toString(player2.getPoint()));

                for(int index = 0; index<stars.size();index++){
                    if(stars.get(index).getStarLocationX()==i && stars.get(index).getStarLocationY() ==y){
                        cell[i][y].removeAll();
                        cellInfos[i][y].setStarInIt(false);
                        stars.remove(index);
                        starNumberLabel.setText("\t"+ stars.size());
                    }
                }
            }
        }
    }
    public static void eatUpStarsOnTheVerticalWay(int originY, int destinationY, int x, Player player) {
        for (int j = Math.min(originY, destinationY); j <= Math.max(originY, destinationY); j++) {
            if (cellInfos[x][j].isStarInIt()) {
                System.out.println("player got the star!");
                player.setPoint(player.getPoint() + 1);
                if(player == player1)
                    player1Points.setText(Integer.toString(player1.getPoint()));
                else
                    player2Points.setText(Integer.toString(player2.getPoint()));

                for(int index = 0; index<stars.size();index++){
                    if(stars.get(index).getStarLocationX()==x && stars.get(index).getStarLocationY() ==j){
                        cell[x][j].removeAll();
                        cellInfos[x][j].setStarInIt(false);
                        stars.remove(index);
                        starNumberLabel.setText("\t"+ stars.size());
                    }
                }
            }
        }
    }


    public static boolean isWallInTheWayHorizentalCase(int originX, int destinationX, int y) {
        for (int i = Math.min(originX, destinationX); i <= Math.max(originX, destinationX); i++) {
            if (cellInfos[i][y].isWallInIt()) {
                return true;
            }
        }
        return false;
    }
    public static boolean isWallInTheWayVerticalCase(int originY, int destinationY, int x) {
        for (int j = Math.min(originY, destinationY); j <= Math.max(originY, destinationY); j++) {
            if (cellInfos[x][j].isWallInIt()) {
                return true;
            }
        }
        return false;
    }





    public static void importCheckPic(){
        try {
            BufferedImage myPicture;
            myPicture = ImageIO.read(new File("CheckIcon.png"));
             checkIcon= new JLabel(new ImageIcon(myPicture));
        } catch (Exception ex) {
            System.out.println("image of star not found");
            checkIcon = null;
        }
    }






    //these 2 following methods are helpful methods that have been used in some above methods
    public static void removeActnLsnrs4OneButton(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    public static void removeActnLsnrs4AllGamePanelButtons() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++){
                for (ActionListener al : cell[i][j].getActionListeners()) {
                    cell[i][j].removeActionListener(al);
                }
            }
        }
    }
}

