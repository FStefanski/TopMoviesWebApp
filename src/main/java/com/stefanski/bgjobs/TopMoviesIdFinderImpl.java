package com.stefanski.bgjobs;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.stefanski.util.HttpConnect;

@Component
public class TopMoviesIdFinderImpl implements TopMoviesIdFinder {

	@Override
	public List<String> findAllTopMoviesId() {

		String top250_url = "https://www.imdb.com/chart/top";
		String webPage = null;

		// movie id parsing keys
		String parsingKeyStart = "data-titleid=\"tt";
		String parsingKeyEnd = "\">";

		// parsing movies id - data in different lines
		List<String> topMoviesIdList = new ArrayList<>();

		// download web page
		try {
			webPage = HttpConnect.download(top250_url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// webPage details
		String line;
		Scanner scanner = new Scanner(webPage);
		int startIndex, endIndex;

		while (scanner.hasNext()) { // terminates when end-of-file found
			line = scanner.next();

			// check if the line contains movie id
			if (line.contains(parsingKeyStart)) {
				startIndex = line.indexOf(parsingKeyStart) + parsingKeyStart.length();
				endIndex = line.indexOf(parsingKeyEnd);

				String id = null;
				try {
					id = "tt" + line.substring(startIndex, endIndex);
				} catch (StringIndexOutOfBoundsException e) {
					System.err.println("\tCuld not parse >Movie id<!");
					e.printStackTrace();
				}
				topMoviesIdList.add(id);
			}
		}
		return topMoviesIdList;
	}

	// public static void main(String[] args) {
	//
	// TopMoviesIdFinder newTopMoviesFinder = new TopMoviesIdFinderImpl();
	// List<String> topMoviesIdList = newTopMoviesFinder.findAllTopMoviesId();
	//
	// System.out.println("Found movie's ids: ");
	//
	// for (String string : topMoviesIdList) {
	// System.out.print(string + ", ");
	// }
	// }
}