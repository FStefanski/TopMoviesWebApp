<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>
<title>List movies</title>

<!-- reference our style sheet -->

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
					<th> </th>
					<th>Title</th>
					<th>Year</th>
					<th>Genre</th>
					<th>Actors</th>
					<th>Directors</th>
					<th>IMDB rating</th>
					<th>User rating</th>
					<th>Want to watch?</th>
				</tr>

				<!-- loop over and print our movies -->

				<!-- construct an "update" link with movie id -->
				<c:url var="updateLink" value="/movie/showFormForUpdate">
				</c:url>

				<!-- construct an "delete" link with movie id -->
				<c:url var="deleteLink" value="/movie/delete">
				</c:url>

				<tr>
					<%-- <td>${tempMovie.title}</td> --%>


					<td>
						<!-- display the update link --> <a href="${updateLink}">Update</a>
						| <!-- display the delete link --> <a href="${deleteLink}">Delete</a>

					</td>
				</tr>


			</table>

		</div>

	</div>


</body>

</html>









