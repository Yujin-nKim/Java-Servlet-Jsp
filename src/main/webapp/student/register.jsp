<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>학생-등록</title>
    <link rel="stylesheet" href="/style.css" />
    <meta charset="UTF-8" />
</head>

<body>
<!-- todo action 주소 설정
    //등록
        action = /student/register
    //수정
        action = /student/update
-->
<form method="post" ${empty student ? 'action ="/student/register"' : 'action="/student/update"'} >
    <table>
        <tbody>
        <tr>
            <th>ID</th>
            <td><input type="text" name="studentId" value="${student.id}" required /></td>
        </tr>
        <!-- todo input 구현 -->
        <tr>
            <th>이름</th>
            <td><input type="text" name="studentName" value="${student.name}"></td>
        </tr>
        <tr>
            <th>성별</th>
            <td>
                <input type="radio" name="gender" value="M" ${student.gender eq 'M' ? 'checked' : '' } />남
                <input type="radio" name="gender" value="M" ${student.gender eq 'F' ? 'checked' : '' } />여
            </td>
        </tr>
        <tr>
            <th>나이</th>
            <td><input type="text" name="studentAge" value="${student.age}"></td>
        </tr>
        </tbody>
    </table>
    <p>
        <button type="submit">
            <c:choose>
                <c:when test="${empty student}">
                    등록
                </c:when>
                <c:otherwise>
                    수정
                </c:otherwise>
            </c:choose>
        </button>
    </p>
</form>
</body>
</html>