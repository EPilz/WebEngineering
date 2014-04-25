<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            <jsp:useBean id="play" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlay" scope="session"/>
            <!-- winner message -->
            <section id="roundwinner" aria-labelledby="roundwinnerheading">
                <h2 id="roundwinnerheading" class="accessibility">Rundenzwischenstand</h2>
                <p class="roundwinnermessage"><%=play.roundWinner()%> gewinnt Runde <%=play.getCount()%>!</p>
            </section>
        
            <!-- round info -->    
            <section id="roundinfo" aria-labelledby="roundinfoheading">
                <jsp:useBean id="player1" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" scope="session"/>
                <jsp:useBean id="player2" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" scope="session"/>
                <h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
                <div id="player1info" class="playerinfo">
                    <span id="player1name" class="playername"><%=player1.getName()%></span>
                    <ul class="playerroundsummary">
                        <li><span class="accessibility">Frage 1:</span><span id="player1answer1" class="correct"><%=player1.getAnswer()%></span></li>
                        <li><span class="accessibility">Frage 2:</span><span id="player1answer2" class="incorrect"><%=player1.getAnswer()%></span></li>
                        <li><span class="accessibility">Frage 3:</span><span id="player1answer3" class="correct"><%=player1.getAnswer()%></span></li>
                    </ul>
                    <p id="player1roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player1wonrounds" class="playerwonrounds">2</span></p>
                </div>
                <div id="player2info" class="playerinfo">
                    <span id="player2name" class="playername"><%=player2.getName()%></span>
                    <ul class="playerroundsummary">
                        <li><span class="accessibility">Frage 1:</span><span id="player2answer1" class="correct"><%=player2.getAnswer()%></span></li>
                        <li><span class="accessibility">Frage 2:</span><span id="player2answer2" class="correct"><%=player2.getAnswer()%></span></li>
                        <li><span class="accessibility">Frage 3:</span><span id="player2answer3" class="correct"><%=player2.getAnswer()%></span></li>
                    </ul>
                    <p id="player2roundcounter" class="playerroundcounter">Gewonnene Runden: <span id="player2wonrounds" class="playerwonrounds">1</span></p>
                </div>
                <a id="next" href="question.jsp">Weiter</a>
            </section>
        </section>

        <!-- footer -->
        <footer role="contentinfo">© 2014 BIG Quiz</footer>
    </body>
</html>