public class SmallBoards {
   
    public String[][][] boards;

    public SmallBoards() {
        boards = new String[7][][];
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
        boards[1] = new String[][]{
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
        boards[2] = new String[][]{
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
        boards[3] = new String[][]{
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
        boards[4] = new String[][]{
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
        boards[5] = new String[][]{
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
        boards[6] = new String[][]{
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

        
    }

    public String[][] getBoard(int board) {
        return boards[board];
    }
}
