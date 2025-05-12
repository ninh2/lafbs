<%@ page session="true" %>
<%
    String role = (String) session.getAttribute("role");
%>
<script>
    localStorage.setItem("role", "<%= role %>");
    window.location.href = "index.html";
</script>
