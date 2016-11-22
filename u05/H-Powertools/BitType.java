public enum BitType{
	Twist,
	Dowelling,
	Masonry;

	public static Material[] canHandle(BitType type){
		Material[] works;
		switch (type){
			case Twist:
				works = new Material[] {Material.Wood, Material.Plastic, Material.Metal};
				break;
			case Dowelling:
				works = new Material[] {Material.Wood, Material.Plastic};
				break;
			case Masonry:
				works = new Material[] {Material.Stone, Material.Concrete, Material.ReinforcedConcrete};	
				break;
		}
		return works;
	}
}
