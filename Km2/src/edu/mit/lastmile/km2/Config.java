package edu.mit.lastmile.km2;

import edu.mit.lastmile.km2.dao.DeliveryShopDataSource;
import edu.mit.lastmile.km2.dao.KmDataSource;
import edu.mit.lastmile.km2.dao.ShopDataSource;
import edu.mit.lastmile.km2.dao.TokenDataSource;
import edu.mit.lastmile.km2.dao.TrafficCountDataSource;

public class Config {
	public static final String LOG_TAG = "Km2";
	public static final class Map{
		public static final int ZOOM = 16;
		public static final class Marker{
			public static final boolean DRAGGABLE = true;
		}
	}
	public static final class Location{
		public static final int SIGNIFICANT_TIME = 1000 * 60 * 2;
		public static final int ACCURACY_DELTA = 200;
		/*
		 * For location update request
		 * */
		public static final int MILLIS_PER_SEC = 1000;
		public static final int SECONDS = 5;
		public static final int UPDATE_INTERVAL = MILLIS_PER_SEC * SECONDS;
		public static final int FAST_SECONDS = 1;
		public static final int FAST_INTERVAL = MILLIS_PER_SEC * FAST_SECONDS;
	}
	public static final class Camera{
		public static final String DIR = LOG_TAG;
	}
	public static final class Database{
		public static final String NAME = "km2.db";
		public static final int VERSION = 1;
		public static final String[] SQL_CREATE_STMT = {
			TokenDataSource.CREATE_TABLE,
			KmDataSource.CREATE_TABLE,
			TrafficCountDataSource.CREATE_TABLE,
			ShopDataSource.CREATE_TABLE,
			DeliveryShopDataSource.CREATE_TABLE
		};
		public static final String[] SQL_DELETE_STMT = {
			TokenDataSource.DROP_TABLE,
			KmDataSource.DROP_TABLE,
			TrafficCountDataSource.DROP_TABLE,
			ShopDataSource.DROP_TABLE,
			DeliveryShopDataSource.DROP_TABLE
		};
	}
	public static final class Server{
		public static final String ENDPOINT = "http://18.111.113.144:3000";
		public static final String CONTROLLER = "/api";
		public static final String MEDIA_PATH = "/media";
		public static final String ENCODING = "UTF-8";
		public static final class Urls{
			public static final class Kms{
				public static final String ASSIGNED = "/kms/assigned";
				public static final String DATA = "/kms/data";
			}
			public static final class Users{
				public static final String LOGIN = "/users/login";
			}
			public static final class Traffic{
				public static final String CREATE = "/traffic_counts/create";
			}
		}
	}
}