<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="at.ac.tuwien.big.we14.lab2.api.Choice" %>
<%@page import="at.ac.tuwien.big.we14.lab2.servlet.BigQuizServlet"%>
<jsp:useBean id="player1" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" />
<jsp:useBean id="player2" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimplePlayer" />
<jsp:useBean id="currentRound" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimpleRound" />
<jsp:useBean id="currentQuestion" scope="session" class="at.ac.tuwien.big.we14.lab2.api.impl.SimpleQuestion" />

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de" lang="de">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Business Informatics Group Quiz</title>
        <link rel="stylesheet" type="text/css" href="style/screen.css" />
        <script src="js/jquery.js" type="text/javascript"></script>
        <script src="js/framework.js" type="text/javascript"></script>
        <script type="text/javascript">
       		window.onload = function() {
	        	if(supportsLocalStorage()) {
	        		var date = localStorage.getItem("date");
	        		
	        		if(date != null) {
	        			last = document.getElementById("lastgame");
	        			last.innerHTML = "<p>Letztes Spiel: " + date + "</p>";
	        		}
	        	}
       		}        	
		</script>
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
        <section role="main">
            <section id="roundinfo" aria-labelledby="roundinfoheading">
                <h2 id="roundinfoheading" class="accessibility">Spielerinformationen</h2>
                <div id="player1info">
                    <span id="player1name"><%= player1.getName()%></span>
                    <ul class="playerroundsummary">
                   	 <% for(int i = 0; i < BigQuizServlet.NUM_QUESTIONS; i++) { %>
                        <li>
                        	<span class="accessibility">Frage <%=i+1%>:</span>
                        	<span id="player1answer<%=i%>" class="<%=currentRound.getAnswersPlayer1()[i].classType() %>">
                        		  	<%=currentRound.getAnswersPlayer1()[i].text() %>
                            </span>
                        </li>
                     <% } %>              
                    </ul>
                </div>
                <div id="player2info">
                    <span id="player2name"><%= player2.getName()%></span>
                    <ul class="playerroundsummary">
                    	<% for(int i = 0; i < BigQuizServlet.NUM_QUESTIONS; i++) { %>
                   	     <li>
                        	<span class="accessibility">Frage <%=i+1%>:</span>
                        	<span id="player2answer<%=i%>" class="<%=currentRound.getAnswersPlayer2()[i].classType() %>">
                        		  	<%=currentRound.getAnswersPlayer2()[i].text() %>
                            </span>
                         </li>
                        <% } %>        
                    </ul>
                </div>
                <div id="currentcategory"><span class="accessibility">Kategorie:</span> <%= currentRound.getCategory().getName() %></div>
            </section>
            
            <!-- Question -->
            <section id="question" aria-labelledby="questionheading">
                
                <form id="questionform" action="BigQuizServlet"  method="POST">
                    <h2 id="questionheading" class="accessibility">Frage</h2>
                    <p id="questiontext"><%= currentQuestion.getText() %></p>
                    <ul id="answers">                       
                        <% List<Choice> list = currentQuestion.getAllChoices(); 
                        	for(int i = 0; i < list.size(); i++) { %>
				        <li>      
				        	<input name="option<%=list.get(i).getId() %>" id="option<%=list.get(i).getId() %>" type="checkbox"/>
				        	<label for="option<%=list.get(i).getId() %>" id="labeloption<%=i %>"><%=list.get(i).getText()%></label>
				        </li>
				    	<% } %>                        
                    </ul>
                    <input id="timeleftvalue" name="timeleftvalue"  type="hidden" value="100"/>
                    <input id="questionid" name="questionid" type="hidden" value="<%= currentQuestion.getId() %>"/>
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
        <footer role="contentinfo">© 2014 BIG Quiz</footer>
        
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
