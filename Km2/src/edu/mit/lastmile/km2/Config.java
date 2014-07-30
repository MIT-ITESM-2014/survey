package edu.mit.lastmile.km2;

import edu.mit.lastmile.km2.dao.TrafficCountDataSource;

public class Config {
	public static final class Map{
		public static final int ZOOM = 16;
	}
	public static final class Database{
		public static final String NAME = "km2.db";
		public static final int VERSION = 1;
		public static final String[] SQL_CREATE_STMT = {
			"CREATE TABLE tokens(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, app_user_id INTEGER, token, secret, identity)",
			TrafficCountDataSource.CREATE_TABLE
		};
		public static final String[] SQL_DELETE_STMT = {
			"DROP TABLE IF EXISTS tokens",
			TrafficCountDataSource.DROP_TABLE
		};
	}
	public static final class Server{
		public static final String ENDPOINT = "http://18.111.26.15:3000";
		public static final String CONTROLLER = "/api";
		public static final String MEDIA_PATH = "/media";
		public static final String ENCODING = "UTF-8";
		public static final class Urls{
			public static final class Kms{
				public static final String LIST = "";
			}
			public static final class Users{
				public static final String LOGIN = "/users/login";
			}
			public static final class Traffic{
				public static final String CREATE = "/traffic_counts/create";
			}
		}
	}
	public static final String LOG_TAG = "Km2";
}