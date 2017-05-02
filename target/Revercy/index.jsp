<%--
  Created by IntelliJ IDEA.
  User: EAA
  Date: 15.04.2017
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="res/js/ajax.js"></script>
    <link rel="stylesheet" href="res/style.css">
  </head>
  <body>
  <div class="header">
		  <div class="left">
				  Игрок:<span id="player"></span>
		  </div>
		  <div class="right">
				  <span id="computer"></span>:Компьютер
		  </div>
		  <center style="margin: 0px 120px 0px 120px;">
				  <input type="button" id="newGame" value="Новая игра"/>
		  </center>
  </div>
  <div class="wraper">
      <table class="table">
        <tr>
          <td id="cell_11" class="cell" onclick="sendMove(0, 0)" onclick="sendMove(0, 0)"></td>
          <td id="cell_12" class="cell" onclick="sendMove(0, 1)"></td>
          <td id="cell_13" class="cell" onclick="sendMove(0, 2)"></td>
          <td id="cell_14" class="cell" onclick="sendMove(0, 3)"></td>
          <td id="cell_15" class="cell" onclick="sendMove(0, 4)"></td>
          <td id="cell_16" class="cell" onclick="sendMove(0, 5)"></td>
          <td id="cell_17" class="cell" onclick="sendMove(0, 6)"></td>
          <td id="cell_18" class="cell" onclick="sendMove(0, 7)"></td>
        </tr>
        <tr>
          <td id="cell_21" class="cell" onclick="sendMove(1, 0)"></td>
          <td id="cell_22" class="cell" onclick="sendMove(1, 1)"></td>
          <td id="cell_23" class="cell" onclick="sendMove(1, 2)"></td>
          <td id="cell_24" class="cell" onclick="sendMove(1, 3)"></td>
          <td id="cell_25" class="cell" onclick="sendMove(1, 4)"></td>
          <td id="cell_26" class="cell" onclick="sendMove(1, 5)"></td>
          <td id="cell_27" class="cell" onclick="sendMove(1, 6)"></td>
          <td id="cell_28" class="cell" onclick="sendMove(1, 7)"></td>
        </tr>
        <tr>
          <td id="cell_31" class="cell" onclick="sendMove(2, 0)"></td>
          <td id="cell_32" class="cell" onclick="sendMove(2, 1)"></td>
          <td id="cell_33" class="cell" onclick="sendMove(2, 2)"></td>
          <td id="cell_34" class="cell" onclick="sendMove(2, 3)"></td>
          <td id="cell_35" class="cell" onclick="sendMove(2, 4)"></td>
          <td id="cell_36" class="cell" onclick="sendMove(2, 5)"></td>
          <td id="cell_37" class="cell" onclick="sendMove(2, 6)"></td>
          <td id="cell_38" class="cell" onclick="sendMove(2, 7)"></td>
        </tr>
        <tr>
          <td id="cell_41" class="cell" onclick="sendMove(3, 0)"></td>
          <td id="cell_42" class="cell" onclick="sendMove(3, 1)"></td>
          <td id="cell_43" class="cell" onclick="sendMove(3, 2)"></td>
          <td id="cell_44" class="cell" onclick="sendMove(3, 3)"></td>
          <td id="cell_45" class="cell" onclick="sendMove(3, 4)"></td>
          <td id="cell_46" class="cell" onclick="sendMove(3, 5)"></td>
          <td id="cell_47" class="cell" onclick="sendMove(3, 6)"></td>
          <td id="cell_48" class="cell" onclick="sendMove(3, 7)"></td>
        </tr>
        <tr>
          <td id="cell_51" class="cell" onclick="sendMove(4, 0)"></td>
          <td id="cell_52" class="cell" onclick="sendMove(4, 1)"></td>
          <td id="cell_53" class="cell" onclick="sendMove(4, 2)"></td>
          <td id="cell_54" class="cell" onclick="sendMove(4, 3)"></td>
          <td id="cell_55" class="cell" onclick="sendMove(4, 4)"></td>
          <td id="cell_56" class="cell" onclick="sendMove(4, 5)"></td>
          <td id="cell_57" class="cell" onclick="sendMove(4, 6)"></td>
          <td id="cell_58" class="cell" onclick="sendMove(4, 7)"></td>
        </tr>
        <tr>
          <td id="cell_61" class="cell" onclick="sendMove(5, 0)"></td>
          <td id="cell_62" class="cell" onclick="sendMove(5, 1)"></td>
          <td id="cell_63" class="cell" onclick="sendMove(5, 2)"></td>
          <td id="cell_64" class="cell" onclick="sendMove(5, 3)"></td>
          <td id="cell_65" class="cell" onclick="sendMove(5, 4)"></td>
          <td id="cell_66" class="cell" onclick="sendMove(5, 5)"></td>
          <td id="cell_67" class="cell" onclick="sendMove(5, 6)"></td>
          <td id="cell_68" class="cell" onclick="sendMove(5, 7)"></td>
        </tr>
        <tr>
          <td id="cell_71" class="cell" onclick="sendMove(6, 0)"></td>
          <td id="cell_72" class="cell" onclick="sendMove(6, 1)"></td>
          <td id="cell_73" class="cell" onclick="sendMove(6, 2)"></td>
          <td id="cell_74" class="cell" onclick="sendMove(6, 3)"></td>
          <td id="cell_75" class="cell" onclick="sendMove(6, 4)"></td>
          <td id="cell_76" class="cell" onclick="sendMove(6, 5)"></td>
          <td id="cell_77" class="cell" onclick="sendMove(6, 6)"></td>
          <td id="cell_78" class="cell" onclick="sendMove(6, 7)"></td>
        </tr>
        <tr>
          <td id="cell_81" class="cell" onclick="sendMove(7, 0)"></td>
          <td id="cell_82" class="cell" onclick="sendMove(7, 1)"></td>
          <td id="cell_83" class="cell" onclick="sendMove(7, 2)"></td>
          <td id="cell_84" class="cell" onclick="sendMove(7, 3)"></td>
          <td id="cell_85" class="cell" onclick="sendMove(7, 4)"></td>
          <td id="cell_86" class="cell" onclick="sendMove(7, 5)"></td>
          <td id="cell_87" class="cell" onclick="sendMove(7, 6)"></td>
          <td id="cell_88" class="cell" onclick="sendMove(7, 7)"></td>
        </tr>
      </table>
  </div>
  </body>
</html>
