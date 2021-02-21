package manager;

import model.GameBoard;

import java.util.Scanner;

public class TicTacToeManager {
    public static void main(String[] args) {
        GameBoard game = new GameBoard("         ");
        printgame(game.getCells());
        int k=1;
        while(getStatus(game.getCells()).equals("Game not finished")){
            if(k%2!=0) {
                requestCoordinatesAndProcess(game, "X");
            }else {
                requestCoordinatesAndProcess(game, "O");
            }
            k++;
        }
        String status = getStatus(game.getCells());
        System.out.print(status);
    }

    private static void requestCoordinatesAndProcess(GameBoard game, String XO) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        String coordinates = s.nextLine();
        if(inputValidator(coordinates)){
            int row = Character.getNumericValue(coordinates.charAt(0));
            int col = Character.getNumericValue(coordinates.charAt(2));
            int pos = getPositionFromCoordinates(row, col);
            if (pos == -1) {
                System.out.println("Coordinates should be from 1 to 3!");
                requestCoordinatesAndProcess(game, XO);
            } else if (game.getCells().charAt(pos) == ' ') {
                game.setCells(game.getCells().substring(0, pos) + XO + game.getCells().substring(pos + 1));
                printgame(game.getCells());
            } else {
                System.out.println("This cell is occupied! Choose another one!");
                requestCoordinatesAndProcess(game, XO);
            }
        }else{
            System.out.println("You should enter numbers!");
            requestCoordinatesAndProcess(game,XO);
        }
    }

    private static boolean inputValidator(String coordinates) {
        String[] coords = coordinates.split(" ");
        if(coords.length!=2){
            return false;
        }else if(!Character.isDigit(coords[0].charAt(0)) || !Character.isDigit(coords[1].charAt(0))) {
            return false;
        }else {
            return true;
        }
    }

    private static int getPositionFromCoordinates(int row, int col) {
        if(row==1){
            if(col==1){
                return 0;
            }else if(col==2){
                return 1;
            }else if(col==3){
                return 2;
            }else{
                return -1;
            }
        }else if(row==2){
            if(col==1){
                return 3;
            }else if(col==2){
                return 4;
            }else if(col==3){
                return 5;
            }else{
                return -1;
            }
        }else if(row==3){
            if(col==1){
                return 6;
            }else if(col==2){
                return 7;
            }else if(col==3){
                return 8;
            }else{
                return -1;
            }
        }else{
            return -1;
        }
    }

    private static void printgame(String cells) {
        System.out.println("---------");
        System.out.print("| ");
        for(int i=0;i<cells.length();i++) {
            System.out.print(cells.charAt(i)+" ");
            if(i==2 || i==5){
                System.out.println("|");
                System.out.print("| ");
            }
        }
        System.out.println("|");
        System.out.println("---------");
    }

    private static String getStatus(String cells) {
        int countX=0;
        int countO=0;
        for(int i=0;i<cells.length();i++) {
            char currentcell = cells.charAt(i);
            if(currentcell=='X'){
                countX++;
            }else if(currentcell=='O'){
                countO++;
            }
        }
        boolean xwins = checkWinner('X',cells);
        boolean owins = checkWinner('O',cells);
        if(checkImpossible(countO,countX,xwins,owins)){
            return "Impossible";
        }else if(xwins){
            return "X wins";
        }else if(owins){
            return "O wins";
        }else if(countX+countO==9){
            return "Draw";
        }else{
            return "Game not finished";
        }
    }

    private static boolean checkImpossible(int countO, int countX, boolean xwins, boolean owins) {
        //XOXOXOXXO
        //System.out.print();
        if(Math.abs(countO-countX)>1){
            return true;
        }else return xwins && owins;
    }

    private static boolean checkWinner(char XO, String cells) {
        for(int i=0;i<cells.length();i++){
            if(cells.charAt(i)==XO){
                if(i==0) {
                    if ((cells.charAt(1) == XO && cells.charAt(2) == XO) ||
                            (cells.charAt(3) == XO && cells.charAt(6) == XO) ||
                            (cells.charAt(4) == XO && cells.charAt(8) == XO)) {
                        return true;
                    }
                }else if(i==1) {
                    if (cells.charAt(4) == XO && cells.charAt(7) == XO) {
                        return true;
                    }
                }else if(i==2) {
                    if ((cells.charAt(5) == XO && cells.charAt(8) == XO) ||
                            (cells.charAt(4) == XO && cells.charAt(6) == XO)) {
                        return true;
                    }
                }else if(i==3) {
                    if (cells.charAt(4) == XO && cells.charAt(5) == XO) {
                        return true;
                    }
                }else if(i==6) {
                    if (cells.charAt(7) == XO && cells.charAt(8) == XO) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

