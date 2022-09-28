package Controller;

import DBUtil.InitDB;
import DBUtil.Initialization;


public class Start {
    public static void main(String[] args) {
        Initialization db = new InitDB();
        db.init();
        db.fill();

        Controller controller = new Controller();
        controller.startController();
    }
}
