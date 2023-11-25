public class WinCheck extends Thread{
    public static boolean tomWin;
    public static boolean jerryWin;

    WinCheck(){
        tomWin = false;
        jerryWin = false;
    }

    @Override
    public void run(){

        while(tomWin == false && jerryWin == false) {
            checkWinning(TomController.tomPosition, JerryController.JerryPosition, JerryController.Exit);
        }
    }
    protected void checkWinning(Position tomPosition, Position jerryPosition, Position exitPosition){
        System.out.print("");
        if (tomPosition.x == jerryPosition.x && tomPosition.y == jerryPosition.y){
            tomWin = true;
        }
        else if(jerryPosition.x == exitPosition.x && jerryPosition.y == exitPosition.y)
            jerryWin = true;
    }

}
