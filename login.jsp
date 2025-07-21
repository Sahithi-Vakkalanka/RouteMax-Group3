<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #a08a72, #5a4f3f);
            display: flex;
            height: 100vh;
            justify-content: center;
            align-items: center;
        }

        .page-container {
            display: flex;
            align-items: center;
            gap: 80px;
        }

        .project-header {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            color: white;
        }

        .project-header h1 {
            font-size: 48px;
            margin: 0 0 20px 0;
            font-weight: bold;
            letter-spacing: 1px;
        }

        .project-header img {
            height: 150px;
            width: 150px;
        }

        .form-box {
            background-color: white;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            width: 350px;
        }

        .form-box h2 {
            text-align: center;
            color: #523726;
        }

        .form-box label {
            display: block;
            margin-top: 15px;
        }

        .form-box input {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }

        .form-box button {
            margin-top: 20px;
            width: 100%;
            padding: 10px;
            background-color: #836e5e;
            border: none;
            border-radius: 10px;
            color: white;
            font-size: 16px;
        }

        .message {
            margin-top: 15px;
            text-align: center;
            color: rgb(0, 96, 27);
        }

        .register-link {
            text-align: center;
            margin-top: 20px;
        }

        .register-link a {
            color: blue;
            text-decoration: none;
        }
    </style>
</head>
<body>

<div class="page-container">

    <div class="project-header">
        <h1>RouteMax</h1>
        <img src="logo.gif.gif" alt="RouteMax Logo" />
    </div>

    <div class="form-box">
        <h2>User Login</h2>

        <form action="/login" method="post">
            <label for="email">Email:</label>
            <input type="email" name="email" required />

            <label for="password">Password:</label>
            <input type="password" name="password" required />

            <button type="submit">Login</button>
        </form>

        <div class="message">${message}</div>

        <div class="register-link">
            Dont have an account? <a href="/register">Register</a>
        </div>
    </div>

</div>

</body>
</html>
