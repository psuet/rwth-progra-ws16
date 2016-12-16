import java.util.*;

public class Zufall {
	private static Random r = new Random();

	/**
	 * Gibt eine zufaellige Zahl zwischen {@code 0} und {@code i - 1} zurueck.
	 * 
	 * @param i
	 *            Die Obergrenze (nicht eingeschlossen).
	 * @return Die Zufallszahl.
	 */
	static int zahl(int i) {
		return r.nextInt(i);
	}

	private static String[] namen = { "Aaron", "Achmed", "Achmet", "Adrian",
			"Ahmad", "Ahmed", "Ahmet", "Aileen", "Alena", "Alex", "Alexander",
			"Alexandra", "Ali", "Alica", "Alice", "Alicia", "Alina", "Amelie",
			"Amely", "Anabel", "Andre", "Andrea", "Andreas", "Angelina",
			"Angelique", "Anica", "Anika", "Anja", "Ann", "Anna", "Annabel",
			"Annabell", "Annabelle", "Annalena", "Anne", "Annica", "Annika",
			"Annkathrin", "Annkatrin", "Anton", "Antonia", "Armin", "Arne",
			"Arthur", "Artur", "Ayleen", "Aylin", "Bastian", "Ben", "Benedict",
			"Benedikt", "Benjamin", "Bennet", "Bennett", "Bianca", "Bianka",
			"Birte", "Birthe", "Bjarne", "Brian", "Bryan", "Calvin", "Can",
			"Caren", "Carina", "Carl", "Carla", "Carlotta", "Carolin",
			"Carolina", "Caroline", "Carsten", "Catarina", "Catharina",
			"Cathrin", "Catrin", "Cedric", "Cedrik", "Celina", "Celine", "Cem",
			"Chantal", "Chantale", "Charlin", "Charline", "Charlotte",
			"Chiara", "Chris", "Christian", "Christin", "Christina",
			"Christine", "Christof", "Christoph", "Christopher", "Cindy",
			"Claas", "Clara", "Claudia", "Clemens", "Colin", "Collin",
			"Conrad", "Constantin", "Corinna", "Cornelius", "Dana", "Daniel",
			"Daniela", "Danny", "Dario", "David", "Denis", "Denise", "Deniz",
			"Dennis", "Diana", "Dilara", "Dominic", "Dominik", "Dominique",
			"Dustin", "Eike", "Eileen", "Elena", "Elias", "Elisa", "Elisabeth",
			"Emilia", "Emilie", "Emily", "Emma", "Emmily", "Emre", "Enrico",
			"Eric", "Erik", "Esther", "Eva", "Fabian", "Fabienne", "Fabio",
			"Felix", "Fenja", "Finja", "Finn", "Finnja", "Fiona", "Florian",
			"Frank", "Franziska", "Frederic", "Frederik", "Frederike",
			"Friederike", "Fynn", "Gabriel", "Georg", "Gerrit", "Gina",
			"Gregor", "Greta", "Hanna", "Hannah", "Hannes", "Hans", "Hauke",
			"Helen", "Helena", "Hendrik", "Henning", "Henri", "Henrik",
			"Henrike", "Henry", "Ina", "Inga", "Isabel", "Isabell", "Isabelle",
			"Ivonne", "Jacob", "Jacqueline", "Jakob", "Jan", "Jana", "Janek",
			"Janin", "Janina", "Janine", "Janis", "Janna", "Janne", "Jannek",
			"Jannes", "Jannik", "Jannika", "Jannis", "Jaqueline", "Jasmin",
			"Jasmina", "Jason", "Jasper", "Jean", "Jennifer", "Jenny", "Jens",
			"Jeremie", "Jeremy", "Jerome", "Jessica", "Jessika", "Jil", "Jill",
			"Joana", "Joanna", "Joel", "Johan", "Johann", "Johanna",
			"Johannes", "John", "Jona", "Jonah", "Jonas", "Jonathan",
			"Josefine", "Josephine", "Joshua", "Judith", "Jule", "Julia",
			"Julian", "Juliane", "Julie", "Julien", "Julius", "Justin",
			"Justus", "Kai", "Kaja", "Karen", "Karina", "Karl", "Karla",
			"Karlotta", "Karolina", "Karoline", "Karsten", "Katarina",
			"Katharina", "Kathrin", "Katja", "Katrin", "Kay", "Kerstin",
			"Kevin", "Kiara", "Kilian", "Kim", "Kimberley", "Kimberly", "Kira",
			"Klaas", "Klara", "Klemens", "Konrad", "Konstantin", "Korinna",
			"Kornelius", "Kristian", "Kristin", "Kristina", "Kristine",
			"Kristof", "Kyra", "Lara", "Larissa", "Lars", "Lasse", "Laura",
			"Lea", "Leah", "Leif", "Lena", "Lennard", "Lennart", "Leo", "Leon",
			"Leonard", "Leoni", "Leonie", "Levin", "Lidia", "Lili", "Lilli",
			"Lilly", "Lina", "Linda", "Linn", "Linus", "Lisa", "Liza",
			"Lorenz", "Louis", "Louisa", "Louise", "Luca", "Lucas", "Lucie",
			"Lucy", "Luis", "Luisa", "Luise", "Luka", "Lukas", "Lydia", "Lynn",
			"Madeleine", "Mads", "Magdalena", "Maik", "Maike", "Maja", "Malin",
			"Malina", "Malte", "Mandy", "Manuel", "Mara", "Marah", "Marc",
			"Marcel", "Marco", "Marcus", "Mareike", "Marek", "Maren", "Maria",
			"Mariam", "Marie", "Marina", "Mario", "Marius", "Mark", "Marko",
			"Markus", "Marleen", "Marlene", "Marlon", "Marten", "Martin",
			"Marvin", "Marwin", "Mathias", "Mathies", "Mathis", "Matis",
			"Mats", "Matthias", "Matthis", "Mattis", "Maurice", "Max", "Maxi",
			"Maximilian", "Maya", "Mehmet", "Meik", "Meike", "Melanie",
			"Melina", "Melissa", "Melvin", "Merle", "Merlin", "Mia", "Michael",
			"Michaela", "Michel", "Michelle", "Mike", "Milena", "Mira",
			"Miriam", "Mirko", "Mohamed", "Mohammed", "Mona", "Monique",
			"Moritz", "Morten", "Muhammed", "Nadine", "Nadja", "Natalie",
			"Natascha", "Nathalie", "Neele", "Nele", "Nic", "Nicholas", "Nick",
			"Niclas", "Nico", "Nicolai", "Nicolas", "Nicole", "Niels", "Nik",
			"Niklas", "Niko", "Nikolai", "Nikolas", "Nils", "Nina", "Noah",
			"Nora", "Norman", "Ole", "Oliver", "Olivia", "Oscar", "Oskar",
			"Pascal", "Patricia", "Patrick", "Patrik", "Patrizia", "Paul",
			"Paula", "Paulina", "Pauline", "Peer", "Per", "Peter", "Phil",
			"Philip", "Philipp", "Pia", "Pierre", "Rafael", "Ralf", "Ralph",
			"Ramona", "Raphael", "Rebecca", "Rebekka", "Ricarda", "Ricardo",
			"Riccardo", "Richard", "Rico", "Riko", "Robert", "Robin", "Roman",
			"Ronja", "Ruben", "Sabine", "Sabrina", "Samantha", "Samira",
			"Samuel", "Sandra", "Sandro", "Sandy", "Sara", "Sarah", "Sascha",
			"Saskia", "Sebastian", "Selina", "Simon", "Simone", "Sina",
			"Sinah", "Sofia", "Sofie", "Sonja", "Sophia", "Sophie", "Stefan",
			"Stefanie", "Steffen", "Stella", "Stephan", "Stephanie", "Steve",
			"Steven", "Susanne", "Svantje", "Svea", "Sven", "Svenja",
			"Swantje", "Swen", "Tabea", "Tamara", "Tania", "Tanja", "Tatjana",
			"Teresa", "Tessa", "Theresa", "Thies", "Thilo", "Thomas",
			"Thorben", "Thore", "Thorge", "Thorsten", "Til", "Till", "Tilo",
			"Tim", "Timm", "Timo", "Timon", "Tina", "Tobias", "Tom", "Tomas",
			"Toni", "Tony", "Torben", "Tore", "Torge", "Torsten", "Tristan",
			"Valentin", "Valerie", "Vanessa", "Verena", "Victor", "Victoria",
			"Vievienne", "Viktor", "Viktoria", "Vincent", "Vivien", "Wibke",
			"Wiebke", "Yannic", "Yannick", "Yannik", "Yannis", "Yasemin",
			"Yasmin", "Yasmina", "Yvonne", "Zoe" };

	/**
	 * Gibt einen zufaelligen Vornamen zurueck, der in den 90ern beliebt war.
	 * 
	 * @return Der Vorname.
	 */
	static String name() {
		return namen[r.nextInt(namen.length)];
	}