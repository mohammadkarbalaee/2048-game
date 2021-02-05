import java.util.Scanner;
import java.util.Random;
public class Main
{
    public static void main(String[] args)
    {
        game2048();
    }
    public static void game2048()
    {
        int[][] board = new int[4][4];
        board[0][0] = 2;
        char moveInput = ' ';
        boolean continueCheck = true;
        Scanner jin = new Scanner(System.in);
        while(continueCheck)
        {
            randomElementMaker(board);
            boardPrint(board);
            moveInput = jin.next().charAt(0);
            if (moveInput == 'w')
            {
                moveUp(board);
                boxMerger(board);
                continueCheck = gameOverCheck(board);
            }
            else if (moveInput == 's')
            {
                moveDown(board);
                boxMerger(board);
                continueCheck = gameOverCheck(board);
            }
            else if (moveInput == 'd')
            {
                moveRight(board);
                boxMerger(board);
                continueCheck = gameOverCheck(board);
            }
            else if (moveInput == 'a')
            {
                moveLeft(board);
                boxMerger(board);
                continueCheck = gameOverCheck(board);
            }
        }
    }
    public static void randomElementMaker(int[][] board)
    {
        Random rand = new Random();
        int randomNumber = rand.nextInt(2);
        int randomRow = 0;
        int randomColumn = 0;
        if(randomNumber == 0)
        {
            do
            {
                randomRow  = rand.nextInt(4);
                randomColumn = rand.nextInt(4);
            }
            while(board[randomRow][randomColumn] != 0);
            board[randomRow][randomColumn] = 2;
        }
        else
        {
            do
            {
                randomRow  = rand.nextInt(4);
                randomColumn = rand.nextInt(4);
            }
            while(board[randomRow][randomColumn] != 0);
            board[randomRow][randomColumn] = 4;
        }
    }
    public static void boardPrint(int[][] board)
    {
        clearScreen();
        System.out.println("---------------------");
        System.out.printf("\u2503%4d\u2503%4d\u2503%4d\u2503%4d\u2503\n",board[0][0],board[0][1],board[0][2],board[0][3]);
        System.out.println("---------------------");
        System.out.printf("\u2503%4d\u2503%4d\u2503%4d\u2503%4d\u2503\n",board[1][0],board[1][1],board[1][2],board[1][3]);
        System.out.println("---------------------");
        System.out.printf("\u2503%4d\u2503%4d\u2503%4d\u2503%4d\u2503\n",board[2][0],board[2][1],board[2][2],board[2][3]);
        System.out.println("---------------------");
        System.out.printf("\u2503%4d\u2503%4d\u2503%4d\u2503%4d\u2503\n",board[3][0],board[3][1],board[3][2],board[3][3]);
        System.out.println("---------------------");
    }
    public static void moveUp(int[][] board)
    {
        int count  = 3;
        while(count >= 0)
        {
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    elementFixerUP(board,i,j);
                }
            }
            count--;
        }
    }
    public static void moveDown(int[][] board)
    {
        int count  = 3;
        while(count >= 0)
        {
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    elementFixerDOWN(board,i,j);
                }
            }
            count--;
        }
    }
    public static void moveRight(int[][] board)
    {
        int count  = 3;
        while(count >= 0)
        {
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    elementFixerRIGHT(board,i,j);
                }
            }
            count--;
        }
    }
    public static void moveLeft(int[][] board)
    {
        int count  = 3;
        while(count >= 0)
        {
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    elementFixerLEFT(board,i,j);
                }
            }
            count--;
        }
    }
    public static boolean gameOverCheck(int[][] board)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(board[i][j] == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void elementFixerUP(int[][] board,int i,int j)
    {

            if(i == 0)
            {
                return;
            }
            else
            {
                int destination = i-1;
                while(board[destination][j] == 0)
                {
                    destination--;
                    if(destination == -1)
                    {
                        break;
                    }
                }
                destination++;
                swapFunctionForUPandDOWN(board,i,j,destination);
            }
    }
    public static void elementFixerDOWN(int[][] board,int i,int j)
    {
            if(i == 3)
            {
                return;
            }
            else
            {
                int destination = i+1;
                while(board[destination][j] == 0)
                {
                    destination++;
                    if(destination == 4)
                    {
                        break;
                    }
                }
                destination--;
                swapFunctionForUPandDOWN(board,i,j,destination);
            }
    }
    public static void elementFixerRIGHT(int[][] board,int i,int j)
    {
            if(j == 3)
            {
                return;
            }
            else
            {
                int destination = j + 1;
                while (board[i][destination] == 0)
                {
                    destination++;
                    if (destination == 4)
                    {
                        break;
                    }
                }
                destination--;
                swapFunctionForRIGHTandLEFT(board, i, j, destination);
            }
    }
    public static void elementFixerLEFT(int[][] board,int i,int j)
    {
        if(j == 0)
        {
            return;
        }
        else
        {
            int destination = j-1;
            while(board[i][destination] == 0)
            {
                destination--;
                if (destination == -1)
                {
                    break;
                }
            }
            destination++;
            swapFunctionForRIGHTandLEFT(board,i,j,destination);
        }
    }
    public static void swapFunctionForUPandDOWN(int[][] board,int i,int j,int destination)
    {
        if(board[i][j] == 0 || i == destination)
        {
            return;
        }
        else
        {
            board[destination][j] = board[i][j];
            board[i][j] = 0;
        }
    }
    public static void swapFunctionForRIGHTandLEFT(int[][] Board,int i,int j,int destination)
    {
        if(Board[i][j] == 0 || j == destination)
        {
            return;
        }
        else
        {
            Board[i][destination] = Board[i][j];
            Board[i][j] = 0;
        }
    }
    public static void boxMergerUP(int[][] board)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(i-1 != -1 && board[i][j] == board[i-1][j] && board[i][j] != 0 && board[i-1][j] != 0)
                {
                    board[i][j] = board[i-1][j] + board[i][j];
                    board[i-1][j] = 0;
                }
            }
        }
    }
    public static void boxMergerDOWN(int[][] board)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(i+1 != 4 && board[i][j] == board[i+1][j] && board[i][j] != 0 && board[i+1][j] != 0)
                {
                    board[i+1][j] = board[i+1][j] + board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }
    public static void boxMergerRIGHT(int[][] board)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(j+1 != 4 && board[i][j] == board[i][j+1] && board[i][j] != 0 && board[i][j+1] != 0)
                {
                    board[i][j+1] = board[i][j+1] + board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }
    public static void boxMergerLEFT(int[][] board)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(j-1 != -1 && board[i][j] == board[i][j-1] && board[i][j] != 0 && board[i][j-1] != 0)
                {
                    board[i][j-1] = board[i][j-1] + board[i][j];
                    board[i][j] = 0;
                }
            }
        }
    }
    public static void boxMerger(int[][] board)
    {
        boxMergerUP(board);
        boxMergerDOWN(board);
        boxMergerRIGHT(board);
        boxMergerLEFT(board);
    }
}