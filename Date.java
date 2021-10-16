import java.util.*;

public class Date {
	private long timestamp;
	private double temp_global;
	private double umid_global;
	private int nr_camere;
	private ArrayList <Camera> camere ;
	Date(int nr, double temp, double umid, long time){
		this.nr_camere = nr;
		this.temp_global = temp;
		this.umid_global = umid;
		this.timestamp = time;
		this.camere = new ArrayList<Camera>(this.nr_camere);
	}
	public void add(Camera o)
	{//adaug o camera in baza de date
		this.camere.add(o);
	}
	public void observe(String id_device, long timestamp, double temp, boolean f)
	{//funtia observe
		if(timestamp >= this.timestamp)
			return;//daca depasesc timpul de referinta
		Camera camera = getCamera(id_device);
			int min_ref = (int) (this.timestamp % 3600);
			int min = (int) (timestamp % 3600);
			int ora = (int)( (timestamp % 86400) / 3600);
			if(min_ref < min) 
				ora++;
			if(ora == 24)
				ora = 0;
			camera.add(ora, temp, f);//adaug temperatura sau umiditatea
	}
	public void list(String nume, long inc_time, long sf_time) {
		for(Camera o: this.camere) {
			if(nume.equals(o.getNume())) {//gasesc camera
				System.out.print(o.getNume() + " ");//afisez numele
				o.list(this.timestamp, inc_time, sf_time);//afisez temperaturile
			}
		}
	}
	public void setTemp(double temp) {
		this.temp_global = temp;
	}
	public void trigger_heat() {
		double numitor = 0;
		double numarator = 0;
		int ult_ora = (int)( (this.timestamp % 86400) / 3600);
		int index = ult_ora;
		if(this.umid_global != 0) {//daca exista umiditate
			for(Camera o : this.camere) {//pentru toate camere
				while(o.no_umid_ult_h(index)) {//daca nu exista temperaturi
					index --;//merg la ora precedenta
				}
				numitor += o.getSuprafata();
				numarator += o.getMaxUmid(index) * o.getSuprafata();
				index = ult_ora;
			}
			if(this.umid_global < numarator/numitor) {//verific daca trebuie pornita centrala
				System.out.println("NO");
				return;
			}
		}
		numitor = 0;
		numarator = 0;
		for(Camera o : this.camere) {
			while(o.no_temp_ult_h(ult_ora)) {
				ult_ora --;
			}
			numitor += o.getSuprafata();
			numarator += o.getMinTemp(ult_ora) * o.getSuprafata();
			ult_ora = index;
		}
		if(this.temp_global < numarator/numitor)
			System.out.println("NO");
		else
			System.out.println("YES");
	}
	private Camera getCamera(String id_device) {
		for(Camera o : this.camere)
			if(o.getId_Device().equals(id_device))
				return o;
		return null;
	}
}