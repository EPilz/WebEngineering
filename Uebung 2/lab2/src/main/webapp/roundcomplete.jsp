<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="at.ac.tuwien.big.we14.lab2.servlet.BigQuizServlet"%>
<jsp:useBean id="currentRound" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimpleRound" />
<jsp:useBean id="game" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimpleGame" />
<jsp:useBean id="player1" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" />
<jsp:useBean id="player2" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" />

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Business Informatics Group Quiz - Zwischenstand</title>
        <link rel="stylesheet" type="text/css" href="style/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/framework.js" type="text/javascript"></script>
    </head>
    <body id="winnerpage">
        <a class="accessibility" href="#roundwinner">Zur Rundenwertung springen</a>
        <header role="banner" aria-labelledby="mainheading"><h1 id="mainheading"><span class="accessibility">Business Informatics Group</span> Quiz</h1></header>
        <nav role="navigation" aria-labelledby="navheading">
            <h2 id="navheading" class="accessibility">Navigation</h2>
            <ul>
                <li><a id="logoutlink" title="Klicke hier um dich abzumelden" href="#" accesskey="l">Abmelden</a></li>
            </ul>
        </nav>
        
        <section role="main">
            <!-- winner message -->
            <section id="roundwinner" aria-labelledby="roundwinnerheading">
                <h2 id="roundwinnerheading" class="accessibility">Rundenzwischenstand</h2>
                <p class="roundwinnermessage"><%=currentRound.getRoundWinnerText() %></p>
            </section>
        
            <!-- round info -->    
            <section id="roundinfo" aria-labelledby="roundinfoheading">
                <h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
                <div id="player1info" class="playerinfo">
                    <span id="player1name" class="playername"><%= player1.getName()%></span>
                    <ul class="playerroundsummary">
                     <% for(int i = 0; i < BigQuizServlet.NUM_QUESTIONS; i++) { %>
                        <li>
                        	<span class="accessibility">Frage <%=i+1%>:</span>
                        	<span id="player1answer<%=i%>"class="<%=currentRound.getAnswersPlayer1()[i].classType() %>">
                        		  	<%=currentRound.getAnswersPlayer1()[i].text() %>
                            </span>
                        </li>
                     <% } %>     
                    </ul>
                    <p id="player1roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player1wonrounds" class="playerwonrounds"><%= game.getPlayer1WinCount()%></span></p>
                </div>
                <div id="player2info" class="playerinfo">
                    <span id="player2name" class="playername"><%= player2.getName()%></span>
                    <ul class="playerroundsummary">
                        <% for(int i = 0; i < BigQuizServlet.NUM_QUESTIONS; i++) { %>
                   	     <li>
                        	<span class="accessibility">Frage <%=i+1%>:</span>
                        	<span id="player2answer<%=i%>"class="<%=currentRound.getAnswersPlayer2()[i].classType() %>">
                        		  	<%=currentRound.getAnswersPlayer2()[i].text() %>
                            </span>
                         </li>
                        <% } %> 
                    </ul>
                    <p id="player2roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player2wonrounds" class="playerwonrounds"><%= game.getPlayer2WinCount()%></span></p>
                </div>
                <form  action="BigQuizServlet" method="GET">
		          	<input id="next" name="action" type="submit" accesskey="n" value="Weiter">
		        </form>
            </section>
        </section>

        <!-- footer -->
        <footer role="contentinfo">© 2014 BIG Quiz</footer>
    </body>
</html>