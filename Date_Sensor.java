import java.util.*;

public class Date_Sensor {
	private ArrayList<Double> temp, umid;
	public Date_Sensor() {
		this.temp = new ArrayList<Double>();
		this.umid = new ArrayList<Double>();
	}
	public void addTemp(double temp) {
		if(!this.temp.contains(temp))
			this.temp.add(temp);
		Collections.sort(this.temp);
	}
	public void addUmid(double umid) {
		if(!this.umid.contains(umid))
			this.umid.add(umid);
		Collections.sort(this.umid);
	}
	public String list_Temp() {
		String result = "";
		for(Double o: this.temp) 
			result = result + String.format("%.2f ", o);
		return result;		
	}
	public double getMinTemp() {
		return this.temp.get(0);
	}
	public double getMaxUmid() {
		return this.umid.get(this.umid.size() - 1);
	}
	public boolean lipsa_t_ult_h() {
		return this.temp.isEmpty();
	}
	public boolean lipsa_u_ult_h() {
		return this.umid.isEmpty();
	}
}