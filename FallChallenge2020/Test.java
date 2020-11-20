public class Test {

    public static boolean someSpellAreCastable(int[][] spellbook){
        int[][] tmpSpellArray = spellbook;
        int tmpSpellUsed = 0;
        for (int row = 0; row<tmpSpellArray.length;row++){
            if (tmpSpellArray[row][5] == 0){
                System.out.println(tmpSpellArray[row][5] == 0);
                tmpSpellUsed = tmpSpellUsed+1;
            }
            if (tmpSpellArray.length == tmpSpellUsed){
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        boolean test = true;
        int[][] spellTest = {
            {45,-1,1,0,0,0},
            {47,-2,1,0,1,0},
            {48,0,-1,1,0,0},
            {49,-1,-1,1,0,0},
            {44,-1,1,0,0,0},
            {41,-1,1,0,0,0}
        };
        int id = -1;

        if(id==-1){
            System.out.println("CA MAAAAARCHE suite");
        }
        
    }
}
