<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.routemax.teamroutemax.entity.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>User Dashboard</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 0;
            background-color: #c7b8aa;
        }

        .top-bar {
            background-color: #a69784;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }

        .top-bar .nav-links a {
            color: #4b3e2d;
            text-decoration: none;
            margin-right: 15px;
            font-weight: 500;
        }

        .top-bar .title {
            font-size: 24px;
            font-weight: bold;
            color: #4b3e2d;
        }

        .profile-icon {
            width: 52px;
            height: 43px;
            border-radius: 50%;
            cursor: pointer;
        }

        .dark-btn {
            background-color: #3b2c1a;
            color: white;
            margin-top: 10px;
            padding: 10px 18px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .dark-btn:hover {
            background-color: #2d2012;
        }

        .content {
            max-width: 700px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .card {
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            padding: 25px 30px;
            margin-bottom: 30px;
            display: none;
        }

        .card h3 {
            margin-top: 0;
            color: #6c5f3f;
        }

        .info {
            margin-bottom: 10px;
        }

        .popup {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #fff;
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.3);
            z-index: 9999;
            display: none;
            width: 400px;
        }

        .popup h3 {
            margin-top: 0;
            text-align: center;
            color: #4b3e2d;
        }

        .popup input, .popup textarea {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            font-family: 'Segoe UI', sans-serif;
        }

        .popup textarea {
            resize: vertical;
            min-height: 60px;
        }

        .popup button {
            margin-top: 10px;
        }

        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            height: 100%;
            width: 100%;
            background: rgba(0,0,0,0.4);
            z-index: 9998;
            display: none;
        }

        .form-section {
            margin-top: 10px;
        }

        .close-btn {
            background-color: #4682B4;
            color: white;
            border: none;
            border-radius: 6px;
            padding: 10px 18px;
            cursor: pointer;
            font-size: 14px;
        }

        .close-btn:hover {
            background-color: #315f84;
        }
    </style>
    <script>
        function toggleProfile() {
            const profileSection = document.getElementById("profile-section");
            profileSection.style.display = profileSection.style.display === "block" ? "none" : "block";
        }

        function openPopup() {
            document.getElementById("overlay").style.display = "block";
            document.getElementById("details-popup").style.display = "block";
        }

        function closePopup() {
            document.getElementById("overlay").style.display = "none";
            document.getElementById("details-popup").style.display = "none";
        }
    </script>
</head>
<body>

<div class="top-bar">
    <div class="nav-links">
        <a href="/">Home</a>
        <a href="/logout">Logout</a>
        <a href="/parcels/track">Track Parcel</a>
    </div>
    <div class="title">ROUTEMAX</div>
    <div>
        <img src="profile.png" alt="User" class="profile-icon" onclick="toggleProfile()" />
    </div>
</div>

<div class="content">
    <!-- Profile Info (Initially Hidden) -->
    <div id="profile-section" class="card">
        <h3>User Profile</h3>
        <div class="info"><strong>Name:</strong> <%= user != null ? user.getName() : "" %></div>
        <div class="info"><strong>Email:</strong> <%= user != null ? user.getEmail() : "" %></div>
        <button class="dark-btn" onclick="openPopup()">Change Details</button>
    </div>
</div>

<!-- Profile Details Modal -->
<div class="overlay" id="overlay" onclick="closePopup()"></div>
<div class="popup" id="details-popup" role="dialog" aria-modal="true" aria-labelledby="details-popup-title">
    <h3 id="details-popup-title">Update Your Details</h3>

    <div class="form-section">
        <form action="/update-name" method="post">
            <input type="text" name="name" placeholder="New Name" required />
            <button type="submit" class="dark-btn">Update Name</button>
        </form>
    </div>

    <div class="form-section">
        <form action="/update-email" method="post">
            <input type="email" name="email" placeholder="New Email" required />
            <button type="submit" class="dark-btn">Update Email</button>
        </form>
    </div>

    <div class="form-section">
        <form action="/update-password" method="post">
            <input type="password" name="newPassword" placeholder="New Password" required />
            <button type="submit" class="dark-btn">Update Password</button>
        </form>
    </div>

    <button onclick="closePopup()" class="close-btn">Close</button>
</div>

</body>
</html>