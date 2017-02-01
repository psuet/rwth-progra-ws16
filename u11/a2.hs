--Made by Benjamin Stutte and not Paul Suetterlin
--Aufgabe 2

--a)
data MulTree a 	= IndexKnoten a a [MulTree a]
				| DatenKnoten a
				deriving Show


t1 :: MulTree Int
t1 = IndexKnoten 3 42 [IndexKnoten 3 15 [DatenKnoten 3, DatenKnoten 11, DatenKnoten 12],
					   IndexKnoten 19 42 [DatenKnoten 42, DatenKnoten 23]
					  ]
--b)
verzweigunggsgrad :: MulTree a -> Int
verzweigunggsgrad (DatenKnoten _) =  0
verzweigunggsgrad (IndexKnoten _ _ xs) = countList xs
		where
			countList :: [MulTree a] -> Int
			countList [] = 0
			countList (x:xs) = 1 + verzweigunggsgrad x + countList xs --gibt noch lediglich Gesamtverzweigung und nicht Maximalverzweigung an.

--c)
datenListe :: MulTree a -> [a]
datenListe (DatenKnoten a) = a
datenListe (IndexKnoten _ _ (x:xs)) = datenListe x :  

{--d)
datenIntervalle :: MulTree Int ->-}

{--e)
contains :: Int -> (MulTree a -> [Int]) -> Bool --gesuchter Wer und Baum werden angegeben
contains x [] = False --bei Erreichen der leeren Liste immer false, da Wer nicht gefunden
contains x (y:ys) = if x == y True else contains x ys --Listenelemente auf Gleichheit mit geprüftem Wert durchsucht, bei ungleichheit rekursive übergabe der verbleibenden Liste
contains x xs = -- Liste rekursiv durchlaufen
contains x MulTree a = contains x (datenListe a) --1. Baum-Werteliste über contains-Funktion abgerufen
-}