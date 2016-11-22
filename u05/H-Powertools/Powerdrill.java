/**
 * Klasse zur Repraesentation einer Bohrmaschine.
 *
 * Bohrmaschinen werden durch ihre Akku-Ladung und ihren Bohrer-Durchmesser und -Typ
 * charakterisiert. Die Klasse stellt Methoden zum Aufladen des Akkus und zum Bohren
 * in verschiedene Materialien zur Verfuegung. Beim Bohren wird auch die Geraeuschentwicklung
 * berechnet.
 */
public class Powerdrill {
//g: Die Klasse muss von Aussen (z.B. der Klasse ConstructionWork) aufrufbar sein. Deshalb ist hier der Access-Level-Modifier private nicht ausreichend.

  /**
   * Maximale Ladung der Akkus aller Bohrer.
   */
  private static final double max_power = 5.0;
  //g: Die Maximale Ladung eines Bohrers ist nur für die charge-Methode diese Methode interessant. Daher kann hier private gewählt werden. (möglichst restriktiv)
  //Da dieser Wert auch nicht von dem aktuellen Powerdrill abhängt kann er weiter als static betrachtet werden, auch wenn dies bei primitiven 
  //Datentypen (wie double) zu keiner anderen Behandlung durch Java fuehrt. Egal ob static oder non-static, es gibt keine Speicherduplikate. Der Möglichkeit halber habe ich es aber gesetzt.

  /**
   * Ladung des Akkus
   */
  private double power;
  //g: Diese Variable sollte Private sein, da andernfalls extern die Ladung der Batterie geändert werden könnte und auch über den oben definierten Maximalbetrag erhöht werden. => private
  //Da der Wert der Variable abhängig vom Powerdrill-Objekt ist, muss diese non-static sein (Die Ladung eines Bohrers kann bei verschiedener Nutzungsdauer der einzelnen Bohrer unterschiedlich sein).

  /**
   * Durchmesser des Bohrers
   */
  private int bitSize;
  //g: Ordnungsgemaesser Aufruf von außen ist trotz des Access-Level-Modifiers private ist über die getter und setter Methoden getBitSize bzw. setBitSize möglich. Somit gilt hier möglichst restriktiv zu sein => private 
  //Da der Wert der Variable abhängig vom Powerdrill-Objekt ist, muss diese non-static sein (Der Bit (und damit auch die bitSize) eines jeden Bohrers kann gewechselt werden).

  /**
   * Typ des Bohrers
   */
  private BitType bit;
  //g: Geauso wie bei bitSize ist hier der Aufruf über getter und setter möglich => private
  //Da der Wert der Variable abhängig vom Powerdrill-Objekt ist, muss diese non-static sein (Der Bit eines jeden Bohrers kann gewechselt werden).

  /**
   * Lese fuer den Bohrer-Typ.
   * @return der eingesetzte Bohrer-Typ
   */
  public BitType getBitType() {
    //g: Mit dieser Mehode kann der Bittype eines Bohrers abgefragt werden. Dies sollte von außen Möglich sein. Daher wäre private unpassend.
    //Da der Bit-Typ nur im Kontext eines Powerdrill-Objekt (also eines Bohrers) existiert, muss diese Methode non-static sein.
    return this.bit;
  }
  /** 
   * Schreibe den Bohrer-Typ.
   * @param bit zu setzender Typ
   */
  public void setBitType(BitType bit) {
    this.bit = bit;
  }
  /**
   * Lese Bohrer-Durchmesser
   * @return der eingesetzte Bohrer-Durchmesser
   */
  /*TODO g)*/ int getBitSize() {
    return this.bitSize;
  }
  /**
   * Schreibe den Bohrer-Durchmesser
   * @param size neuer Bohrer-Durchmesser
   */
  /*TODO g)*/ void setBitSize(int size) {
    this.bitSize = size;
  }

  /**
   * Erzeugt einen neuen Bohrer mit vollem Akku
   * @param bit eingesetzter Bohrer-Typ
   * @param bitSize eingesetzter Bohrer-Durchmesser
   */
  public Powerdrill(BitType bit, int bitSize) {
    //g: Konstruktoren können weder private noch static sein.
    this.bit = bit;
    this.bitSize=bitSize;
    this.power = Powerdrill.max_power;
  }
  /**
   * Erzeugt einen neuen Bohrer mit angegebener Ladung.
   *
   * Uebersteigt die angegebene Ladung die maximale Ladung,
   * wird ein Bohrer mit vollem Akku erzeugt.
   *
   * @param power Ladung
   * @see Powerdrill(BitType, int)
   */
  public Powerdrill(BitType bit, int bitSize, double power) {
    //g: Konstruktoren können weder private noch static sein.
    this.bit = bit;
    this.bitSize = bitSize;
    if(power < Powerdrill.max_power) {
      this.power = power;
    } else {
      this.power = Powerdrill.max_power;
    }
  }
  /**
   * Laedt den Akku um den angegebenen Anteil auf, maximal jedoch voll.
   * @param ammount Anteil einer vollen Ladung, der aufgeladen werden soll.
   */
  public void charge(double amount) {
    //g: Mit dieser Mehode kann ein explizter Bohrer aufgeladen werden. Dies sollte (und muss für ConstructionWork) logischerweise auch ausserhalb der Klasse  möglich sein . => public
    //Da das Laden von Bohrern (und die Modifikation der Ladung) nur im Kontext eines Powerdrill-Objekt (also eines Bohrers) möglich ist, muss diese Methode non-static sein.
    this.power += Powerdrill.max_power * amount;
    if (this.power > Powerdrill.max_power) {
      this.power = Powerdrill.max_power;
    }
  }

  /**
   * Fuehrt eine Bohrung durch.
   *
   * Eine Bohrung ist erfolgreich, wenn der eingesetzte Bohrer mit
   * material kompatibel ist und vor dem Aufruf der Methode
   * noch genug Akku-Ladung vorhanden war.
   *
   * @param material Das gebohrte Material
   * @param noise Das Geraeuschniveau in der Umgebung (wird aktualisiert)
   *
   * @return Ob erfolgreich gebohrt wurde
   */
  public boolean drill(Material material, NoiseLevel noise) {
    //g: Mit dieser Mehode kann ein explizter Bohrer angewiesen werden durch ein Material zu bohren- Diese Anweisung sollte (und muss für ConstructionWork) auch ausserhalb der Klasse möglich sein. => public
    //Da das Bohren nur im Kontext eines Powerdrill-Objekt (also eines Bohrers) möglich ist, muss diese Methode non-static sein.
    boolean correctBit = false;
    for(Material mat : BitType.canHandle(this.bit)) {
      if(mat == material) {
        correctBit = true;
      }
    }
    
    boolean hasPower = this.usePower(material, correctBit);
    if(hasPower) {
      this.makeNoise(material, noise, correctBit);
    }

    return correctBit && hasPower;
  }
  
  /**
   * Reduziert die Akku-Ladung entsprechend der beim Bohren verbrauchten Energie.
   * @param material Material, in das gebohrt wird.
   * @param correctBit gibt an, ob der aktuelle Bohrer mit material kompatibel ist
   * @return ob noch genug Energie im Akku war.
   */
   private boolean usePower(Material material, boolean correctBit) {
    //g: Mit dieser Mehode kann "Strom verbraucht" werden. Da dies nur möglich sein sollte, wenn der Bohrer bohrt ist eine Verfügbarkeit außerhalb der Klasse nicht notwendig => private
    //Da das Verbrauchen nur im Kontext eines konkreten Powerdrill-Objekt (also eines Bohrers) möglich ist, muss diese Methode non-static sein.
    if(correctBit && this.power > 0.5) {
      switch(material) {
        case Wood:
        case Plastic:
          this.power *= 0.75;
          break;
        case Metal:
          this.power *= 0.6;
          break;
        case ReinforcedConcrete:
          this.power *= 0.4;
        default:
          this.power *= 0.5;
      }
      return true;
    } else if( this.power > 0.5) {
      this.power -= 0.5;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Aktualisiert das Geraeuschniveau in der Umgebung beim Bohren.
   *
   * Holz- und Kunststoff-Bohren erhoeht das aktuelle Geraeuschniveau um 5
   * Metall-Bohren mit einem kompatiblen Bohrer erhoeht das Geraeuschniveau um
   * 3 + Bohrer-Durchmesser, aber hoechstens um 10,
   * bei falschem Bohrer immer um 10.
   * Alles andere Bohren erhoeht bei kompatiblem Bohrer das aktuelle Geraeuschniveau um
   * 11, sonst um 8.
   *
   * @param material Das zu bohrende Material
   * @param noise Das aktuelle Geraeuschniveau der Umgebung (wird aktualisiert)
   * @param correctBit gibt an ob, der aktuelle Bohrer mit material kompatibel ist
   * 
   */
  /*TODO h)*/ 

  private void makeNoise(Material material, MoiseLevel noise. Boolean correctBit){
    int add = 0;
    switch(material) {
      case Material.Wood:
      case Material.Plastic:
        add = 5;
      case Material.Metall:
        add = (3 + this.bitSize < 10 && correctBit) ? 3 + this.bitSize : 10;
      default:
        add = (correctBit) ? 11 : 8;
    }
  }
}
