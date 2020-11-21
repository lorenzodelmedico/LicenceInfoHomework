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
    public static int spellLauncher(int[][] spellbook, int[] inventory, int inventorystate){
        int[][] tmpSpellArray = spellbook;
        int[] tmpArray = inventory;
        int store = -2;
        boolean check = false;
        for (int row = 0; row<tmpSpellArray.length;row++){
            check = spellCanLaunch(tmpSpellArray[row], tmpArray, inventorystate);
            if (tmpSpellArray[row][5]==1 && check){
                return store = tmpSpellArray[row][0];
            }
        }
        return store;
    }

    public static boolean spellCanLaunch(int[] spell, int[] inventory, int inventorystate){
        int[] tmpInv = inventory;
        int[] tmpSpell = spell;
        boolean canLaunch = false;
        if (inventorystate>=9){
            for (int i=0; i<tmpInv.length;i++){
                //test si la spell ne rapporte pas de 2 ou plus d'un même ressource
                if(tmpSpell[i+1]>=2){
                    return false;
                }
                // int[]inventory={4,3,1,2};
                // int[] spellSolo = {55,-1,0,0,1,0};
                //test si inv dispose des ressources pour cast
                if(tmpSpell[i+1]+tmpInv[i]>=0){
                    canLaunch = true;
                }
                //test pour voir si la spell retire ou ne rapporte rien de la ressource
                if(tmpSpell[i+1]<=0){
                    continue;
                }
                //test si je ne dispose pas de 2 ou plus de la ressource
                if(tmpInv[i]>=2){
                    return false;
                }
                 
            }
        }
        if (inventorystate>6){
            for (int i=0; i<tmpInv.length;i++){
                if(tmpInv[0]<=1 && tmpSpell[i+1]>=2){
                    return true;
                }
                //test si la spell ne rapporte pas 2fois la même ressource, si on en dispose de deja 2-3 en stock
                if(tmpSpell[i+1]>=2){
                    canLaunch = false;
                    return canLaunch;
                }
                //test si inv dispose des ressources pour cast
                if(tmpSpell[i+1]+tmpInv[i]>=0){
                    canLaunch = true;
                }
                //test pour voir si la spell retire ou ne rapporte rien de la ressource
                if(tmpSpell[i+1]<=0){
                    continue;
                } 
                //test si je ne dispose pas de plus de 3 de la ressource
                if(tmpInv[i]>=3){
                    return false;
                } 
                           
                   
            }
        }
        /*if (inventorystate>=5){
            for (int i=0; i<tmpInv.length;i++){
                //test si la spell ne rapporte pas 2fois la même ressource 
                if(tmpSpell[i+1]>=2){
                    canLaunch = false;
                    return canLaunch;
                }
                //test pour voir si la spell retire ou ne rapporte rien de la ressource
                if(tmpSpell[i+1]<=0){
                    continue;
                }
                //test si je ne dispose pas de 4 ou plus de la ressource
                if(tmpInv[i]>=4){
                    canLaunch = false;
                    return canLaunch;
                }
                //test si inv dispose des ressources pour cast
                if(tmpSpell[i+1]+tmpInv[i]>=0){
                    canLaunch = true;
                }
                
            }
        }*/
        else{
            for (int i=0; i<tmpInv.length;i++){
                //test si inv dispose des ressources pour cast
                if(tmpSpell[i+1]+tmpInv[i]>=0){
                    canLaunch = true;
                }
                else{
                    return false;
                }
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
            int castableInt = 0;
            int recipecount = 0;
            int castcount = 0;
            int inventoryState = 0;
            int[] inv = new int[3];
            int[][] spellBook = new int[4][];
            int[][] recipeBook = new int[5][];
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
                if (actionType.equals("BREW")){ 
                    recipeBook[recipecount] = fillRecipeBook(actionId, d0, d1, d2, d3, price);
                    recipecount = recipecount + 1;
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
                    inventoryState = inv0+inv1+inv2+inv3;
                }
                
                
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