import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/



/**
spell:
    1. Lancer les sort dit gratuit
    2. Si tout les sorts sont épuisés je REST 
    3. Si l'opponent cast ? 

bestRecipe:
    1.Regarde les ingredients dans l'inventaire, 
    2. Choisir la recette avec the best price
 **/
class Player2 {

    // méthode qui renvoit l'id d'un spell si les ressources nécessaires à sa réalisation sont en inventaire
    public static int spellLauncher(int[][] spellbook, int[] inventory, int spellcount){
        int[][] tmpSpellArray = spellbook;
        int[] tmpArray = inventory;
        int store = -2;
        boolean check = false;
        for (int row = 0; row<spellcount-1;row++){
            for (int col = 0; col<tmpSpellArray[row].length-2;col++){
                check = spellCanLaunch(tmpSpellArray[row], tmpArray);
                if (tmpSpellArray[row][5] == 1 && check){
                    return store = tmpSpellArray[row][0];
                }
            }
        }
        return store;
    }

    public static boolean spellCanLaunch(int[] spell, int[] inventory){
        int[] tmpInv = inventory;
        int[] tmpSpell = spell;
        boolean check = false;
        int sumSpell = 0;
        int sumInv = 0;
        for (int i=0; i<tmpInv.length;i++){
            sumSpell = sumSpell + tmpSpell[i+1];
            sumInv = sumInv + tmpInv[i];
            if(tmpInv[i]>=tmpSpell[i+1]){
                check = true;
            }
            else if(sumSpell+sumInv>=10){
                return false;
            }
            else{
                check = false;
            }
        }
        return check;
    }

    //methode qui renvoit l'id d'une Recipe si elle peut-être créee
    public static int recipeLauncher(int[][] recipebook, int[] inventory, int recipecount){
        int[][] tmpRecipeArray = recipebook;
        int[] tmpArray = inventory;
        int store = -1;
        boolean check = false;
        for (int row = 0; row<recipecount-1;row++){

            for (int col = 0; col<tmpRecipeArray[row].length-2;col++){

                if (tmpRecipeArray[row][col+1]+tmpArray[col]>=0) 
                {
                    check = true;
                    store = tmpRecipeArray[row][0];
                }
                else{
                    return store = -1;
                }
            }
            if (check){
                return store;
            }
        }
        return store;
    }

    //méthode qui remplit le livre de Spell 
    public static int[] fillSpellBook(int actionId, int d0, int d1, int d2, int d3, int castable){
        int[] tmpArray = {actionId, d0, d1, d2, d3, castable};
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
        Boolean spellToUse = true;
        Boolean inventoryFull = false;
        // game loop
        while (true) {
            int actionCount = in.nextInt(); // the number of spells and recipes in play
            int castCount = 0;
            int recipeCount = 0;
            int castableInt = 0;
            int[] inv = new int[3];
            int[][] spellBook = new int[actionCount][5];
            int[][] recipeBook = new int[actionCount][5];
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
                if (actionType.equals("BREW")){ 
                    recipeBook[recipeCount] = fillRecipeBook(actionId, d0, d1, d2, d3, price);
                    recipeCount = recipeCount+1;
                }
                if (actionType.equals("CAST")){
                    spellBook[castCount] = fillSpellBook(actionId, d0, d1, d2, d3, castableInt);
                    castCount = castCount+1;
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
                    if(inv0+inv1+inv2+inv3==10){
                        inventoryFull = true;
                    }
                }
                
            }

            id = recipeLauncher(recipeBook, inv, recipeCount);
            actionToDo = "BREW ";
            if (id == -1){
                id = spellLauncher(spellBook, inv, castCount);
                actionToDo = "CAST ";
            }
            if (id == -2){
                actionToDo = "REST";
                System.out.println(actionToDo);
            }
            if (id!= -1 && id!=-2){
                System.out.println(actionToDo + id);
            }
            System.out.println("REST");            

            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // in the first league: BREW <id> | WAIT; later: BREW <id> | CAST <id> [<times>] | LEARN <id> | REST | WAIT
            
        }
    }
}