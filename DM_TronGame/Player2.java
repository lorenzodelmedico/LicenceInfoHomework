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
    //Idées pour améliorer : Implémenter l'algorithme du premier DM, en utilisant la stratégie suivante : b (le point de cible) représente la position actuelle et on simule 4 fois (une fois par position adjacente) 
    // avec un a qui correspond à la case non occupé la plus eloigné sur le board , de chaque coin (0,0 ; 0,19 ; 29,0; 29,19 : au début)
    // comportement espéré : on obtient sur chaque case adjacente un score , on selectionne le meilleur

class Player2 {

    /* Usage : Methode qui sert à afficher la Matrice pour du debug : 
        Args : int[][] matrice : matrice à afficher
        Body : affiche la matrice après transformation du format. 
    */

    public static void afficheMatrice(int[][] matrice)
	{
        // Formatage d'un affichage compréhensible de la matrice
		for(int i = 0 ; i < matrice.length ; i++)
		{
			for (int j = 0 ; j < matrice[i].length ; j++)
				System.err.format("%6d", matrice[i][j]);
			System.err.println();
		}
	}

    /* Usage : Methode qui configure la première ligne et colonne de la matrice, ces valeurs nous permettent de réaliser nos calculs ensuite : 
    {
        Args : int[][] matrice : matrice à afficher
        Return :  int[][] matrice : matrice configurer 
    */

    public static int[][] configMatrix(int[][] matrix){
        matrix[0][0] = 1;
        matrix[21][0] = 1;
        matrix[0][31] = 1;
        matrix[21][31] = 1;
        for(int j = 1; j < matrix[0].length-1; j++){
              matrix[0][j] = 1;
              matrix[21][j] = 1;
        }
        for(int i = 1; i < matrix.length-1; i++) {
                matrix[i][0] = 1;
                matrix[i][31] = 1;
        }

        return matrix;
    }

    /* Usage : Methode qui implemente la fonction totalPath, permettant de varier les valeurs col/row et X/Y
        Args : int[][] matrice : matrice pour le calcul ; intX, int Y : permet de modifier la part de la matrice a calculer
        Body : on transforme une copie de la matrice afin de réaliser nos calculs
        Return :  int qui correspond a la valeur totale de chemin entre a et b
    */

     public static String FindAwayPosition(int[][] matrix, String fixedCorner) {
        String[] tmpStart = fixedCorner.split(",");
        int row=Integer.parseInt(tmpStart[0]);          
        int col=Integer.parseInt(tmpStart[1]);
        //recherche la première position libre dans la matrice à partir d'une position donnée. 
        for(int i=row+1; i < matrix.length; i++) {
            for(int j = col+1; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    return (i-1) +","+(j-1);
                }
            }
        }
        return "0,0";
    }

    /* Usage : Methode qui calcul la distance en chemin pour se rendre d'un point a à un point b :
        Args : int[][] matrice : matrice pour le calcul ; intX, int Y : permet de modifier la position de fin du calcul ; String start: permet de modifier la position de depart du calcul
        Body : on transforme une copie de la matrice afin de réaliser nos calculs
        Return :  int qui correspond a la valeur totale de chemin entre a et b
    */

    public static int totalPathTopLeft(int[][] matrix, int X, int Y, String start) {
        //calcule le nb de chemins depuis la position (i, j) jusqu'à la position du player (X,Y)
        String[] tmpStart = start.split(",");
        int row=Integer.parseInt(tmpStart[0]);          
        int col=Integer.parseInt(tmpStart[1]); 
        for(int i=row+1; i < Y; i++) {
            for(int j = col+1; j < X; j++) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] = 0;
                }
                else if(matrix[i][j] == 0) {
                    matrix[i][j] = matrix[i][j-1] + matrix[i-1][j];
                }
            }
        }
        System.err.println(matrix[Y][X]);
        return matrix[Y+1][X+1];
    }

    /* Usage : Methode qui calcul la distance en chemin pour se rendre d'un point a à un point b :
        Args : int[][] matrice : matrice pour le calcul ; intX, int Y : permet de modifier la position de fin du calcul ; String start: permet de modifier la position de depart du calcul
        Body : on transforme une copie de la matrice afin de réaliser nos calculs
        Return :  int qui correspond a la valeur totale de chemin entre a et b
    */

    public static int totalPathBottomLeft(int[][] matrix, int X, int Y, String start) {
        //calcule le nb de chemins depuis la position (i, j) jusqu'à la position du player (X,Y)
        String[] tmpStart = start.split(",");
        int row=Integer.parseInt(tmpStart[0]);          
        int col=Integer.parseInt(tmpStart[1]); 
        for(int i=row+1; i > Y; i--) {
            for(int j = col+1; j < X; j++) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] = 0;
                }
                else if(matrix[i][j] == 0) {
                    matrix[i][j] = matrix[i][j+1] + matrix[i+1][j];
                }
            }
        }
        System.err.println(matrix[Y][X+1]);
        return matrix[Y+1][X+1];
    }

    public static int totalPathTopRight(int[][] matrix, int X, int Y, String start) {
        //calcule le nb de chemins depuis la position (i, j) jusqu'à la position du player (X,Y)
        String[] tmpStart = start.split(",");
        int row=Integer.parseInt(tmpStart[0]);          
        int col=Integer.parseInt(tmpStart[1]); 
        for(int i=row+1; i < Y; i++) {
            for(int j = col+1; j > X; j--) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] = 0;
                }
                else if(matrix[i][j] == 0) {
                    matrix[i][j] = matrix[i][j+1] + matrix[i-1][j];
                }
            }
        }
        System.err.println(matrix[Y+1][X+1]);
        return matrix[Y+1][X+1];
    }

    public static int totalPathBottomRight(int[][] matrix, int X, int Y, String start) {
        //calcule le nb de chemins depuis la position (i, j) jusqu'à la position du player (X,Y)
        String[] tmpStart = start.split(",");
        int col=Integer.parseInt(tmpStart[0]);          
        int row=Integer.parseInt(tmpStart[1]); 
        for(int i=row+1; i > Y; i--) {
            for(int j = col+1; j > X; j--) {
                if(matrix[i][j] == 1) {
                    matrix[i][j] = 0;
                }
                else if(matrix[i][j] == 0) {
                    matrix[i][j] = matrix[i][j+1] + matrix[i+1][j];
                }
            }
        }
        System.err.println(matrix[Y+1][X+1]);
        return matrix[Y+1][X+1];
    }
    
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
        int opponentX=0;
        int opponentY=0;
        String positionCorners = "";
        String[] fixedFurthestCorner = {
            "0,0",
            "0,19",
            "29,0",
            "29,19"
            };

        // Instanciation of Matrix 
        int[][] matrix = new int[22][32];

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
                    else{
                        opponentX = X0;
                        opponentY = Y0;
                    }
                    positionArray[i][turnCompteur]=fillPlayerPosition(X0, Y0);
                }
                else{
                    if (i == P){
                        currentX = X1;
                        currentY = Y1;
                    }
                    else{
                        opponentX = X1;
                        opponentY = Y1;
                    }
                    positionArray[i][turnCompteur]=fillPlayerPosition(X1, Y1);
                }
            }
            
            //Matrix init 
            for (int i = 0; i < 22; i++) {
                for(int j = 0; j < 32; j++) {
                    //prend la position actuelle des joueurs en jeu et indique que leur position est bloque sur le board
                    if ( ( (i==currentY+1) && (j==currentX+1) ) || ( (i==opponentY+1) && (j==opponentX+1) ) ) {
                        matrix[i][j] = 1 ;  
                    }
                    else if(matrix[i][j] == 1){
                        matrix[i][j] = 1 ;
                    }
                    else{
                        // si aucun joueur ne s'est encore rendu sur la case alors elle est libre
                        matrix[i][j] = 0;
                    }
                    
                }
            }
            matrix = configMatrix(matrix) ;
            int bestPath = 0;
            // on boucle 4 fois pour tester le nb de chemins depuis les 4 fixedCOrner. Puis on selectionne le meilleur. 
            for (int i = 0; i<4;i++){
                positionCorners = fixedFurthestCorner[i];
                String positionTest = (currentX-1) + "," + (currentY);
                if (positionCorners == "0,0"){
                    // on teste les path pour les valeurs x+1, -1, y+1, -1
                    if(canMoove(positionArray, positionTest, turnCompteur)){
                        int[][] matrixRecopie = new int[22][32];
                        matrixRecopie = matrix;
                        int path = totalPathTopLeft(matrixRecopie, currentX+1, currentY, positionCorners);
                        if (path > bestPath){
                            bestPath = path ;
                            direction = mooveDirector(currentX, currentY, currentX+1, currentY);
                        }
                    }
                    positionTest = (currentX) + "," + (currentY-1);
                    // on teste les path pour les valeurs x+1, -1, y+1, -1
                    if(canMoove(positionArray, positionTest, turnCompteur)){
                        int[][] matrixRecopie = new int[22][32];
                        matrixRecopie = matrix;
                        int path = totalPathTopLeft(matrixRecopie, currentX, currentY+1, positionCorners);
                        if (path > bestPath){
                            bestPath = path ;
                            direction = mooveDirector(currentX, currentY, currentX, currentY+1);
                        }
                    }
                }
                if (positionCorners == "0,19"){
                    positionTest = (currentX+1) + "," + (currentY);
                    // on teste les path pour les valeurs x+1, -1, y+1, -1
                    if(canMoove(positionArray, positionTest, turnCompteur)){
                        int[][] matrixRecopie = new int[22][32];
                        matrixRecopie = matrix;
                        int path = totalPathBottomLeft(matrixRecopie, currentX-1, currentY, positionCorners);
                        if (path > bestPath){
                            bestPath = path ;
                            direction = mooveDirector(currentX, currentY, currentX-1, currentY);
                        }
                    }
                    positionTest = (currentX) + "," + (currentY-1);
                    // on teste les path pour les valeurs x+1, -1, y+1, -1
                    if(canMoove(positionArray, positionTest, turnCompteur)){
                        int[][] matrixRecopie = new int[22][32];
                        matrixRecopie = matrix;
                        int path = totalPathBottomLeft(matrixRecopie, currentX, currentY+1, positionCorners);
                        if (path > bestPath){
                            bestPath = path ;
                            direction = mooveDirector(currentX, currentY, currentX, currentY+1);
                        }
                    }
                }
                if (positionCorners == "29,19"){
                    positionTest = (currentX+1) + "," + (currentY);
                    // on teste les path pour les valeurs x+1, -1, y+1, -1
                    if(canMoove(positionArray, positionTest, turnCompteur)){
                        int[][] matrixRecopie = new int[22][32];
                        matrixRecopie = matrix;
                        int path = totalPathBottomRight(matrixRecopie, currentX-1, currentY, positionCorners);
                        if (path > bestPath){
                            bestPath = path ;
                            direction = mooveDirector(currentX, currentY, currentX-1, currentY);
                        }
                    }
                    positionTest = (currentX) + "," + (currentY+1);
                    // on teste les path pour les valeurs x+1, -1, y+1, -1
                    if(canMoove(positionArray, positionTest, turnCompteur)){
                        int[][] matrixRecopie = new int[22][32];
                        matrixRecopie = matrix;
                        int path = totalPathBottomRight(matrixRecopie, currentX, currentY-1, positionCorners);
                        if (path > bestPath){
                            bestPath = path ;
                            direction = mooveDirector(currentX, currentY, currentX, currentY-1);
                        }
                    }
                }
                if (positionCorners == "29,0"){
                    positionTest = (currentX) + "," + (currentY+1);
                    // on teste les path pour les valeurs x+1, -1, y+1, -1
                    if(canMoove(positionArray, positionTest, turnCompteur)){
                        int[][] matrixRecopie = new int[22][32];
                        matrixRecopie = matrix;
                        int path = totalPathTopRight(matrixRecopie, currentX, currentY-1, positionCorners);
                        if (path > bestPath){
                            bestPath = path ;
                            direction = mooveDirector(currentX, currentY, currentX, currentY-1);
                        }
                    }
                    positionTest = (currentX+1) + "," + (currentY);
                    // on teste les path pour les valeurs x+1, -1, y+1, -1
                    if(canMoove(positionArray, positionTest, turnCompteur)){
                        int[][] matrixRecopie = new int[22][32];
                        matrixRecopie = matrix;
                        int path = totalPathBottomRight(matrixRecopie, currentX-1, currentY, positionCorners);
                        if (path > bestPath){
                            bestPath = path ;
                            direction = mooveDirector(currentX, currentY, currentX-1, currentY);
                        }
                    }
                }
            }
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            turnCompteur = turnCompteur + 1 ;
            System.out.println(direction); // A single line with UP, DOWN, LEFT or RIGHT
        }
    }
}