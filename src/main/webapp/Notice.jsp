<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Notice</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Custom CSS */
        body {
            padding: 20px;
        }
        form {
            margin-bottom: 20px;
        }
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
<h2>Notice</h2>
 <form action="NoticeServlet" method="post">
        <div class="form-group">
            <label for="notice">Notice:</label>
            <textarea class="form-control" id="notice" name="notice" rows="3" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Notice</button>
    </form>
    <ul>
        <c:forEach var="notice" items="${noticeList}">
            <li>${notice}</li>
        </c:forEach>
    </ul>
    
     <!-- Bootstrap JS (Optional, if needed for functionality) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    
</body>
</html>