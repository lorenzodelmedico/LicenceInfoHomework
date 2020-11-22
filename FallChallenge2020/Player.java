import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/



/**

[bronze] => trying to get silver 

 **/
class Player {

    // méthode qui renvoit l'id d'un spell si les ressources nécessaires à sa réalisation sont en inventaire
    public static int spellLauncher(int[][] spellbooksort, int[] inventory, int inventorystate){
        int[][] tmpSpellArray = spellbooksort;
        int[] tmpArray = inventory;
        int store = -2;
        int tmpTime = 0;
        boolean check = false;
        for (int row = 0; row<tmpSpellArray.length;row++){
            if (tmpSpellArray[row][6]!=0){
                tmpTime = tmpSpellArray[row][6];
            }
            check = canLaunchBestSpell(tmpSpellArray[row], tmpArray, inventorystate, tmpTime);
            if (tmpSpellArray[row][5]==1 && check){
                return store = tmpSpellArray[row][0];
            }
        }
        return store;
    }

    public static boolean canLaunchBestSpell(int[] spell, int[] inventory, int inventorystate, int times){
        int[] tmpInv = inventory;
        int[] tmpSpell = spell;
        int tmpCount = 0;
        int canLaunchcompteur = 0;
        boolean canLaunch = true;
        if (inventorystate>=9){
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
        }
        if (inventorystate>6){
            for (int i=0; i<tmpInv.length;i++){
                //test si le total de ressource ajoute + le total de ressource en inv ne depasse pas la limite 
                tmpCount = tmpCount + tmpSpell[i+1];
                //test si on dispose de 1 ou 0 ressource bleu et que le sort +2 arrive
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
        }
        else{
            for (int i=0; i<tmpInv.length;i++){
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
        int tmpMatch = 0;
        int compteur = 0;
        int[] tmpMatchArray = new int[3];
        //tant que la derniere valeur de match n'a pas été attribué a tmpArray
        while(compteur < matcharray.length){
            //je trie mon Array par meilleurs matchs
            for(int row = 0; row<matcharray.length; row++){
                if (matcharray[row][1]>tmpMatch){
                    tmpMatch = matcharray[row][1];
                    tmpMatchArray = matcharray[row];
                }
            }
            tmpArray[compteur] = tmpMatchArray;
            for(int row = 0; row<spellbook.length; row++){
                if (spellbook[row][0]==tmpArray[compteur][0]){
                    tmpMatch = matcharray[row][1];
                    tmpSpellArray[compteur] = spellbook[row];
                    tmpSpellArray[compteur][6] = tmpArray[compteur][3];
                }
            }
        }
        
        return tmpSpellArray;
    }

    //methode qui regarde quel spell rapporte le plus de ressource en rapport avec la meilleure recette [0], si repeatable regarde le best times
    public static int[][] bestMatchRatioSpellForFirst3Spell(int[][] recipebook, int[][] spellbook){
        int[][] matchRatioArray = new int[spellbook.length][3];
        for(int row = 0; row < spellbook.length; row++){
            //recherche du spell qui match le mieux avec la recette [1]
            for(int col = 1; col <spellbook[col].length-2; col++){
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
                            matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                            matchRatioArray[row][2]=i;
                        }
                    }
                }
                else if ((recipebook[0][col] + spellbook[row][col] == -1) || (recipebook[0][col] + spellbook[row][col] == 1)){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                    matchRatioArray[row][2]=1;
                }
                else if ((recipebook[0][col] + spellbook[row][col] == -2) || (recipebook[0][col] + spellbook[row][col] == 2)){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                    matchRatioArray[row][2]=1;
                }
                else{
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 0;
                    matchRatioArray[row][2]=1;
                }

            }
        }

        return matchRatioArray;
    }

     //methode qui regarde quel spell rapporte le plus de ressource en rapport avec la meilleure recette [1], si repeatable regarde le best times
    public static int[][] bestMatchRatioSpellForLast3Spell(int[][] recipebook, int[][] spellbook){
        int[][] matchRatioArray = new int[spellbook.length][3];
        for(int row = 0; row < spellbook.length; row++){
            //recherche du spell qui match le mieux avec la recette [1]
            for(int col = 1; col <spellbook[col].length-2; col++){
                if (recipebook[1][col] + spellbook[row][col] == 0){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                    matchRatioArray[row][2]=1;
                }
                //si repeatable je regarde combien je dois repeter pour avoir le meilleur match
                else if (spellbook[row][6] == 1){
                    for (int i=0; i<2; i++){
                        if ((spellbook[row][col]*i) + recipebook[1][col] == 0){
                            matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                            matchRatioArray[row][2]=i;
                        }
                    }
                    for (int i=0; i<2; i++){
                        if ( ((spellbook[row][col]*i) + recipebook[1][col] == -1) && ((spellbook[row][col]*i) + recipebook[1][col] == 1) ){
                            matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                            matchRatioArray[row][2]=i;
                        }
                    }
                }
                else if ((recipebook[1][col] + spellbook[row][col] == -1) || (recipebook[1][col] + spellbook[row][col] == 1)){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                    matchRatioArray[row][2]=1;
                }
                else if ((recipebook[1][col] + spellbook[row][col] == -2) || (recipebook[1][col] + spellbook[row][col] == 2)){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 10;
                    matchRatioArray[row][2]=1;
                }
                else{
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 0;
                    matchRatioArray[row][2]=1;
                }

            }
        }

        return matchRatioArray;
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
        int id = 0;
        String actionToDo = "REST";
        // game loop
        while (true) {
            int actionCount = in.nextInt(); // the number of spells and recipes in play
            int castCount = 0;
            int castableInt = 0;
            int repeatableInt = 0;
            int inventoryState = 0;
            int[] inv = new int[3];
            int[][] emptyreference = new int[2][];
            int[][] spellBook = new int[10][];
            int[] learnBook = new int[6];
            int[][] recipeBook = new int[2][];
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
                if (actionType.equals("BREW") && tomeIndex == 3){ 
                    recipeBook[0] = fillRecipeBook(actionId, d0, d1, d2, d3, price);
                }
                else if(actionType.equals("BREW") && tomeIndex == 1 ){
                    recipeBook[1] = fillRecipeBook(actionId, d0, d1, d2, d3, price);
                }  
                else if (actionType.equals("BREW") && recipeBook.equals(emptyreference)){
                    recipeBook[0] = fillRecipeBook(actionId, d0, d1, d2, d3, price);
                }
                if (actionType.equals("CAST")){
                    spellBook[castCount] = fillSpellBook(actionId, d0, d1, d2, d3, castableInt, repeatableInt);
                    castCount = castCount+1;
                }
                 if (actionType.equals("LEARN") && taxCount == 0){
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

            //tant qu'on a pas 12 spell dans le livre on apprend de nouveaux 
            if (castCount<=12){
                actionToDo = "LEARN ";
                id = iWantToLearnThemAll(learnBook);
                System.out.println(actionToDo + id);
            }
            id = recipeLauncher(recipeBook, inv);
            actionToDo = "BREW ";
            if (id == -1){
                id = spellLauncher(spellBook, inv, inventoryState);
                actionToDo = "CAST ";
            }
            if (id == -2){
                actionToDo = "REST";
                System.out.println(actionToDo);
            }
            if (id!= -1 && id!=-2){
                System.out.println(actionToDo + id);
            }            

            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // in the first league: BREW <id> | WAIT; later: BREW <id> | CAST <id> [<times>] | LEARN <id> | REST | WAIT
            
        }
    }
}