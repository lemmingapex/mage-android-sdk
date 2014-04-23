package mil.nga.giat.mage.sdk.fetch;

import mil.nga.giat.mage.sdk.connectivity.ConnectivityUtility;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LocationFetchAlarmReciever extends BroadcastReceiver {

	public static final int REQUEST_CODE = 91735;
	private static final String LOG_NAME = LocationFetchAlarmReciever.class.getName();
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(LOG_NAME, "Alarm fired to fetch locations");
		
		if (!ConnectivityUtility.isOnline(context)) {
			Log.d(LOG_NAME, "The device is currently disconnected. Can't fetch locations.");
			return;
		}
		
		Intent observationIntent = new Intent(context, LocationServerFetchIntentService.class);
		context.startService(observationIntent);
	}

}
