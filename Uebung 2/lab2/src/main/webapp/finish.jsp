<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="game" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimpleGame" />
<jsp:useBean id="player1" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" />
<jsp:useBean id="player2" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" />

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Business Informatics Group Quiz - Spielende</title>
        <link rel="stylesheet" type="text/css" href="style/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/framework.js" type="text/javascript"></script>
        <script type="text/javascript">
        	if(supportsLocalStorage()) {		
				localStorage.setItem("date", new Date().toLocaleString()); 
        	}
		</script>
    </head>
    <body id="winnerpage">
        <a class="accessibility" href="#roundwinner">Zur Spielwertung springen</a>
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
                <h2 id="roundwinnerheading" class="accessibility">Endstand</h2>
                <p class="roundwinnermessage"><%=game.getWinnerText()%></p>
            </section>
        
            <!-- round info -->    
            <section id="roundinfo" aria-labelledby="roundinfoheading">
                <h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
                <div id="player1info" class="playerinfo">
                    <span id="player1name" class="playername"><%= player1.getName()%></span>
                    <p id="player1roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player1wonrounds" class="playerwonrounds"><%= game.getPlayer1WinCount()%></span></p>
                </div>
                <div id="player2info" class="playerinfo">
                    <span id="player2name" class="playername"><%= player2.getName()%></span>
                    <p id="player2roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player2wonrounds" class="playerwonrounds"><%= game.getPlayer2WinCount()%></span></p>
                </div>
                <form  action="BigQuizServlet" method="GET">
		          	<input id="next" name="action" type="submit" accesskey="n" value="Neues Spiel">
		        </form>
            </section>
        </section>

        <!-- footer -->
        <footer role="contentinfo">© 2014 BIG Quiz</footer>
    </body>
</html>
