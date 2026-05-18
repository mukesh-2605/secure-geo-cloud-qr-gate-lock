<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="Dbconn.dbconn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement" %>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
   <%@ page import="java.util.*" %>
    <%@ page import="java.util.Random" %>
    <%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css1/bootstrap.min.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>

<title>QR-Code Generator</title>
<style>
*{margin:0px; padding:0px;font-family: Helvetica, Arial, sans-serif;}

input[type=text]{
    width: 90%;
    padding: 12px 20px;
    margin: 8px 26px;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
	font-size:16px;
}
button {
       background-color: #b71b1b;
    color: white;
    padding: 6px 22px;
    margin: 10px 37px;

    border: none;
    cursor: pointer;
  width: 61%;
    height: 46px;
	font-size:20px;
}
button:hover {
    opacity: 0.8;
}
</style>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<button onclick="history.back();" class="btn btn-danger" style="width:100px; float:right;">back</button>
<body >

<center>
<%String email=session.getAttribute("semail").toString(); %>
<div class="col-sm-2"></div>
        <div class="col-sm-8">
        <h3>Staff Download here!!!</h3>

        <center>
        <table class="table table-sm" style="margin-top: 100px">
        <tr>

        <th>Filename</th>

        <th>TL Email</th>
        <th>Filekey </th>
		 <th>QR Generate</th>

        <th>Download</th>
        </tr>
             <%
 String sno="";
 String file="";



Connection con = dbconn.create();
PreparedStatement p = con.prepareStatement("SELECT * FROM bc04.manageract where sfemail='"+email+"' and status='sended'");
ResultSet rs=p.executeQuery();

	while(rs.next())
	{
		sno=rs.getString(1);

		file=rs.getString(8);
	%>
	<tr>
<td><%=rs.getString(3)%></td>
<td><%=rs.getString(4)%></td>
<td><%=rs.getString(6)%></td>

<td>


        <button class="generate" onclick="myFunction(this.value)" value="<%=file %>">generate</button>

</td>

<td><a href="staffdownloadjava.jsp?fname1=<%=rs.getString(3)%>&&fkeyl=<%=rs.getString(6)%>&&headkey=<%=file %>"><button class="btn-btn-info btn-sm"> Download</button></a></td>
</tr>
<%}%>
            </table>
        </center>
        </div>
        <div class="col-sm-2"></div>
            </center>
                 </div>




  <div style="float:right;">
   <div id="image" style="margin:auto"></div>
   <div id="link" style="margin-top:10px; text-align:center"></div>
  </div>

  <div id="code" style="float:left; width:100%; height:20px; text-align:center; margin-top:10px"></div>
<%--<script>--%>

<%--function myFunction(data) {--%>

<%--var size = 248;--%>
<%--if(data == "") {--%>
<%--	console.log("out")--%>
<%--    $("#alert").append("<p style='color:#fff;font-size:20px'>Please Enter A Url Or Text</p>"); // If Input Is Blank--%>
<%--    return false;--%>
<%--} else {--%>
<%--    if( $("#image").is(':empty')) {--%>
<%--		console.log("in")--%>
<%--	  //QR Code Image--%>
<%--      $("#image").append("<img src='http://chart.apis.google.com/chart?cht=qr&chl=" + data + "&chs=" + size + "' alt='qr' />");--%>
<%--	  --%>
<%--	  //This Provide An Image Download Link--%>
<%--      $("#link").append("<a style='color:#fff;' href='http://chart.apis.google.com/chart?cht=qr&chl=" + data + "&chs=" + size + "'>Download QR Code</a>");--%>
<%--	  --%
<%--	  //This Provide the Image Link Path In Text--%>
<%--     // $("#code").append("<p style='color:#fff;'><strong>Image Link:</strong> http://chart.apis.google.com/chart?cht=qr&chl=" + data + "&chs=" + size + "</p>");--%>
<%--      return true;--%>
<%--	} else {--%>
<%--		console.log("else out")--%>
<%--      $("#image").html("");--%>
<%--      $("#link").html("");--%>
<%--      $("#code").html("");--%>
<%--	  --%>
<%--      //QR Code Image--%>
<%--      $("#image").append("<img src='http://chart.apis.google.com/chart?cht=qr&chl=" + data + "&chs=" + size + "' alt='qr' />");--%>
<%--	  --%>
<%--	  //This Provide An Image Download Link--%>
<%--      $("#link").append("<a style='color:#fff;' href='http://chart.apis.google.com/chart?cht=qr&chl=" + data + "&chs=" + size + "'>Download QR Code</a>");--%>
<%--	  --%>
<%--	  //This Provide the Image Link Path In Text--%>
<%--      $("#code").append("<p style='color:#fff;'><strong>Image Link:</strong> http://chart.apis.google.com/chart?cht=qr&chl=" + data + "&chs=" + size + "</p>");--%>
<%--      return false;--%>
<%--    }--%>
<%--  }--%>
<%--}--%>
<%--</script>--%>
<script>
    function myFunction(data) {
        // 1. SET YOUR EXACT SIZE HERE (Width x Height)
        // Your old code used 248, so we use "248x248" to make it a perfect square.
        var size = "248x248";

        if(data == "") {
            console.log("out");
            // Optional: Alert the user if empty
            // $("#alert").append("<p>Please Enter Text</p>");
            return false;
        } else {
            // 2. Clear previous QR codes
            $("#image").html("");
            $("#link").html("");
            $("#code").html("");

            // 3. Generate the URL
            var qrUrl = "https://api.qrserver.com/v1/create-qr-code/?data=" + encodeURIComponent(data) + "&size=" + size;

            console.log("Generating QR for: " + data);

            // 4. Show the Image (Forced styling ensures it doesn't stretch)
            $("#image").append("<img src='" + qrUrl + "' alt='qr' style='width: 248px; height: 248px;' />");

            // 5. Download Link
            $("#link").append("<a style='color:blue; font-weight:bold;' href='" + qrUrl + "' target='_blank'>Right Click Image -> Save As</a>");
        }
    }
</script>
</center>
</body>
</html>