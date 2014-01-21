package pakete.contenedor.ligavoleibolsvm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final String TAG = DBHelper.class.getSimpleName();
	public static final String DB_NAME = "svm.db";
	public static final int DB_VERS = 1;
	public static final String TABLAclasificacion = "clasificacion";
	public static final String TABLAgaleriafotos = "galeriafotos";
	public static final String TABLAmvp = "MVP";
	public static final String TABLAsepteto = "septeto";
	public static final String TABLApartidos = "partidos";
	public static final String TABLAplantillas = "plantillas";
	public static final String TABLAopciones = "opciones";
	public static final String TABLAcontacto = "contacto";
	public static final String TABLAnoticias = "noticias";
	public static final boolean Debug = false;
		
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERS);
	}
	
	public Cursor query(SQLiteDatabase db, String query) {
		Cursor cursor = db.rawQuery(query, null);
		if (Debug) {
			Log.d(TAG, "Executing Query: "+ query);
		}
		return cursor;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		/* Create table Logic, once the Application has ran for the first time. */
		String sql = String.format("CREATE TABLE %s (id_mvp INTEGER PRIMARY KEY AUTOINCREMENT, ruta text, filesizehash text)", TABLAmvp);
		String sql2 = String.format("CREATE TABLE %s (id_septeto INTEGER PRIMARY KEY AUTOINCREMENT, ruta text, filesizehash text)", TABLAsepteto);
		String sql3 = String.format("CREATE TABLE %s (id_foto INTEGER PRIMARY KEY AUTOINCREMENT, ruta text, jornada text)", TABLAgaleriafotos);
		String sql4 = String.format("CREATE TABLE %s (id_plantilla INTEGER PRIMARY KEY AUTOINCREMENT, ruta text, equipo text, posicion integer)", TABLAplantillas);
		String sql5 = String.format("CREATE TABLE %s (id_clasificacion INTEGER PRIMARY KEY AUTOINCREMENT, puesto text, equipo text, pj text, ptos text)", TABLAclasificacion);
		String sql6 = String.format("CREATE TABLE %s (id_partido INTEGER PRIMARY KEY AUTOINCREMENT, local text, imagenlocal text, visitante text, imagenvisitante text, estadio text, fecha text, hora text, rfevb text, streaming text, jornada text)", TABLApartidos);
		String sql7 = String.format("CREATE TABLE %s (id_opcion INTEGER PRIMARY KEY AUTOINCREMENT, version text, actualizacion text, primeravez text)", TABLAopciones);
		String sql8 = String.format("CREATE TABLE %s (id_contacto INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, email text, url text, fuentedatos text, notafinal text)", TABLAcontacto);
		String sql9 = String.format("CREATE TABLE %s (id_noticia INTEGER PRIMARY KEY AUTOINCREMENT, autor text, noticia text, fecha text, hora text)", TABLAnoticias);
		
		db.execSQL(sql); db.execSQL(sql2); db.execSQL(sql3);db.execSQL(sql4);
		db.execSQL(sql5); db.execSQL(sql6); db.execSQL(sql7);db.execSQL(sql8);db.execSQL(sql9);
		if (Debug) {
			Log.d(TAG, "onCreate Called.");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLAmvp));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLAsepteto));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLAgaleriafotos));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLAplantillas));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLAclasificacion));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLApartidos));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLAopciones));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLAcontacto));
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLAnoticias));
		if (Debug) {
			Log.d(TAG, "Upgrade: Dropping Table and Calling onCreate");
		}
		this.onCreate(db);
		
	}
}
