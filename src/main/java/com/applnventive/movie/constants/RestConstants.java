package com.applnventive.movie.constants;

public interface RestConstants {

	String SLASH = "/";

	String API = SLASH + "api";

	String VERSION_v1 = "v1";

	String CURRENT_VERSION = VERSION_v1;

	String APP_INVENTIVE_API = API + SLASH + CURRENT_VERSION;

	interface moviesDetails {
		String MOVIES = SLASH + "movies";
		
		String MOVIES_BY_ID = MOVIES + SLASH + "/{id}";

	}

}
