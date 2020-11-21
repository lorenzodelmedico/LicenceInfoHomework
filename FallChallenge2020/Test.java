public class Test {

    //methode
    public static boolean spellCanLaunch(int[] spell, int[] inventory, int inventorystate){
        int[] tmpInv = inventory;
        int[] tmpSpell = spell;
        boolean canLaunch = false;
        if (inventorystate==10){
            for (int i=0; i<tmpInv.length;i++){
                //test si la spell ne rapporte pas de 2 ou plus d'un même ressource
                if(tmpSpell[i+1]>=2){
                    canLaunch = false;
                    return canLaunch;
                }
                // int[]inventory={4,3,1,2};
                // int[] spellSolo = {55,-1,0,0,1,0};
                //test pour voir si la spell retire ou ne rapporte rien de la ressource
                if(tmpSpell[i+1]<=0){
                    continue;
                }
                System.out.println(tmpInv[i]);
                //test si je ne dispose pas de 2 ou plus de la ressource
                if(tmpInv[i]>=2){
                    canLaunch = false;
                    return canLaunch;
                }
                //test si inv dispose des ressources pour cast
                if(tmpSpell[i+1]+tmpInv[i]>=0){
                    canLaunch = true;
                }
            }
        }
        if (inventorystate>=8){
            for (int i=0; i<tmpInv.length;i++){
                //test si la spell ne rapporte pas 2fois la même ressource ou que inv ne dispose pas de 4 ou plus de la ressource
                if(tmpSpell[i+1]>=2){
                    canLaunch = false;
                    return canLaunch;
                }
                //test pour voir si la spell retire ou ne rapporte rien de la ressource
                if(tmpSpell[i+1]<=0){
                    continue;
                } 
                 //test si je ne dispose pas de 3 ou plus de la ressource
                if(tmpInv[i]>=3){
                    canLaunch = false;
                    return canLaunch;
                }   
                //test si inv dispose des ressources pour cast
                if(tmpSpell[i+1]+tmpInv[i]>=0){
                    canLaunch = true;
                }     
            }
        }
        if (inventorystate>=5){
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
        }
        else{
            for (int i=0; i<tmpInv.length;i++){
                //test si inv dispose des ressources pour cast
                if(tmpSpell[i+1]+tmpInv[i]>=0){
                    canLaunch = true;
                }
            }
        }
        
        return canLaunch;
    }

    public static void main(String args[]) {
        boolean test = false;
        int recipecount=6;
        int spellcount=4;
        int idrecipe = 0;
        int[]inventory={4,3,2,1};
        int[][] recipeTest = {
            {45,-8,-1,0,0,0},
            {47,-8,-1,0,-1,0},
            {48,-8,-1,-1,0,0},
            {49,-5,-1,-1,0,0},
            {44,-5,-1,0,0,0},
            {41,-3,-4,-2,0,0}
        };
        int[][] spellTest = {
            {55,-1,1,0,0,0},
            {57,-2,1,0,1,0},
            {58,0,-1,1,0,0},
            {59,-1,1,1,0,0},
        };
        int[] spellSolo = {55,-1,0,0,1,0};
        test = spellCanLaunch(spellSolo, inventory, 7);
        System.out.println(test);
        // for(int j=0; j<spellRequire.length;j++){
        //     for(int y=0; y<spellRequire[j].length;y++){
        //         System.out.println(spellRequire[j][y]);
        //     }
        // }
    }
}
