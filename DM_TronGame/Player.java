import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

//Enregistre la position de chaque joueur 
    //Construire un graphe avec la position actualisée de chaque joueur/tour 

    //debut : se diriger sur la grid en tenant compte de la position des adversaires et si le chemin a deja été visité ou non 

    // on essaye de reagir a la pos actuelle et se diriger vers les borders pour gagner en espace 

class Player {

    
    public static String fillPlayerPosition(int X0, int Y0){
        
        String tmpArray = X0 + "," + Y0;
        return tmpArray;
    }

    public static String mooveToBorder(String[][] playersPosition, int currentX, int currentY, int compteur){
        if (20 - currentY > 10){
            String positionTest = currentX + "," + (currentY+1);
            if (canMoove(playersPosition, positionTest, compteur )){
            return mooveDirector(currentX, currentY, currentX, currentY+1);
        }
        }
        else{
            String positionTest = currentX + "," + (currentY-1);
            if (canMoove(playersPosition, positionTest, compteur )){
            return mooveDirector(currentX, currentY, currentX, currentY-1);
        }
        }
        if (30 - currentX>15){
            String positionTest = (currentX - 1) + "," + currentY;
            if (canMoove(playersPosition, positionTest, compteur)){
            return mooveDirector(currentX, currentY, currentX-1, currentY);
        }
        }
        else {
            String positionTest = (currentX + 1) + "," + currentY;
            if (canMoove(playersPosition, positionTest, compteur)){
            return mooveDirector(currentX, currentY, currentX+1, currentY);
        }
        }
        
        return mooveTo(playersPosition, currentX, currentY, compteur);
    }


    public static String mooveTo(String[][] playersPosition, int currentX, int currentY, int compteur){
        String positionTest = currentX + "," + (currentY+1);
        if (canMoove(playersPosition, positionTest, compteur )){
            return mooveDirector(currentX, currentY, currentX, currentY+1);
        }
        positionTest = currentX + "," + (currentY-1);
        if (canMoove(playersPosition, positionTest, compteur ) && (currentY-1>0)){
            return mooveDirector(currentX, currentY, currentX, currentY-1);
        }
        positionTest = (currentX - 1) + "," + currentY;
        if (canMoove(playersPosition, positionTest, compteur)){
            return mooveDirector(currentX, currentY, currentX-1, currentY);
        }
        positionTest = (currentX + 1) + "," + currentY;
        if (canMoove(playersPosition, positionTest, compteur)){
            return mooveDirector(currentX, currentY, currentX+1, currentY);
        }
        
        return mooveDirector(currentX, currentY, currentX, currentY);
    }

    public static String mooveDirector(int currentX, int currentY, int nextX, int nextY){
        if ((currentY-1) == nextY){
            return "UP";
        }
        if ((currentY+1) == nextY){
            return "DOWN";
        }
        if ((currentX+1) == nextX){
            return "RIGHT";
        }if ((currentX-1) == nextX){
            return "LEFT";
        }

        return "RIGHT";
    }

    public static boolean canMoove(String[][] playersPosition, String position, int compteur){
        for (int i=0; i<playersPosition.length; i++){
            if (isOccupied(playersPosition[i], position, compteur)){
                return false;
            }
            if (isBorder(position)){
                return false;
            }
        }
        return true;
    }

    public static boolean isOccupied(String[] positionArray, String position, int compteur){
        for (int i=0; i<compteur+1; i++){
            if (positionArray[i].equals(position)){
                return true;
            }
        }
        return false;
    }

    public static boolean isBorder(String position){
        if (position.startsWith("0,")){
            return true;
        }
        if (position.contains("30,")){
            return true;
        }
        if (position.contains(",20")){
            return true;
        }
        if (position.contains(",0")){
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String[][] positionArray = new String[2][700];
        String direction = "UP";
        int turnCompteur = 0 ;
        int currentX=0;
        int currentY=0;
        
        // game loop
        
        while (true) {
            int N = in.nextInt(); // total number of players (2 to 4).
            int P = in.nextInt(); // your player number (0 to 3).
            for (int i = 0; i < N; i++) {
                int X0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
                int Y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
                int X1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
                int Y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)
                if (turnCompteur == 0){
                    if (i == P){
                        currentX = X0;
                        currentY = Y0;
                    }
                    positionArray[i][turnCompteur]=fillPlayerPosition(X0, Y0);
                }
                else{
                    if (i == P){
                        currentX = X1;
                        currentY = Y1;
                    }
                    positionArray[i][turnCompteur]=fillPlayerPosition(X1, Y1);
                }
            }
            
            direction = mooveToBorder(positionArray, currentX, currentY, turnCompteur);
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            turnCompteur = turnCompteur + 1 ;
            System.out.println(direction); // A single line with UP, DOWN, LEFT or RIGHT
        }
    }
}