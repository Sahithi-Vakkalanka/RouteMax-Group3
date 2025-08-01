<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.routemax.teamroutemax.entity.Parcel" %>
<%
    Parcel parcel = (Parcel) request.getAttribute("parcel");
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
    <title>Track Parcel</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 40px; background-color: #f4f1ec; }
        form { margin-bottom: 20px; }
        input[type="text"] {
            padding: 10px; font-size: 16px; width: 300px; margin-right: 10px;
            border: 1px solid #ccc; border-radius: 6px;
        }
        button {
            padding: 10px 16px; font-size: 16px;
            background-color: #6c5f3f; color: white;
            border: none; border-radius: 6px;
            cursor: pointer;
        }
        .card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            max-width: 500px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .card h3 {
            margin-top: 0;
            color: #4b3e2d;
        }
        .field {
            margin: 8px 0;
        }
    </style>
</head>
<body>

<h2>Track a Parcel</h2>

<form method="post" action="/parcels/track">
    <input type="text" name="trackingId" placeholder="Enter Tracking ID" required />
    <button type="submit">Track</button>
</form>

<% if (error != null) { %>
    <p style="color: red;"><%= error %></p>
<% } %>

<% if (parcel != null) { %>
    <div class="card">
        <h3>Parcel Details</h3>
        <div class="field"><strong>Sender Name:</strong> <%= parcel.getSenderName() %></div>
        <div class="field"><strong>Sender Address:</strong> <%= parcel.getSenderAddress() %></div>
        <div class="field"><strong>Receiver Name:</strong> <%= parcel.getReceiverName() %></div>
        <div class="field"><strong>Receiver Address:</strong> <%= parcel.getReceiverAddress() %></div>
        <div class="field"><strong>Receiver Email:</strong> <%= parcel.getReceiverEmail() %></div>
        <div class="field"><strong>Description:</strong> <%= parcel.getDescription() %></div>
        <div class="field"><strong>Tracking ID:</strong> <%= parcel.getTrackingId() %></div>
        <div class="field"><strong>Status:</strong> <%= parcel.getStatus() %></div>
    </div>
<% } %>

</body>
</html>
