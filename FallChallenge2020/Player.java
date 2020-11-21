import java.util.*;
import java.io.*;
import java.math.*;
import java.lang.*;

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
    1 blue = 0.5 turn 
    1 green = 1 turn 
    1 orange = green + 1 turn 
    1 yellow = orange + 1 turn 
 **/
class Player {

    //methode qui regarde la recipe la moins cher en temps et se concentre dessus
    public static double getRecipeTime(int[] recipe){
        double time = 0;
        for (int row = 0; row<recipe.length;row++){
            time = time - (recipe[row]*0.5) - (recipe[row]*1) - (recipe[row]*2) - (recipe[row]*3);
        }
        return time;
    }

    //methode qui appel getRecipeTime et retourne la meilleur recipe en fct de son temps puis prix 
    public static int getRecipePriority(int[][]recipebook, int recipecount){
        double[] time = new double[recipecount];
        int[] price = new int[recipecount];
        int[] bestId = new int[recipecount];
        int[][] tmpRecipeArray = recipebook;
        double bestTime = 10000;
        int bestPrice = 0;
        int bestRecipeId = 0;
        for (int row = 0; row<recipecount-1;row++){
            time[row] = getRecipeTime(tmpRecipeArray[row]);
            price[row] = tmpRecipeArray[row][5];
            bestId[row] = tmpRecipeArray[row][0];
        }
        for (int j = 0; j<time.length;j++){
            if (time[j] < bestTime){
                bestTime = time[j];
                bestPrice = price[j];
                bestRecipeId = bestId[j];
            }
            if (time[j]==bestTime){
                if (price[j] > bestPrice){
                    bestTime = time[j];
                    bestPrice = price[j];
                    bestRecipeId = bestId[j];
                }
            }
        }

        return bestRecipeId;
    }

    // reste à faire => ressources nécessaires à la best Recipe puis spell nécessaires à la best recipe;
    

    // méthode qui renvoit l'id d'un spell si les ressources nécessaires à sa réalisation sont en inventaire
    public static int spellLauncher(int[][] spellbook, int[] inventory, int spellcount){
        int[][] tmpSpellArray = spellbook;
        int[] tmpArray = inventory;
        int store = -1;
        for (int row = 0; row<spellcount-1;row++){
            for (int col = 0; col<tmpSpellArray[row].length;col++){
                if (spellCanLaunch(tmpSpellArray[row], tmpArray)){
                    store = tmpSpellArray[row][0];
                }
                if(inventoryMayBeFull(tmpArray, tmpSpellArray[row])){
                    store = -1;
                }
            }
        }
        return store;
    }

    // méthode qui regarde si l'inventaire dispose des ressources nécessaires à la réalisation d'un spell 
    public static boolean spellCanLaunch(int[] spell, int[] inventory){
        int[] tmpInv = inventory;
        int[] tmpSpell = spell;
        boolean check = false;
        for (int i=0; i<tmpInv.length;i++){
            if(tmpInv[i]>=tmpSpell[i+1]){
                check = true;
            }
            else{
                check = false;
            }
        }
        return check;
    }
    
    //méthode qui regarde si on a encore des spell castable
    public static boolean someSpellAreCastable(int[][] spellbook, int spellcount){
        int[][] tmpSpellArray = spellbook;
        int tmpSpellUsed = 0;
        for (int row = 0; row<spellcount-1;row++){
            if (tmpSpellArray[row][5] == 0){
                tmpSpellUsed = tmpSpellUsed+1;
            }
            if (spellcount == tmpSpellUsed){
                return false;
            }
        }
        return true;
    }

    // méthode qui regarde si l'inventaire dispose des ressources nécessaires à la réalisation d'un recipe
    public static boolean recipeCanLaunch(int[] recipe, int[] inventory){
        int[] tmpInv = inventory;
        int[] tmpRecipe = recipe;
        boolean check = false;
        for (int i=0; i<tmpInv.length;i++){
            if(tmpInv[i]+tmpRecipe[i+1]>=0){
                check = true;
            }
            else{
                check = false;
            }
        }
        return check;
    }

    //methode qui renvoit l'id d'une Recipe si elle peut-être créee
    //si possible le plus haut prix est selectionné
    public static int recipeLauncher(int[][] recipebook, int[] inventory, int recipecount){
        int[][] tmpRecipeArray = recipebook;
        int[] tmpArray = inventory;
        int store = -1;
        int maxPrice = 0;
        for (int row = 0; row<recipecount-1;row++){
            for (int col = 0; col<tmpRecipeArray[row].length;col++){
                if (recipeCanLaunch(tmpRecipeArray[row], tmpArray)){
                    if (tmpRecipeArray[row][5]>=maxPrice){
                        store = tmpRecipeArray[row][0];
                    }
                }
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

    //méthode qui regarde si l'inventaire est plein 
    public static boolean isInventoryFull(int[] inventory){
        int tmp = 0;
        for (int i=0;i<inventory.length-1;i++){
            tmp = tmp + inventory[i];
            if (tmp==10){
                return true;
            }
        }

        return false;

    }

    //méthode qui regarde si le cast d'une spell ne risque pas de faire déborder l'inventaire 
    public static boolean inventoryMayBeFull(int[] inventory, int[] spell){
        int sumSpell = 0;
        int sumInv = 0;
        for (int j=1;j<spell.length-1;j++){
            sumSpell = sumSpell + spell[j];
        }
        for (int i=0;i<inventory.length;i++){
            sumInv = sumInv + inventory[i];
        }

        if (sumSpell+sumInv>=10){
            return true;
        }
        return false;

    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int id = 0;
        String actionToDo = "REST";
        Boolean spellToUse = true;
        Boolean inventoryFull = false;
        // game loop
        while (true) {
            int actionCount = in.nextInt(); // the number of spells and recipes in play3
            int castCount = 0;
            int castableInt = 0;
            int recipeCount = 0;
            int[] inv = new int[3];
            int[][] spellBook = new int[actionCount][5];
            int[][] recipeBook = new int[actionCount][5];
            System.err.println(actionCount);
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
                if (i==1){
                    inv = fillInventory(inv0, inv1, inv2, inv3);
                };
                inventoryFull = isInventoryFull(inv);
            }

            //si l'inventaire est plein, on essaye de lancer une recette, si aucune recette n'est réalisable, on lance à nouveau un spell
            if(inventoryFull){
                id = recipeLauncher(recipeBook, inv, recipeCount);
                actionToDo = "BREW ";
                if (id==-1){
                    id = spellLauncher(spellBook, inv, castCount);
                     actionToDo = "CAST ";
                }
                System.out.println(actionToDo + id);            
            }

            spellToUse = someSpellAreCastable(spellBook, castCount);
            //si l'inventaire est plein, on essaye de lancer une recette, si aucune recette n'est réalisable, on lance à nouveau un spell
            if(spellToUse){
                id = spellLauncher(spellBook, inv, castCount);
                actionToDo = "CAST ";
                if (id==-1){
                    actionToDo = "REST ";
                }
                System.out.println(actionToDo + id);            
            }

            

            
            System.out.println("REST");

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // in the first league: BREW <id> | WAIT; later: BREW <id> | CAST <id> [<times>] | LEARN <id> | REST | WAIT
            
        }
    }
}