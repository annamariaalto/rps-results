package org.example;

//import java.lang.reflect.Array;

public class HistoryResult {
    private String cursor;
    JSONObject data = new JSONObject();
    //private ArrayIndexOutOfBoundsException data;
    //private String GAME_RESULT;
    //private String gameId;
    //private int t;
    JSONObject playerA = new JSONObject();
    JSONObject playerB = new JSONObject();

    private class JSONObject {
        private ArrayIndexOutOfBoundsException data;
        private String GAME_RESULT;
        private String gameId;
        private int t;

        private class JSONObject2 {
            private String name;
            private String played;
        }
    }

    //private class JSONObject {
      //  private String name;
      //  private String played;
    //}

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
    /*
    public ArrayIndexOutOfBoundsException getData() {
        return data;
    }

    public void setData(ArrayIndexOutOfBoundsException data) {
        this.data = data;
    }

    public String getGAME_RESULT() {
        return GAME_RESULT;
    }

    public void setGAME_RESULT(String GAME_RESULT) {
        this.GAME_RESULT = GAME_RESULT;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }*/

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONObject getPlayerA() {
        return playerA;
    }

    public void setPlayerA(JSONObject playerA) {
        this.playerA = playerA;
    }

    public JSONObject getPlayerB() {
        return playerB;
    }

    public void setPlayerB(JSONObject playerB) {
        this.playerB = playerB;
    }

    @Override
    public String toString() {
        return "HistoryResult{" +
                "cursor='" + cursor + '\'' +
                ", data='" + data + '\'' +
                //", GAME_RESULT='" + GAME_RESULT + '\'' +
                //", gameId='" + gameId + '\'' +
                //", t=" + t +
                //", playerA=" + playerA +
                //", playerB=" + playerB +
                '}';
    }
}


