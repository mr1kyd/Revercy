package edu.ssau.servlet;

import edu.ssau.revercy.ReversiBoard;
import edu.ssau.revercy.ReversiPlayer;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.io.PrintWriter;

/**
 * Created by EAA on 15.04.2017.
 */
public class ReversiServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        JSONObject object = new JSONObject();
        char human = 'L';
        char computer = 'D';
        ReversiBoard board = (ReversiBoard)session.getAttribute("reversiboard");
        if(board == null){
            board = new ReversiBoard(8);
            session.setAttribute("reversiboard", board);
        }
        else {
            String x = req.getParameter("x");
            String y = req.getParameter("y");
            if(x != null && y != null) {
                int i = Integer.valueOf(x);
                int j = Integer.valueOf(y);
                if (board.isValid(human, i, j)) {
                    board.playMove(human, i, j);
                }
                try {
                    object.put("playerMove", board.boardToJSON());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ReversiPlayer.playMove(board, computer);
        }
        PrintWriter pw = resp.getWriter();
        try {
            object.put("computerMove", board.boardToJSON());
            pw.write(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
