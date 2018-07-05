
class City {
	//An object just for the city seemed logical because each city came with many properties.
	private int number;
	private String code;
	private String name;
	private int population;
	private int elevation;
	
	public City (int cityNumber, String cityCode, String cityName, int cityPopulation, int cityElevation) {
		number = cityNumber;
		code = cityCode;
		name = cityName;
		population = cityPopulation;
		elevation = cityElevation;
	}
	//getters, setters
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getElevation() {
		return elevation;
	}

	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	
	public void printCityInfo() {
		//prints a statement with the city's information. Used for city query
		System.out.println(this.number + " " + this.code + " " + this.name + " " + this.population + " " + this.elevation);
	}
	
	
}
