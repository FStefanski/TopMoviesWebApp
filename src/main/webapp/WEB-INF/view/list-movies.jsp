<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>
<title>List movies</title>

<!-- reference our style sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Top 100 Greatest Movies of All Time (The Ultimate List)</h2>
		</div>
	</div>

	<div id="container">

		<div id="content">

			<!-- put new button: Add movie -->

			<!--  add a search box -->

			<!--  add our html table here -->

			<table>
				<tr>
					<th></th>
					<th>Title</th>
					<th>Year</th>
					<th>Genre</th>
					<th>Actors</th>
					<th>Directors</th>
					<th>IMDB rating</th>
					<th>User rating</th>
					<th>Want to watch?</th>
					<th>Options</th>
					<th></th>
					<th></th>
				</tr>

				<!-- loop over and print our movies -->
				<c:forEach var="tempMovie" items="${movies}">
					<!-- how to use JSTL tags https://www.tutorialspoint.com/jsp/jsp_standard_tag_library.htm -->

					<!-- construct an "update" link with movie id -->
					<c:url var="updateLink" value="/movie/showFormForUpdate">

					</c:url>

					<!-- construct an "delete" link with movie id -->
					<c:url var="deleteLink" value="/movie/delete">

					</c:url>

					<tr>
						<td>${tempMovie.id}</td>
						<td>${tempMovie.title}</td>
						<td>${tempMovie.year}</td>
						<td>${tempMovie.genre}</td>
						<td>${tempMovie.actors}</td>
						<td>${tempMovie.directors}</td>
						<td>${tempMovie.imdbRating}</td>
						<td>${tempMovie.userRating}</td>
						<td>${tempMovie.wantToWatch}</td>


						<td>
							<!-- display the update link --> <a href="${updateLink}">Update</a>
						</td>
						<td>|</td>
						<td>
							<!-- display the delete link --> <a href="${deleteLink}"
							onclick="if(!(confirm('Are tou sure you want to delete this movie?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>

			</table>

		</div>

	</div>


</body>

</html>









