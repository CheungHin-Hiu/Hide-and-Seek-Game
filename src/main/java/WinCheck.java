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
            checkWinning();
        }
    }
    private void checkWinning(){
        System.out.print("");
        if (TomController.tomPosition.x == JerryController.JerryPosition.x && TomController.tomPosition.y ==JerryController.JerryPosition.y){
            tomWin = true;
        }
        else if(JerryController.JerryPosition.x == JerryController.Exit.x && JerryController.JerryPosition.y == JerryController.Exit.y)
            jerryWin = true;
    }

}
