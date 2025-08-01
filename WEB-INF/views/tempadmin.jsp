<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #a08a72, #5a4f3f);
            min-height: 100vh;
            margin: 0;
        }

        .page-container {
            display: flex;
            height: 100vh;
            justify-content: center;
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
            width: 400px;
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
            cursor: pointer;
        }

        .form-box button:hover {
            opacity: 0.9;
        }

        .error-message {
            color: red;
            text-align: center;
            margin-top: 10px;
        }

        .user-management {
            display: none;
            padding: 40px;
            color: white;
        }

        .user-management h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .action-buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-bottom: 20px;
        }

        .btn {
            padding: 14px 28px;
            font-size: 18px;
            font-weight: bold;
            border: none;
            border-radius: 10px;
            color: white;
            cursor: pointer;
            transition: all 0.2s ease-in-out;
        }

        .btn-add {
            background-color: #28a745;
        }

        .btn-delete {
            background-color: #dc3545;
        }

        .btn-edit {
            background-color: #007bff;
        }

        .btn:hover {
            opacity: 0.9;
            transform: scale(1.03);
        }

        #searchInput {
            display: block;
            margin: 0 auto 20px auto;
            padding: 10px;
            width: 320px;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
            background: white;
            color: #333;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #4a6582;
            color: white;
        }

        .popup {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 6px 18px rgba(0,0,0,0.3);
            z-index: 9999;
            display: none;
            width: 350px;
            font-family: Arial, sans-serif;
        }

        .popup h3 {
            margin-top: 0;
            color: #4b3e2d;
            text-align: center;
        }

        .popup input, .popup textarea {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        .popup button {
            margin-top: 10px;
            cursor: pointer;
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

        /* Button container for User/Parcel Management toggle buttons */
        #managementButtons {
            display: flex;
            justify-content: center;
            gap: 24px;
            margin: 30px 0;
        }
        #managementButtons .btn {
            width: 180px;
            font-size: 20px;
        }
    </style>

    <script>
        const ADMIN_PASSWORD = "admin123";
        let currentEditId = null;

        // Admin login handler: shows management buttons and default section
        function handleLogin(event) {
            event.preventDefault();
            const password = document.getElementById("adminPassword").value.trim();
            const error = document.getElementById("errorMessage");

            if (password === ADMIN_PASSWORD) {
                document.querySelector('.page-container').style.display = 'none';
                document.querySelector('.user-management').style.display = 'block';
                showUserManagement(); // Show user management by default
            } else {
                error.innerText = "Incorrect admin password!";
            }
        }

        // User Management section show/hide
        function showUserManagement() {
            document.getElementById("userManagementSection").style.display = "block";
            document.getElementById("parcelManagementSection").style.display = "none";
            fetchUsers();
        }

        // Parcel Management section show/hide
        function showParcelManagement() {
            document.getElementById("userManagementSection").style.display = "none";
            document.getElementById("parcelManagementSection").style.display = "block";
            fetchParcels();
        }

        // Fetch users and populate table
        async function fetchUsers() {
            try {
                const response = await fetch('/api/admin/users');
                if (!response.ok) {
                    alert("Failed to fetch users");
                    return;
                }
                const users = await response.json();
                const tbody = document.getElementById('userTableBody');
                tbody.innerHTML = '';

                users.forEach(user => {
                    const tr = document.createElement('tr');

                    const tdId = document.createElement('td');
                    tdId.textContent = user.id;

                    const tdName = document.createElement('td');
                    tdName.textContent = user.name || '(no name)';

                    const tdEmail = document.createElement('td');
                    tdEmail.textContent = user.email || '(no email)';

                    tr.appendChild(tdId);
                    tr.appendChild(tdName);
                    tr.appendChild(tdEmail);
                    tbody.appendChild(tr);
                });
            } catch (error) {
                alert("Error fetching users: " + error.message);
            }
        }

        // Search/filter users by name
        function filterUsers() {
            const input = document.getElementById('searchInput').value.toLowerCase();
            const rows = document.querySelectorAll('#userTableBody tr');

            rows.forEach(row => {
                const name = row.cells[1].textContent.toLowerCase();
                row.style.display = name.includes(input) ? '' : 'none';
            });
        }

        // Add User popup controls
        function openAddPopup() {
            document.getElementById("addOverlay").style.display = "block";
            document.getElementById("addPopup").style.display = "block";
        }
        function closeAddPopup() {
            document.getElementById("addOverlay").style.display = "none";
            document.getElementById("addPopup").style.display = "none";
        }
        async function addUser() {
            const name = document.getElementById("addName").value.trim();
            const email = document.getElementById("addEmail").value.trim();
            const password = document.getElementById("addPassword").value;
            if(!name || !email || !password) {
                alert("Please fill in all fields.");
                return;
            }
            const response = await fetch('/api/admin/users', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name, email, password})
            });
            if(response.ok) {
                alert("User added successfully!");
                closeAddPopup();
                fetchUsers();
            } else {
                const msg = await response.text();
                alert("Failed to add user: " + msg);
            }
        }

        // Delete User popup controls
        function openDeletePopup() {
            document.getElementById("deleteOverlay").style.display = "block";
            document.getElementById("deletePopup").style.display = "block";
        }
        function closeDeletePopup() {
            document.getElementById("deleteOverlay").style.display = "none";
            document.getElementById("deletePopup").style.display = "none";
        }
        async function deleteUserById() {
            const id = document.getElementById("deleteUserId").value.trim();
            if(isNaN(id) || id === "") {
                alert("Please enter a valid User ID");
                return;
            }
            if(confirm("Are you sure you want to delete this user?")) {
                const response = await fetch('/api/admin/users/${id}', {
                    method: 'DELETE',
                    headers: {'Content-Type': 'application/json'}
                });
                if(response.ok) {
                    alert("User deleted successfully");
                    closeDeletePopup();
                    fetchUsers();
                } else {
                    alert("Failed to delete user");
                }
            }
        }

        // Edit User popup controls
        function openEditIdPopup() {
            document.getElementById("editIdOverlay").style.display = "block";
            document.getElementById("editIdPopup").style.display = "block";
        }
        function closeEditIdPopup() {
            document.getElementById("editIdOverlay").style.display = "none";
            document.getElementById("editIdPopup").style.display = "none";
        }
        async function fetchUserForEdit() {
            const id = document.getElementById("editUserIdInput").value.trim();
            if(isNaN(id) || id === "") {
                alert("Please enter a valid User ID");
                return;
            }
            const response = await fetch('/api/admin/users/${id}');
            if(response.ok) {
                const user = await response.json();
                closeEditIdPopup();
                openEditPopup(user.id, user.name, user.email);
            } else {
                alert("User not found.");
            }
        }
        function openEditPopup(id, name, email) {
            currentEditId = id;
            document.getElementById("editName").value = name;
            document.getElementById("editEmail").value = email;
            document.getElementById("overlay").style.display = "block";
            document.getElementById("editPopup").style.display = "block";
        }
        function closeEditPopup() {
            document.getElementById("overlay").style.display = "none";
            document.getElementById("editPopup").style.display = "none";
        }
        async function updateUser() {
            const name = document.getElementById("editName").value.trim();
            const email = document.getElementById("editEmail").value.trim();
            if(!name || !email) {
                alert("Please fill all fields.");
                return;
            }
            const response = await fetch('/api/admin/users/${currentEditId}', {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name, email})
            });
            if(response.ok) {
                alert("User updated!");
                closeEditPopup();
                fetchUsers();
            } else {
                alert("Update failed.");
            }
        }

        // Fetch parcels and populate parcel table
async function fetchParcels() {
    try {
        const response = await fetch('/api/admin/parcels');
        if (!response.ok) {
            alert("Failed to fetch parcels");
            return;
        }

        const parcels = await response.json();
        console.log("Fetched parcels:", parcels); // ✅ Add this line here

        const tbody = document.getElementById('parcelTableBody');
        tbody.innerHTML = '';

        parcels.forEach(parcel => {
            const tr = document.createElement('tr');

            const tdId = document.createElement('td');
            tdId.textContent = parcel.id;

            const tdSenderName = document.createElement('td');
            tdSenderName.textContent = parcel.senderName || '(no name)';

            const tdSenderAddress = document.createElement('td');
            tdSenderAddress.textContent = parcel.senderAddress || '(no address)';

            const tdReceiverName = document.createElement('td');
            tdReceiverName.textContent = parcel.receiverName || '(no name)';

            const tdReceiverAddress = document.createElement('td');
            tdReceiverAddress.textContent = parcel.receiverAddress || '(no address)';

            const tdReceiverEmail = document.createElement('td');
            tdReceiverEmail.textContent = parcel.receiverEmail || '(no email)';

            const tdDescription = document.createElement('td');
            tdDescription.textContent = parcel.description || '(no description)';

            const tdTracking = document.createElement('td');
            tdTracking.textContent = parcel.trackingId || '(no tracking ID)';

            const tdStatus = document.createElement('td');
            tdStatus.textContent = parcel.status || 'In transit';  // ✅ Display status

            tr.appendChild(tdId);
            tr.appendChild(tdSenderName);
            tr.appendChild(tdSenderAddress);
            tr.appendChild(tdReceiverName);
            tr.appendChild(tdReceiverAddress);
            tr.appendChild(tdReceiverEmail);
            tr.appendChild(tdDescription);
            tr.appendChild(tdTracking);
            tr.appendChild(tdStatus); // ✅ Append status cell

            tbody.appendChild(tr);
        });

    } catch (error) {
        alert("Error fetching parcels: " + error.message);
    }
}



        // Add Parcel popup controls remain same
        function openParcelPopup() {
            document.getElementById("parcelOverlay").style.display = "block";
            document.getElementById("parcelPopup").style.display = "block";
        }
        function closeParcelPopup() {
            document.getElementById("parcelOverlay").style.display = "none";
            document.getElementById("parcelPopup").style.display = "none";
            // Clear form fields
            document.getElementById("senderName").value = "";
            document.getElementById("senderAddress").value = "";
            document.getElementById("receiverName").value = "";
            document.getElementById("receiverAddress").value = "";
            document.getElementById("receiverEmail").value = "";
            document.getElementById("description").value = "";
        }
        async function submitParcel() {
            const senderName = document.getElementById("senderName").value.trim();
            const senderAddress = document.getElementById("senderAddress").value.trim();
            const receiverName = document.getElementById("receiverName").value.trim();
            const receiverAddress = document.getElementById("receiverAddress").value.trim();
            const receiverEmail = document.getElementById("receiverEmail").value.trim();
            const description = document.getElementById("description").value.trim();

            if(!senderName || !senderAddress || !receiverName || !receiverAddress || !receiverEmail || !description) {
                alert("Please fill all fields.");
                return;
            }
            try {
                const response = await fetch('/api/admin/parcels', {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({ senderName, senderAddress, receiverName, receiverAddress, receiverEmail, description })
                });
                if(response.ok) {
                    alert("Parcel added successfully!");
                    closeParcelPopup();
                    // Optionally refresh parcel list if in view
                    if(document.getElementById('parcelManagementSection').style.display === "block") fetchParcels();
                } else {
                    const errMsg = await response.text();
                    alert("Failed to add parcel: " + errMsg);
                }
            } catch(error) {
                alert("Error adding parcel: " + error.message);
            }
        }
    </script>
</head>
<body>

<!-- Admin Login Section -->
<div class="page-container">
    <div class="project-header">
        <h1>RouteMax</h1>
        <img src="logo.gif.gif" alt="RouteMax Logo" />
    </div>

    <div class="form-box">
        <h2>Admin Login</h2>
        <form onsubmit="handleLogin(event)">
            <label for="adminPassword">Enter Admin Password:</label>
            <input type="password" id="adminPassword" required />
            <button type="submit">Login</button>
        </form>
        <div id="errorMessage" class="error-message"></div>
    </div>
</div>

<!-- Admin Dashboard -->
<div class="user-management">

    <h2>Admin Dashboard</h2>

    <div id="managementButtons">
        <button class="btn btn-edit" onclick="showUserManagement()">User Management</button>
        <button class="btn btn-add" onclick="showParcelManagement()">Parcel Management</button>
    </div>

    <!-- User Management Section -->
    <div id="userManagementSection" style="display: none;">
        <h3>User Management</h3>
        <div class="action-buttons">
            <button class="btn btn-add" onclick="openAddPopup()">Add a New User</button>
            <button class="btn btn-delete" onclick="openDeletePopup()">Delete a User</button>
            <button class="btn btn-edit" onclick="openEditIdPopup()">Edit a User</button>
        </div>
        <input type="text" id="searchInput" placeholder="Search by name..." onkeyup="filterUsers()">

        <table>
            <thead>
                <tr><th>ID</th><th>Name</th><th>Email</th></tr>
            </thead>
            <tbody id="userTableBody"></tbody>
        </table>

        <!-- Add User Popup -->
        <div class="overlay" id="addOverlay" onclick="closeAddPopup()"></div>
        <div class="popup" id="addPopup">
            <h3>Add New User</h3>
            <input type="text" id="addName" placeholder="Name" required>
            <input type="email" id="addEmail" placeholder="Email" required>
            <input type="password" id="addPassword" placeholder="Password" required>
            <button onclick="addUser()">Add</button>
            <button onclick="closeAddPopup()">Cancel</button>
        </div>

        <!-- Delete User Popup -->
        <div class="overlay" id="deleteOverlay" onclick="closeDeletePopup()"></div>
        <div class="popup" id="deletePopup">
            <h3>Delete User</h3>
            <input type="number" id="deleteUserId" placeholder="Enter User ID">
            <button onclick="deleteUserById()">Delete</button>
            <button onclick="closeDeletePopup()">Cancel</button>
        </div>

        <!-- Edit User ID Popup -->
        <div class="overlay" id="editIdOverlay" onclick="closeEditIdPopup()"></div>
        <div class="popup" id="editIdPopup">
            <h3>Edit User</h3>
            <input type="number" id="editUserIdInput" placeholder="Enter User ID">
            <button onclick="fetchUserForEdit()">Next</button>
            <button onclick="closeEditIdPopup()">Cancel</button>
        </div>

        <!-- Edit User Details Popup -->
        <div class="overlay" id="overlay" onclick="closeEditPopup()"></div>
        <div class="popup" id="editPopup">
            <h3>Edit User Details</h3>
            <input type="text" id="editName" placeholder="New Name">
            <input type="email" id="editEmail" placeholder="New Email">
            <button onclick="updateUser()">Save</button>
            <button onclick="closeEditPopup()">Cancel</button>
        </div>
    </div>

    <!-- Parcel Management Section -->
    <div id="parcelManagementSection" style="display: none;">
        <h3>Parcel Management</h3>

        <button id="addParcelBtn" onclick="openParcelPopup()" class="btn btn-add" style="margin-bottom: 20px;">
            Add Parcel
        </button>

        <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Sender Name</th>
                <th>Sender Address</th>
                <th>Receiver Name</th>
                <th>Receiver Address</th>
                <th>Receiver Email</th>
                <th>Description</th>
                <th>Tracking ID</th> 
                <th>Status</th>
            </tr>
        </thead>

            <tbody id="parcelTableBody"></tbody>
        </table>

        <!-- Add Parcel Popup -->
        <div class="overlay" id="parcelOverlay" onclick="closeParcelPopup()"></div>
        <div class="popup" id="parcelPopup" role="dialog" aria-modal="true" aria-labelledby="parcelPopupTitle">
            <h3 id="parcelPopupTitle">Add Parcel Details</h3>

            <label for="senderName">Sender Name:</label>
            <input type="text" id="senderName" placeholder="Sender Name" required />

            <label for="senderAddress">Sender Address:</label>
            <textarea id="senderAddress" placeholder="Sender Address" rows="2" required></textarea>

            <label for="receiverName">Receiver Name:</label>
            <input type="text" id="receiverName" placeholder="Receiver Name" required />

            <label for="receiverAddress">Receiver Address:</label>
            <textarea id="receiverAddress" placeholder="Receiver Address" rows="2" required></textarea>

            <label for="receiverEmail">Receiver Email:</label>
            <input type="email" id="receiverEmail" placeholder="Receiver Email" required />

            <label for="description">Description:</label>
            <textarea id="description" placeholder="Description" rows="3" required></textarea>

            <button onclick="submitParcel()" style="width: 100%; background: #28a745; color: white; font-weight: bold; margin-top: 12px; border-radius: 8px; padding: 10px; border: none; cursor: pointer;">
                Add Parcel
            </button>
            <button onclick="closeParcelPopup()" style="width: 100%; background: #dc3545; color: white; font-weight: bold; margin-top: 8px; border-radius: 8px; padding: 10px; border: none; cursor: pointer;">
                Cancel
            </button>
        </div>
    </div>

</div>

</body>
</html>

