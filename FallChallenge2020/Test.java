public class Test {

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

    //methode qui regarde quel spell rapporte le plus de ressource en rapport avec la meilleure recette [0], si repeatable regarde le best times
    public static int[][] bestMatchRatioSpellForFirst3Recipe(int[][] recipebook, int[][] spellbook){
        int[][] matchRatioArray = new int[spellbook.length][3];
        for(int row = 0; row < spellbook.length; row++){
            matchRatioArray[row][2]=1;
            //recherche du spell qui match le mieux avec la recette [1]
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
                        }
                    }
                }
                else if ((recipebook[0][col] + spellbook[row][col] == -1) || (recipebook[0][col] + spellbook[row][col] == 1)){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 8;                }
                else if ((recipebook[0][col] + spellbook[row][col] == -2) || (recipebook[0][col] + spellbook[row][col] == 2)){
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 7;
                }
                else{
                    matchRatioArray[row][1] = matchRatioArray[row][1] + 0;
                }

            }
        }

        return matchRatioArray;
    }


    public static void main(String args[]) {
        boolean test = false;
        int recipecount=6;
        int spellcount=4;
        int idrecipe = 0;
        int[]inventory={4,3,2,1};
        int[][] matchbook2 = new int[10][];
        int[][] matchbook={
            {42,0,1},
            {43,15,1},
            {44,12,1},
            {45,0,1},
            {46,10,1},
            {47,7,1},
            {48,5,1},
            {49,0,1},
            {50,13,1},
            {51,0,1},
            {52,0,1},
            {53,17,1},
            {54,0,1},
            {55,0,1},
            {56,0,1},
        };
        int[][] recipeTest = {
            {45,-1,-4,-3,0,0},
            {47,-4,-1,-2,-1,0},
        };
        int[][] spellTest = {
            {55,-1,1,0,0,1,0},
            {57,-2,1,0,1,1,0},
            {58,0,-1,1,0,1,0},
            {59,-2,4,3,0,1,0},
            {60,-1,1,1,0,1,0},
            {61,-3,1,5,0,1,1},
            {62,-1,1,1,0,1,1},
            {63,-1,-1,1,5,1,1},
            {64,-2,1,6,0,1,1},
            {65,-2,4,-2,0,1,1},
        };
        int[] spellSolo = {55,-1,0,0,1,0};
        matchbook2 = bestMatchRatioSpellForFirst3Recipe(recipeTest, spellTest);
        int[][] spellTest2= new int[spellTest.length][];
        spellTest2 = orderRatioArray(matchbook2, spellTest);
        System.out.println(spellTest2[0][6]);
        System.out.println(spellTest2[3][6]);
        System.out.println(spellTest2[6][6]);
        // for(int j=0; j<spellRequire.length;j++){
        //     for(int y=0; y<spellRequire[j].length;y++){
        //         System.out.println(spellRequire[j][y]);
        //     }
        // }
    }
}
