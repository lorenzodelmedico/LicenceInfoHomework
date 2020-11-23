import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/



/**

[bronze] => trying to get silver 
[bronze] => 2nd launch code in arena => ended around '5'

 **/
class Player3 {

    // méthode qui renvoit l'id d'un spell si les ressources nécessaires à sa réalisation sont en inventaire
    public static int[] spellLauncher(int[][] spellbooksort, int[] inventory, int inventorystate){
        int[][] tmpSpellArray = spellbooksort;
        int[] tmpArray = inventory;
        int[] store = {-2, 1};
        boolean check = false;
        for (int row = 0; row<tmpSpellArray.length;row++){
            check = canLaunchBestSpell(tmpSpellArray[row], tmpArray, inventorystate, tmpSpellArray[row][6]);
            if (tmpSpellArray[row][5]==1 && check){
                store[0] = tmpSpellArray[row][0];
                store[1] = tmpSpellArray[row][6];

                return store;
            }
        }
        return store;
    }

    //methode qui regarde si je peux lancer la spell en parametre, en prenant en compte le nb de times qu'elle doit être lancée
    public static boolean canLaunchBestSpell(int[] spell, int[] inventory, int inventorystate, int times){
        int[] tmpInv = inventory;
        int[] tmpSpell = spell;
        int tmpCount = 0;
        int canLaunchcompteur = 0;
        boolean canLaunch = true;
        for (int i=0; i<tmpInv.length;i++){
            //test si le total de ressource ajoute + le total de ressource en inv ne depasse pas la limite 
            tmpCount = tmpCount + tmpSpell[i+1];

            //test si inv dispose des ressources pour cast
            if(tmpSpell[i+1]+tmpInv[i]>=0){
                canLaunchcompteur = canLaunchcompteur +1;
            }
        }
        if (tmpCount + inventorystate > 10){
            return false;
        }
        if (tmpSpell[6]==1 && tmpCount * times + inventorystate > 10){
            return false;
        }
        if (canLaunchcompteur!=4){
            return false;
        }
        return canLaunch;
    }


    //methode qui renvoit l'id d'une Recipe si elle peut-être créee
    public static int recipeLauncher(int[][] recipebook, int[] inventory){
        int[][] tmpRecipeArray = recipebook;
        int[] tmpArray = inventory;
        int store = -1;
        for (int row = 0; row<recipebook.length;row++){

            for (int col = 0; col<tmpRecipeArray[row].length-2;col++){
                if (tmpRecipeArray[row][col+1]+tmpArray[col]>=0) 
                {
                    store = tmpRecipeArray[row][0];
                }
                else{
                    store = -1;
                    break;
                }
            }
            if (store!=-1){
                return store;
            }
        }
        return store;
    }


    //methode qui ordonne le spellBook par bestRatio 
    public static int[][] orderRatioArray(int[][] matcharray, int[][] spellbook){
        int[][] tmpArray = new int[matcharray.length][3];
        int[][] tmpSpellArray = new int[spellbook.length][7];
        int[][] tmpMatchArray2 = matcharray;
        int tmpMatch = 0;
        int compteur = 0;
        int line = 0;
        int[] tmpMatchArray = new int[3];
        //tant que la derniere valeur de match n'a pas été attribué a tmpArray
        while(compteur < matcharray.length){
            tmpMatch = 0;
            line = 0;
            //je trie mon Array par meilleurs matchs
            for(int row = 0; row<matcharray.length; row++){
                if (tmpMatchArray2[row][1]>tmpMatch){
                    tmpMatch = tmpMatchArray2[row][1];
                    tmpMatchArray = tmpMatchArray2[row];
                    line = row;
                }
            }
            tmpArray[compteur] = tmpMatchArray;
            tmpMatchArray2[line][1] = 0;
   
            for(int row = 0; row<spellbook.length; row++){
                if (spellbook[row][0]==tmpArray[compteur][0]){
                    tmpSpellArray[compteur] = spellbook[row];
           
                    tmpSpellArray[compteur][6] = tmpArray[compteur][2];
                }
            }
            
            compteur = compteur + 1;
            
        }
        
        return tmpSpellArray;
    }

    //methode qui regarde quel spell rapporte le plus de ressource en rapport avec la meilleure recette, si repeatable regarde le best times
    public static int[][] bestMatchRatioSpellForFirst3Recipe(int[][] recipebook, int[][] spellbook){
        int[][] matchRatioArray = new int[spellbook.length][3];
        int[][] matchRatioArray2 = new int[spellbook.length][3];
        int[][] matchRatioArray3 = new int[spellbook.length][3];
        int[][] matchRatioArray4 = new int[spellbook.length][3];
        int[][] matchRatioArray5 = new int[spellbook.length][3];
        int[][] finalMatchRatioArray = new int[spellbook.length][3];
        for(int row = 0; row < spellbook.length; row++){
            matchRatioArray[row][2]=1;
            matchRatioArray2[row][2]=1;
            matchRatioArray3[row][2]=1;
            matchRatioArray4[row][2]=1;
            matchRatioArray5[row][2]=1;
            //recherche du spell qui match le mieux avec la recette [0]
            for(int col = 1; col <spellbook[col].length-2; col++){
                matchRatioArray[row][0]= spellbook[row][0];
                if (recipebook[0][col] + spellbook[row][col] == 0){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                    matchRatioArray[row][2]=1;
                }
                //si repeatable je regarde combien je dois repeter pour avoir le meilleur match
                else if (spellbook[row][6] == 1){
                    for (int i=0; i<2; i++){
                        if ((spellbook[row][col]*i) + recipebook[0][col] == 0){
                            matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                            matchRatioArray[row][2]=i;
                        }
                    }
                    for (int i=0; i<2; i++){
                        if ( ((spellbook[row][col]*i) + recipebook[0][col] == -1) && ((spellbook[row][col]*i) + recipebook[0][col] == 1) ){
                            matchRatioArray[row][1] = matchRatioArray[row][1] + 9;
                            matchRatioArray[row][2]=i;
                        }
                    }
                }
                else if ((recipebook[0][col] + spellbook[row][col] == -1) || (recipebook[0][col] + spellbook[row][col] == 1)){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 8;
                }
                else if ((recipebook[0][col] + spellbook[row][col] == -2) || (recipebook[0][col] + spellbook[row][col] == 2)){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 7;
                }
                else{
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 0;
                }

            }
              //recherche du spell qui match le mieux avec la recette [1]
            for(int col = 1; col <spellbook[col].length-2; col++){
                matchRatioArray2[row][0]= spellbook[row][0];
                if (recipebook[1][col] + spellbook[row][col] == 0){
                    matchRatioArray2[row][1] = matchRatioArray2[row][1] + 10;
                    matchRatioArray2[row][2]=1;
                }
                //si repeatable je regarde combien je dois repeter pour avoir le meilleur match
                else if (spellbook[row][6] == 1){
                    for (int i=0; i<2; i++){
                        if ((spellbook[row][col]*i) + recipebook[1][col] == 0){
                            matchRatioArray2[row][1] = matchRatioArray2[row][1] + 10;
                            matchRatioArray2[row][2]=i;
                        }
                    }
                    for (int i=0; i<2; i++){
                        if ( ((spellbook[row][col]*i) + recipebook[1][col] == -1) && ((spellbook[row][col]*i) + recipebook[1][col] == 1) ){
                            matchRatioArray2[row][1] = matchRatioArray2[row][1] + 9;
                            matchRatioArray2[row][2]=i;
                        }
                    }
                }
                else if ((recipebook[1][col] + spellbook[row][col] == -1) || (recipebook[1][col] + spellbook[row][col] == 1)){
                    matchRatioArray2[row][1] = matchRatioArray2[row][1] + 8;
                }
                else if ((recipebook[1][col] + spellbook[row][col] == -2) || (recipebook[1][col] + spellbook[row][col] == 2)){
                    matchRatioArray2[row][1] = matchRatioArray2[row][1] + 7;
                }
                else{
                    matchRatioArray2[row][1] = matchRatioArray2[row][1] + 0;
                }

            }  
            //recherche du spell qui match le mieux avec la recette [2]
            for(int col = 1; col <spellbook[col].length-2; col++){
                matchRatioArray3[row][0]= spellbook[row][0];
                if (recipebook[2][col] + spellbook[row][col] == 0){
                    matchRatioArray3[row][1] = matchRatioArray3[row][1] + 10;
                    matchRatioArray3[row][2]=1;
                }
                //si repeatable je regarde combien je dois repeter pour avoir le meilleur match
                else if (spellbook[row][6] == 1){
                    for (int i=0; i<2; i++){
                        if ((spellbook[row][col]*i) + recipebook[2][col] == 0){
                            matchRatioArray3[row][1] = matchRatioArray3[row][1] + 10;
                            matchRatioArray3[row][2]=i;
                        }
                    }
                    for (int i=0; i<2; i++){
                        if ( ((spellbook[row][col]*i) + recipebook[2][col] == -1) && ((spellbook[row][col]*i) + recipebook[2][col] == 1) ){
                            matchRatioArray3[row][1] = matchRatioArray3[row][1] + 9;
                            matchRatioArray3[row][2]=i;
                        }
                    }
                }
                else if ((recipebook[2][col] + spellbook[row][col] == -1) || (recipebook[2][col] + spellbook[row][col] == 1)){
                    matchRatioArray3[row][1] = matchRatioArray3[row][1] + 8;
                }
                else if ((recipebook[2][col] + spellbook[row][col] == -2) || (recipebook[2][col] + spellbook[row][col] == 2)){
                    matchRatioArray3[row][1] = matchRatioArray3[row][1] + 7;
                }
                else{
                    matchRatioArray3[row][1] = matchRatioArray3[row][1] + 0;
                }

            }
              //recherche du spell qui match le mieux avec la recette [3]
            for(int col = 1; col <spellbook[col].length-2; col++){
                matchRatioArray5[row][0]= spellbook[row][0];
                if (recipebook[3][col] + spellbook[row][col] == 0){
                    matchRatioArray5[row][1] = matchRatioArray5[row][1] + 10;
                    matchRatioArray5[row][2]=1;
                }
                //si repeatable je regarde combien je dois repeter pour avoir le meilleur match
                else if (spellbook[row][6] == 1){
                    for (int i=0; i<2; i++){
                        if ((spellbook[row][col]*i) + recipebook[3][col] == 0){
                            matchRatioArray5[row][1] = matchRatioArray5[row][1] + 10;
                            matchRatioArray5[row][2]=i;
                        }
                    }
                    for (int i=0; i<2; i++){
                        if ( ((spellbook[row][col]*i) + recipebook[3][col] == -1) && ((spellbook[row][col]*i) + recipebook[3][col] == 1) ){
                            matchRatioArray5[row][1] = matchRatioArray5[row][1] + 9;
                            matchRatioArray5[row][2]=i;
                        }
                    }
                }
                else if ((recipebook[3][col] + spellbook[row][col] == -1) || (recipebook[3][col] + spellbook[row][col] == 1)){
                    matchRatioArray5[row][1] = matchRatioArray5[row][1] + 8;
                }
                else if ((recipebook[3][col] + spellbook[row][col] == -2) || (recipebook[3][col] + spellbook[row][col] == 2)){
                    matchRatioArray5[row][1] = matchRatioArray5[row][1] + 7;
                }
                else{
                    matchRatioArray5[row][1] = matchRatioArray5[row][1] + 0;
                }

            }
              //recherche du spell qui match le mieux avec la recette [4]
            for(int col = 1; col <spellbook[col].length-2; col++){
                matchRatioArray4[row][0]= spellbook[row][0];
                if (recipebook[4][col] + spellbook[row][col] == 0){
                    matchRatioArray4[row][1] = matchRatioArray4[row][1] + 10;
                    matchRatioArray4[row][2]=1;
                }
                //si repeatable je regarde combien je dois repeter pour avoir le meilleur match
                else if (spellbook[row][6] == 1){
                    for (int i=0; i<2; i++){
                        if ((spellbook[row][col]*i) + recipebook[4][col] == 0){
                            matchRatioArray4[row][1] = matchRatioArray4[row][1] + 10;
                            matchRatioArray4[row][2]=i;
                        }
                    }
                    for (int i=0; i<2; i++){
                        if ( ((spellbook[row][col]*i) + recipebook[4][col] == -1) && ((spellbook[row][col]*i) + recipebook[4][col] == 1) ){
                            matchRatioArray4[row][1] = matchRatioArray4[row][1] + 9;
                            matchRatioArray4[row][2]=i;
                        }
                    }
                }
                else if ((recipebook[4][col] + spellbook[row][col] == -1) || (recipebook[4][col] + spellbook[row][col] == 1)){
                    matchRatioArray4[row][1] = matchRatioArray4[row][1] + 8;
                }
                else if ((recipebook[4][col] + spellbook[row][col] == -2) || (recipebook[4][col] + spellbook[row][col] == 2)){
                    matchRatioArray4[row][1] = matchRatioArray4[row][1] + 7;
                }
                else{
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 0;
                }

            }
        }
        for(int row = 0; row<matchRatioArray.length ; row ++){
            if (matchRatioArray[row][1] > matchRatioArray2[row][1]){
                finalMatchRatioArray = matchRatioArray;
            }
            else{
                finalMatchRatioArray = matchRatioArray2;
            }
            if (matchRatioArray3[row][1] > finalMatchRatioArray[row][1]){
                finalMatchRatioArray = matchRatioArray3;
            }
            if (matchRatioArray4[row][1] > finalMatchRatioArray[row][1]){
                finalMatchRatioArray = matchRatioArray4;
            }
            if (matchRatioArray5[row][1] > finalMatchRatioArray[row][1]){
                finalMatchRatioArray = matchRatioArray5;
            }
            
        }
        return finalMatchRatioArray;
    }
    // retourne le spell a apprendre ce tour ci 
    public static int iWantToLearnThemAll(int[] learnbook){
        return learnbook[0];
    }

    //méthode qui remplit le livre des 4 Spell de base 
    public static int[] fillSpellBook(int actionId, int d0, int d1, int d2, int d3, int castable, int repeatable){
        int[] tmpArray = {actionId, d0, d1, d2, d3, castable, repeatable};
        return tmpArray;
    }

    //méthode qui remplit le livre depuis le grimoire
    public static int[] fillfromSpellBook(int actionId, int d0, int d1, int d2, int d3, int castable, int repeatable){
        int[] tmpArray = {actionId, d0, d1, d2, d3, castable, repeatable};
        return tmpArray;
    }

    //méthode qui remplit le livre de Recipes 
    public static int[] fillRecipeBook(int actionId, int d0, int d1, int d2, int d3, int price){
        int[] tmpArray = {actionId, d0, d1, d2, d3, price};
        return tmpArray;
    }

    //méthode qui remplit l'inventaire
    public static int[] fillInventory(int inv0, int inv1, int inv2, int inv3){
        int[] tmpArray = {inv0, inv1, inv2, inv3};
        return tmpArray;
    }


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int potionCompteur = 0;
        int restCompteur = 0;
        String actionToDo = "LEARN ";
        // game loop
        while (true) {
            int actionCount = in.nextInt(); // the number of spells and recipes in play
            int id = 0;
            int times = 0;
            int[] tmpid = new int[2];
            int castCount = 0;
            int recipecount = 0;
            int castableInt = 0;
            int castedSpell = 0;
            int repeatableInt = 0;
            int inventoryState = 0;
            int[] inv = new int[3];
            int[][] spellBook = new int[42][7];
            int[][] matchBook = new int[42][3];
            int[] learnBook = new int[6];
            int[][] recipeBook = new int[5][6];
            for (int i = 0; i < actionCount; i++) {
                int actionId = in.nextInt(); // the unique ID of this spell or recipe
                String actionType = in.next(); // in the first league: BREW; later: CAST, OPPONENT_CAST, LEARN, BREW
                int d0 = in.nextInt(); // tier-0 ingredient change
                int d1 = in.nextInt(); // tier-1 ingredient change
                int d2 = in.nextInt(); // tier-2 ingredient change
                int d3 = in.nextInt(); // tier-3 ingredient change
                int price = in.nextInt(); // the price in rupees if this is a potion
                int tomeIndex = in.nextInt(); // in the first two leagues: always 0; later: the index in the tome if this is a tome spell, equal to the read-ahead tax; For brews, this is the value of the current urgency bonus
                int taxCount = in.nextInt(); // in the first two leagues: always 0; later: the amount of taxed tier-0 ingredients you gain from learning this spell; For brews, this is how many times you can still gain an urgency bonus
                boolean castable = in.nextInt() != 0; // in the first league: always 0; later: 1 if this is a castable player spell
                boolean repeatable = in.nextInt() != 0; // for the first two leagues: always 0; later: 1 if this is a repeatable player spell  
                if (castable){
                    castableInt = 1;
                }
                else if(!castable){
                    castableInt = 0;
                }
                if (repeatable){
                    repeatableInt = 1;
                }
                else if(!repeatable){
                    repeatableInt = 0;
                }
                if (actionType.equals("BREW")){ 
                    recipeBook[recipecount] = fillRecipeBook(actionId, d0, d1, d2, d3, price);
                    recipecount = recipecount + 1;
                }
                if (actionType.equals("CAST")){
                    spellBook[castCount] = fillSpellBook(actionId, d0, d1, d2, d3, castableInt, repeatableInt);
                    castCount = castCount+1;
                }
                if (actionType.equals("LEARN") && tomeIndex == 0){
                    learnBook = fillfromSpellBook(actionId, d0, d1, d2, d3, castableInt, repeatableInt);
                }   
            }
            for (int i = 0; i < 2; i++) {
                int inv0 = in.nextInt(); // tier-0 ingredients in inventory
                int inv1 = in.nextInt();
                int inv2 = in.nextInt();
                int inv3 = in.nextInt();
                int score = in.nextInt(); // amount of rupees
                if (i==0){
                    inv = fillInventory(inv0, inv1, inv2, inv3);
                    inventoryState = inv0+inv1+inv2+inv3;
                }
                
                
            }

            //tant qu'on a pas 12 spell dans le spellBook on en apprends de nouveaux
            if (potionCompteur > restCompteur){
                restCompteur = restCompteur + 1;
                System.out.println("REST");
            }
            else if (castCount<13){
                actionToDo = "LEARN ";
                id = iWantToLearnThemAll(learnBook);
                System.out.println(actionToDo + id);
            }
            else{
                matchBook = bestMatchRatioSpellForFirst3Recipe(recipeBook, spellBook);
                spellBook = orderRatioArray(matchBook, spellBook);
                if(id == 0){
                    id = recipeLauncher(recipeBook, inv);
                    actionToDo = "BREW ";
                }
                if (id == -1){
                    tmpid = spellLauncher(spellBook, inv, inventoryState);
                    id = tmpid[0];
                    actionToDo = "CAST ";
                    times = tmpid[1];
                }
                if (id == -2){
                    actionToDo = "LEARN ";
                    id = iWantToLearnThemAll(learnBook);
                    System.out.println(actionToDo + id);
                }
                if (actionToDo == "CAST " && times>1){
                    System.out.println(actionToDo + id + " " + times); 
                }
                else if (actionToDo == "CAST "){
                    System.out.println(actionToDo + id);
                }
                else if (actionToDo == "BREW "){
                    potionCompteur = potionCompteur + 1 ;
                    System.out.println(actionToDo + id);
                }
                
            }
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // in the first league: BREW <id> | WAIT; later: BREW <id> | CAST <id> [<times>] | LEARN <id> | REST | WAIT
            
        }
    }
}
