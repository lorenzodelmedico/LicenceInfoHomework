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
class Player {

   static class Spell {
        private int actionId;
        private int[] itemList;

        public Spell() {
            this.actionId = 0;
            this.itemList = new int[3];
        }

        public Spell(final int actionId,final int d0,final int d1,final int d2,final int d3) {
            this.actionId = actionId;
            this.itemList = new int[]{d0, d1, d2, d3};
        }

        public int getSpellId() { return this.actionId; }
        public int[] getSpellCost(){ return this.itemList; }

    }

    static class Inventory {
        private int[] itemList;

        public Inventory() {
            this.itemList = new int[3];
        }

        public Inventory(final int inv0,final int inv1,final int inv2,final int inv3) {
            this.itemList = new int[]{inv0,inv1, inv2, inv3};
        }

        public int[] getInventoryState(){ return this.itemList; }
        public boolean isInventoryFull(){
            int tmp = 0;
            for (int i=0;i<itemList.length-1;i++){
                tmp = tmp + itemList[i];
                if (tmp==10){
                    return true;
                }
            }

            return false;

    }

    /*static boolean inventory(int inv0, int inv1, int inv2, int inv3){
        if (inv0 + inv1 + inv2 + inv3 <= 10){
            return true;
        }

        return false;
    }*/
    
    /*static int bestRecipe(){

    }*/


    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Spell sp0 =  new Spell(); 
        Spell sp1 =  new Spell();
        Spell sp2 =  new Spell();
        Spell sp3 =  new Spell();
        Inventory masterInv = new Inventory();
        int id = 0;
        int high_price = 0;
        Boolean resetSpell = false;
        // game loop
        while (true) {
            int actionCount = in.nextInt(); // the number of spells and recipes in play
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
                if (actionType.equals("CAST")){
                    sp0 = new Spell(actionId, d0, d1, d2, d3);
                    if (sp0.getSpellId()!=0 && sp1.getSpellId()!=0 && sp2.getSpellId()!=0){
                        sp3 = new Spell(actionId, d0, d1, d2, d3);
                    }
                    else if(sp0.getSpellId()!=0 && sp1.getSpellId()!=0){
                        sp2 = new Spell(actionId, d0, d1, d2, d3);
                    }
                    else{
                        sp1 = new Spell(actionId, d0, d1, d2, d3);
                    }
                }
            }
            
            for (int i = 0; i < 2; i++) {
                int inv0 = in.nextInt(); // tier-0 ingredients in inventory
                int inv1 = in.nextInt();
                int inv2 = in.nextInt();
                int inv3 = in.nextInt();
                int score = in.nextInt(); // amount of rupees
                masterInv = new Inventory(inv0, inv1, inv2, inv3);
            }

            /*reste à faire un array des recipes*/
            /*le but est remplir l'inventaire, regarde si on peut faire des potions, 
                si oui on la fait, sinon on continue a cast*/ 

            /* si je trouve une potion qui correspond à une combinaison dans mon inventaire je la fais*/

            if(resetSpell){
                System.out.println("REST");
            }
            else{
                System.out.println("CAST " + sp1.getSpellId());
            }

            if(masterInv.isInventoryFull()){
                System.out.println("BREW " + sp1.getSpellId());
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // in the first league: BREW <id> | WAIT; later: BREW <id> | CAST <id> [<times>] | LEARN <id> | REST | WAIT
            
        }
    }
}}