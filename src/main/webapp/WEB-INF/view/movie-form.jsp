<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>
<title>Save Movie</title>

<!-- reference our style sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/add-movie-style.css">

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Top Rated Movies - Top 250 as rated by IMDb Users</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Movie</h3>


		<form:form action="saveMovie" modelAttribute="movie" method="POST">

			<!-- need to associate this data with movie id -->
			<form:hidden path="id" />

			<table>
				<tbody>
					<tr>
						<td><label>Imdb Position:</label></td>
						<td><form:input path="imdbPosition" /></td>
					</tr>
					<tr>
						<td><label>Imdb ID:</label></td>
						<td><form:input path="imdbID" /></td>
					</tr>
					<tr>
						<td><label>Poster URL:</label></td>
						<td><form:input path="poster" /></td>
					</tr>
					<tr>
						<td><label>Title:</label></td>
						<td><form:input path="title" /></td>
					</tr>
					<tr>
						<td><label>Year:</label></td>
						<td><form:input path="year" /></td>
					</tr>
					<tr>
						<td><label>Genre:</label></td>
						<td><form:input path="genre" /></td>
					</tr>
					<tr>

						<td><label>Actors:</label></td>
						<td><form:input path="actors" /></td>
					</tr>
					<tr>

						<td><label>Directors:</label></td>
						<td><form:input path="directors" /></td>
					</tr>
					<tr>

						<td><label>IMDB Rating:</label></td>
						<td><form:input path="imdbRating" /></td>
					</tr>
					<tr>

						<td><label>User Rating:</label></td>
						<td><form:input path="userRating" /></td>
					</tr>
					<tr>
						<td><label>Want to watch?:</label></td>
						<td><form:input path="wantToWatch" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>

		<div style=""></div>
		<p>
			<a href="${pageContext.request.contextPath}/movie/list">Back to
				List</a>
		</p>
	</div>
</body>

</html>









