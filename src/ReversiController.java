public class ReversiController {
    public static boolean[] compareResult(){
        boolean[] list = {false,false,false,false};
        if (ReversiModel.last[0] == ReversiModel.cp[0]) {
            ReversiModel.value++;
            list[0] = true;
        }
        if (ReversiModel.last[1] == ReversiModel.cp[1]) {
            ReversiModel.value++;
            list[1] = true;
        }
        if (ReversiModel.last[2] == ReversiModel.cp[2]) {
            ReversiModel.value++;
            list[2] = true;
        }
        if (ReversiModel.last[3] == ReversiModel.cp[3]) {
            ReversiModel.value++;
            list[3] = true;
        }
        return list;
    }
}
