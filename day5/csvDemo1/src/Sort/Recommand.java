package Sort;


public class Recommand implements Comparable<Recommand>{
	public String user_id;
	public String sku_id;
	public float rate;
	//public Map<String, Float> map=new HashMap<>();
	
	

	public String getUser_id() {
		return user_id;
	}



	public Recommand(String user_id, String sku_id, float rate) {
		super();
		this.user_id = user_id;
		this.sku_id = sku_id;
		this.rate = rate;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Recommand)) {
			return false;
		}
		Recommand r=(Recommand)obj;
		
		return super.equals(obj)&&r.getUser_id().equals(user_id);
	}



	@Override
	public int compareTo(Recommand o) {
		
		if (o  instanceof Recommand ) {
			if (o.user_id.compareTo(this.sku_id)==0) {
				//return Float.valueOf(this.rate).compareTo(Float.valueOf(o.rate));
				
				return Float.valueOf(o.rate).compareTo(Float.valueOf(this.rate));
			}else {
				return o.user_id.compareTo(this.sku_id);
			}
		}
		else {
			return -1;
		}
		
		
	}

	
	

	
	
	

}
