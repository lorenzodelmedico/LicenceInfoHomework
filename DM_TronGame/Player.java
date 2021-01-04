import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/

//Enregistre la position de chaque joueur 
    //Construire un graphe avec la position actualisée de chaque joueur/tour 

    //strategie : se diriger sur la grid en tenant compte de la position des adversaires et si le chemin a deja été visité ou non 

    // ranking [bronze I] : 1149 
    //Idées pour améliorer : 

class Player {

    
    /* Usage : Sert à remplir un 2d array avec les inputs des positions des players : 
    {
        Args : int X, int Y représente la position X et Y du joueur sur le plateau
        Return : un String sous la forme "X,Y" qui va remplir le String 2dArray positionPlayers
    }
    */
    public static String fillPlayerPosition(int X, int Y){
        
        String tmpArray = X + "," + Y;
        return tmpArray;
    }

    /* Usage :sert à gérer le déplacement de notre player : 
    {
        Args : String[][] playersPosition : 2dArray qui comprend les positions occupées ; int currentX, int currentY : représente la position actuelle de notre player ; int compteur : représente le nombre de tour joué
        Body : On appel 2 fonctions mooveTo et mooveToBorder qui gère les déplacements et ses restrictions
        Return : un String sous la forme "UP" || "DOWN" || "LEFT" || "RIGHT" qui va indiquer la prochaine direction à suivre 
    }
    */
    public static String mooveManager(String[][] playersPosition, int currentX, int currentY, int compteur){
        if (currentY == 0 || currentY == 20){
            return mooveTo(playersPosition, currentX, currentY, compteur);
        }
        else{
            return mooveToBorder(playersPosition, currentX, currentY, compteur);
        }
    }
        
       
    /* Usage :sert à forcer le déplacement de notre player vers une border distante : 
    {
        Args : String[][] playersPosition : 2dArray qui comprend les positions occupées ; int currentX, int currentY : représente la position actuelle de notre player ; int compteur : représente le nombre de tour joué
        Body : On essaye de forcer le déplacement du player vers la border dont il se trouve le plus éloigné. 
        Return : un String sous la forme "UP" || "DOWN" || "LEFT" || "RIGHT" qui va indiquer la prochaine direction à suivre , si aucune situation ne correspond on fait appel à la fonction mooveTo qui propose une autre gestion
    }
    */
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


    /* Usage : Methode qui test les positions adjacentes à la notre pour voir si on peut s'y déplacer : 
    {
        Args :  String[][] playersPosition : 2dArray qui comprend les positions occupées ; int currentX, int currentY : représente la position actuelle de notre player ; int compteur : représente le nombre de tour joué
        Body : Test une par une les positions adjacentes pour trouver celle vers laquelle on peut se déplacer, en favorisant sur l'axe Y par rapport à l'axe X. 
        Return : un String qui vaut soit "UP" || "DOWN" || "RIGHT" || "LEFT" ; 
    */
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

    /* Usage : Methode qui gère la direction à prendre, en fonction de la position actuelle : 
    {
        Args : int currentX, int currentY : représente la position actuelle ; int nextX, int nextY : représente la case sur laquelle on veut se rendre. 
        Return : un String qui vaut soit "UP" || "DOWN" || "RIGHT" || "LEFT" ; Si aucune situation ne correspond, cela signifie probablement que j'ai perdu, 
        je décide donc de renvoyer une direction afin de ne pas perdre par réponse non fournie. 
    */

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
        return "DOWN";
    }

    /* Usage : Methode qui englobe des checkers qui regarde si la position à atteindre est accessible : 
    {
        Args : String[] positionArray : array des positions d'un joueur ; String position : la position sur laquelle on souhaite se déplacér ; int compteur : le nombre de tours joué
        Return : un boolean qui vaut true si la case est disponible, false sinon. 
    }
    */

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

     /* Usage : Methode qui regarde si une case est déjà occupé : 
    {
        Args : String[] positionArray : array des positions d'un joueur ; String position : la position sur laquelle on souhaite se déplacér ; int compteur : le nombre de tours joué
        Return : un boolean qui vaut true si la case est occupé, false sinon. 
    }
    */
    public static boolean isOccupied(String[] positionArray, String position, int compteur){
        for (int i=0; i<compteur+1; i++){
            if (positionArray[i].equals(position)){
                return true;
            }
        }
        return false;
    }

     /* Usage : Methode qui regarde si une case est hors-limite : 
    {
        Args : String position : la position sur laquelle on souhaite se déplacér 
        Return : un boolean qui vaut true si la case est hors-limite, false sinon. 
    }
    */

    public static boolean isBorder(String position){
        if (position.startsWith("-1,")){
            return true;
        }
        if (position.contains("30,")){
            return true;
        }
        if (position.contains(",20")){
            return true;
        }
        if (position.contains(",-1")){
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
            
            direction = mooveManager(positionArray, currentX, currentY, turnCompteur);
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            turnCompteur = turnCompteur + 1 ;
            System.out.println(direction); // A single line with UP, DOWN, LEFT or RIGHT
        }
    }
}