import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ReversiBoard {
    List<Consumer> ls = new ArrayList<>();
    Object avg;
    public ReversiBoard(){


    }
    public void addObserver(Consumer c){
        ls.add(c);
    }

    public void update(java.util.Observer o,Object avg){
        this.avg = avg;
        notifyObserves();
    }

    public void notifyObserves(){
        ls.forEach(v->{
            v.accept(avg);
        });
    }
}

class Ser{
    public void doSer(GridPane p){
        String s = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Circle c = (Circle) p.getChildren().get(8 * i + j).getUserData();
                s += c.getFill()+"-";

//                w = 0;
//                b = 0;
//                curr = 0;
//                lb.setText("white" + b + " - black:" + w);


            }
        }
        try {
            Files.write(new File("save_game.dat").toPath(),s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String reSer(GridPane p) {
        try {
            String s = Files.readAllLines(new File("save_game.dat").toPath()).get(0);
            return s;
        } catch (Exception e) {
     //       e.printStackTrace();
            return null;
        }

    }

}