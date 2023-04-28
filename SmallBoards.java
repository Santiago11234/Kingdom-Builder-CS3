public class SmallBoards {
   
    public String[][][] boards;

    public SmallBoards() {
        boards = new String[7][][];
        //board1
        boards[0] = new String[][]{
            {"c", null, "d", null, "d", null, "d", null,"d", null,"d", null,"d", null, "d", null, "d", null, "d", null},
            {null, "c", null, "c", null, "c", null,"d", null,"d", null,"d", null, "d", null, "d", null, "c", null, "d"},
            {"m", null, "m", null, "m", null, "d", null,"m", null,"m", null,"s", null, "d", null, "d", null, "c", null},
            {null, "c", null, "m", null, "m", null,"m", null,"m", null,"m", null, "f", null, "f", null, "c", null, "c"},
            {"c", null, "c", null, "c", null, "m", null,"m", null,"s", null,"f", null, "f", null, "f", null, "c", null},
            {null, "g", null, "c", null, "c", null,"c", null,"m", null,"f", null, "f", null, "w", null, "b", null, "c"},
            {"g", null, "g", null, "s", null, "b", null,"f", null,"f", null,"w", null, "f", null, "f", null, "b", null},
            {null, "g", null, "g", null, "b", null,"b", null,"f", null,"f", null, "g", null, "s", null, "b", null, "b"},
            {"g", null, "g", null, "g", null, "b", null,"b", null,"w", null,"g", null, "g", null, "b", null, "b", null},
            {null, "g", null, "g", null, "g", null,"b", null,"w", null,"g", null, "g", null, "b", null, "b", null, "b"},
        };
        //board2
        boards[1] = new String[][]{
            {"d", null, "d", null, "c", null, "w", null,"w", null,"b", null,"b", null, "b", null, "g", null, "g", null},
            {null, "d", null, "s", null, "c", null,"w", null,"b", null,"b", null, "b", null, "s", null, "g", null, "g"},
            {"c", null, "c", null, "c", null, "f", null,"f", null,"f", null,"b", null, "c", null, "f", null, "f", null},
            {null, "c", null, "c", null, "f", null,"f", null,"w", null,"d", null, "d", null, "c", null, "c", null, "f"},
            {"c", null, "g", null, "g", null, "w", null,"f", null,"f", null,"d", null, "d", null, "c", null, "c", null},
            {null, "g", null, "g", null, "s", null,"f", null,"w", null,"f", null, "w", null, "d", null, "d", null, "c"},
            {"g", null, "g", null, "g", null, "b", null,"f", null,"f", null,"w", null, "w", null, "d", null, "d", null},
            {null, "g", null, "g", null, "b", null,"b", null,"m", null,"w", null, "w", null, "w", null, "d", null, "w"},
            {"g", null, "m", null, "b", null, "b", null,"w", null,"w", null,"w", null, "w", null, "w", null, "w", null},
            {null, "b", null, "b", null, "b", null,"w", null,"w", null,"w", null, "w", null, "w", null, "w", null, "w"},
        };
        //board3
        boards[2] = new String[][]{
            {"g", null, "g", null, "g", null, "b", null,"b", null,"w", null,"g", null, "b", null, "b", null, "b", null},
            {null, "g", null, "g", null, "g", null,"s", null,"b", null,"w", null, "g", null, "b", null, "b", null, "b"},
            {"g", null, "f", null, "f", null, "g", null,"b", null,"b", null,"w", null, "g", null, "g", null, "b", null},
            {null, "f", null, "f", null, "c", null,"g", null,"b", null,"w", null, "f", null, "s", null, "b", null, "b"},
            {"f", null, "f", null, "f", null, "c", null,"c", null,"w", null,"f", null, "f", null, "w", null, "w", null},
            {null, "m", null, "m", null, "c", null,"g", null,"g", null,"w", null, "w", null, "w", null, "d", null, "d"},
            {"c", null, "c", null, "c", null, "m", null,"g", null,"f", null,"f", null, "f", null, "d", null, "d", null},
            {null, "c", null, "c", null, "s", null,"d", null,"m", null,"d", null, "f", null, "f", null, "c", null, "c"},
            {"w", null, "w", null, "w", null, "d", null,"d", null,"d", null,"d", null, "m", null, "c", null, "c", null},
            {null, "w", null, "w", null, "w", null,"w", null,"d", null,"d", null, "d", null, "d", null, "d", null, "c"},
        };
        //board4
        boards[3] = new String[][]{
            {"g", null, "g", null, "b", null, "b", null,"b", null,"w", null,"g", null, "b", null, "b", null, "f", null},
            {null, "g", null, "f", null, "b", null,"b", null,"w", null,"g", null, "b", null, "b", null, "f", null, "f"},
            {"g", null, "f", null, "f", null, "b", null,"w", null,"g", null,"g", null, "f", null, "f", null, "f", null},
            {null, "f", null, "f", null, "b", null,"b", null,"w", null,"g", null, "m", null, "f", null, "d", null, "d"},
            {"c", null, "f", null, "s", null, "b", null,"w", null,"g", null,"d", null, "d", null, "d", null, "d", null},
            {null, "c", null, "c", null, "b", null,"w", null,"g", null,"g", null, "m", null, "m", null, "d", null, "d"},
            {"c", null, "c", null, "w", null, "w", null,"w", null,"g", null,"d", null, "d", null, "d", null, "c", null},
            {null, "w", null, "w", null, "g", null,"g", null,"w", null,"w", null, "s", null, "c", null, "m", null, "c"},
            {"w", null, "d", null, "s", null, "g", null,"w", null,"m", null,"w", null, "c", null, "c", null, "c", null},
            {null, "w", null, "d", null, "d", null, "w", null,"w", null,"w", null, "w", null, "c", null, "c", null, "c"},
        };
        //board5
        boards[4] = new String[][]{
            {"b", null, "b", null, "b", null, "b", null,"m", null,"m", null,"g", null, "m", null, "c", null, "c", null},
            {null, "b", null, "m", null, "b", null,"b", null,"f", null,"g", null, "m", null, "m", null, "m", null, "c"},
            {"f", null, "f", null, "b", null, "f", null,"f", null,"f", null,"g", null, "g", null, "w", null, "m", null},
            {null, "d", null, "f", null, "f", null,"f", null,"w", null,"s", null, "g", null, "w", null, "m", null, "m"},
            {"d", null, "d", null, "d", null, "d", null,"f", null,"w", null,"g", null, "w", null, "c", null, "c", null},
            {null, "d", null, "c", null, "d", null,"d", null,"d", null,"w", null, "w", null, "c", null, "g", null, "c"},
            {"d", null, "d", null, "c", null, "d", null,"d", null,"w", null,"f", null, "s", null, "g", null, "c", null},
            {null, "c", null, "c", null, "s", null,"d", null,"w", null,"f", null, "f", null, "f", null, "g", null, "g"},
            {"d", null, "c", null, "w", null, "w", null,"w", null,"b", null,"b", null, "f", null, "g", null, "g", null},
            {null, "d", null, "c", null, "c", null,"w", null,"b", null,"b", null, "b", null, "g", null, "g", null, "g"},
        };
        //board6
        boards[5] = new String[][]{
            {"d", null, "d", null, "c", null, "w", null,"w", null,"b", null,"b", null, "g", null, "g", null, "g", null},
            {null, "d", null, "c", null, "w", null,"f", null,"f", null,"b", null, "b", null, "b", null, "g", null, "g"},
            {"d", null, "d", null, "w", null, "f", null,"f", null,"b", null,"b", null, "s", null, "f", null, "g", null},
            {null, "w", null, "w", null, "w", null,"f", null,"g", null,"b", null, "f", null, "f", null, "f", null, "f"},
            {"w", null, "w", null, "w", null, "w", null,"g", null,"g", null,"g", null, "g", null, "f", null, "f", null},
            {null, "w", null, "b", null, "b", null,"w", null,"g", null,"g", null, "c", null, "c", null, "d", null, "c"},
            {"w", null, "b", null, "c", null, "b", null,"w", null,"g", null,"c", null, "c", null, "d", null, "c", null},
            {null, "w", null, "s", null, "c", null,"f", null,"w", null,"s", null, "d", null, "d", null, "c", null, "w"},
            {"w", null, "w", null, "c", null, "f", null,"w", null,"w", null,"w", null, "d", null, "d", null, "w", null},
            {null, "w", null, "w", null, "w", null,"w", null,"w", null,"w", null, "w", null, "w", null, "w", null, "w"},
        };
        //board7
        boards[6] = new String[][]{
            {"c", null, "c", null, "c", null, "d", null,"d", null,"w", null,"d", null, "d", null, "d", null, "d", null},
            {null, "m", null, "m", null, "c", null,"d", null,"d", null,"w", null, "d", null, "d", null, "d", null, "d"},
            {"m", null, "m", null, "c", null, "m", null,"m", null,"w", null,"d", null, "d", null, "s", null, "f", null},
            {null, "m", null, "c", null, "m", null,"m", null,"w", null,"m", null, "d", null, "f", null, "f", null, "f"},
            {"c", null, "c", null, "g", null, "g", null,"w", null,"m", null,"m", null, "c", null, "f", null, "f", null},
            {null, "c", null, "b", null, "b", null,"w", null,"c", null,"c", null, "c", null, "m", null, "f", null, "f"},
            {"c", null, "s", null, "b", null, "b", null,"w", null,"f", null,"f", null, "f", null, "f", null, "f", null},
            {null, "g", null, "g", null, "b", null,"w", null,"g", null,"s", null, "g", null, "f", null, "g", null, "b"},
            {"g", null, "g", null, "b", null, "b", null,"w", null,"g", null,"g", null, "g", null, "g", null, "b", null},
            {null, "g", null, "g", null, "b", null,"b", null,"w", null,"g", null, "g", null, "g", null, "b", null, "b"},
        };

        
    }

    public String[][] getBoard(int board) {
        return boards[board];
    }
}
