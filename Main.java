package Boats;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int X = 7 ,Y = 7;
    static final char[] TYPES_TREASURE = {'C', 'I', 'G', 'D'};
    static char[][] treasureMap = new char[X][Y];
    static char[][] userTreasureTipMap = new char[X][Y];
    static int coins = 0;
    static int power = 5;

    static void setUp(){
        final char BACKGROUND_CELL = ' ';
        for (int i = 0; i < X; i++){
            for (int j = 0; j < Y; j++) {
                treasureMap[i][j] = BACKGROUND_CELL ;userTreasureTipMap[i][j] = BACKGROUND_CELL;
            }
        }
    }
    static void draw(){
        System.out.println("Coins: " + coins + " Power: " + power);
        System.out.println();
        for (int i = 0; i < X; i++){
            for (int j = 0; j < Y; j++) {
                System.out.print("[" + userTreasureTipMap[i][j] + "]");
            }
        System.out.println();
        }   
        System.out.println();
    }
    static void generate(){
        Random rnd = new Random();
        final int  DIAMONDS_CHANCE = 9, GOLD_CHANCE = 8, IRON_CHANCE = 5, COAL_CHANCE = 1;
        for (int i = 1; i < X * Y / COAL_CHANCE; i++) {
            treasureMap[rnd.nextInt(X)][rnd.nextInt(Y)] = TYPES_TREASURE[0];
        }
        for (int i = 1; i < X * Y / IRON_CHANCE; i++) {
            treasureMap[rnd.nextInt(X)][rnd.nextInt(Y)] = TYPES_TREASURE[1];
        }
        for (int i = 1; i < X * Y / GOLD_CHANCE; i++) {
            treasureMap[rnd.nextInt(X)][rnd.nextInt(Y)] = TYPES_TREASURE[2];
        }
        for (int i = 1; i < X * Y / DIAMONDS_CHANCE; i++) {
            treasureMap[rnd.nextInt(X)][rnd.nextInt(Y)] = TYPES_TREASURE[3];
        }

        //rnd.nextInt(TYPES_TREASURE.length)
    }

    public static void main(String[] agrs){
        Integer userInputY = 0,userInputX = 0; char userInputTip;
        Scanner sc = new Scanner(System.in);
                
        while (true) {
            coins = 0;
            power = 5;
            setUp();
            generate();
            draw();
            while (true) {
                System.out.print("Zadejte souradnice: ");
                for (int i = 0; i < 2; i++) {
                    if (!sc.hasNextInt()) {
                        System.err.println("Nezadali jste číslo.");
                        sc.next();
                        continue;
                    }
                    if (i == 0){
                        userInputX = sc.nextInt() - 1;
                    } else if (i == 1) {
                        userInputY = sc.nextInt() - 1;
                    }
                }

                if (userInputX < 0 || userInputY < 0 || userInputX >= X || userInputY >= Y) {
                    System.err.println("Souradnice je mimo rozsah");  
                } else {
                    userInputTip = treasureMap[userInputX][userInputY];                    

                    if (!(userInputTip == 'n')){
                        userTreasureTipMap[userInputX][userInputY] = userInputTip;
                    } 

                    switch (userInputTip) {
                        case 'C':
                            coins += 5;
                            power++;
                            break;
                        case 'I':
                            coins += 10;
                            power++;
                            break;
                        case 'G':
                            coins += 50;
                            power++;
                            break;
                        case 'D':
                            coins += 100;
                            power++;
                            break;
                        case 'n':
                            System.out.println("Tato souradnice je již vykomana.");
                            break;
                        case ' ':
                            power--;
                            userTreasureTipMap[userInputX][userInputY] = 'X';
                            break;
                        default:
                            System.err.println("Neco se pokazilo.");
                    }
                    treasureMap[userInputX][userInputY] = 'n';
                    
                    draw();
                    sc.nextLine();
                    if (power < 0) {
                        break;
                    }
                }
            }
            
        System.out.print("\nChcete pokračovat? (Y/n): ");
        if (sc.nextLine().toLowerCase().equals("n")){
            break;
            }

        }

        sc.close();

    }
}
