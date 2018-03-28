package com.stefanski.bgjobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.stefanski.util.HttpConnect;

@Component
public class TopMoviesIdFinderImpl implements TopMoviesIdFinder {

	@Override
	public Map<Integer, String> findAllTopMovies() {

		String top250_url = "https://www.imdb.com/chart/top";
		String webPage = null;

		// movie top movie position parsing keys
		String positionParsingKeyStart = "=chttp_tt_";
		String positionPrsingKeyEnd = "\"";

		// movie id parsing keys
		String idParsingKeyStart = "data-titleid=\"tt";
		String idPrsingKeyEnd = "\">";

		// Mapping movie imdb top movie position (key) with the imbd id (value)
		Map<Integer, String> topMoviesMap = new TreeMap<>(); // HashMap - unsorted, TreeMap - sorted.

		// (value) parsing movies id - data in different lines
		List<String> topMoviesIdList = new ArrayList<>();

		// (key) parsing imdb top movie position list - data in different lines
		List<Integer> topMoviesPositionList = new ArrayList<>();

		// download web page
		try {
			webPage = HttpConnect.download(top250_url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// webPage parsing details
		String line = null;
		Scanner scanner = new Scanner(webPage);
		int startIndex, endIndex;

		// movie details
		String imbdID = null;
		Integer imdbPosition = null;

		while (scanner.hasNext()) { // terminates when end-of-file found
			line = scanner.next();

			// check if the line contains title column with top movie position
			// AND for not empty topMoviesPositionList check if the line contains an
			// different position than the last found
			if (line.contains(positionParsingKeyStart) && (topMoviesPositionList.isEmpty() ? true
					: (!line.contains(
							positionParsingKeyStart + topMoviesPositionList.get(topMoviesPositionList.size() - 1))))) {

				// System.out.println("\t\t Postion found: " + line);
				startIndex = line.indexOf(positionParsingKeyStart) + positionParsingKeyStart.length();
				endIndex = line.length() - positionPrsingKeyEnd.length(); // e.g. "=chttp_tt_121" minus last " to 121

				try {
					imdbPosition = Integer.parseInt(line.substring(startIndex, endIndex));
					// System.out.println(
					// "\t\t line.substring(startIndex, endIndex): " + line.substring(startIndex,
					// endIndex));
				} catch (StringIndexOutOfBoundsException e) {
					System.err.println("\tCuld not parse >Movie id<!");
					e.printStackTrace();
				}
				topMoviesPositionList.add(imdbPosition);
			}

			// check if the line contains movie id
			if (line.contains(idParsingKeyStart)) {

				// System.out.println("\t\t Id found: " + line);
				startIndex = line.indexOf(idParsingKeyStart) + idParsingKeyStart.length();
				endIndex = line.indexOf(idPrsingKeyEnd);

				imbdID = null;
				try {
					imbdID = "tt" + line.substring(startIndex, endIndex);
					// System.out.println("\t\t tt + line.substring(startIndex, endIndex): " + "tt"
					// + line.substring(startIndex, endIndex));
				} catch (StringIndexOutOfBoundsException e) {
					System.err.println("\tCuld not parse >Movie id<!");
					e.printStackTrace();
				}
				topMoviesIdList.add(imbdID);
			}

			// Mapping movie imbd id (key) with the imdb top movie position (value)
			for (int listIndexCounter = 0; listIndexCounter < topMoviesIdList.size(); listIndexCounter++) {

				topMoviesMap.put(topMoviesPositionList.get(listIndexCounter), topMoviesIdList.get(listIndexCounter));
			}
		}
		return topMoviesMap;
	}

	public static void main(String[] args) {

		TopMoviesIdFinder newTopMoviesFinder = new TopMoviesIdFinderImpl();
		Map<Integer, String> topMoviesMap = newTopMoviesFinder.findAllTopMovies();

		System.out.println("\n\n>> Found movie's: ");
		// Print the key value pair in one line.
		topMoviesMap.forEach((k, v) -> System.out.println("key: " + k + " value:" + v));
	}
}