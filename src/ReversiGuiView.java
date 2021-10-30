import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ReversiGuiView extends Application {
    ToolBar tb = new ToolBar();
    Circle[][] clis = new Circle[8][8];
    ReversiBoard mo = new ReversiBoard();
    Label lb = new Label("white:0 - black:0");
    private static int curr = 1;

    GridPane p = new GridPane();


    /**
     * call back method
     * @param primaryStage stage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(v -> {
            new Ser().doSer(p);
        });
        Button button = new Button("button");
        button.setFont(Font.font(20));
        BorderPane root1 = new BorderPane();
        p.setStyle("-fx-background-color: #009300;");
        extracted1(p);
        p.setPadding(new Insets(8, 8, 8, 8));
        MenuBar menuBar = new MenuBar();
        root1.setTop(menuBar);
        menuBar.getMenus().addAll(fileMenu());
        tb.getItems().add(lb);
        root1.setBottom(tb);
        root1.setCenter(p);
        primaryStage.setScene(new Scene(root1));
        primaryStage.setWidth(350);
        primaryStage.setHeight(450);
        primaryStage.setTitle(ReversiModel.title);
        primaryStage.show();
    }

    /**
     * extracted1
     * @param p gradpane
     */
    private void extracted1(GridPane p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Circle circle = new Circle(25, 25, 15);
                circle.setUserData(new ReversiModel.Posi(i, j));
                clis[i][j] = circle;
                StackPane sp = new StackPane(circle);
                sp.setUserData(circle);
                Background background = new Background(new BackgroundFill(Paint.valueOf("#009300"), new CornerRadii(20), new Insets(10)));
                sp.setBackground(background);
                Border border = new Border(new BorderStroke(Paint.valueOf("#000000"), BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1)));
                sp.setBorder(border);
                sp.setAlignment(Pos.CENTER);

                sp.setOnMouseClicked(v -> {
                    mo.update((o, arg) -> {

                    }, v);
                    Circle c = ((Circle) ((StackPane) v.getSource()).getUserData());
                    int curx = ((ReversiModel.Posi) c.getUserData()).x;
                    int cury = ((ReversiModel.Posi) c.getUserData()).y;
                    if(whenUserClick(curx, cury)!=0){
                        return;
                    }
                    while(whenUserClick((int)(Math.random()*7),(int)(Math.random()*7))!=0);
                });

                if (j == 3 && i == 3 || j == 4 && i == 4) {
                    circle.setFill(Color.BLACK);
                } else if (i == 4 && j == 3 || i == 3 && j == 4) {
                    circle.setFill(Color.WHITE);
                } else {
                    circle.setFill(Color.TRANSPARENT);
                }

                sp.setMaxSize(40, 40);
                sp.setMinSize(40, 40);


                p.add(sp, j, i);
            }
        }
        if (new Ser().reSer(p) != null) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Circle c = (Circle) p.getChildren().get(8 * i + j).getUserData();
                    String v[] = new Ser().reSer(p).split("-");
                    c.setFill(Color.valueOf(v[i * 8 + j]));
                }
            }
        }
    }

    private int whenUserClick(int curx, int cury) {
        if (clis[curx][cury].getFill().equals(Color.BLACK)||
        clis[curx][cury].getFill().equals(Color.WHITE)){
            return -1;
        }
        curr++;
        Color bb = curr % 2 != 0 ? Color.WHITE :  Color.BLACK;
        boolean bv = false;

        if (cury -1>=0&& cury -1<8&&clis[curx][cury -1].getFill().equals(bb)){
            bv = true;
        }
        if (cury + 1 < 8 && clis[curx][cury + 1].getFill().equals(bb)){
            bv = true;
        }
        if (curx -1>=0&& curx -1<8&&clis[curx -1][cury].getFill().equals(bb)){
            bv = true;
        }
        if (curx + 1 < 8 && clis[curx + 1][cury].getFill().equals(bb)){
            bv = true;
        }

        if (curx +1<8&& cury +1<8&&clis[curx +1][cury +1].getFill().equals(bb)){
            bv = true;
        }
        if (curx +1<8&& cury -1>=0&&clis[curx +1][cury -1].getFill().equals(bb)){
            bv = true;
        }
        if (curx -1>=0&& cury -1>=0&&clis[curx -1][cury -1].getFill().equals(bb)){
            bv = true;
        }
        if (curx -1>=0&& cury +1<8&&clis[curx -1][cury +1].getFill().equals(bb)){
            bv = true;
        }
        if (!bv){
            curr--;
            return -1;
        }
        if (curr % 2 == 0) {
            clis[curx][cury].setFill(Color.WHITE);
        } else {
            clis[curx][cury].setFill(Color.BLACK);
        }
        Color b = curr % 2 == 0 ? Color.WHITE :  Color.BLACK;
        for (int k = curx -1; k > 0; k--) {
            // up
            if (clis[k][cury].getFill().equals(b)) {
                for (int l = k; l <= curx; l++) {
                    clis[l][cury].setFill(b);
                }
            }else{
                System.out.println(clis[k][cury].getFill());
            }
        }
        for (int k = curx +1; k < 8; k++) {
// do
            if (clis[k][cury].getFill().equals(b)) {
                for (int l = curx; l <=k; l++) {
                    clis[l][cury].setFill(b);

                }
            }
        }
        for (int k = cury -1; k >0; k--) {
// left
            if (clis[curx][k].getFill().equals(b)) {
                for (int l = k; l <= cury; l++) {
                    clis[curx][l].setFill(b);

                }
            }
        }
        for (int k = cury +1; k <8; k++) {
            if (clis[curx][k].getFill().equals(b)) {
                for (int l = cury; l <= k; l++) {
                    clis[curx][l].setFill(b);

                }
            }
        }
        //rd
        for (int k = curx +1, r = cury +1; k <=7&& r<=7; k++,r++) {
            if (clis[k][r].getFill().equals(b)) {
                for (int l1 = curx, l2 = cury; l1<=k&&l2<=r; l1++,l2++) {
                    clis[l1][l2].setFill(b);

                }
            }
        }
//                    //ld
        for (int k = curx -1, r = cury -1; k >=0&& r>=0; k--,r--) {
            if (clis[k][r].getFill().equals(b)) {
                for (int l1 = curx, l2 = cury; l1>=k&&l2>=r; l1--,l2--) {
                     clis[l1][l2].setFill(b);

                }
            }
        }
//                     //rd

        for (int k = curx -1, r = cury +1; k >=0&& r<=7; k--,r++) {
            if (clis[k][r].getFill().equals(b)) {
                for (int l1 = curx, l2 = cury; l1>=k&&l2<=r; l1--,l2++) {
                    clis[l1][l2].setFill(b);

                }
            }
        }
//                    //ld
        for (int k = curx +1, r = cury -1; k <=7&& r>=0; k++,r--) {
            if (clis[k][r].getFill().equals(b)) {
                for (int l1 = curx, l2 = cury; l1<=k&&l2>=r; l1++,l2--) {
                    clis[l1][l2].setFill(b);

                }
            }
        }

        ShowAndCalValye();

//
        if (ReversiModel.w>32){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message Here...");
            alert.setHeaderText("Message");
            alert.setContentText("You lost!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        }
        if (ReversiModel.b>32){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message Here...");
            alert.setHeaderText("Message");
            alert.setContentText("You Won!");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        }
        return 0;
    }

    /**
     * ShowAndCalValue
     */
    private void ShowAndCalValye() {
        ReversiModel.w=0;
        ReversiModel.b=0;
        for (Circle[] cli : clis) {
            for (Circle circle1 : cli) {
                if (circle1.getFill().equals(Color.WHITE)){
                    ReversiModel.w++;
                }
                if (circle1.getFill().equals(Color.BLACK)){
                    ReversiModel.b++;
                }
            }
        }

        lb.setText("white" + ReversiModel.b + " - black:" + ReversiModel.w);
    }

    /**
     * init panel
     * @param p panel
     */
    private void extracted2(GridPane p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Circle c = (Circle) p.getChildren().get(8 * i + j).getUserData();
                if (j == 3 && i == 3 || j == 4 && i == 4) {
                    c.setFill(Color.BLACK);
                } else if (i == 4 && j == 3 || i == 3 && j == 4) {
                    c.setFill(Color.WHITE);
                } else {
                    c.setFill(Color.TRANSPARENT);
                }

                ReversiModel.w = 2;
                ReversiModel.b = 2;
                curr = 0;
                lb.setText("white" + ReversiModel.b + " - black:" + ReversiModel.w);


            }
        }
    }


    /**
     * show menu
     * @return a menu
     */
    private Menu fileMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New Game");
        newMenuItem.setOnAction(v -> extracted2(p));
        fileMenu.getItems().addAll(newMenuItem,
                new SeparatorMenuItem());
        return fileMenu;
    }

}
