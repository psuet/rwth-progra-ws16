public class ConstructionWork {

  public static void main(String [] args) {

    NoiseLevel noise = new NoiseLevel();

    Powerdrill [] drills = new Powerdrill[5];
    drills[0] = new Powerdrill(BitType.Twist, 5, 3.0);
    drills[1] = new Powerdrill(BitType.Dowelling, 8);
    drills[2] = new Powerdrill(BitType.Masonry, 12);
    drills[3] = new Powerdrill(BitType.Masonry, 12);
    drills[4] = new Powerdrill(BitType.Masonry, 12);

    while(drills[0].drill(Material.Metal, noise)) {
      System.out.println("Bohren in Metall, es ist " + noise.toString());
      noise = new NoiseLevel();
    }
    System.out.println("Der Akku ist leer!");
    drills[0].charge(0.5);
    while(drills[0].drill(Material.Metal, noise)) {
      System.out.println("Bohren in Metall, es ist " + noise.toString());
      noise = new NoiseLevel();
    }
    System.out.println("Der Akku ist wieder leer!");

    noise = new NoiseLevel();
    boolean success = drills[1].drill(Material.Wood, noise);
    if(success) {
      System.out.println("Bohren in Holz erfolgreich, es ist " + noise.toString());
    } else {
      System.out.println("Bohren in Holz gescheitert, es ist " + noise.toString());
    }
    noise = new NoiseLevel();
    success = drills[1].drill(Material.Metal, noise);
    if(success) {
      System.out.println("Bohren in Metall erfolgreich, es ist " + noise.toString());
    } else {
      System.out.println("Bohren in Metall gescheitert, es ist " + noise.toString());
    }

    noise = new NoiseLevel();
    drills[2].drill(Material.Concrete, noise);
    drills[3].drill(Material.Concrete, noise);
    drills[4].drill(Material.ReinforcedConcrete, noise);
    System.out.println("Drei mal Bohren in Beton ist " + noise.toString());

  }
}
