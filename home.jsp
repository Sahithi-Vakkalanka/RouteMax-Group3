

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>RouteMax - Welcome</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('background.png') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .overlay-box {
            background-color: rgba(255, 255, 255, 0.92);
            padding: 40px 60px;
            border-radius: 16px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
            text-align: center;
        }

        h1 {
            font-size: 36px;
            margin-bottom: 30px;
            color: #333;
        }

        .profiles {
            display: flex;
            justify-content: space-around;
            align-items: center;
            gap: 50px;
        }

        .profile-box {
            text-align: center;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .profile-box:hover {
            transform: scale(1.05);
        }

        .profile-box img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            object-fit: cover;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        .profile-label {
            margin-top: 10px;
            font-size: 20px;
            font-weight: bold;
            color: #6c5f3f;
            text-decoration: none;
            display: inline-block;
        }

        .profile-label:hover {
            color: #4b3e2d;
        }
    </style>
</head>
<body>

<div class="overlay-box">
    <h1>Welcome to RouteMax</h1>

    <div class="profiles">
        <div class="profile-box" onclick="location.href='/register'">
            <img src="user.png" alt="User">
            <div class="profile-label">User</div>
        </div>

        <div class="profile-box" onclick="location.href='/admin'">
            <img src="admin.png" alt="Admin">
            <div class="profile-label">Admin</div>
        </div>
    </div>
</div>

</body>
</html>

