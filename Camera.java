import java.util.*;

public class Camera {
	private String nume;
	private String id_device;
	private double suprafata;
	private ArrayList<Date_Sensor> intervale;
	public Camera(String nume, String id_device, double suprafata)
	{
		this.nume = nume;
		this.id_device = id_device;
		this.suprafata = suprafata;
		this.intervale = new ArrayList<Date_Sensor>(25);
		for(int i = 0 ; i < 24; i++) {
			this.intervale.add(new Date_Sensor());
		}
	}
	public String getId_Device() {
		return this.id_device;
	}
	public String getNume() {
		return this.nume;
	}
	public double getSuprafata() {
		return this.suprafata;
	}
	public double getMinTemp(int index) {
		Date_Sensor o = this.intervale.get(index);
		return o.getMinTemp();
	}
	public double getMaxUmid(int index) {
		Date_Sensor o = this.intervale.get(index);
		return o.getMaxUmid();
	}
	public void add(int index, double val, boolean f) {
		Date_Sensor o = this.intervale.get(index);
		if(f)
			o.addTemp(val);
		else
			o.addUmid(val);
	}
	public boolean no_temp_ult_h(int index) {
		Date_Sensor o = this.intervale.get(index);
		return o.lipsa_t_ult_h();
	}
	public boolean no_umid_ult_h(int index) {
		Date_Sensor o = this.intervale.get(index);
		return o.lipsa_u_ult_h();
	}
	public void list(long timestamp, long inc_time, long sf_time) {
		int inc_h = (int)( (inc_time % 86400) / 3600);
		int sf_h = (int)( (sf_time % 86400) / 3600);
		int ref_min = (int)( (timestamp % 3600) / 60);
		int inc_min = (int)( (inc_time % 3600) /60);
		Date_Sensor o;
		String afis = "";
		if(ref_min < inc_min)
			inc_h++;
		if(sf_h < inc_h)
		{//daca am interval cu diferenta de zi
			while(sf_h >= 0) {//afisez temperaturile din a 2-a zi
				o = this.intervale.get(sf_h);
				afis = afis + o.list_Temp();
				sf_h--;
			}
			sf_h = 23;
		}
		while(sf_h > inc_h){
			o = this.intervale.get(sf_h);
			afis = afis + o.list_Temp();
			sf_h--;
		}
		System.out.print(afis.trim());
	}
}