<!--jsp:useBean id="player1" scope ="session" class="Beans.Player" />
jsp:useBean id="player2" scope ="session" class="Beans.Player" />-->


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Business Informatics Group Quiz</title>
        <link rel="stylesheet" type="text/css" href="style/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/framework.js" type="text/javascript"></script>
    </head>
    <body id="questionpage">
        <a class="accessibility" href="#question">Zur Frage springen</a>
        <header role="banner" aria-labelledby="mainheading"><h1 id="mainheading"><span class="accessibility">Business Informatics Group</span> Quiz</h1></header>
        <nav role="navigation" aria-labelledby="navheading">
            <h2 id="navheading" class="accessibility">Navigation</h2>
            <ul>
                <li><a id="logoutlink" title="Klicke hier um dich abzumelden" href="#" accesskey="l">Abmelden</a></li>
            </ul>
        </nav>
        
        <!-- round info -->
        <jsp:useBean id = "category" class = "at.ac.tuwien.big.we14.lab2.api.impl.SimpleCategory" scope="session"/>
        <jsp:useBean id="player1"   class = "at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" scope="session"/>
        <jsp:useBean id="player2"   class = "at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" scope="session"/>
        <jsp:useBean id="answer" class="at.ac.tuwien.big.we14.lab2.api.impl.SimpleAnswer" scope="session"/>
        <section role="main">
            <section id="roundinfo" aria-labelledby="roundinfoheading">
                <h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
                <div id="player1info">
                    <span id="player1name"><%= player1.getName()%></span>
                    <ul class="playerroundsummary">
                        <!-- mit answer -->
                        <li><span class="accessibility">Frage 1:</span><span id="player1answer1" class="correct"><jsp:setProperty name="answer" property="answer" value="true"/></span></li>
                        <li><span class="accessibility">Frage 2:</span><span id="player1answer2" class="incorrect"><jsp:setProperty name="answer" property="answer" value="false"/></span></li>
                        <li><span class="accessibility">Frage 3:</span><span id="player1answer3" class="unknown"><jsp:setProperty name="answer" property="answer"/></span></li>
                    </ul>
                </div>
                <div id="player2info">
                    <span id="player2name"><%= player2.getName()%></span>
                    <ul class="playerroundsummary">
                        <li><span class="accessibility">Frage 1:</span><span id="player2answer1" class="correct">Richtig</span></li>
                        <li><span class="accessibility">Frage 2:</span><span id="player2answer2" class="correct">Richtig</span></li>
                        <li><span class="accessibility">Frage 3:</span><span id="player2answer3" class="unknown">Unbekannt</span></li>
                    </ul>
                </div>
                <div id="currentcategory"><span class="accessibility">Kategorie:</span> <%= category.getName()%></div>
            </section>
            
            <!-- Question -->
            <jsp:useBean id = "question" class = "at.ac.tuwien.big.we14.lab2.api.impl.SimpleQuestion" scope="session"/>
            <jsp:useBean id="choice" class="at.ac.tuwien.big.we14.lab2.api.impl.SimpleChoice" scope="session"/>
            <section id="question" aria-labelledby="questionheading">
                
                <form id="questionform" action="question.jsp" method="post">
                    <h2 id="questionheading" class="accessibility">Frage</h2>
                    <%= question.getText()%>
                    <jsp:setProperty name="question" property="text"/>
                    <ul id="answers">

                        <li><input id="option1" type="checkbox"/><label for="option1"><%= choice.getText()%></label></li>
                        <li><input id="option2" type="checkbox"/><label for="option2"><%= choice.getText()%></label></li>
                        <li><input id="option3" type="checkbox"/><label for="option3"><%= choice.getText()%></label></li>
                        <li><input id="option4" type="checkbox"/><label for="option4"><%= choice.getText()%></label></li>
                    </ul>
                    <input id="timeleftvalue" type="hidden" value="100"/>
                    <input id="next" type="submit" value="weiter" accesskey="s"/>
                </form>
            </section>
            
            <section id="timer" aria-labelledby="timerheading">
                <h2 id="timerheading" class="accessibility">Timer</h2>
                <p><span id="timeleftlabel">Verbleibende Zeit:</span> <time id="timeleft">00:30</time></p>
                <meter id="timermeter" min="0" low="20" value="100" max="100"/>
            </section>
            
            <section id="lastgame">
                <p>Letztes Spiel: Nie</p>
            </section>
        </section>

        <!-- footer -->
        <footer role="contentinfo">� 2014 BIG Quiz</footer>
        
        <script type="text/javascript">
            //<![CDATA[
            
            // initialize time
            $(document).ready(function() {
                var maxtime = 30;
                var hiddenInput = $("#timeleftvalue");
                var meter = $("#timer meter");
                var timeleft = $("#timeleft");
                
                hiddenInput.val(maxtime);
                meter.val(maxtime);
                meter.attr('max', maxtime);
                meter.attr('low', maxtime/100*20);
                timeleft.text(secToMMSS(maxtime));
            });
            
            // update time
            function timeStep() {
                var hiddenInput = $("#timeleftvalue");
                var meter = $("#timer meter");
                var timeleft = $("#timeleft");
                
                var value = $("#timeleftvalue").val();
                if(value > 0) {
                    value = value - 1;   
                }
                
                hiddenInput.val(value);
                meter.val(value);
                timeleft.text(secToMMSS(value));
                
                if(value <= 0) {
                    $('#questionform').submit();
                }
            }
            
            window.setInterval(timeStep, 1000);
            
            //]]>
        </script>
    </body>
</html>
